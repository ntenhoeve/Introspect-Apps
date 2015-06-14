package nth.software.doc.generator.service.sitemap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nth.software.doc.generator.framer.HtmlSingleFileFramer;
import nth.software.doc.generator.model.DocumentationModel;
import nth.software.doc.generator.service.GitHubHtmlInfo;

public class SiteMap {

	private static final String XML_ENCODING = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	private static final String FILE_NAME = "sitemap.xml";
	private static final String UTF_8_ENCODING = "UTF-8";
	private static final String ELEMENT_URLSET_BEGIN = "<urlset xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";
	private static final String ELEMENT_URLSET_END = "</urlset>";
	private static final String ELEMENT_URL_BEGIN = "<url>";
	private static final String ELEMENT_URL_END = "</url>";
	private static final String ELEMENT_LOC_BEGIN = "<loc>";
	private static final String ELEMENT_LOC_END = "</loc>";
	private static final String ELEMENT_LASTMOD_BEGIN = "<lastmod>";
	private static final String ELEMENT_LASTMOD_END = "</lastmod>";
	
	public static void witeFile(GitHubHtmlInfo gitHubHtmlInfo) throws FileNotFoundException, UnsupportedEncodingException {
		File file = new File(gitHubHtmlInfo.getGithubHtmlProjectLocation(),FILE_NAME);
		PrintWriter writer=new PrintWriter(file, UTF_8_ENCODING);
		writer.println(XML_ENCODING);
		writer.println(ELEMENT_URLSET_BEGIN);
		
		writer.println(ELEMENT_URL_BEGIN);
		
		writer.print(ELEMENT_LOC_BEGIN);
		writer.print("http://");
		writer.print(gitHubHtmlInfo.getGitHubUserName());
		writer.print(".github.io/");
		writer.print(HtmlSingleFileFramer.FILE_NAME);
		writer.println(ELEMENT_LOC_END);
		
		writer.print(ELEMENT_LASTMOD_BEGIN);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
		String now = sdf.format(new Date());
		writer.print(now);
		writer.println(ELEMENT_LASTMOD_END);
		
		writer.println(ELEMENT_URL_END);
		
		writer.println(ELEMENT_URLSET_END);
		writer.flush();
		writer.close();
		
	}

	
}
