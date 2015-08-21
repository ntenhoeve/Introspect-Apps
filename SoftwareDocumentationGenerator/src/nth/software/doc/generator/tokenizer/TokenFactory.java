package nth.software.doc.generator.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class TokenFactory {

	public static final Token TEXT_TOKEN = new TextToken();

	public static List<Token> create() {
		List<Token> tokens = new ArrayList<>();
		tokens.addAll(createHtmlTokens());
		tokens.addAll(createTagTokens());
		tokens.addAll(createInlineTagTokens());
		return tokens;
	}


	public static List<Token> createInlineTagTokens() {
		List<Token> tokens = new ArrayList<>();
		for (InlineTagName inlineTagName:InlineTagName.values()) {
			tokens.add(new InlineTagToken(inlineTagName)); 		
		}
		return tokens;
	}

	public static List<Token> createTagTokens() {
		List<Token> tokens = new ArrayList<>();
		for (TagName tagName:TagName.values()) {
			tokens.add(new TagToken(tagName));
		}
		return tokens;
	}

	private static List<Token> createHtmlTokens() {
		List<Token> tokens = new ArrayList<>();
		for (ElementName elementName:ElementName.values()) {
			tokens.add(new HtmlToken(elementName, ElementType.START_OR_END));
		}
		return tokens;
	}

	public static String getAsText(List<FoundToken> foundTokenes) {
		StringBuilder title = new StringBuilder();
		for (FoundToken foundToken : foundTokenes) {
			if (foundToken.matches(TokenFactory.TEXT_TOKEN)) {
				title.append(foundToken.getText());
			}
		}
		return title.toString();
	}


}
