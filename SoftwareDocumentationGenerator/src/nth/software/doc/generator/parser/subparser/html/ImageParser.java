package nth.software.doc.generator.parser.subparser.html;

import nth.software.doc.generator.model.Image;
import nth.software.doc.generator.parser.SubParser;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.ElementType;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.HtmlToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.Token;

public class ImageParser implements SubParser<Image> {

	private static final HtmlToken HTML_IMAGE_START_TOKEN = new HtmlToken(ElementName.IMG, ElementType.START);

	public Image parse(JavaDocTokenizer javaDocTokenizer) {
		String src = parseSrc(javaDocTokenizer);
		Image image= new Image(src);
		return image;
	}

	private String parseSrc(JavaDocTokenizer javaDocTokenizer) {
		FoundToken foundToken = javaDocTokenizer.currentToken();
		String text = foundToken.getText();
		HtmlToken token = (HtmlToken) foundToken.getToken();
		String src = token.getAttribute("src", text);
		return src;
	}

	
	@Override
	public Token getStartToken() {
		return HTML_IMAGE_START_TOKEN;
	}


}
