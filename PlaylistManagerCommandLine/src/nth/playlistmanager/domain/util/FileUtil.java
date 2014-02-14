package nth.playlistmanager.domain.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	public static String getExtention(File file) {
		String fileName = file.getName();
		int startPosExtention = fileName.lastIndexOf(".");
		if (startPosExtention > 0) {
			return fileName.substring(startPosExtention + 1);
		}
		return "";
	}

	public static boolean isMusicFile(File file) {
		if (!file.isFile())
			return false;
		String extension = getExtention(file);
		if ("mp3".equals(extension))
			return true;
		if ("wav".equals(extension))
			return true;
		if ("wma".equals(extension))
			return true;
		return false;
	}

	public static List<String> readFile(File file) {
		List<String> lines = new ArrayList<String>();
		try {
			FileInputStream fstream = new FileInputStream(file);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			// Read File Line By Line
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static void writeFile(File file, String text) {
		PrintWriter out;
		try {
			out = new PrintWriter(file);
			out.println(text);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String getFileNameWithoutExtention(File file) {
		String fileName = file.getName();
		int startPosExtention = fileName.indexOf(".");
		if (startPosExtention > 0) {
			return fileName.substring(0,startPosExtention);
		}
		return fileName;
	}

}
