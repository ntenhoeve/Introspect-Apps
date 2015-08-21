package nth.software.doc.generator.parser.subparser.html;

import java.util.List;

import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.model.TextBlock;
import nth.software.doc.generator.parser.JavaDocParser;
import nth.software.doc.generator.parser.SubParser;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.ElementType;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.HtmlToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.Token;

/**
 * Parser for HTML element PRE (Predefined fixed width font)
 * 
 * @author nilsth
 *
 */
public class TextBlokParser implements SubParser<TextBlock> {

	private static final HtmlToken HTML_PARAGRAPH_END_TOKEN = new HtmlToken(
			ElementName.P, ElementType.END);
	private static final HtmlToken HTML_PARAGRAPH_START_TOKEN = new HtmlToken(
			ElementName.P, ElementType.START);

	public TextBlock parse(JavaDocTokenizer javaDocTokenizer) {
		List<Node> children = parseContent(javaDocTokenizer);
		TextBlock textBlok = new TextBlock(children);
		//FIXME Endless loop ???
		return textBlok;
	}

	private List<Node> parseContent(JavaDocTokenizer javaDocTokenizer) {
		javaDocTokenizer.nextToken();
		List<FoundToken> contentsTokens = javaDocTokenizer
				.getAllTokensUntil(HTML_PARAGRAPH_END_TOKEN);
		javaDocTokenizer.previousToken();
		JavaDocParser javaDocParser = new JavaDocParser(contentsTokens);
		List<Node> children = javaDocParser.parse();
		return children;
	}


	@Override
	public Token getStartToken() {
		return HTML_PARAGRAPH_START_TOKEN;
	}

}
