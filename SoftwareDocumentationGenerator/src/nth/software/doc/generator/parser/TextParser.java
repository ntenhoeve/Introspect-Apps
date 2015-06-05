package nth.software.doc.generator.parser;

import nth.software.doc.generator.model.Text;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.TextToken;
import nth.software.doc.generator.tokenizer.Token;

public class TextParser implements SubParser<Text> {

	private static final Token TEXT_TOKEN = new TextToken();

	@Override
	public Token getStartToken() {
		return TEXT_TOKEN;
	}

	@Override
	public Text parse(JavaDocTokenizer javaDocTokenizer) {
		FoundToken foundToken=javaDocTokenizer.currentToken();
		String text = foundToken.getText();
		return new Text(text);
	}


}
