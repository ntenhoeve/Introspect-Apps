package nth.meyn.display.translate.dom.abbreviation;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import nth.reflect.fw.layer5provider.url.application.ApplicationUrl;

public class AbbreviationRepository {

	private static final String ABBREVIATION_IN_ENGLISH = "AbbreviationInEnglish";
	private static final String FULL_TEXT_IN_ENGLISH = "FullTextInEnglish";

	public static Abbreviations read() throws URISyntaxException, IOException {

		URI uri = new ApplicationUrl("/","abbreviations.csv").toInternalURL().toURI();
		Path path=Paths.get(uri);
		
		Reader reader = Files.newBufferedReader(path, Charset.forName("WINDOWS-1252"));
		final CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
		Abbreviations abbreviations = new Abbreviations();
		try {
			for (final CSVRecord record : parser) {
				String abbreviationInEnglish = record.get(ABBREVIATION_IN_ENGLISH);
				String fullTextInEnglish = record.get(FULL_TEXT_IN_ENGLISH);
				abbreviations.put(abbreviationInEnglish, fullTextInEnglish);
			}
		} finally {
			parser.close();
			reader.close();
		}
		return abbreviations;

	}
}
