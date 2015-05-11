package nth.software.doc.generator.tokenizer;

import java.util.regex.Matcher;

public class FoundToken {

	private final String text;
	private final int startPosition;
	private final int endPosition;
	private final Token token;

	public FoundToken(Token token, String text, int startPosition,
			int endPosition) {
		this.token = token;
		this.text = text;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}

	public String getText() {
		return text;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public int getEndPosition() {
		return endPosition;
	}

	public Token getToken() {
		return token;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != FoundToken.class) {
			return false;
		}
		FoundToken foundToken = (FoundToken) obj;
		boolean tokenMatches = foundToken.getToken() == token;
		boolean textMatches = foundToken.getText().equals(text);
		boolean startPositionMatches = foundToken.getStartPosition() == startPosition;
		boolean endPositionMatches = foundToken.getEndPosition() == endPosition;

		return tokenMatches && textMatches && startPositionMatches
				&& endPositionMatches;
	}

	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();
		reply.append(token.getClass().getSimpleName());
		reply.append("; ");
		reply.append(text);
		reply.append("; ");
		reply.append(startPosition);
		reply.append("; ");
		reply.append(endPosition);
		return reply.toString();
	}

	public boolean matches(Token tokenToCompare) {
		if (token.getClass()!=tokenToCompare.getClass()) {
			return false;
		}
		Matcher matcher = tokenToCompare.getPattern().matcher(text);
		return matcher.find();
	}

}
