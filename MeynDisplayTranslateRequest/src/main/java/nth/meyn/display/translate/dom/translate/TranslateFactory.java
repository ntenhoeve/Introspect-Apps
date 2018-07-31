package nth.meyn.display.translate.dom.translate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import nth.meyn.display.translate.dom.abbreviation.AbbreviationRepository;
import nth.meyn.display.translate.dom.abbreviation.Abbreviations;
import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer1userinterface.controller.UploadStream;

public class TranslateFactory {

	private static final String ESCAPED_QOUATE = "\\\"";
	private static final String ONLY_WHEN_NOT_PRECEEDS_WITH_COMMA = "(?<!\\,)";
	private static final String ONLY_WHEN_NOT_FOLLOWED_WITH_COMMA = "(?!,)";

	
	public static DownloadStream createTranslateRequest(TranslateInfo translateInfo)
			throws URISyntaxException, IOException {
		File sourceFile = translateInfo.getCxDesignerExportFile().getFile();
		InputStreamReader reader = createReader(sourceFile);
		skipFirstLine(reader);

		CSVParser parser = createParser(reader);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(out, false, StandardCharsets.UTF_16.toString());
		writeFirstLines(printStream, translateInfo);

		Abbreviations abbreviations = AbbreviationRepository.read();
		try {
			for (final CSVRecord csvRecord : parser) {
				TranslateRecord translateRecord = new TranslateRecord(csvRecord, abbreviations);
				TranslatePrinter.print(translateRecord, printStream);
			}
		} finally {
			parser.close();
			reader.close();
		}
		
		File destinationFile=DestinationFileFactory.create(translateInfo);
		return new DownloadStream(destinationFile, out);
	}


	private static void writeFirstLines(PrintStream printStream,TranslateInfo translateInfo )
			throws IOException, URISyntaxException {
		File omronDisplayCvsFile=translateInfo.getCxDesignerExportFile().getFile();
		String language=translateInfo.getTranslateToLanguage();
		InputStreamReader reader = createReader(omronDisplayCvsFile);
		char ch;

		// copy first line
		do {
			ch = (char) reader.read();
			printStream.print(ch);
//			System.out.print(ch);
		} while (ch != '\n');

//		System.out.println("===");
		
		// copy second line
		do {
			ch = (char) reader.read();
			//System.out.print(ch);
			if (ch == '\r') {
				printStream.print(
						",\""+language+"                                                                                   \"");
				printStream.print(",\"How to translate\"");
				printStream.print(",\"Maximum translation size\"");
				printStream.print(",\"Translation tips\"\n");
			} else {
				printStream.print(ch);
			}
		} while (ch != '\r' || ch==-1);
	}

	private static void skipFirstLine(InputStreamReader reader)
			throws IOException, URISyntaxException {
		char ch;
		do {
			ch = (char) reader.read();
		} while (ch != '\n');
	}

	public static String findAbreviationCandidates(File omronDisplayCvsFile)
			throws URISyntaxException, IOException {
		Set<String> candidates = new HashSet<>();
		InputStreamReader reader = createReader(omronDisplayCvsFile);
		skipFirstLine(reader);
		CSVParser parser = createParser(reader);

		Abbreviations abbreviations = AbbreviationRepository.read();
		try {
			for (final CSVRecord csvRecord : parser) {
				String line = csvRecord.get(TranslateRecord.ENGLISH);
				candidates.addAll(abbreviations.findAbbreviationCandidates(line));
			}
		} finally {
			parser.close();
			reader.close();
		}
		System.out.println(candidates);
		return candidates.toString();
	}

	private static CSVParser createParser(InputStreamReader reader)
			throws IOException, URISyntaxException {
		// return new CSVParser(reader,CSVFormat.EXCEL.withHeader());

		return CSVFormat.EXCEL.withIgnoreEmptyLines().withRecordSeparator('\n').withQuote('"')
				.withFirstRecordAsHeader().withDelimiter(',').withTrim()
				.parse(reader);
	}

	private static InputStreamReader createReader(File omronDisplayCvsFile)
			throws URISyntaxException, IOException {
		InputStream fis = new FileInputStream(omronDisplayCvsFile);
		InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_16);
		return reader;
	}

	public static String validateTranslations(File omronDisplayCvsFile)
			throws URISyntaxException, IOException {
		StringBuilder report = new StringBuilder();
		InputStreamReader reader = createReader(omronDisplayCvsFile);
		skipFirstLine(reader);
		CSVParser parser = createParser(reader);
		try {
			for (final CSVRecord csvRecord : parser) {
				report.append(validate(csvRecord));
			}
		} finally {
			parser.close();
			reader.close();
		}
		return report.toString();
	}

	private static StringBuilder validate(CSVRecord csvRecord) {
		StringBuilder report = new StringBuilder();
		Map<String, String> values = csvRecord.toMap();
		Set<String> columnNames = values.keySet();
		System.out.println(columnNames);

		// TODO Auto-generated method stub
		report.append("TODO");
		report.append("\n");
		return report;
	}
}
