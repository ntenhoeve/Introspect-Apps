package nth.meyn.control.systems.dom.activity;

import nth.reflect.fw.generic.util.StringUtil;

public enum ActivityType {
	HARDWARE_ENGINEERING(17, 8), SOFTWARE_ENGINEERING(8, 2), LAYOUT_ENGINEERING(
			4, 2), TESTING(2, 1), REVISION(1, 0);
	private final int startInWeeksFromNow;
	private final int completionInWeeksFromNow;

	ActivityType(int startInWeeksFromNow, int completionInWeeksFromNow) {
		this.startInWeeksFromNow = startInWeeksFromNow;
		this.completionInWeeksFromNow = completionInWeeksFromNow;
	}

	public int getStartInWeeksFromNow() {
		return startInWeeksFromNow;
	}

	public int getCompletionInWeeksFromNow() {
		return completionInWeeksFromNow;
	}

	@Override
	public String toString() {
		String string=StringUtil.eliphantCaseToNormal(this.name());
		return string;
	}
}
