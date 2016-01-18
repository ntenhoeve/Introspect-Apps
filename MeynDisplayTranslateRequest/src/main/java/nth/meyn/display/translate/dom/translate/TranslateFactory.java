package nth.meyn.display.translate.dom.translate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.controller.UploadStream;
import nth.meyn.display.translate.dom.abbreviation.AbbreviationRepository;
import nth.meyn.display.translate.dom.abbreviation.Abbreviations;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class TranslateFactory {

	//public static DownloadStream createTranslateRequest(
//			UploadStream uploadStream) throws URISyntaxException, IOException {
				public static DownloadStream createTranslateRequest() throws URISyntaxException, IOException {
		//InputStreamReader reader = createReader(uploadStream);
		InputStreamReader reader = createReader();
		skipFirstLine(reader);
		CSVParser parser = createParser(reader);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(out, false,
				StandardCharsets.UTF_16.toString());
		writeFirstLines(printStream);

		Abbreviations abbreviations = AbbreviationRepository.read();
		try {
			for (final CSVRecord csvRecord : parser) {
				TranslateRecord translateRecord = new TranslateRecord(
						csvRecord, abbreviations);
				TranslatePrinter.print(translateRecord, printStream);
			}
		} finally {
			parser.close();
			reader.close();
		}
		File file = new File("XXXXDEYY English to ZZZZZ.csv");
		return new DownloadStream(file, out);
	}

	private static void writeFirstLines(PrintStream printStream)
			throws IOException, URISyntaxException {
		InputStreamReader reader = createReader();
		char ch;

		// copy first line
		do {
			ch = (char) reader.read();
			printStream.print(ch);
		} while (ch != '\n');

		// copy second line
		do {
			ch = (char) reader.read();
			if (ch == '\r') {
				printStream
						.print(",\"New Language                                                                                   \"");
				printStream.print(",\"How to translate\"");
				printStream.print(",\"Maximum translation size\"");
				printStream.print(",\"Translation tips\"\n");
			} else {
				printStream.print(ch);
			}
		} while (ch != '\r');
	}

	private static void skipFirstLine(InputStreamReader reader)
			throws IOException, URISyntaxException {
		char ch;
		do {
			ch = (char) reader.read();
		} while (ch != '\n');
	}

	public static String findAbreviationCandidates() throws URISyntaxException,
			IOException {
		Set<String> candidates = new HashSet<>();
		InputStreamReader reader = createReader();
		skipFirstLine(reader);
		CSVParser parser = createParser(reader);

		Abbreviations abbreviations = AbbreviationRepository.read();
		try {
			for (final CSVRecord csvRecord : parser) {
				String line = csvRecord.get(TranslateRecord.ENGLISH);
				candidates.addAll(abbreviations
						.findAbbreviationCandidates(line));
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
		final CSVParser parser = new CSVParser(reader,
				CSVFormat.EXCEL.withHeader());
		return parser;
	}

	private static InputStreamReader createReader(UploadStream uploadStream)
			throws URISyntaxException, IOException {
		InputStreamReader reader = new InputStreamReader(
				uploadStream.getInputStream(), StandardCharsets.UTF_16);
		return reader;
	}

	private static InputStreamReader createReader() throws URISyntaxException,
			IOException {
//		Path path = Paths.get(TranslateFactory.class.getResource(
//				"/sourceFileToTranslate.csv").toURI());
		Path path=Paths.get("w:/sourceFileToTranslate.csv");
		InputStreamReader reader = new InputStreamReader(
				Files.newInputStream(path), StandardCharsets.UTF_16);
		return reader;
	}

	public static String validateTranslations() throws URISyntaxException,
			IOException {
		StringBuilder report = new StringBuilder();
		 InputStreamReader reader = createReader();
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
