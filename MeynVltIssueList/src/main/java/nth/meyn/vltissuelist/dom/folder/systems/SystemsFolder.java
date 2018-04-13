package nth.meyn.vltissuelist.dom.folder.systems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import nth.meyn.vltissuelist.dom.folder.project.ProjectFolder;
import nth.meyn.vltissuelist.dom.folder.project.ProjectFolderFilter;

public class SystemsFolder {

	private File systemsFolder;

	public SystemsFolder(File systemsFolder) {
		this.systemsFolder = systemsFolder;
	}

	public List<ProjectFolder> getProjectFolders() throws FileNotFoundException {
		File[] foundFolders = systemsFolder.listFiles(new ProjectFolderFilter());
		if (foundFolders.length == 0) {
			throw new FileNotFoundException("Could not find a project folder in: "
					+ systemsFolder.getPath());
		}
		List<ProjectFolder> projectFolders = new ArrayList<>();
		for (File foudFolder : foundFolders) {
			ProjectFolder projectFolder = new ProjectFolder(foudFolder);
			projectFolders.add(projectFolder);
		}
		return projectFolders;
	}

	public ProjectFolder getProjectFolder(String electricalSchematic) throws FileNotFoundException {
		File[] foundFolders = systemsFolder.listFiles(new ProjectFolderFilter(electricalSchematic));
		if (foundFolders.length == 0) {
			throw new FileNotFoundException(
					"Could not find a project folder with electrical schematic nr: "
							+ electricalSchematic + " in: " + systemsFolder.getPath());
		}
		if (foundFolders.length > 1) {
			throw new FileNotFoundException(
					"Found multiple project folders with electrical schematic nr: "
							+ electricalSchematic + " in: " + systemsFolder.getPath());
		}
		ProjectFolder projectFolder = new ProjectFolder(foundFolders[0]);
		return projectFolder;
	}
}
