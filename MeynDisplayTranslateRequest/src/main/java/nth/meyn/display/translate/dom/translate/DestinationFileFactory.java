package nth.meyn.display.translate.dom.translate;

import java.io.File;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationFileFactory {

	/**
	 * Creates a file with format: SOURCE_PATH\XXXXDEYY English to ZZZZZ.csv
	 * with help of the source file path
	 */
	public static File create(File sourceFile) {
		StringBuilder destinationPath = new StringBuilder();
		Optional<String> customerAndPanelNr = getCustomerNumberAndPanelNr(sourceFile);
		destinationPath.append(getSourceFolder(sourceFile));
		destinationPath.append(File.separator);
		destinationPath.append(getCustomerNumber(customerAndPanelNr));
		destinationPath.append("DE");
		destinationPath.append(getPanelNumber(customerAndPanelNr));
		destinationPath.append(" English to ZZZZZ.csv");

		File destinationFile = new File(destinationPath.toString());

		return destinationFile;
	}

	private static String getPanelNumber(Optional<String> customerAndPanelNr) {
		if (customerAndPanelNr.isPresent()) {
			return customerAndPanelNr.get().substring(4, 6);
		} else {
			return "YY";
		}
	}

	private static String getCustomerNumber(Optional<String> customerAndPanelNr) {
		if (customerAndPanelNr.isPresent()) {
			return customerAndPanelNr.get().substring(0, 4);
		} else {
			return "XXXX";
		}
	}

	private static Optional<String> getCustomerNumberAndPanelNr(File sourceFile) {
		String sourcePath = sourceFile.getAbsolutePath();
		Pattern pattern = Pattern.compile("[0-9]{6}");
		Matcher matcher = pattern.matcher(sourcePath);
		if (matcher.find()) {
			return Optional.of(matcher.group());
		} else {
			return Optional.empty();
		}
	}

	private static String getSourceFolder(File sourceFile) {
		File sourceFolder = sourceFile.getParentFile();
		return (sourceFolder == null) ? "" : sourceFolder.getAbsolutePath();
	}

}
