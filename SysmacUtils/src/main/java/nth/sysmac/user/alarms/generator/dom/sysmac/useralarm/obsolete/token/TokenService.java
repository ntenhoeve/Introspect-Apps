package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.array.ArrayToken;

public class TokenService {

	public String removeAllTokensFrom(String expression) {
		String result = expression;
		for (TokenDefinition tokenDefinition: TokenDefinitions.ALL) {
			result = tokenDefinition.getRegex().removeAllFrom(result);
		}
		return result;
	}

//	public ComponentCodes createComponentCodes(String expression) {
//		List<ComponentCodeNode> componentCodesWithoutBrackets = findAllComponentCodesWithoutBrackets(expression);
//		List<ComponentCodeNode> componentCodesWithBrackets = findAllComponentCodesWithBrackets(expression);
//		List<Character> componentCodeReferences = findAllComponentCodeReferenses(expression);
//		ComponentCodes componentCodes = new ComponentCodes(componentCodesWithoutBrackets, componentCodesWithBrackets,
//				componentCodeReferences);
//		return componentCodes;
//	}

//	private List<ComponentCodeNode> findAllComponentCodesWithBrackets(String expression) {
//		List<ComponentCodeNode> componentCodeNodes = new ArrayList<>();
//		List<String> tokens = ComponentCodeWithBracketsParser.REGEX.findMatches(expression);
//		ComponentCodeWithBracketsParser componentCodeWithBracketsParser = new ComponentCodeWithBracketsParser();
//		for (String token : tokens) {
//			System.out.println(expression + ":" + token);
//			ComponentCodeNode componentCodeNode = componentCodeWithBracketsParser.parse(token);
//			componentCodeNodes.add(componentCodeNode);
//		}
//		return componentCodeNodes;
//	}

//	private List<ComponentCodeNode> findAllComponentCodesWithoutBrackets(String expression) {
//		List<ComponentCodeNode> componentCodeNodes = new ArrayList<>();
//		List<String> tokens = ComponentCodeWithoutBracketsParser.REGEX.findMatches(expression);
//		ComponentCodeWithoutBracketsParser componentCodeWithoutBracketsParser = new ComponentCodeWithoutBracketsParser();
//		for (String token : tokens) {
//			System.out.println(expression + ":" + token);
//			ComponentCodeNode componentCodeNode = componentCodeWithoutBracketsParser.parse(token);
//			componentCodeNodes.add(componentCodeNode);
//		}
//		return componentCodeNodes;
//	}

	private List<Character> findAllComponentCodeReferenses(String expression) {
		return new ArrayList<>();
	}

	public String replaceArrayTokens(String expression, int arrayIndex) {
		String result = ArrayToken.REGEX.replaceAll(expression, Integer.toString(arrayIndex));
		return result;
	}
}
