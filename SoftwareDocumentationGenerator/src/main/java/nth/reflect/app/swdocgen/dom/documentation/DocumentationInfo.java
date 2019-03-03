package nth.reflect.app.swdocgen.dom.documentation;

import java.io.File;

import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class DocumentationInfo {

	private File projectsFolder;
	private String className;
	
	@Order(value= 1)
	public File getProjectsFolder() {
		return projectsFolder;
	}
	public void setProjectsFolder(File projectsFolder) {
		this.projectsFolder = projectsFolder;
	}
	@Order(value=2)
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
