package nth.meyn.cx.sysmac.converter.sysmac.types;

public enum SysmacDataType {
	ANY, ANY_ELEMENTARY_EXCEPT_BOOL, ANY_NUM_OR_STRING, BOOL, TIME, DATE, INT;

	@Override
	public String toString() {
		// yuk exceptions in Sysmac XML
		if (this == ANY_ELEMENTARY_EXCEPT_BOOL) {
			return "ANY_ELEMENTARY(except BOOL)";
		} else if (this == ANY_NUM_OR_STRING) {
			return "ANY_NUM, STRING";
		} else {
			return super.toString();
		}
	}

}
