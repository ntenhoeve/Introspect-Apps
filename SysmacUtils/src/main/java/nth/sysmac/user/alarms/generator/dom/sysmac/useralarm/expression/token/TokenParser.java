package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import nth.reflect.util.regex.Regex;

/**
 * Converting a sequence of characters (such as in a computer program or web
 * page) into a sequence of tokens (strings with an assigned and thus identified
 * meaning). See
 * <a href="https://en.wikipedia.org/wiki/Lexical_analysis">Lexical Analysis</a>
 * 
 * @author nilsth
 *
 */
public class TokenParser {
	private static final Rest REST = new Rest();
	private final List<TokenDefinition> tokenDefintions;

	public TokenParser(List<TokenDefinition> tokenDefintions) {
		this.tokenDefintions = tokenDefintions;
	}

	public List<Token> parse(String expression) {
		List<Token> tokens = new ArrayList<>();

		Token foundToken = findNext(expression);
		tokens.add(foundToken);

		String remainingExpression = getRemainingExpression(expression, foundToken);
		if (!remainingExpression.isEmpty()) {
			List<Token> remainingTokens = parse(remainingExpression);
			tokens.addAll(remainingTokens);
		}

		return tokens;
	}

	private String getRemainingExpression(String expression, Token foundToken) {
		int tokenLength = foundToken.getValue().length();
		String remainingExpression = expression.substring(tokenLength);
		return remainingExpression;
	}

	private Token findNext(String expression) {
		int firstNextIndex = expression.length();

		for (TokenDefinition tokenDefinition : tokenDefintions) {
			Regex regex = tokenDefinition.getRegex();
			Matcher matcher = regex.toMatcher(expression);
			if (matcher.find()) {
				int foundIndex = matcher.start();
				if (foundIndex == 0) {
					String value = matcher.group();
					Token token = new Token(tokenDefinition, value);
					return token;
				} else if (foundIndex < firstNextIndex) {
					firstNextIndex = foundIndex;
				}
			}
		}

		String rest = expression.substring(0, firstNextIndex);
		return new Token(REST, rest);
	}
}
