package nth.software.doc.generator.javafile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Pattern;

import nth.software.doc.generator.factory.Parser;
import nth.software.doc.generator.model.inlinetag.InlineTag;
import nth.software.doc.generator.regex.Regex;
import nth.software.doc.generator.regex.Repetition;
import nth.software.doc.generator.tokenizer.InlineTagName;
import nth.software.doc.generator.tokenizer.TokenFactory;

/**
 * 
 * @author nilsth
 * 
 */

public class JavaFile {

//	private static final String REGEX_STARTS_WITH_ASTRIX = "^\\*+";
//	private static final String REGEX_STARTS_WITH_WHITE_SPACE = "^\\s+";
//	private static final String START_OF_COMMENT = "/**";
	private static final String END_OF_COMMENT = "*/";
	private static final Pattern PACKAGE_LINE = new Regex().beginOfLine()
			.whiteSpace(Repetition.zeroOrMoreTimes()).literal("package")
			.whiteSpace(Repetition.oneOrMoreTimes())
			.literals("a-zA-Z", Repetition.oneOrMoreTimes())
			.anyCharacter(Repetition.zeroOrMoreTimes()).literal(";")
			.whiteSpace(Repetition.zeroOrMoreTimes()).asPattern();
	private static final Pattern IMPORT_LINE = new Regex().multiLineMode()
			.beginOfLine().whiteSpace(Repetition.zeroOrMoreTimes())
			.literal("import").whiteSpace(Repetition.oneOrMoreTimes())
			.literals("a-zA-Z", Repetition.oneOrMoreTimes())
			.anyCharacter(Repetition.zeroOrMoreTimes()).literal(";")
			.whiteSpace(Repetition.zeroOrMoreTimes()).endOfLine().asPattern();
	private static final Pattern SINGLE_LINE_COMMENT = new Regex().multiLineMode()
			.beginOfLine().whiteSpace(Repetition.zeroOrMoreTimes())
			.literal("//").anyCharacter(Repetition.zeroOrMoreTimes()).endOfLine().asPattern();
	private static final Pattern STARTS_WITH_ASTRIX = new Regex()
			.multiLineMode().beginOfLine()
			.whiteSpaceWithoutCrLf(Repetition.zeroOrMoreTimes()).literals("*").asPattern();
//			.whiteSpaceWithoutCrLf(Repetition.zeroOrMoreTimes()).asPattern();
	private static final Pattern EMPTY_LINE = new Regex().multiLineMode()
			.beginOfLine().whiteSpace(Repetition.zeroOrMoreTimes()).endOfLine().asPattern();
	private static final String START_JAVADOC_COMMENTS = "/**";

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
			parser.removeAll(SINGLE_LINE_COMMENT);
			parser.removeAll(EMPTY_LINE);
			if (parser.startsWith("\r\n")) {
				// This line is needed because the previous line does not remove the first crlf
				parser.removeFirst("\r\n");
			}

			if (parser.startsWith(START_JAVADOC_COMMENTS)) {
				parser.removeFirst(START_JAVADOC_COMMENTS);
				parser.removeFrom(END_OF_COMMENT);
				parser.removeAll(STARTS_WITH_ASTRIX);
				
				String beginOfFileTag=createBeginOfFileTag();
				parser.insert(beginOfFileTag,0);
				String endOfFileTag=new InlineTag(InlineTagName.ENDOFFILE).toString();
				parser.append(endOfFileTag);

				documentation = parser.getResult();
			} else {
				documentation = "";
			}
		}
		return documentation;
	}

	private String createBeginOfFileTag() {
		StringBuilder tag=new StringBuilder();
		String nameWithoutExtention=getNameWithoutExtention();
		String beginOfFileTag=new InlineTag(InlineTagName.BEGINOFFILE, nameWithoutExtention).toString();
		tag.append(beginOfFileTag);
		tag.append("\r\n");
		return tag.toString();
	}

	private String getNameWithoutExtention() {
		String fileName=javaFile.getName();
		int startExtention=fileName.indexOf(".");
		if (startExtention!=-1) {
			fileName=fileName.substring(0,startExtention);
		}
		return fileName;
	}
	
	/**
	 * Searches for a resource file (like a image) in the same package as java file
	 */
	public File getResourcePath(String resourceName){
		String path = javaFile.getParent()+"/"+resourceName;
		File file=new File(path);
		return file;
	}

}
