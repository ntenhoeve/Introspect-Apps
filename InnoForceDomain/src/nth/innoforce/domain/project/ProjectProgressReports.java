package nth.innoforce.domain.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import nth.innoforce.domain.resource.Resource;
import nth.introspect.provider.domain.info.property.FieldModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FieldMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.Format;

import org.apache.poi.hwpf.extractor.WordExtractor;

public class ProjectProgressReports {

	private Date dateOfReport;
	private String reportSource;
	private Resource reportCreator;
	private String projectNumberNameAndProgress;

	public ProjectProgressReports() throws FileNotFoundException, IOException {
		readFromFile();
	}

	@Format("yyyy-MM-dd")
	public Date getDateOfReport() {
		return dateOfReport;
	}

	public void setDateOfReport(Date dateOfReport) {
		this.dateOfReport = dateOfReport;
	}

	public String getReportSource() {
		return reportSource;
	}

	public void setReportSource(String reportSource) {
		this.reportSource = reportSource;
	}

	public Resource getReportCreator() {
		return reportCreator;
	}

	public void setReportCreator(Resource reportCreator) {
		this.reportCreator = reportCreator;
	}

	@FieldMode(FieldModeType.TEXT_AREA)
	public String getProjectNumberNameAndProgress() {
		return projectNumberNameAndProgress;
	}

	public void setProjectNumberNameAndProgress(String projectNumberNameAndProgress) {
		this.projectNumberNameAndProgress = projectNumberNameAndProgress;
	}

	public void readFromFile() throws FileNotFoundException, IOException {
		File file = new File("P:/IBO/IBO 2011-11/Minutes of IBO Primary processing 2011-11-16.doc"); // TODO replace this line with: File file = new File(uri);
		this.dateOfReport = new Date(file.lastModified());
		this.reportSource = file.getName();
		WordExtractor wordExtractor = new WordExtractor(new FileInputStream(file));
		this.projectNumberNameAndProgress = wordExtractor.getText();

		// WordExtractor wordExt = new WordExtractor(is);
		// String bodyText = WordExtractor.stripFields(wordExt.getText());
		//
		// StringBuffer cleanString = new StringBuffer();
		// StringBuffer dirtyString = new StringBuffer(bodyText);
		//
		// while(!StringUtils.isAsciiPrintable(dirtyString.toString()))
		// {
		// char c;
		// int index = 0;
		//
		// c = dirtyString.charAt(index);
		// while(StringUtils.isAsciiPrintable(String.valueOf(c)))
		// {
		// index++;
		// c = dirtyString.charAt(index);
		// }
		// dirtyString = new
		// StringBuffer(dirtyString.toString().replaceAll(String.valueOf(dirtyString.charAt(index)), " "));
		// }
		//
		// return dirtyString.toString();

	}

}
