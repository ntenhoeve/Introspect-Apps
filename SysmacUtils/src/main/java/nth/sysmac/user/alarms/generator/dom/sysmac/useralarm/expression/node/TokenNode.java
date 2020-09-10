package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.Token;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.TokenDefinition;

/**
 * A wrapper of a {@link Token} so that it can be put in a {@link ParseTree}. It
 * most likely will be replaced by the {@link NodeParser} by a more complex
 * {@link Node} type
 * 
 * @author nilsth
 *
 */
public class TokenNode extends Node {

	private final Token token;

	public TokenNode(Token token) {
		super(token.toString());
		this.token = token;
	}

	public TokenNode(TokenDefinition tokenDefinition, String value) {
		this(new Token(tokenDefinition, value));
	}

	public TokenDefinition getDefinition() {
		return token.getTokenDefinition();
	}

	public Token getToken() {
		return token;
	}

	@Override
	public boolean equals(Object that) {
		if (!super.equals(that)) {
			return false;
		}
		if (that instanceof TokenNode) {
			TokenNode thatTokenNode = (TokenNode) that;
			boolean equalTokens = token.equals(thatTokenNode.getToken());
			return equalTokens;
		} else {
			return false;
		}
	}

	public String getValue() {
		return token.getValue();
	}

}
