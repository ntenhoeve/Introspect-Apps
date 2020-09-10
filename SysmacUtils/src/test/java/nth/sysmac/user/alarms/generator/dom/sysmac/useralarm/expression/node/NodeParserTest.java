package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;

import java.util.List;

import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.impl.NodeReplacements;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.Token;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.rule.TokenRules;

class NodeParserTest {

	@Test
	void testParse() {
		NodeParser nodeParser = new NodeParser(NodeReplacements.all());

		TokenParser tokenParser = new TokenParser(TokenRules.all());
		List<Token> tokens = tokenParser.parse(
				"30M5 Scalder 1 pump {U} missing phase {ack}{p=critical}{d=Check motor wiring from inverter to motor.}");

		ParseTree parseTree = nodeParser.parse(tokens);
		System.out.println(parseTree);
	}

}
