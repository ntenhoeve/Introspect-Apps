package nth.synchronizefolder.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.util.regex.Pattern;

import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;

import org.apache.commons.io.IOUtils;

public class FolderService {
	/**
	 * The number of bytes in a kilobyte.
	 */
	public static final long ONE_KB = 1024;

	/**
	 * The number of bytes in a kilobyte.
	 * 
	 * @since 2.4
	 */
	public static final BigInteger ONE_KB_BI = BigInteger.valueOf(ONE_KB);

	/**
	 * The number of bytes in a megabyte.
	 */
	public static final long ONE_MB = ONE_KB * ONE_KB;

	/**
	 * The number of bytes in a megabyte.
	 * 
	 * @since 2.4
	 */
	public static final BigInteger ONE_MB_BI = ONE_KB_BI.multiply(ONE_KB_BI);

	/**
	 * The file copy buffer SIZE (30 MB)
	 */
	private static final long FILE_COPY_BUFFER_SIZE = ONE_MB * 30;

	/**
	 * The number of bytes in a gigabyte.
	 */
	public static final long ONE_GB = ONE_KB * ONE_MB;

	/**
	 * The number of bytes in a gigabyte.
	 * 
	 * @since 2.4
	 */
	public static final BigInteger ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);

	/**
	 * The number of bytes in a terabyte.
	 */
	public static final long ONE_TB = ONE_KB * ONE_GB;

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void synchronize(SynchronizeArgument synchronizeArgument) throws IOException {
		// source
		File source = synchronizeArgument.getSource();
		if (!source.exists()) {
			throw new RuntimeException("Source: " + source.getAbsolutePath() + " does not exist");
		}
		if (!source.isDirectory()) {
			throw new RuntimeException("Source: " + source.getAbsolutePath() + " is not a folder");
		}

		// destination
		File destination = synchronizeArgument.getDestination();
		if (!destination.exists()) {
			throw new RuntimeException("Destination: " + destination.getAbsolutePath() + " does not exist");
		}
		if (!destination.isDirectory()) {
			throw new RuntimeException("Destination: " + destination.getAbsolutePath() + " is not a folder");
		}

		boolean removeOldFiles = synchronizeArgument.isRemoveOldFiles();
		boolean skipSameNameAndSize = synchronizeArgument.isSkipSameNameAndSize();

		synchronize(source, destination, removeOldFiles, skipSameNameAndSize);
	}

	private void synchronize(File srcFolder, File destFolder, boolean removeOldFiles, boolean skipSameNameAndSize) throws IOException {
		// recurse
		File[] srcFiles = srcFolder.listFiles();
		File[] destFiles = destFolder.listFiles();
		if (srcFiles == null) { // null if abstract pathname does not denote a directory, or if an I/O error occurs
			throw new IOException("Failed to list contents of " + srcFolder);
		}
		if (destFolder.exists()) {
			if (destFolder.isDirectory() == false) {
				throw new IOException("Destination '" + destFolder + "' exists but is not a directory");
			}
		} else {
			System.out.println("Make:   " + shortFileName(destFolder));
			if (!destFolder.mkdirs() && !destFolder.isDirectory()) {
				throw new IOException("Destination '" + destFolder + "' directory cannot be created");
			}
		}
		if (destFolder.canWrite() == false) {
			throw new IOException("Destination '" + destFolder + "' cannot be written to");
		}
		for (File srcFile : srcFiles) {
			//System.out.println("A1");
			File dstFile = new File(destFolder, srcFile.getName());
			if (srcFile.isDirectory()) {
				//System.out.println("A1");
				synchronize(srcFile, dstFile, removeOldFiles, skipSameNameAndSize);
				//System.out.println("A3");
			} else {
				//System.out.println("A5");
				if (skipSameNameAndSize && compares(srcFile, dstFile)) {
					System.out.println("Skip:   " + shortFileName(srcFile));
				} else {
					System.out.println("Copy:   " + shortFileName(srcFile));
					copyFile(srcFile, dstFile);
				}
			}
			//System.out.println("A6");

		}
		//System.out.println("A7");

		if (removeOldFiles && destFiles != null) {
			for (File destFile : destFiles) {
				//System.out.println("A8" + destFile);

				if (!containsComparableFile(srcFiles, destFile)) {
					System.out.println("Remove: " + shortFileName(destFile));
					if (destFile.isDirectory()) {
						//System.out.println("A9");

						deleteDirectory(destFile);
						//System.out.println("A10");

					} else {
						//System.out.println("A11");
						destFile.delete();
						//System.out.println("A12");

					}
				}
				//System.out.println("A13");

			}
			//System.out.println("A14");

		}
		//System.out.println("A15");

		// Do this last, as the above has probably affected directory metadata
		destFolder.setLastModified(srcFolder.lastModified());
		//System.out.println("A15");

	}

	private void deleteDirectory(File directory) throws IOException {
		if (!directory.exists()) {
			return;
		}

		if (!isSymlink(directory)) {
			cleanDirectory(directory);
		}

		if (!directory.delete()) {
			String message = "Unable to delete directory " + directory + ".";
			throw new IOException(message);
		}
	}

	private void cleanDirectory(File directory) throws IOException {
		if (!directory.exists()) {
			String message = directory + " does not exist";
			throw new IllegalArgumentException(message);
		}

		if (!directory.isDirectory()) {
			String message = directory + " is not a directory";
			throw new IllegalArgumentException(message);
		}

		File[] files = directory.listFiles();
		if (files == null) { // null if security restricted
			throw new IOException("Failed to list contents of " + directory);
		}

		IOException exception = null;
		for (File file : files) {
			try {
				forceDelete(file);
			} catch (IOException ioe) {
				exception = ioe;
			}
		}

		if (null != exception) {
			throw exception;
		}
	}

	private void forceDelete(File file) throws IOException {
		if (file.isDirectory()) {
			deleteDirectory(file);
		} else {
			boolean filePresent = file.exists();
			if (!file.delete()) {
				if (!filePresent) {
					throw new FileNotFoundException("File does not exist: " + file);
				}
				String message = "Unable to delete file: " + file;
				throw new IOException(message);
			}
		}
	}

	private boolean isSystemWindows() {
		return File.separatorChar == '\\';
	}

	private boolean isSymlink(File file) throws IOException {
		if (file == null) {
			throw new NullPointerException("File must not be null");
		}
		if (isSystemWindows()) {
			return false;
		}
		File fileInCanonicalDir = null;
		if (file.getParent() == null) {
			fileInCanonicalDir = file;
		} else {
			File canonicalDir = file.getParentFile().getCanonicalFile();
			fileInCanonicalDir = new File(canonicalDir, file.getName());
		}

		if (fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile())) {
			return false;
		} else {
			return true;
		}
	}

	private boolean containsComparableFile(File[] files, File fileToFind) {
		for (File file : files) {
			if (compares(file, fileToFind)) {
				return true;
			}
		}
		return false;
	}

	private void copyFile(File srcFile, File destFile) throws IOException {
		if (destFile.exists() && destFile.isDirectory()) {
			throw new IOException("Destination '" + destFile + "' exists but is a directory");
		}

		if (destFile.exists()) {
			destFile.delete();
		}

		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel input = null;
		FileChannel output = null;
		try {
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(destFile);
			input = fis.getChannel();
			output = fos.getChannel();
			long size = input.size();
			long pos = 0;
			long count = 0;
			while (pos < size) {
				count = size - pos > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : size - pos;
				pos += output.transferFrom(input, pos, count);
			}
		} finally {
			IOUtils.closeQuietly(output);
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(fis);
		}

		if (srcFile.length() != destFile.length()) {
			throw new IOException("Failed to copy full contents from '" + srcFile + "' to '" + destFile + "'");
		}
		destFile.setLastModified(srcFile.lastModified());
	}

	private boolean compares(File file1, File file2) {
		boolean sameName = file1.getName().equals(file2.getName());
		if (!sameName)
			return false;
		boolean bothExist = file1.exists() && file2.exists();
		if (!bothExist)
			return false;
		boolean sameType = (!file1.isDirectory() && !file2.isDirectory()) || (file1.isDirectory() && file2.isDirectory());
		if (!sameType)
			return false;
		if (!file1.isDirectory()) {// SIZE is ignored if it is a directory
			boolean sameSize = (file1.length() == file2.length());
			if (!sameSize)
				return false;
		}
		// boolean sameFile=(file1.getCanonicalFile().equals(file2.getCanonicalFile()));
		return true;
	}

	private static String shortFileName(File file) {
		String filePath = file.getPath();
		int MAX_LENGTH = 70;
		if (filePath.length() <= MAX_LENGTH) {
			return filePath;
		} else {
			String fileSeperator = File.separator;
			String pattern = Pattern.quote(fileSeperator);
			String[] splittedFilePath = filePath.split(pattern);

			StringBuffer shortFilePath = new StringBuffer();
			shortFilePath.append(splittedFilePath[0]);
			shortFilePath.append(fileSeperator);
			shortFilePath.append("...");

			int truncatedFilePathLength = MAX_LENGTH - shortFilePath.length();
			int startPos = filePath.length() - truncatedFilePathLength;
			filePath = filePath.substring(startPos);

			shortFilePath.append(filePath);

			return shortFilePath.toString();
		}
	}
}
