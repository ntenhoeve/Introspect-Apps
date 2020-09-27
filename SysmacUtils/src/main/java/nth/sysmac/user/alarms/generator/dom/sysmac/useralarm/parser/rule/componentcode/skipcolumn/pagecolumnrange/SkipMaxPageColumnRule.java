package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.pagecolumnrange;

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
 * s=-30.2: skips all columns up and including column 2 of page 30
 * 
 * @see SkipPageColumnRule
 * @author nilsth
 *
 */
public class SkipMaxPageColumnRule extends SkipColumnRule {

	private static final MatchRules MAX_PAGE = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());

	private static final MatchRules MAX_COLUMN = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());

	private static final MatchRules ATTRIBUTE_VALUE_RULES = new MatchRules()//
			.add(TokenNodePredicate.dash())//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.add(MAX_PAGE)//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.add(TokenNodePredicate.dot())//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.add(MAX_COLUMN)//
			;

	public SkipMaxPageColumnRule() {
		super(ATTRIBUTE_VALUE_RULES);
	}

	@Override
	protected SkipColumnNode createSkipColumnNode(MatchResults matchResults) {
		int maxPage = getNumber(matchResults, MAX_PAGE);
		int maxColumn = getNumber(matchResults, MAX_COLUMN);
		SkipPageColumnRangeNode skipPageColumnRangeNode = new SkipPageColumnRangeNode(1,1,maxPage, maxColumn);
		return skipPageColumnRangeNode;
	}

	private int getNumber(MatchResults matchResults, MatchRules rules) {
		List<Node> found = matchResults.getFoundNodes(rules);
		TokenNode tokenNode=(TokenNode) found.get(0);
		int number = Integer.valueOf(tokenNode.getValue());
		return number;
	}

}
