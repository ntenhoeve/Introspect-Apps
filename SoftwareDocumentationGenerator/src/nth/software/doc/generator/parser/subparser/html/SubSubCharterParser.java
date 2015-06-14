package nth.software.doc.generator.parser.subparser.html;

import java.util.List;

import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.model.SubSubChapter;
import nth.software.doc.generator.parser.JavaDocParser;
import nth.software.doc.generator.parser.SubParser;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.ElementType;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.HtmlToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.Token;
import nth.software.doc.generator.tokenizer.TokenFactory;

public class SubSubCharterParser implements SubParser<SubSubChapter> {

	private static final HtmlToken HTML_H3_END_TOKEN = new HtmlToken(ElementName.H3, ElementType.END);
	private static final HtmlToken HTML_H1_START_TOKEN = new HtmlToken(ElementName.H1, ElementType.START);
	private static final HtmlToken HTML_H2_START_TOKEN = new HtmlToken(ElementName.H2, ElementType.START);
	private static final HtmlToken HTML_H3_START_TOKEN = new HtmlToken(ElementName.H3, ElementType.START);

	public SubSubChapter parse(JavaDocTokenizer javaDocTokenizer) {
		String title = parseTitle(javaDocTokenizer);
		List<Node> children = parseContent(javaDocTokenizer);
		SubSubChapter subSubChapter= new SubSubChapter(title, children);
		return subSubChapter;
	}

	private List<Node> parseContent(JavaDocTokenizer javaDocTokenizer) {
		javaDocTokenizer.nextToken();
		List<FoundToken> contentsTokens = javaDocTokenizer
				.getAllTokensUntil(HTML_H1_START_TOKEN, HTML_H2_START_TOKEN, HTML_H3_START_TOKEN);
		javaDocTokenizer.previousToken();	
		JavaDocParser javaDocParser = new JavaDocParser(contentsTokens);
		List<Node> children = javaDocParser.parse();
		return children;
	}

	private String parseTitle(JavaDocTokenizer javaDocTokenizer) {
		List<FoundToken> titleTokens = javaDocTokenizer
				.getAllTokensUntil(HTML_H3_END_TOKEN);
		String title = TokenFactory.getAsText(titleTokens);
		return title;
	}

	@Override
	public Token getStartToken() {
		return HTML_H3_START_TOKEN;
	}


}
