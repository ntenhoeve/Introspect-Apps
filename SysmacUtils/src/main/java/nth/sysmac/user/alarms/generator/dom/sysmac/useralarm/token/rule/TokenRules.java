package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.util.parser.token.parser.TokenRule;

public enum TokenRules {

	CLOSE_BRACE(new CloseBrace()),
	COMMA(new Comma()),
	DASH(new Dash()),
	DOT(new Dot()),
	EQUAL(new Equal()),
	OPEN_BRACE(new OpenBrace()),
	UNSIGNED_INTEGER(new UnsignedInteger()),
	WHITESPACE(new WhiteSpace());

	private final TokenRule tokenRule;

	TokenRules(TokenRule tokenRule) {
		this.tokenRule = tokenRule;
	}

	public TokenRule get() {
		return tokenRule;
	}

	public static List<TokenRule> all() {
		return Arrays.asList(values()).stream().map(TokenRules::get).collect(Collectors.toUnmodifiableList());
	}

}
