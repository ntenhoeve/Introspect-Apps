package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token;

/**
 * A lexical {@link Token} or simply {@link Token} is a string with an assigned
 * and thus identified meaning. It is structured as a pair consisting of a
 * {@link TokenDefinition} and an optional token value.
 * 
 * @author nilsth
 *
 */
public class Token {
	private final TokenDefinition tokenDefinition;
	private final String value;

	public Token(TokenDefinition tokenDefinition, String value) {
		super();
		this.tokenDefinition = tokenDefinition;
		this.value = value;

	}

	public TokenDefinition getTokenDefinition() {
		return tokenDefinition;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object that) {
		if (that == null) {
			return false;
		}
		if (this.getClass() != that.getClass()) {
			return false;
		}
		Token thatToken = (Token) that;
		if (!this.getValue().equals(thatToken.getValue())) {
			return false;
		}
		if (this.getTokenDefinition().getClass() != thatToken.getTokenDefinition().getClass()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return toShortString();
	}

	public String toShortString() {
		if (tokenDefinition.hasValue()) {
			return "[" + value + "]";
		} else {
			return "{" + tokenDefinition.getClass().getSimpleName() + "}";
		}
	}
	
	public String toLongString() {
		StringBuilder reply=new StringBuilder();
		reply.append(Token.class.getSimpleName());
		reply.append(".");
		reply.append(tokenDefinition.getClass().getSimpleName());
		if (tokenDefinition.hasValue()) {
			reply.append(" = ");
			reply.append(value);
		} 
		return reply.toString();
	}

}
