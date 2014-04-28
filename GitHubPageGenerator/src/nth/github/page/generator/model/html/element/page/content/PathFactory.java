package nth.github.page.generator.model.html.element.page.content;

import java.io.File;
import java.net.URI;

import nth.github.page.generator.Config;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;

public class PathFactory {

	private static final String PATH_SEPARATOR = "/";
	private static final String HTML_EXTENSION = ".html";

	// i.e. https://github.com/ntenhoeve/Introspect-Framework/wiki
	public static String createRemoteGitHubRepositoryWikiUri(Config config) {
		StringBuilder path = new StringBuilder();
		path.append("https://github.com/");
		path.append(config.getGitHubUserName());
		path.append("/");
		path.append(config.getGitHubRepositoryName());
		path.append("/wiki");
		return path.toString();
	}

	// i.e. http://ntenhoeve.github.io/
	public static String createRemoteGitHubWebSiteUri(Config config) {
		StringBuilder path = new StringBuilder();
		path.append("http://");
		path.append(config.getGitHubUserName());
		path.append(".github.io");
		return path.toString();
	}

	// i.e. C:\Users\nilsth\My Git\ntenhoeve.github.io
	public static File createLocalGitHubWebSiteRepositoryPath(Config config) {
		StringBuilder path = new StringBuilder();
		path.append(config.getLocalGitHubRepositoryLocation());
		path.append("/");
		path.append(config.getGitHubUserName());
		path.append(".github.io");
		return new File(path.toString());
	}

	public static File createLocalGitHubWebSiteRepositorySmallPath(Config config) {
		File htmlSmallFolder = new File(createLocalGitHubWebSiteRepositoryPath(
				config).getAbsolutePath()
				+ "/small");
		return htmlSmallFolder;
	}

	public static File createLocalGitHubWebSiteRepositoryWidePath(Config config) {
		File htmlWideFolder = new File(createLocalGitHubWebSiteRepositoryPath(
				config).getAbsolutePath()
				+ "/wide");
		return htmlWideFolder;
	}

	// i.e. C:\Users\nilsth\My Git\Introspect-Framework.wiki
	public static File createLocalGitHubWikiRepositoryPath(Config config) {
		StringBuilder path = new StringBuilder();
		path.append(config.getLocalGitHubRepositoryLocation());
		path.append("/");
		path.append(config.getGitHubRepositoryName());
		path.append(".wiki");
		return new File(path.toString());
	}

	public static String createSmallHtmlPagePath(Config config,
			TextChapterLevel1 chapterLevel1, TextChapterLevel2 chapterLevel2) {
		StringBuilder path = new StringBuilder();
		path.append(PathFactory
				.createLocalGitHubWebSiteRepositorySmallPath(config));
		path.append(PATH_SEPARATOR);
		path.append(createHtmlFileName(chapterLevel1, chapterLevel2));
		path.append(HTML_EXTENSION);
		return path.toString();
	}

	public static String createWideHtmlPagePath(Config config,
			TextChapterLevel1 chapterLevel1, TextChapterLevel2 chapterLevel2) {
		StringBuilder path = new StringBuilder();
		path.append(PathFactory
				.createLocalGitHubWebSiteRepositoryWidePath(config));
		path.append(PATH_SEPARATOR);
		path.append(createHtmlFileName(chapterLevel1, chapterLevel2));
		path.append(HTML_EXTENSION);
		return path.toString();
	}

