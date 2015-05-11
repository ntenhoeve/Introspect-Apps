package nth.software.doc.generator.parser.subparser.html;

import nth.software.doc.generator.model.ListItem;
import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.parser.JavaDocParser;
import nth.software.doc.generator.parser.SubParser;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.ElementType;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.HtmlToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.Token;

public class ListItemParser implements SubParser<ListItem> {

	private static final HtmlToken HTML_LI_END_TOKEN = new HtmlToken(ElementName.LI, ElementType.END);
	private static final HtmlToken HTML_LI_START_TOKEN = new HtmlToken(ElementName.LI, ElementType.START);

	public ListItem parse(JavaDocTokenizer javaDocTokenizer) {
		java.util.List<Node> children = parseContent(javaDocTokenizer);
		ListItem listItem = new ListItem(children);
		return listItem;
	}

	private java.util.List<Node> parseContent(JavaDocTokenizer javaDocTokenizer) {
		javaDocTokenizer.nextToken();
		java.util.List<FoundToken> contentsTokens = javaDocTokenizer
				.getAllTokensUntil(HTML_LI_END_TOKEN);
		JavaDocParser javaDocParser = new JavaDocParser(contentsTokens);
		java.util.List<Node> children = javaDocParser.parse();
		return children;
	}


	@Override
	public Token getStartToken() {
		return HTML_LI_START_TOKEN;
	}


}
