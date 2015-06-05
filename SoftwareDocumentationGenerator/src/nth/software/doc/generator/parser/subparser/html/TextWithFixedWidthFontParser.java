package nth.software.doc.generator.parser.subparser.html;

import java.util.List;

import nth.software.doc.generator.model.Chapter;
import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.model.TextWithFixedWidthFont;
import nth.software.doc.generator.parser.JavaDocParser;
import nth.software.doc.generator.parser.SubParser;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.ElementType;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.HtmlToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.Token;
import nth.software.doc.generator.tokenizer.TokenFactory;

/**
 * Parser for HTML element PRE (Predefined fixed width font)
 * @author nilsth
 *
 */
public class TextWithFixedWidthFontParser implements SubParser<TextWithFixedWidthFont> {

	
	private static final HtmlToken HTML_PRE_END_TOKEN = new HtmlToken(ElementName.PRE, ElementType.END);
	private static final HtmlToken HTML_PRE_START_TOKEN = new HtmlToken(ElementName.PRE, ElementType.START);

	public TextWithFixedWidthFont parse(JavaDocTokenizer javaDocTokenizer) {
		String text = parseContent(javaDocTokenizer);
		TextWithFixedWidthFont textWithFixedWidthFont=new TextWithFixedWidthFont(text);
		return textWithFixedWidthFont;
	}

	private String parseContent(JavaDocTokenizer javaDocTokenizer) {
		List<FoundToken> titleTokens = javaDocTokenizer 
				//TODO How do we prevent indentation being lost?
				.getAllTokensUntil(HTML_PRE_END_TOKEN);
		String text = TokenFactory.getAsText(titleTokens);
		return text;
	}


	@Override
	public Token getStartToken() {
		return HTML_PRE_START_TOKEN;
	}


}
