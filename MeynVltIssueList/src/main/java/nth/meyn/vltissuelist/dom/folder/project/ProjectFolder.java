package nth.meyn.vltissuelist.dom.folder.project;

import java.io.File;
import java.io.FileNotFoundException;

import nth.meyn.vltissuelist.dom.folder.software.SoftwareFolder;
import nth.meyn.vltissuelist.dom.folder.software.SoftwareFolderFilter;

public class ProjectFolder {

	private final File projectFolder;

	public ProjectFolder(File projectFolder) throws FileNotFoundException {
		if (!projectFolder.exists()) {
			throw new FileNotFoundException("Could not find project: "+projectFolder.getPath());
		}
		this.projectFolder = projectFolder;
	}

	public ProjectType getProjectType() {
		return ProjectType.valueOf(projectFolder);
	}
	
	public SoftwareFolder getSoftwareFolder() throws FileNotFoundException {
		File[] softwareFolders = projectFolder.listFiles(new SoftwareFolderFilter());
		if (softwareFolders.length==0) {
			throw new FileNotFoundException("Could not find software folder in: "+projectFolder.getPath());
		}
		if (softwareFolders.length>1) {
			throw new FileNotFoundException("Found multiple software folders in: "+projectFolder.getPath());
		}
		SoftwareFolder softwareFolder=new SoftwareFolder(softwareFolders[0]);
		return softwareFolder;
	}

	public File getPath() {
		return projectFolder;
	}
	
}
