package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.array.ArrayToken;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.ComponentCode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.ComponentCodeWithBracketsToken;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.ComponentCodeWithoutBracketsToken;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.ComponentCodes;

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
		List<String> tokens = ComponentCodeWithBracketsToken.REGEX.findMatches(expression);
		ComponentCodeWithBracketsToken componentCodeWithBracketsToken = new ComponentCodeWithBracketsToken();
		for (String token : tokens) {
			System.out.println(expression + ":" + token);
			ComponentCode componentCode = componentCodeWithBracketsToken.parse(token);
			componentCodes.add(componentCode);
		}
		return componentCodes;
	}

	private List<ComponentCode> findAllComponentCodesWithoutBrackets(String expression) {
		List<ComponentCode> componentCodes = new ArrayList<>();
		List<String> tokens = ComponentCodeWithoutBracketsToken.REGEX.findMatches(expression);
		ComponentCodeWithoutBracketsToken componentCodeWithoutBracketsToken = new ComponentCodeWithoutBracketsToken();
		for (String token : tokens) {
			System.out.println(expression + ":" + token);
			ComponentCode componentCode = componentCodeWithoutBracketsToken.parse(token);
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
