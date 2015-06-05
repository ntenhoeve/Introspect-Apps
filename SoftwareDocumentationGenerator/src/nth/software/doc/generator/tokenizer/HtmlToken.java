package nth.software.doc.generator.tokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nth.software.doc.generator.regex.Regex;
import nth.software.doc.generator.regex.Repetition;

public class HtmlToken implements Token {

	private static final String END_ELEMENT_INDICATOR = "/";
	private final ElementName elementName;
	private final Pattern pattern;

	public HtmlToken(ElementName elementName, ElementType beginOrEnd) {
		this.elementName = elementName;
		pattern = createHtmlElementPattern(elementName, beginOrEnd);
	}

	private Pattern createHtmlElementPattern(ElementName elementName,
			ElementType beginOrEnd) {
		Regex attribute = new Regex().whiteSpace(Repetition.oneOrMoreTimes())
				.letters(Repetition.oneOrMoreTimes()).whiteSpace(Repetition.zeroOrMoreTimes()).literal("=").whiteSpace(Repetition.zeroOrMoreTimes())
				.anyCharacter(Repetition.oneOrMoreTimes().reluctant());

		Regex element = new Regex().caseUnsensativeMode().literal("<")
				.whiteSpace(Repetition.zeroOrMoreTimes());
		if (beginOrEnd == ElementType.START_OR_END) {
			element.literal(END_ELEMENT_INDICATOR, Repetition.zeroOrMoreTimes());
		} else if (beginOrEnd == ElementType.END) {
			element.literal(END_ELEMENT_INDICATOR);
		}
		element.whiteSpace(Repetition.zeroOrMoreTimes())
				.literal(elementName.toLowerCase())
				.whiteSpace(Repetition.zeroOrMoreTimes())
				.group(attribute, Repetition.zeroOrMoreTimes())
				.whiteSpace(Repetition.zeroOrMoreTimes()).literal(">");
		return element.asPattern();
	}

	public Pattern getPattern() {
		return pattern;
	}

	public boolean isEndElement(String html) {
		return html.contains(END_ELEMENT_INDICATOR);
	}

	public String getAttribute(String attrubuteName, String element) {
		Regex attributeEnd = new Regex()
				.whiteSpace(Repetition.oneOrMoreTimes()).or().literal(">");
		Pattern pattern = new Regex().caseUnsensativeMode()
				.literal(attrubuteName)
				.whiteSpace(Repetition.zeroOrMoreTimes()).literal("=")
				.anyCharacter(Repetition.oneOrMoreTimes()).group(attributeEnd)
				.asPattern();
		Matcher mather = pattern.matcher(element);
		if (mather.find()) {
			String attribute = mather.group();
			int valuePosition = attribute.indexOf("=");
			String attributeValue = attribute.substring(valuePosition + 1)
					.replaceAll(">$", "").trim().replaceAll("['\"]", "");
			return attributeValue;
		}
		return null;
	}
}
