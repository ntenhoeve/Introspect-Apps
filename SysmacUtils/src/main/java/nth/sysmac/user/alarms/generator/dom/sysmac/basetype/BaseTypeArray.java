package nth.sysmac.user.alarms.generator.dom.sysmac.basetype;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;

public class BaseTypeArray {

	private static final Regex NUMBER = new Regex().digit(Repetition.oneOrMoreTimes());
	private static final Regex REGEX_FOR_RANGE = new Regex().append(NUMBER).literal("..").append(NUMBER);
	private static final Regex REGEX_FOR_RANGE_MIN = new Regex().group(NUMBER).literal("..").append(NUMBER);
	private static final Regex REGEX_FOR_RANGE_MAX = new Regex().append(NUMBER).literal("..").group(NUMBER);
	private static final Regex REGEX_FOR_REPEATED_RANGE = new Regex().literal(",").append(REGEX_FOR_RANGE);
	private static final Regex REGEX_FOR_ARRAY = new Regex()//
			.beginOfLine()//
			.literal("ARRAY[")//
			.append(REGEX_FOR_RANGE)//
			.group(REGEX_FOR_REPEATED_RANGE, Repetition.zeroOrMoreTimes())//
			.literal("]")//
			.whiteSpace()//
			.literal("OF")//
			.whiteSpace();
	public static final Regex REGEX_FOR_BASETYPE_EXPRESSION_WITH_ARRAY = new Regex().append(REGEX_FOR_ARRAY)
			.anyCharacter(Repetition.zeroOrMoreTimes());

	private final List<BaseTypeArrayRange> arrayRanges;

	public BaseTypeArray(String baseTypeExpression) {
		arrayRanges = createArrayRanges(baseTypeExpression);
		setListeners();
	}

	private void setListeners() {
		for (int index = 0; index < arrayRanges.size() - 1; index++) {
			BaseTypeArrayRange range = arrayRanges.get(index);
			BaseTypeArrayRange nextRange = arrayRanges.get(index + 1);
			range.addListener(nextRange);
		}
	}

	private List<BaseTypeArrayRange> createArrayRanges(String baseTypeExpression) {
		List<BaseTypeArrayRange> arrayRanges = new ArrayList<>();
		List<String> ranges = REGEX_FOR_RANGE.findMatches(baseTypeExpression);
		for (int index = 0; index < ranges.size(); index++) {
			String range = ranges.get(ranges.size() - 1 - index);
			arrayRanges.add(createRange(range, index));
		}
		return arrayRanges;
	}

	private BaseTypeArrayRange createRange(String range, int index) {
		int min = getInt(range, REGEX_FOR_RANGE_MIN);
		int max = getInt(range, REGEX_FOR_RANGE_MAX);
		return new BaseTypeArrayRange(min, max, index);
	}

	private static int getInt(String expression, Regex regex) {
		List<String> results = regex.findGroups(expression);
		if (results.size() == 2) {
			String number = results.get(1);
			return Integer.valueOf(number);
		}
		throw new RuntimeException("Could not find length value of a string in expression: " + expression);
	}

	public static Optional<BaseTypeArray> create(String baseTypeExpression) {
		if (REGEX_FOR_ARRAY.hasMatchIn(baseTypeExpression)) {
			return Optional.of(new BaseTypeArray(baseTypeExpression));
		}
		return Optional.empty();
	}

	public boolean canGoToNext() {
		for (BaseTypeArrayRange baseTypeArrayRange : arrayRanges) {
			if (baseTypeArrayRange.canGoToNext()) {
				return true;
			}
		}
		return false;// none of the baseTypeArrayRange's can go to next
	}

	public void goToNext() {
		if (!arrayRanges.isEmpty()) {
			arrayRanges.get(0).goToNext(0);
		}
	}

	public static String remove(String baseTypeExpression) {
		String result = REGEX_FOR_ARRAY.removeAllFrom(baseTypeExpression);
		return result;
	}

	public String getValue() {
		StringBuilder result=new StringBuilder();
		result.append("(");
		for (int index = arrayRanges.size()-1; index >= 0; index--) {
			if (result.length()>1) {
				result.append(",");
			}
			BaseTypeArrayRange baseTypeArrayRange = arrayRanges.get(index);
			String arrayValue=baseTypeArrayRange.getValue();
			result.append(arrayValue);
		}
		result.append(")");
		return result.toString();
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
}
