package nth.software.doc.generator.parser.subparser.html;

import java.util.List;

import nth.software.doc.generator.model.Chapter;
import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.parser.JavaDocParser;
import nth.software.doc.generator.parser.SubParser;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.ElementType;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.HtmlToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.Token;
import nth.software.doc.generator.tokenizer.TokenFactory;

public class ChapterParser implements SubParser<Chapter> {

	private static final HtmlToken HTML_H1_END_TOKEN = new HtmlToken(ElementName.H1, ElementType.END);
	private static final HtmlToken HTML_H1_START_TOKEN = new HtmlToken(ElementName.H1, ElementType.START);

	public Chapter parse(JavaDocTokenizer javaDocTokenizer) {
		String title = parseTitle(javaDocTokenizer);
		List<Node> children = parseContent(javaDocTokenizer);
		Chapter chapter = new Chapter(title, children);
		return chapter;
	}

	private List<Node> parseContent(JavaDocTokenizer javaDocTokenizer) {
		javaDocTokenizer.nextToken();
		List<FoundToken> contentsTokens = javaDocTokenizer
				.getAllTokensUntil(HTML_H1_START_TOKEN);
		javaDocTokenizer.previousToken();
		JavaDocParser javaDocParser = new JavaDocParser(contentsTokens);
		List<Node> children = javaDocParser.parse();
		return children;
	}

	private String parseTitle(JavaDocTokenizer javaDocTokenizer) {
		List<FoundToken> titleTokens = javaDocTokenizer
				.getAllTokensUntil(HTML_H1_END_TOKEN);
		String title = TokenFactory.getAsText(titleTokens);
		return title;
	}

	@Override
	public Token getStartToken() {
		return HTML_H1_START_TOKEN;
	}


}
