package nth.meyn.cx.sysmac.converter.cx.ladder.model;

public enum CxVariableType {
  BOOL(0),X(1),UINT(2), UNKNOWN(-1); 
	
	//BOOL Bit data
	//INT Integer
	//DINT Double integer
	//LINT Long (8-byte) integer
	//UINT Unsigned integer
	//UINT-BCD Unsigned BCD integer
	//UDINT Unsigned double integer 32
	//UDINT-BCD Unsigned double BCD integer
	//ULINT Unsigned long (8-byte)integer
	//ULINT-BCD Unsigned long (8-byte)BCD integer
	//REAL Real number
	//LREALLong real number
	//WORD 16-bit data
	//DWORD 32-bit data
	//LWORD 64-bit data
	//STRING Text string
	//FUNCTION-BLOCK Function block instanceBLOCK
	//CHANNEL Word 
	//NUMBER Constant or number
  
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
