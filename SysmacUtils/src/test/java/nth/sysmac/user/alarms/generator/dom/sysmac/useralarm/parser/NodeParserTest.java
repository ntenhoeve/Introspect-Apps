package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser;

import java.util.List;

import org.junit.jupiter.api.Test;

import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.ParseTree;
import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.NodeParserRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;

class NodeParserTest {

	/**
	 * @deprecated to be replaced by DataTypePathConverterTest
	 */
	@Test
	void testParse() {
		NodeParser nodeParser = new NodeParser(NodeParserRules.all());

		TokenParser tokenParser = new TokenParser(TokenRules.all());
		List<Token> tokens = tokenParser.parse(
				"30M5 Scalder 1 pump {U} missing phase {ack}{p=c}{d=Check motor wiring from inverter to motor.}");

		ParseTree parseTree = nodeParser.parse(tokens);
		System.out.println(parseTree);
		
		
	}

}