	private static String createHtmlFileName(TextChapterLevel1 chapterLevel1,
			TextChapterLevel2 chapterLevel2) {
		StringBuilder fileNameBuilder = new StringBuilder();
		fileNameBuilder.append(chapterLevel1.getTitle().trim());
		if (chapterLevel2 != null) {
			fileNameBuilder.append("-");
			fileNameBuilder.append(chapterLevel2.getTitle().trim());
		}
		String fileName = fileNameBuilder.toString();
		// Test ,. ?;':"[]\{}|!@#$%^&*()_ =
		// Test-,.%3F;':%22%5B%5D%5C%7B%7D%7C!@%23$%25%5E&*()_--=
		fileName = fileName.replace(" ", "-");
		fileName = fileName.replace(PATH_SEPARATOR, "");
		fileName = fileName.replace("<", "");
		fileName = fileName.replace(">", "");
		fileName = fileName.replace("?", "");
		fileName = fileName.replace(";", "");
		fileName = fileName.replace("'", "");
		fileName = fileName.replace(":", "");
		fileName = fileName.replace("\"", "");
		fileName = fileName.replace("[", "");
		fileName = fileName.replace("]", "");
		fileName = fileName.replace("\\", "");
		fileName = fileName.replace("{", "");
		fileName = fileName.replace("}", "");
		fileName = fileName.replace("|", "");
		fileName = fileName.replace("!", "");
		fileName = fileName.replace("@", "");
		fileName = fileName.replace("#", "");
		fileName = fileName.replace("$", "");
		fileName = fileName.replace("%", "");
		fileName = fileName.replace("^", "");
		fileName = fileName.replace("&", "");
		fileName = fileName.replace("*", "");
		fileName = fileName.replace("(", "");
		fileName = fileName.replace(")", "");
		fileName = fileName.replace("_", "_");
		fileName = fileName.replace("+", "");
		fileName = fileName.replace("-", "-");
		fileName = fileName.replace("=", "");
		return fileName;
	}

	private static String createWikiFileName(TextChapterLevel1 chapterLevel1,
			TextChapterLevel2 chapterLevel2) {
		StringBuilder fileNameBuilder = new StringBuilder();
		fileNameBuilder.append(chapterLevel1.getTitle().trim());
		if (chapterLevel2 != null) {
			fileNameBuilder.append("-");
			fileNameBuilder.append(chapterLevel2.getTitle().trim());
		}
		return createWikiFileName(fileNameBuilder.toString());
	}

	private static String createWikiFileName(String title) {
		String fileName = new String(title);
		// Test ,. ?;':"[]\{}|!@#$%^&*()_ =
		// Test-,.%3F;':%22%5B%5D%5C%7B%7D%7C!@%23$%25%5E&*()_--=
		fileName = fileName.replace(" ", "-");
		fileName = fileName.replace(PATH_SEPARATOR, "");
		fileName = fileName.replace("<", "");
		fileName = fileName.replace(">", "");
		fileName = fileName.replace("?", "%3F");
		fileName = fileName.replace(";", ";");
		fileName = fileName.replace("'", "'");
		fileName = fileName.replace(":", ":");
		fileName = fileName.replace("\"", "%22");
		fileName = fileName.replace("[", "%5B");
		fileName = fileName.replace("]", "%5D");
		fileName = fileName.replace("\\", "%5C");
		fileName = fileName.replace("{", "%7B");
		fileName = fileName.replace("}", "%7D");
		fileName = fileName.replace("|", "%7C");
		fileName = fileName.replace("!", "!");
		fileName = fileName.replace("@", "@");
		fileName = fileName.replace("#", "%23");
		fileName = fileName.replace("$", "$");
		fileName = fileName.replace("%", "%25");
		fileName = fileName.replace("^", "%5E");
		fileName = fileName.replace("&", "&");
		fileName = fileName.replace("*", "*");
		fileName = fileName.replace("(", "(");
		fileName = fileName.replace(")", ")");
		fileName = fileName.replace("_", "_");
		fileName = fileName.replace("+", "-");
		fileName = fileName.replace("-", "-");
		fileName = fileName.replace("=", "=");
		return fileName;
	}

	public static String createRemoteGitHubWikiPath(Config config,
			TextChapterLevel1 chapterLevel1, TextChapterLevel2 chapterLevel2) {
		StringBuilder path = new StringBuilder();
		path.append(PathFactory.createRemoteGitHubRepositoryWikiUri(config));
		path.append("/");
		path.append(createWikiFileName(chapterLevel1, chapterLevel2));
		return path.toString();
	}

	public static String createLocalGitHubWikiRepositoryPath(Config config,
			String title) {
		StringBuilder path = new StringBuilder();
		path.append(PathFactory.createLocalGitHubWikiRepositoryPath(config));
		path.append("/");
		path.append(createWikiFileName(title));
		path.append(".md");
		return path.toString();
	}

}
