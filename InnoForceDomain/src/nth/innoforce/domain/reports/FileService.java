package nth.innoforce.domain.reports;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileService {
	private final File file;

	public FileService(File file) {
		this.file = file;
	}

	/**
	 * @return a list FileService objects that represent the children in this folder
	 */
	public List<Object> getChildren() {
		List<Object> children = new ArrayList<Object>();
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				children.add(new FileService(child));
			}
		}
		return children;
	}

	/**
	 * NakedGui will shows methods that return a URL in a browser.
	 */
	public URL show() throws MalformedURLException {
		// File file = new File ("C:/Documents and Settings/nilsth/My Documents/Documents/Information/Innovation/The Ten Faces of Innovation.pdf");
		return file.toURI().toURL();
	}

	/**
	 * NakedGui convention: hide show method when file is a directory
	 */
	public boolean showVisible(){
		return file.isFile();
	}
	
	/**
	 *  Naked GUI convention: overwrites name of class with file name
	 */
	public String fileServiceName() {
		return file.getName();
	}
	
	/**
	 *  Naked GUI convention: class only visible when it contains children
	 */
	public boolean fileServiceVisible() {
		return getChildren().size()>0;
	}
	

}
