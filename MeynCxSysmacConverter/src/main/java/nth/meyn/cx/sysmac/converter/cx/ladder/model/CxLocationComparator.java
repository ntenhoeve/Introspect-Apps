package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.util.Comparator;

public class CxLocationComparator implements Comparator<CxLocation> {

	@Override
	public int compare(CxLocation l1, CxLocation l2) {
		int yCompare=Integer.compare(l1.getY(), l2.getY());
		if (yCompare==0) {
			int xCompare = Integer.compare(l1.getX(), l2.getX());
			return xCompare;
		} else {
			return yCompare;
		}
	}

}
