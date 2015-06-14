package nth.software.doc.generator.parser;

import java.util.ArrayList;
import java.util.List;

import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.parser.subparser.html.BoldParser;
import nth.software.doc.generator.parser.subparser.html.ChapterParser;
import nth.software.doc.generator.parser.subparser.html.HyperlinkParser;
import nth.software.doc.generator.parser.subparser.html.ImageParser;
import nth.software.doc.generator.parser.subparser.html.LineBreakParser;
import nth.software.doc.generator.parser.subparser.html.ListItemParser;
import nth.software.doc.generator.parser.subparser.html.ListParser;
import nth.software.doc.generator.parser.subparser.html.SubChapterParser;
import nth.software.doc.generator.parser.subparser.html.TextWithFixedWidthFontParser;
import nth.software.doc.generator.parser.subparser.html.SubSubCharterParser;
import nth.software.doc.generator.parser.subparser.html.UnderlineParser;
import nth.software.doc.generator.parser.subparser.tag.TagParser;
import nth.software.doc.generator.parser.subparser.tag.inline.InlineTagParser;
import nth.software.doc.generator.tokenizer.InlineTagName;
import nth.software.doc.generator.tokenizer.TagToken;
import nth.software.doc.generator.tokenizer.Token;
import nth.software.doc.generator.tokenizer.TokenFactory;

public class SubParserFactory {

	public static List<SubParser<? extends Node>> create() {

		ArrayList<SubParser<? extends Node>> subParsers = new ArrayList<SubParser<? extends Node>>();
		subParsers.addAll(createHtmlParsers());
		subParsers.addAll(createInlineTagParsers());
		subParsers.addAll(createTagParsers());
		subParsers.add(new TextParser());
		return subParsers;
	}

	private static List<SubParser<? extends Node>> createInlineTagParsers() {
		List<SubParser<? extends Node>> subParsers = new ArrayList<SubParser<? extends Node>>();
for (InlineTagName inlineTagToken:InlineTagName.values()) {
	InlineTagParser inlineTagParser = new InlineTagParser(inlineTagToken); 
	subParsers.add(inlineTagParser); 
} 		
		return subParsers;
	}

	private static List<SubParser<? extends Node>> createTagParsers() {
		List<SubParser<? extends Node>> subParsers = new ArrayList<SubParser<? extends Node>>();
		List<Token> tagTokens = TokenFactory.createTagTokens();
		for (Token token : tagTokens) {//TODO use ENUM
			TagToken tagToken=(TagToken) token;
			TagParser tagParser = new TagParser(tagToken.getTagName()); 
			subParsers.add(tagParser); 
		}
		return subParsers;
	}

	private static List<SubParser<? extends Node>> createTextBlokParsers() {
		List<SubParser<? extends Node>> subParsers = new ArrayList<SubParser<? extends Node>>();
		subParsers.add(new ChapterParser());
		subParsers.add(new SubChapterParser());
		subParsers.add(new SubSubCharterParser());
		subParsers.add(new TextWithFixedWidthFontParser());
		return subParsers;
	}

	private static List<SubParser<? extends Node>> createHtmlParsers() {
		List<SubParser<? extends Node>> subParsers = new ArrayList<SubParser<? extends Node>>();
		subParsers.addAll(createTextBlokParsers());
		subParsers.add(new HyperlinkParser());
		subParsers.add(new LineBreakParser());
		subParsers.add(new ListParser());
		subParsers.add(new ListItemParser());
		subParsers.add(new BoldParser());
		subParsers.add(new UnderlineParser());
		subParsers.add(new ImageParser());
		return subParsers;
	}

}
