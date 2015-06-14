package nth.software.doc.generator.service.robottxt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class RobotsTxt {

	private static final String UTF_8_ENCODING = "UTF-8";
	private static final String FILE_NAME = "robots.txt";
	private static final String DISALLOW_NOTHING = "Disallow:";
	private static final String ALL_USER_AGENTS = "User-agent: *";

	public static void writeFile(File destinationFolder) throws FileNotFoundException, UnsupportedEncodingException {
		File file = new File(destinationFolder,FILE_NAME);
		PrintWriter writer=new PrintWriter(file, UTF_8_ENCODING);
		writer.println(ALL_USER_AGENTS);
		writer.println(DISALLOW_NOTHING);
		writer.flush();
		writer.close();
		
		
		
//		<urlset xsi:schemaLocation="http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
//		<url> <loc>http://ntenhoeve.github.io/</loc> 
//			<lastmod>2015-06-09T11:50:33+00:00</lastmod> 
//			</url> 
//			</urlset>
	}

}
