package nth.sysmac.user.alarms.generator.dom.sysmac.basetype;

import java.util.Optional;

public class BaseType {

	public static final String ENUM = "ENUM";
	public static final String STRUCT = "STRUCT";
	private final Optional<BaseTypeArray> array;
	private final Optional<BaseTypeString> string;
	private final boolean isStruct;
	private final boolean isEnum;
	private final boolean isOmronHmiType;
	private final Optional<OmronBaseType> omronBaseType;
	private final Optional<String> reference;

	public BaseType(String baseTypeExpression) {
		if (baseTypeExpression==null) {
			baseTypeExpression="";
		}
		array = BaseTypeArray.create(baseTypeExpression);
		baseTypeExpression=BaseTypeArray.remove(baseTypeExpression);
		
		string=BaseTypeString.create(baseTypeExpression);
		baseTypeExpression=BaseTypeString.remove(baseTypeExpression);
		
		omronBaseType = OmronBaseType.optionalValueOf( baseTypeExpression);
		
		isOmronHmiType=OmronBaseType.hmiTypeValues().contains(baseTypeExpression);
		
		isStruct=STRUCT.equals(baseTypeExpression);
		
		isEnum=ENUM.equals(baseTypeExpression);
		
		reference = getReference(baseTypeExpression);
	}

	private Optional<String> getReference(String baseTypeExpression) {
		boolean isReference=string.isEmpty() &&  omronBaseType.isEmpty() && !isStruct && !isEnum && !baseTypeExpression.isBlank() && !isOmronHmiType;
		if (isReference) {
			return Optional.of(baseTypeExpression);
		}
		return Optional.empty();
	}

	public Optional<BaseTypeArray> getArray() {
		return array;
	}

	
	public Optional<BaseTypeString> getString() {
		return string;
	}

	public boolean isStruct() {
		return isStruct;
	}

	public boolean isEnum() {
		return isEnum;
	}

	public Optional<OmronBaseType> getOmronType() {
		return omronBaseType;
	}

	public Optional<String> getReference() {
		return reference;
	}

	public boolean isOmronHmiType() {
		return isOmronHmiType;
	}
	
	
}
