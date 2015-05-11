package nth.software.doc.generator.parser.subparser.tag;

import java.util.List;

import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.model.tag.Tag;
import nth.software.doc.generator.parser.JavaDocParser;
import nth.software.doc.generator.parser.SubParser;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.InlineTagName;
import nth.software.doc.generator.tokenizer.InlineTagToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.TagName;
import nth.software.doc.generator.tokenizer.TagToken;
import nth.software.doc.generator.tokenizer.Token;
import nth.software.doc.generator.tokenizer.TokenFactory;

public  class TagParser implements SubParser<Tag> {

	private final TagName tageName;
	private final TagToken token;

	public TagParser(TagName tagName) {
		this.tageName = tagName;
		this.token=new TagToken(tagName);
	}


	@Override
	public Token getStartToken() {
		return token;
	}

	@Override
	public Tag parse(JavaDocTokenizer javaDocTokenizer) {
		javaDocTokenizer.nextToken();
		Tag javaDocTag = new Tag(tageName) ;
		List<Token> tokens = TokenFactory.createTagTokens();
		tokens.add(new InlineTagToken(InlineTagName.ENDOFFILE));
		List<FoundToken> contentsTokens = javaDocTokenizer.getAllTokensUntil(tokens);
		JavaDocParser javaDocParser=new JavaDocParser(contentsTokens);
		List<Node> children = javaDocParser.parse();
		javaDocTag.getChildren().addAll(children);
		return javaDocTag;
	}

	
	
}
