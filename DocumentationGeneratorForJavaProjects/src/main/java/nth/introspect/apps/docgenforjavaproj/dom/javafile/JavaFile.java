package nth.introspect.apps.docgenforjavaproj.dom.javafile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.regex.Pattern;

import nth.introspect.apps.docgenforjavaproj.dom.javadoc.tag.EndTag;
import nth.introspect.apps.docgenforjavaproj.dom.javadoc.tag.HtmlLinkToReference;
import nth.introspect.generic.regex.Regex;
import nth.introspect.generic.regex.Repetition;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

/**
 * 
 * @author nilsth
 * 
 */

public class JavaFile {

	private static final String JAVA_EXTENSION = ".java";
	private static final Regex END_OF_COMMENT = new Regex().literal("*/");
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
	private static final Pattern EMPTY_LINE = new Regex().multiLineMode()
			.beginOfLine().whiteSpace(Repetition.zeroOrMoreTimes()).endOfLine().asPattern();
	private static final String START_JAVADOC_COMMENTS = "/**";

	private File javaFile;
	private String documentation;
	private String contents;

	public JavaFile(File javaFile) {
		this.javaFile = javaFile;
	}

	private String getContents() throws IOException {
		if (contents == null) {
			byte[] encoded = Files.readAllBytes(javaFile.toPath());
			// Charset used by eclipse: Window -> Preferences -> General -> Workspace : Text file encoding CP1252
			Charset DEFAULT_TEXT_ENCODING = Charset.forName("Cp1252");
			contents = new String(encoded, DEFAULT_TEXT_ENCODING);
		}
		return contents;
	}

	public String getJavaDocOfClassDescriptor() {
		if (documentation == null) {
			String contents;
			try {
				contents = getContents();
			} catch (IOException e) {
				throw new RuntimeException("Could not read javadoc of file:"+javaFile.getAbsolutePath());
			}
			RegexParser regexParser = new RegexParser(contents);
			regexParser.removeAll(PACKAGE_LINE);
			regexParser.removeAll(IMPORT_LINE);
			regexParser.removeAll(SINGLE_LINE_COMMENT);
			regexParser.removeAll(EMPTY_LINE);
			if (regexParser.startsWith("\r\n")) {
				// This line is needed because the previous line does not remove the first crlf
				regexParser.removeFirst("\r\n");
			}

			if (regexParser.startsWith(START_JAVADOC_COMMENTS)) {
				regexParser.removeFirst(START_JAVADOC_COMMENTS);
				regexParser.removeFrom(END_OF_COMMENT);
				regexParser.removeAll(STARTS_WITH_ASTRIX);
				
				String reference=createRefferenceElement().toString();
				regexParser.insert(reference,0);

				removeEndTags(regexParser);
				
				documentation = regexParser.getResult();
			} else {
				documentation = "";
			}
		}
		return documentation;
	}

	private Element createRefferenceElement() {
		return new Element(Tag.valueOf("a"), "").attr("id",HtmlLinkToReference.REFERENCE+ getNameWithoutExtention());
	}

	private void removeEndTags(RegexParser regexParser) {
		for (EndTag endTag : EndTag.values()) {
			regexParser.removeFrom(endTag.asRegex());
		}
	}

//	private String createRefferenceComment() {
//		StringBuilder comment=new StringBuilder();
//		comment.append(BEGIN_HTML_COMMENT);
//		comment.append(HtmlLinkToReference.REFERENCE);
//		comment.append(":");
//		comment.append(getNameWithoutExtention());
//		comment.append(END_HTML_COMMENT);
//		return comment.toString();
//	}

	public String getNameWithoutExtention() {
		String nameWithoutExtention=javaFile.getName().replace(JAVA_EXTENSION, "");
		return nameWithoutExtention;
	}
	
	/**
	 * Searches for a resource file (like a image) in the same package as java file
	 */
	public File getResourcePath(String resourceName){
		String path = javaFile.getParent()+"/"+resourceName;
		File file=new File(path);
		if (file.exists()) {
		return file;
		} 
		String mavenSourceFolder = createMainMavenFolder("java");
		String mavenResourceFolder = createMainMavenFolder("resources");
		if (file.getAbsolutePath().contains(mavenSourceFolder)) {
			file=new File(file.getAbsolutePath().replace(mavenSourceFolder, mavenResourceFolder) );
			if (file.exists()) {
				return file;
			}
		}
		return null;
	}


	private String createMainMavenFolder(String suffix) {
		StringBuilder folder=new StringBuilder();
		folder.append(File.separator);
		folder.append("src");
		folder.append(File.separator);
		folder.append("main");
		folder.append(File.separator);
		folder.append(suffix);
		folder.append(File.separator);
		return folder.toString();
	}

	public static boolean isJavaFile(File file) {
		return file.isFile()&&file.getName().toLowerCase().endsWith(JAVA_EXTENSION);
	}

}
