package nth.sysmac.user.alarms.generator.dom.sysmac.basetype;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * See https://www.myomron.com/index.php?action=kb&article=1628
 * @author nilsth
 *
 */
public enum OmronBaseType {
	INT("Short"), DINT("Integer"), LINT("Long"), UINT("UShort"), WORD("UShort"), UDINT("UInteger"), DWORD("UInteger"),
	ULINT("Ulong"), LWORD("Ulong"), REAL("Single"), LREAL("Double"), BOOL("Boolean"), STRING("String"), SINT("SByte"),
	USINT("Byte"), BYTE("Byte"), TIME("TimeSpan"), DATE("Date"), DATE_AND_TIME("DateTime"), TIME_OF_DAY("DateTime");

	private final String hmiType;

	private OmronBaseType(String hmiType) {
		this.hmiType = hmiType;
	}
	
	public String getHmiType() {
		return hmiType;
	}



	public static Optional<OmronBaseType> optionalValueOf(String baseTypeExpression) {
		try {
			return Optional.of(OmronBaseType.valueOf(baseTypeExpression));
		} catch (IllegalArgumentException | NullPointerException e) {
			return Optional.empty();
		}
	}

	public static Set<String> hmiTypeValues() {
		Set<String> hmiTypes=new HashSet<>();
		for (OmronBaseType value : values()) {
			hmiTypes.add(value.getHmiType());
		}
		hmiTypes.add("Decimal");
		hmiTypes.add("Char");
		return hmiTypes;
	}
}
