package nth.innoforce.domain.reports;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ReportService {
	public List<Object> getChildren() {
		return null;
	}
	
	public URL getDocument() throws MalformedURLException {
		File file = new File ("C:/Documents and Settings/nilsth/My Documents/Documents/Information/Innovation/The Ten Faces of Innovation.pdf");
		return file.toURI().toURL(); 
	}
	
	public String getDocumentName() {
		return "test";
	}
}
