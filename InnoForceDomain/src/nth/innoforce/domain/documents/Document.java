package nth.innoforce.domain.documents;

import java.io.File;
import java.util.Date;

import nth.introspect.provider.domain.info.valuemodel.annotations.Format;

public class Document {

	private String name;
	private Date date;
	private File file;

	public Document(File file) {
		this.file = file;
		this.name = file.getName();
		this.date = new Date();
		this.date.setTime(file.lastModified());
	}

	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file=file;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	@Format("yyyy-MM-dd HH:mm:ss")
	public Date getDate() {
		return date;
	}

}
