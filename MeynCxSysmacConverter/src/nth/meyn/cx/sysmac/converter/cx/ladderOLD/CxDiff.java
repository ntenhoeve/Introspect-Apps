package nth.meyn.cx.sysmac.converter.cx.ladderOLD;

public enum CxDiff {
  UP("@"), DOWN("%"), NONE("");
	
	private final String presentation;

	private CxDiff(String presentation) {
		this.presentation = presentation;
	}

	public String getPresentation() {
		return presentation;
	}

	public static CxDiff valueOfPresentation(String presentationToFind) {
		for (CxDiff diff : values()) {
			if (diff.getPresentation().equals(presentationToFind)) {
				return diff;
			}
		}
		return NONE;
	}
	
}
