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
 * s=30.2-31.5: skips column 2 of page 30 until column 5 of page 31
 * @see SkipPageColumnRule
 * @author nilsth
 *
 */
public class SkipMinMaxPageColumnRule extends SkipColumnRule {

	private static final MatchRules MIN_PAGE_NUMBER = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());
	private static final MatchRules MIN_COLUMN_NUMBER = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());
	private static final MatchRules MAX_PAGE_NUMBER = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());	
	private static final MatchRules MAX_COLUMN_NUMBER = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());
	
	private static final MatchRules SKIP_MIN_MAX_COLUMN_ATTRIBUTE_VALUE_RULES = new MatchRules()//
			.add(MIN_PAGE_NUMBER)//
			.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore())//
			.add(TokenNodePredicate.dot())//
			.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore())//
			.add(MIN_COLUMN_NUMBER)//
			.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore())//
			.add(TokenNodePredicate.dash())//
			.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore())//
			.add(MAX_PAGE_NUMBER)//
			.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore())//
			.add(TokenNodePredicate.dot())//
			.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore())//
			.add(MAX_COLUMN_NUMBER);

	public SkipMinMaxPageColumnRule() {
		super(SKIP_MIN_MAX_COLUMN_ATTRIBUTE_VALUE_RULES);
	}


	@Override
	protected SkipColumnNode createSkipColumnNode(MatchResults matchResults) {
		int minPage = getNumber(matchResults, MIN_PAGE_NUMBER);
		int minColumn = getNumber(matchResults, MIN_COLUMN_NUMBER);
		int maxPage = getNumber(matchResults, MAX_PAGE_NUMBER);
		int maxColumn = getNumber(matchResults, MAX_COLUMN_NUMBER);
		SkipPageColumnRangeNode skipPageColumnRangeNode=new SkipPageColumnRangeNode(minPage, minColumn, maxPage, maxColumn);
		return skipPageColumnRangeNode;
	}

	private int getNumber(MatchResults matchResults, MatchRules rules) {
		List<Node> found = matchResults.getFoundNodes(rules);
		TokenNode tokenNode=(TokenNode) found.get(0);
		int number = Integer.valueOf(tokenNode.getValue());
		return number;
	}



}
