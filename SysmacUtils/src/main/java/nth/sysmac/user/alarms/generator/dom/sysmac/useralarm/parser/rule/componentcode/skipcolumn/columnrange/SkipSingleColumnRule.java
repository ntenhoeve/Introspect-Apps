package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange;

import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
/**
 * s=2: skips column 2 (of all pages)
 * @see SkipPageColumnRule
 * @author nilsth
 *
 */
public class SkipSingleColumnRule extends SkipColumnRule {

	private static final MatchRules SKIP_SINGLE_COLUMN_ATTRIBUTE_VALUE_RULES = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());

	public SkipSingleColumnRule() {
		super(SKIP_SINGLE_COLUMN_ATTRIBUTE_VALUE_RULES);
	}


	@Override
	protected SkipColumnNode createSkipColumnNode(MatchResults matchResults) {
		int columnNumber = getColumnNumber(matchResults);
		SkipColumnRangeNode skipColumnRangeNode=new SkipColumnRangeNode(columnNumber, columnNumber);
		return skipColumnRangeNode;
	}

	private int getColumnNumber(MatchResults matchResults) {
		List<Node> found = matchResults.getFoundNodes(SKIP_SINGLE_COLUMN_ATTRIBUTE_VALUE_RULES);
		TokenNode tokenNode=(TokenNode) found.get(0);
		int column = Integer.valueOf(tokenNode.getValue());
		return column;
	}

}
