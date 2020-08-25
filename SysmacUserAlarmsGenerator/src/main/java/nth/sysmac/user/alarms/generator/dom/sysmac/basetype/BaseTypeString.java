package nth.sysmac.user.alarms.generator.dom.sysmac.basetype;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BaseTypeString {

	private static final String REG_EXP_FOR_STRING = "^STRING\\[[0-9]+]";
	private final int length;

	public BaseTypeString(String baseTypeExpression) {
		length = getInt(baseTypeExpression, "^STRING\\[([0-9]+)\\]");
	}

	public int getLength() {
		return length;
	}


	private static int getInt(String baseTypeExpression, String regExp) {
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(baseTypeExpression);
		if (matcher.find()) {
		    String found = matcher.group(1);
		    return Integer.valueOf(found);
		}
		throw new RuntimeException("Could not find length value of a string in expression: "+baseTypeExpression);
	}


	public static Optional<BaseTypeString> create(String baseTypeExpression) {
		if (baseTypeExpression.matches(REG_EXP_FOR_STRING)) {
			return Optional.of(new BaseTypeString(baseTypeExpression));
		}
		return Optional.empty();
	}


	public static String remove(String baseTypeExpression) {
		String result = baseTypeExpression.replaceAll(REG_EXP_FOR_STRING, "");
		return result;
	}


	
}
