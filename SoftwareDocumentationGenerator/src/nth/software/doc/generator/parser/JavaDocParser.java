package nth.software.doc.generator.parser;

import java.util.ArrayList;
import java.util.List;

import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;

public class JavaDocParser {
	private final List<SubParser<? extends Node>> subParsers;
	private final JavaDocTokenizer javaDocTokenizer;

	public JavaDocParser(JavaDocTokenizer javaDocTokenizer) {
		this.javaDocTokenizer = javaDocTokenizer;
		this.subParsers = SubParserFactory.create();
	}

	public JavaDocParser(String javaDoc) {
		this(new JavaDocTokenizer(javaDoc));
	}

	public JavaDocParser(List<FoundToken> foundTokens) {
		this(new JavaDocTokenizer(foundTokens));
	}

	public List<Node> parse() {
		ArrayList<Node> nodes = new ArrayList<Node>();

		while (javaDocTokenizer.hasNextToken()) {
			FoundToken foundToken = javaDocTokenizer.nextToken();
			SubParser<?> subParser = findSubParser(foundToken);
			System.out.println(":"+foundToken+":"+((subParser==null)?"NULL":subParser.getClass().getSimpleName()));
			if (subParser != null) {
				Node node = subParser.parse(javaDocTokenizer);
				nodes.add(node);
			}
		}

		return nodes;
	}

	private SubParser<? extends Node> findSubParser(FoundToken foundToken) {
		for (SubParser<? extends Node> subParser : subParsers) {
			if (foundToken.matches(subParser.getStartToken())) {
				// System.out.println(foundToken+";"+subParser.getClass().getSimpleName());
				return subParser;
			}
		}
		return null;
	}

}