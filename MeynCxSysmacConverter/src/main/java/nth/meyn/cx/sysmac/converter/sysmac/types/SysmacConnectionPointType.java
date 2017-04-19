package nth.meyn.cx.sysmac.converter.sysmac.types;

public enum SysmacConnectionPointType {
	INPUT, OUTPUT;

	public SysmacConnectionPointType getInverse() {
		if (this == SysmacConnectionPointType.INPUT) {
			return OUTPUT;
		} else if (this == SysmacConnectionPointType.OUTPUT) {
			return INPUT;
		} else {
			return null;
		}
	}
}