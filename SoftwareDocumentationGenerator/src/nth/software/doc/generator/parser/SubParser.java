package nth.software.doc.generator.parser;

import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;
import nth.software.doc.generator.tokenizer.Token;

public interface SubParser<T extends Node> {

	public Token getStartToken();

	public T parse(JavaDocTokenizer javaDocTokenizer);

}
