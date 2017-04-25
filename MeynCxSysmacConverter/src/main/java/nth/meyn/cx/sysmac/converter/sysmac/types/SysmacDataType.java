package nth.meyn.cx.sysmac.converter.sysmac.types;

public enum SysmacDataType {
	 ANY, ANY_ELEMENTARY_EXCEPT_BOOL, BOOL, TIME, DATE, INT;
	
	@Override
	public String toString() {
		if (this==ANY_ELEMENTARY_EXCEPT_BOOL) {
			return "ANY_ELEMENTARY(except BOOL)";//yuk exception in Sysmac XML
		}
		return super.toString();
	}

}
