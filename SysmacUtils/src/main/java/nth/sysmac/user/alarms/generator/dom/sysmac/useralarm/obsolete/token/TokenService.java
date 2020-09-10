package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.array.ArrayToken;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.ComponentCode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.ComponentCodeWithBracketsParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.ComponentCodeWithoutBracketsParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.ComponentCodes;

public class TokenService {

	public String removeAllTokensFrom(String expression) {
		String result = expression;
		for (TokenDefinition tokenDefinition: TokenDefinitions.ALL) {
			result = tokenDefinition.getRegex().removeAllFrom(result);
		}
		return result;
	}

	public ComponentCodes createComponentCodes(String expression) {
		List<ComponentCode> componentCodesWithoutBrackets = findAllComponentCodesWithoutBrackets(expression);
		List<ComponentCode> componentCodesWithBrackets = findAllComponentCodesWithBrackets(expression);
		List<Character> componentCodeReferences = findAllComponentCodeReferenses(expression);
		ComponentCodes componentCodes = new ComponentCodes(componentCodesWithoutBrackets, componentCodesWithBrackets,
				componentCodeReferences);
		return componentCodes;
	}

	private List<ComponentCode> findAllComponentCodesWithBrackets(String expression) {
		List<ComponentCode> componentCodes = new ArrayList<>();
		List<String> tokens = ComponentCodeWithBracketsParser.REGEX.findMatches(expression);
		ComponentCodeWithBracketsParser componentCodeWithBracketsParser = new ComponentCodeWithBracketsParser();
		for (String token : tokens) {
			System.out.println(expression + ":" + token);
			ComponentCode componentCode = componentCodeWithBracketsParser.parse(token);
			componentCodes.add(componentCode);
		}
		return componentCodes;
	}

	private List<ComponentCode> findAllComponentCodesWithoutBrackets(String expression) {
		List<ComponentCode> componentCodes = new ArrayList<>();
		List<String> tokens = ComponentCodeWithoutBracketsParser.REGEX.findMatches(expression);
		ComponentCodeWithoutBracketsParser componentCodeWithoutBracketsParser = new ComponentCodeWithoutBracketsParser();
		for (String token : tokens) {
			System.out.println(expression + ":" + token);
			ComponentCode componentCode = componentCodeWithoutBracketsParser.parse(token);
			componentCodes.add(componentCode);
		}
		return componentCodes;
	}

	private List<Character> findAllComponentCodeReferenses(String expression) {
		return new ArrayList<>();
	}

	public String replaceArrayTokens(String expression, int arrayIndex) {
		String result = ArrayToken.REGEX.replaceAll(expression, Integer.toString(arrayIndex));
		return result;
	}
}
