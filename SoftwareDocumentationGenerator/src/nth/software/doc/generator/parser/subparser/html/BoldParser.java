package nth.software.doc.generator.parser.subparser.html;

import java.util.List;

import nth.software.doc.generator.model.Bold;
import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.parser.JavaDocParser;
import nth.software.doc.generator.parser.SubParser;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.ElementType;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.HtmlToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.Token;

public class BoldParser implements SubParser<Bold> {

	private static final HtmlToken HTML_BOLD_END_TOKEN = new HtmlToken(ElementName.B, ElementType.END);
	private static final HtmlToken HTML_BOLD_START_TOKEN = new HtmlToken(ElementName.B, ElementType.START);

	public Bold parse(JavaDocTokenizer javaDocTokenizer) {
		List<Node> children = parseContent(javaDocTokenizer);
		Bold bold = new Bold(children);
		return bold;
	}

	private java.util.List<Node> parseContent(JavaDocTokenizer javaDocTokenizer) {
		javaDocTokenizer.nextToken();
		java.util.List<FoundToken> contentsTokens = javaDocTokenizer
				.getAllTokensUntil(HTML_BOLD_END_TOKEN);
		JavaDocParser javaDocParser = new JavaDocParser(contentsTokens);
		java.util.List<Node> children = javaDocParser.parse();
		return children;
	}


	@Override
	public Token getStartToken() {
		return HTML_BOLD_START_TOKEN;
	}


}
