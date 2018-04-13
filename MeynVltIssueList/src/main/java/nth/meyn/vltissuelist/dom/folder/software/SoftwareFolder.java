package nth.meyn.vltissuelist.dom.folder.software;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nth.meyn.vltissuelist.dom.folder.plc.PlcFolder;
import nth.meyn.vltissuelist.dom.folder.plc.PlcFolderComparator;
import nth.meyn.vltissuelist.dom.folder.plc.PlcFolderFilter;

public class SoftwareFolder {

	private final File softwareFolder;

	public SoftwareFolder(File softwareFolder) {
		this.softwareFolder = softwareFolder;
	}

	public List<PlcFolder> getOrderedPlcFolders() throws FileNotFoundException {
		List<PlcFolder> plcFolders = recursivePlcFolderFinder(new PlcFolderFilter(), softwareFolder);
		if (plcFolders.size() == 0) {
			throw new FileNotFoundException("Could not find PLC folders in: "
					+ softwareFolder.getPath());
		}
		Collections.sort(plcFolders, new PlcFolderComparator());
		return plcFolders;
	}

	private List<PlcFolder> recursivePlcFolderFinder(PlcFolderFilter plcFolderFilter, File plcFolder) {
		List<PlcFolder> plcFolders = new ArrayList<>();
		for (File child : plcFolder.listFiles()) {
			if (child.isDirectory()) {
				if (plcFolderFilter.accept(child)) {
					plcFolders.add(new PlcFolder(child));
				} else if (!child.getName().toUpperCase().contains("DISP")) {
					List<PlcFolder> foundPlcFolders = recursivePlcFolderFinder(plcFolderFilter,
							child);
					plcFolders.addAll(foundPlcFolders);
				}
			}
		}
		return plcFolders;
	}

	public List<PlcFolder> getLatestPlcFolders() throws FileNotFoundException {
		List<PlcFolder> foundFolders = new ArrayList<>();
		List<PlcFolder> orderedPlcFolders = getOrderedPlcFolders();

		for (PlcFolder plcFolder : orderedPlcFolders) {
			if (foundFolders.size() == 0) {
				foundFolders.add(plcFolder);
			}
			PlcFolder lastFoundFolder = foundFolders.get(foundFolders.size() - 1);
			if (plcFolder.getFirstLetters().equals(lastFoundFolder.getFirstLetters())) {
				foundFolders.remove(lastFoundFolder);
				foundFolders.add(plcFolder);// newer version
			} else {
				foundFolders.add(plcFolder);// other type
			}
		}

		return foundFolders;
	}
}
