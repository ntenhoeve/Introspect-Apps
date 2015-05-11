package nth.software.doc.generator.parser.subparser.html;

import java.util.List;

import nth.software.doc.generator.model.Hyperlink;
import nth.software.doc.generator.parser.SubParser;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.ElementType;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.HtmlToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.Token;
import nth.software.doc.generator.tokenizer.TokenFactory;

public class HyperlinkParser implements SubParser<Hyperlink> {

	private static final HtmlToken HTML_A_END_TOKEN = new HtmlToken(ElementName.A, ElementType.END);
	private static final HtmlToken HTML_A_START_TOKEN = new HtmlToken(ElementName.A, ElementType.START);

	@Override
	public Token getStartToken() {
		return HTML_A_START_TOKEN;
	}

	@Override
	public Hyperlink parse(JavaDocTokenizer javaDocTokenizer) {
		String href = parseHref(javaDocTokenizer);
		String text = parseText(javaDocTokenizer);
		Hyperlink hyperlink = new Hyperlink(text, href);
		return hyperlink;
	}

	private String parseText(JavaDocTokenizer javaDocTokenizer) {
		javaDocTokenizer.nextToken();
		List<FoundToken> textTokens = javaDocTokenizer
				.getAllTokensUntil(HTML_A_END_TOKEN);
		String text = TokenFactory.getAsText(textTokens);
		return text;
	}

	private String parseHref(JavaDocTokenizer javaDocTokenizer) {
		FoundToken foundToken = javaDocTokenizer.currentToken();
		String text = foundToken.getText();
		HtmlToken token = (HtmlToken) foundToken.getToken();
		String href = token.getAttribute("href", text);
		return href;
	}

}
