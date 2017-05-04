package nth.meyn.cx.sysmac.converter.cx.ladder.model;

public enum CxVariableType {
	
	INTERNAL(0),FB_INPUT(1), FB_OUTPUT(2), FB_IN_OUT(3), FB_EXTERNAL(4);
	
	private final int id;

	private CxVariableType(int id) {
		this.id = id;
}

	public int getId() {
		return id;
	}

	public static CxVariableType valueOfId(int variableTypeIdToFind) {
		for (CxVariableType variableType : values()) {
			if (variableType.getId()==variableTypeIdToFind) {
				return variableType;
			}
		}
		throw new RuntimeException("Unkown variable type: "+variableTypeIdToFind);
//		return UNKNOWN;
	}
	
}
