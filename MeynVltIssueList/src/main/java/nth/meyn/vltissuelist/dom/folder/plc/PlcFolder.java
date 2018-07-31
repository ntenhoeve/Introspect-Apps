package nth.meyn.vltissuelist.dom.folder.plc;

import java.io.File;
import java.io.FileNotFoundException;

import nth.meyn.vltissuelist.dom.folder.vlt.VltFolder;
import nth.meyn.vltissuelist.dom.folder.vlt.VltFolderFilter;
import nth.reflect.fw.generic.regex.Regex;
import nth.reflect.fw.generic.regex.Repetition;

public class PlcFolder {

	private final File plcFolder;
	private final Regex firstLettersRegex;

	
	public PlcFolder(File plcFolder) {
		this.plcFolder = plcFolder;
		firstLettersRegex = new Regex().beginOfLine().letters(Repetition.oneOrMoreTimes());
	}

	public String getName() {
		return plcFolder.getName();
	}
	
	public VltFolder getVltFolder() throws FileNotFoundException {
		File[] foundFolders = plcFolder.listFiles(new VltFolderFilter());
		if (foundFolders.length==0) {
			throw new FileNotFoundException("Could not find vlt folder in: "+plcFolder.getPath());
		}
		if (foundFolders.length>1) {
			throw new FileNotFoundException("Found multiple vlt folders in: "+plcFolder.getPath());
		}
		VltFolder vltFolder=new VltFolder(foundFolders[0]);
		return vltFolder;
	}

	public String getFirstLetters() {
		String firstLetters = firstLettersRegex.findFirstMatchIn(plcFolder.getName()).toUpperCase();
		return firstLetters;
	}

}
