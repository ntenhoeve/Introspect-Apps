package nth.software.doc.generator.parser.subparser.html;

import nth.software.doc.generator.model.LineBreak;
import nth.software.doc.generator.parser.SubParser;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.ElementType;
import nth.software.doc.generator.tokenizer.HtmlToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.Token;

public class LineBreakParser implements SubParser<LineBreak> {

	private static final HtmlToken HTML_BR_TOKEN = new HtmlToken(ElementName.BR, ElementType.START_OR_END);

	@Override
	public Token getStartToken() {
		return HTML_BR_TOKEN;
	}

	@Override
	public LineBreak parse(JavaDocTokenizer javaDocTokenizer) {
		return new LineBreak();
	}


}
