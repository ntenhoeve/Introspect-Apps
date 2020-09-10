package nth.sysmac.user.alarms.generator.dom.sysmac.basetype;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseTypeArray {

	private static final String REG_EXP_FOR_ARRAY = "^ARRAY\\[[0-9]+\\.\\.[0-9]+.*\\]\\sOF\\s";
	private static final String REG_EXP_FOR_EXPRESSION_WITH_ARRAY = REG_EXP_FOR_ARRAY + ".*";

	private final int min;
	private final int max;

	public BaseTypeArray(String baseTypeExpression) {
		min = getInt(baseTypeExpression, "^ARRAY\\[([0-9]+)\\.\\.[0-9]+.*\\]\\sOF\\s");
		max = getInt(baseTypeExpression, "^ARRAY\\[[0-9]+\\.\\.([0-9]+).*\\]\\sOF\\s");
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	private static int getInt(String baseTypeExpression, String regExp) {
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(baseTypeExpression);
		if (matcher.find()) {
			String found = matcher.group(1);
			return Integer.valueOf(found);
		}
		throw new RuntimeException("Could not find length value of a string in expression: " + baseTypeExpression);
	}

	public static Optional<BaseTypeArray> create(String baseTypeExpression) {
		if (baseTypeExpression.matches(REG_EXP_FOR_EXPRESSION_WITH_ARRAY)) {
			return Optional.of(new BaseTypeArray(baseTypeExpression));
		}
		return Optional.empty();
	}

	public static String remove(String baseTypeExpression) {
		String result = baseTypeExpression.replaceAll(REG_EXP_FOR_ARRAY, "");
		return result;
	}

}
