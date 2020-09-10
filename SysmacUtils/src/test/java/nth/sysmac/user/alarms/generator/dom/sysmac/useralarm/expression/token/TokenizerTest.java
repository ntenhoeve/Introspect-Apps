package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl.CloseBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl.Comma;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl.Dash;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl.Dot;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl.Equal;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl.OpenBrace;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl.TokenDefinitions;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl.UnsignedInteger;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl.WhiteSpace;

class TokenizerTest {

	private static final String REST = "Rest";
	private static final String DASH = "-";
	private static TokenParser tokenParser;
	private static ExpressionsAndTokens expressionsAndTokens;

	@BeforeAll
	static void beforeAll() {
		expressionsAndTokens = new ExpressionsAndTokens();
		expressionsAndTokens.put("}", new CloseBrace());
		expressionsAndTokens.put(",", new Comma());
		expressionsAndTokens.put("-", new Dash());
		expressionsAndTokens.put(".", new Dot());
		expressionsAndTokens.put("=", new Equal());
		expressionsAndTokens.put("{", new OpenBrace());
		expressionsAndTokens.put("1", new UnsignedInteger());
		expressionsAndTokens.put("123", new UnsignedInteger());
		expressionsAndTokens.put(" ", new WhiteSpace());
		expressionsAndTokens.put("\t", new WhiteSpace());

	}

	@ParameterizedTest
	@MethodSource
	void testParse_givenOneValidTokenExpression_returnsCorrectToken(String expression, List<Token> tokens) {
		tokenParser = new TokenParser(TokenDefinitions.all());
		List<Token> actual = tokenParser.parse(expression);
		assertThat(actual).containsAll(tokens);
	}

	static Stream<Arguments> testParse_givenOneValidTokenExpression_returnsCorrectToken() {
		return expressionsAndTokens.createTestArguments();
	}

	@Test
	void testParse_givenUndefinedTokenExpression_returnsRestToken() {
		tokenParser = new TokenParser(TokenDefinitions.all());
		List<Token> actual = tokenParser.parse(REST);
		Token expected = new Token(new Rest(), REST);
		assertThat(actual).containsExactly(expected);
	}

	@Test
	void testParse_givenDashAndUndefinedTokenExpression_returnsDashAndRestToken() {
		tokenParser = new TokenParser(TokenDefinitions.all());
		List<Token> actual = tokenParser.parse(REST + DASH);
		Token restToken = new Token(new Rest(), REST);
		Token dashToken = new Token(new Dash(), DASH);
		assertThat(actual).containsExactly(restToken, dashToken);
	}

	@Test
	void testParse_givenUndefinedTokenAndDashExpression_returnsRestAndDashToken() {
		tokenParser = new TokenParser(TokenDefinitions.all());
		List<Token> actual = tokenParser.parse(DASH + REST);
		Token dashToken = new Token(new Dash(), DASH);
		Token restToken = new Token(new Rest(), REST);
		assertThat(actual).containsExactly(dashToken, restToken);
	}


}
