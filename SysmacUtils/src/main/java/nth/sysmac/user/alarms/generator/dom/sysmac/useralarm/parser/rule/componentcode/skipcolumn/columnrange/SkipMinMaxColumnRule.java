package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange;

import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
/**
 * s=3-5: skips columns 3 until 5 (of all pages)
 * @see SkipPageColumnRule
 * @author nilsth
 *
 */
public class SkipMinMaxColumnRule extends SkipColumnRule {

	private static final MatchRules MIN_COLUMN_NUMBER = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());
	private static final MatchRules MAX_COLUMN_NUMBER = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());
	
	private static final MatchRules SKIP_MIN_MAX_COLUMN_ATTRIBUTE_VALUE_RULES = new MatchRules()//
			.add(MIN_COLUMN_NUMBER)//
			.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore())//
			.add(TokenNodePredicate.dash())//
			.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore())//
			.add(MAX_COLUMN_NUMBER);

	public SkipMinMaxColumnRule() {
		super(SKIP_MIN_MAX_COLUMN_ATTRIBUTE_VALUE_RULES);
	}


	@Override
	protected SkipColumnNode createSkipColumnNode(MatchResults matchResults) {
		int minColumn = getMinimumColumnNumber(matchResults);
		int maxColumn = getMaximumColumnNumber(matchResults);
		SkipColumnRangeNode skipColumnRangeNode=new SkipColumnRangeNode(minColumn, maxColumn);
		return skipColumnRangeNode;
	}

	private int getMinimumColumnNumber(MatchResults results) {
		List<Node> found = results.getFoundNodes(MIN_COLUMN_NUMBER);
		TokenNode tokenNode=(TokenNode) found.get(0);
		int column = Integer.valueOf(tokenNode.getValue());
		return column;
	}
	
	private int getMaximumColumnNumber(MatchResults results) {
		List<Node> found = results.getFoundNodes(MAX_COLUMN_NUMBER);
		TokenNode tokenNode=(TokenNode) found.get(0);
		int column = Integer.valueOf(tokenNode.getValue());
		return column;
	}


}
