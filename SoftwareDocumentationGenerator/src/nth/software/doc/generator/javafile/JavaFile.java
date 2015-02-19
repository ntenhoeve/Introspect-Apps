package nth.software.doc.generator.javafile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import com.sun.xml.internal.rngom.binary.Pattern;

import nth.software.doc.generator.factory.Parser;
import nth.software.doc.generator.regex.Regex;
import nth.software.doc.generator.regex.Repetition;

/**
 * 
 *  @author nilsth
 * 
 */

public class JavaFile {

	private static final String REGEX_STARTS_WITH_ASTRIX = "^\\*+";
	private static final String REGEX_STARTS_WITH_WHITE_SPACE = "^\\s+";
	private static final String START_OF_COMMENT = "/**";
	private static final String END_OF_COMMENT = "*/";
	private static final Regex PACKAGE_LINE = new Regex().beginOfLine()
			.whiteSpace(Repetition.zeroOrMoreTimes()).literal("package")
			.whiteSpace(Repetition.oneOrMoreTimes())
			.literals("a-zA-Z", Repetition.oneOrMoreTimes())
			.anyCharacter(Repetition.zeroOrMoreTimes()).literal(";")
			.whiteSpace(Repetition.zeroOrMoreTimes());
	private static final Regex IMPORT_LINE = new Regex().multiLineMode()
			.beginOfLine().whiteSpace(Repetition.zeroOrMoreTimes())
			.literal("import").whiteSpace(Repetition.oneOrMoreTimes())
			.literals("a-zA-Z", Repetition.oneOrMoreTimes())
			.anyCharacter(Repetition.zeroOrMoreTimes()).literal(";")
			.whiteSpace(Repetition.zeroOrMoreTimes()).endOfLine();
	private static final Regex STARTS_WITH_ASTRIX = new Regex().multiLineMode()
			.beginOfLine().whiteSpaceWithoutCrLf(Repetition.zeroOrMoreTimes())
			.literals("*").whiteSpaceWithoutCrLf(Repetition.zeroOrMoreTimes());
	
//	private static final Regex STARTS_WITH_ASTRIX = new Regex()
//			.newLine().whiteSpace(Repetition.zeroOrMoreTimes())
//			.literals("*");
	
	
	private static final Regex EMPTY_LINE = new Regex().multiLineMode()
			.beginOfLine().whiteSpace(Repetition.zeroOrMoreTimes()).endOfLine();
	private static final String START_JAVADOC_COMMENTS = "/**";
	private static final Regex AUTHOR_TAG = new Regex().multiLineMode().literal("@author")
			.anyCharacter(Repetition.zeroOrMoreTimes()).endOfLine();

	private File javaFile;
	private List<String> lines;
	private String documentation;
	private String contents;

	public JavaFile(File javaFile) {
		this.javaFile = javaFile;
	}

	private List<String> getLines() throws IOException {
		if (lines == null) {
			Charset DEFAULT_TEXT_ENCODING = Charset.defaultCharset();
			lines = Files
					.readAllLines(javaFile.toPath(), DEFAULT_TEXT_ENCODING);
		}
		return lines;
	}

	private String getContents() throws IOException {
		if (contents == null) {
			byte[] encoded = Files.readAllBytes(javaFile.toPath());
			Charset DEFAULT_TEXT_ENCODING = Charset.defaultCharset();
			contents = new String(encoded, DEFAULT_TEXT_ENCODING);
		}
		return contents;
	}

	public String getDocumentation() throws IOException {
		if (documentation == null) {
			String contents = getContents();
			Parser parser = new Parser(contents);
			parser.removeAll(PACKAGE_LINE);
			parser.removeAll(IMPORT_LINE);
			parser.removeAll(EMPTY_LINE);
			if (parser.startsWith("\r\n")) {// This line is needed because the
											// previous line does not remove the
											// first crlf
				parser.removeFirst("\r\n");
			}

			if (parser.startsWith(START_JAVADOC_COMMENTS)) {
				parser.removeFirst(START_JAVADOC_COMMENTS);
				parser.removeFrom(END_OF_COMMENT);
				parser.removeAll(STARTS_WITH_ASTRIX);
				parser.removeAll(AUTHOR_TAG);
				documentation = parser.getResult();
			} else {
				documentation = "";
			}
		}
		return documentation;
	}
}
