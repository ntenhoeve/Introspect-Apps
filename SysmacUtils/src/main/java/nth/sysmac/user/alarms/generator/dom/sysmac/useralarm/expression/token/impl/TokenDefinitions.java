package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.TokenDefinition;

public enum TokenDefinitions {

	CLOSE_BRACE(new CloseBrace()),
	COMMA(new Comma()),
	DASH(new Dash()),
	DOT(new Dot()),
	EQUAL(new Equal()),
	OPEN_BRACE(new OpenBrace()),
	UNSIGNED_INTEGER(new UnsignedInteger()),
	WHITESPACE(new WhiteSpace());

	private final TokenDefinition tokenDefinition;

	TokenDefinitions(TokenDefinition tokenDefinition) {
		this.tokenDefinition = tokenDefinition;
	}

	public TokenDefinition get() {
		return tokenDefinition;
	}

	public static List<TokenDefinition> all() {
		return Arrays.asList(values()).stream().map(TokenDefinitions::get).collect(Collectors.toUnmodifiableList());
	}

}
