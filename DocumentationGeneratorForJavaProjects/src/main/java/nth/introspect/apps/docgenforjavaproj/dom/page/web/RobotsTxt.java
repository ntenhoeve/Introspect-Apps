package nth.introspect.apps.docgenforjavaproj.dom.page.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import nth.introspect.apps.docgenforjavaproj.dom.page.WritableFile;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class RobotsTxt implements WritableFile {

	private final File file;
	private static final String UTF_8_ENCODING = "UTF-8";
	private static final String DISALLOW_NOTHING = "Disallow:";
	private static final String ALL_USER_AGENTS = "User-agent: *";
	private static final String ROBOTS_TXT = "robots.txt";
	
	public RobotsTxt(File destinationFolder) {
		file=new File(destinationFolder,ROBOTS_TXT);
	}
	
	@Override
	public void write() throws IOException {
		PrintWriter writer=new PrintWriter(file, UTF_8_ENCODING);
		writer.println(ALL_USER_AGENTS);
		writer.println(DISALLOW_NOTHING);
		writer.flush();
		writer.close();
	}


}
