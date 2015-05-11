package nth.software.doc.generator.tokenizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaDocTokenizer {

	private static final String NOTHING = "";
	private static final String SINGLE_SPACE = " ";
	private static final String DOUBLE_SPACE = "  ";
	private static final String NEW_LINE = "\n";
	private static final String RETURN = "\r";
	private static final Token TEXT_TOKEN = new TextToken();
	private List<FoundToken> foundTokens;
	int currentIndex = -1;

	public JavaDocTokenizer(String javaDoc) {
		javaDoc = removeUneededWhiteSpaces(javaDoc);
		List<Token> tokens = TokenFactory.create();
		foundTokens = findTokens(javaDoc, tokens);
	}

	public JavaDocTokenizer(List<FoundToken> foundTokens) {
		this.foundTokens = foundTokens;
	}

	private String removeUneededWhiteSpaces(String javaDoc) {
		javaDoc = javaDoc.replace(RETURN, NOTHING);
		javaDoc = javaDoc.replace(NEW_LINE, SINGLE_SPACE);
		javaDoc = javaDoc.replace(DOUBLE_SPACE, SINGLE_SPACE);
		return javaDoc;
	}

	public FoundToken nextToken() {
		if (hasNextToken()) {
			FoundToken foundToken = foundTokens.get(++currentIndex);
			return foundToken;
		} else {
			return null;
		}
	}

	public FoundToken previousToken() {
		FoundToken foundToken = foundTokens.get(--currentIndex);
		return foundToken;
	}

	public boolean hasNextToken() {
		return currentIndex < foundTokens.size() - 1;
	}

	private List<FoundToken> findTokens(String javaDoc, List<Token> tokens) {
		List<FoundToken> foundTokens = new ArrayList<>();
		for (Token token : tokens) {
			Pattern pattern = token.getPattern();
			Matcher matcher = pattern.matcher(javaDoc);
			while (matcher.find()) {
				String text = matcher.group();
				int startPosition = matcher.start();
				int endPosition = matcher.end();
				FoundToken foundToken = new FoundToken(token, text,
						startPosition, endPosition);
				foundTokens.add(foundToken);
			}
		}
		sortFoundTokens(foundTokens);
		List<FoundToken> foundTextTokens = createTextTokensForRemainingJavaDoc(
				foundTokens, javaDoc);
		foundTokens.addAll(foundTextTokens);
		sortFoundTokens(foundTokens);
		return foundTokens;
	}

	private List<FoundToken> createTextTokensForRemainingJavaDoc(
			List<FoundToken> foundTokens, String javaDoc) {
		List<FoundToken> foundTextTokens = new ArrayList<>();
		int beginPosition = 0;
		for (FoundToken foundToken : foundTokens) {
			int endPosition = foundToken.getStartPosition();
			if (beginPosition < endPosition) {
				String text = javaDoc.substring(beginPosition, endPosition);
				FoundToken foundTextToken = new FoundToken(TEXT_TOKEN, text,
						beginPosition, endPosition);
				foundTextTokens.add(foundTextToken);
			}
			beginPosition = foundToken.getEndPosition();
		}
		return foundTextTokens;
	}

	private void sortFoundTokens(List<FoundToken> foundTokens) {
		Comparator<FoundToken> comparator = createComparator();
		Collections.sort(foundTokens, comparator);
	}

	private Comparator<FoundToken> createComparator() {
		return new Comparator<FoundToken>() {

			@Override
			public int compare(FoundToken t1, FoundToken t2) {
				int startPos1 = t1.getStartPosition();
				int startPos2 = t2.getStartPosition();
				return Integer.compare(startPos1, startPos2);
			}
		};
	}

	public void goToNext(Token tokenToFind) {
		currentIndex = findNextToken(tokenToFind);
	}

	private int findNextToken(Token tokenToFind) {
		for (int index = currentIndex; index < foundTokens.size(); index++) {
			FoundToken foundToken = foundTokens.get(index);
			if (foundToken.matches(tokenToFind)) {
				return index;
			}
		}
		return foundTokens.size() - 1;
	}

	public List<FoundToken> getAllTokensUntil(Token token) {
		List<Token> tokens = new ArrayList<>();
		tokens.add(token);
		return getAllTokensUntil(tokens);
	}

	public List<FoundToken> getAllTokensUntil(Token... tokens) {
		List<Token> tokenList = new ArrayList<>();
		for (Token token : tokens) {
			tokenList.add(token);
		}
		return getAllTokensUntil(tokenList);
	}

	public List<FoundToken> getAllTokensUntil(List<Token> tokens) {
		List<FoundToken> result = new ArrayList<>();
		int beginIndex = currentIndex;
		int endIndex = findFirstToken(tokens);

		for (int index = beginIndex; index < endIndex; index++) {
			FoundToken foundToken = foundTokens.get(index);
			result.add(foundToken);
		}
		currentIndex = endIndex;
		return result;
	}

	private int findFirstToken(List<Token> tokens) {
		int firstIndex = foundTokens.size() - 1;
		for (Token token : tokens) {
			int index = findFirstToken(token);
			if (index < firstIndex) {
				firstIndex = index;
			}
		}
		return firstIndex;
	}

	private int findFirstToken(Token token) {
		for (int index = currentIndex; index < foundTokens.size(); index++) {
			FoundToken foundToken = foundTokens.get(index);
			if (foundToken.matches(token)) {
				return index;
			}
		}
		return foundTokens.size() - 1;
	}

	public FoundToken currentToken() {
		return foundTokens.get(currentIndex);
	}

}
