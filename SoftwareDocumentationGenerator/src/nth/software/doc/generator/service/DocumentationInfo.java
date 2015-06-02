package nth.software.doc.generator.service;

import java.io.File;

import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInForm;

public class DocumentationInfo {

	private File projectsFolder;
	private String className;
	
	@OrderInForm(1)
	public File getProjectsFolder() {
		return projectsFolder;
	}
	public void setProjectsFolder(File projectsFolder) {
		this.projectsFolder = projectsFolder;
	}
	@OrderInForm(2)
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
