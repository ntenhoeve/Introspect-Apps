package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.util.ArrayList;
import java.util.List;

public class CxVerticals {

	private final List<CxLocation> verticals;

	public CxVerticals() {
		verticals = new ArrayList<>();
	}

	public void add(CxLocation location) {
		verticals.add(location);
	}

	public boolean goingUpFrom(int x, int y) {
		CxLocation location = new CxLocation(x, y - 1);
		return verticals.contains(location);
	}

	public boolean goingDownFrom(int x, int y) {
		CxLocation location = new CxLocation(x, y);
		return verticals.contains(location);
	}

	public int getTop(int x, int y) {
		if (goingUpFrom(x, y)) {
			return getTop(x, y - 1);
		} else {
			return y;
		}
	}

	public int getBottom(int x, int y) {
		if (goingDownFrom(x, y)) {
			return getBottom(x, y + 1);
		} else {
			return y;
		}
	}

}
