package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange;

import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
/**
 * s=-5: skips columns 1 until 5 (of all pages)
 * @see SkipColumnRule
 * @author nilsth
 *
 */
public class SkipMaxColumnRule extends SkipColumnRule {

	private static final MatchRules MAX_COLUMN_NUMBER = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());
	
	private static final MatchRules SKIP_MAX_COLUMN_ATTRIBUTE_VALUE_RULES = new MatchRules()//
			.add(TokenNodePredicate.dash())//
			.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore())//
			.add(MAX_COLUMN_NUMBER);

	public SkipMaxColumnRule() {
		super(SKIP_MAX_COLUMN_ATTRIBUTE_VALUE_RULES);
	}


	@Override
	protected SkipColumnNode createSkipColumnNode(MatchResults matchResults) {
		int columnNumber = getColumnNumber(matchResults);
		SkipColumnRangeNode skipColumnRangeNode=new SkipColumnRangeNode(1, columnNumber);
		return skipColumnRangeNode;
	}

	private int getColumnNumber(MatchResults matchResults) {
		List<Node> nodes = matchResults.getNodes();
		NodeMatcher nodeMatcher=new NodeMatcher(SKIP_MAX_COLUMN_ATTRIBUTE_VALUE_RULES);
		MatchResults results=nodeMatcher.match(nodes);
		List<Node> found = results.getFoundNodes(MAX_COLUMN_NUMBER);
		TokenNode tokenNode=(TokenNode) found.get(0);
		int column = Integer.valueOf(tokenNode.getValue());
		return column;
	}

}
