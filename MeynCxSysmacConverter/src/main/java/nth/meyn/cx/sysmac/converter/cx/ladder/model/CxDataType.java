package nth.meyn.cx.sysmac.converter.cx.ladder.model;

public enum CxDataType {
  BOOL(0),INT(1),UINT(2), DINT(3),LINT(5), ULINT(6), WORD(7), DWORD(8), LWORD(9), REAL(10), LREAL(11), COUNTER(12), TIMER(13), FUNCTION_BLOCK(14), STRING(15), STRUCT(16), UNKNOWN(-1); 
	
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

	private CxDataType(int id) {
		this.id = id;
}

	public int getId() {
		return id;
	}

	public static CxDataType valueOfId(int dataTypeIdToFind) {
		for (CxDataType dataType : values()) {
			if (dataType.getId()==dataTypeIdToFind) {
				return dataType;
			}
		}
		throw new RuntimeException("Unkown data type: "+dataTypeIdToFind);
//		return UNKNOWN;
	}
	
	
}
