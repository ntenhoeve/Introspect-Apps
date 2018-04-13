package nth.meyn.vltissuelist.dom.folder.plc;

import java.util.Comparator;

public class PlcFolderComparator implements Comparator<PlcFolder> {

	@Override
	public int compare(PlcFolder o1, PlcFolder o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
