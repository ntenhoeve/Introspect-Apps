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
 * s=30.2: skips column 2 of page 30
 * @see SkipPageColumnRule
 * @author nilsth
 *
 */
public class SkipSinglePageColumnRule extends SkipColumnRule {

	private static final MatchRules PAGE_NUMBER = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());
	private static final MatchRules COLUMN_NUMBER = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());

	private static final MatchRules ATTRIBUTE_VALUE_RULES = new MatchRules()//
			.add(PAGE_NUMBER)//
			.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore())//
			.add(TokenNodePredicate.dot())//
			.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore())//
			.add(COLUMN_NUMBER)//
			;

	public SkipSinglePageColumnRule() {
		super(ATTRIBUTE_VALUE_RULES);
	}


	@Override
	protected SkipColumnNode createSkipColumnNode(MatchResults matchResults) {
		int page = getNumber(matchResults, PAGE_NUMBER);
		int column = getNumber(matchResults,COLUMN_NUMBER);
		
		SkipPageColumnRangeNode skipPageColumnRangeNode=new SkipPageColumnRangeNode(page,column,page,column);
		return skipPageColumnRangeNode;
	}


	private int getNumber(MatchResults matchResults, MatchRules rules) {
		List<Node> found = matchResults.getFoundNodes(rules);
		TokenNode tokenNode=(TokenNode) found.get(0);
		int number = Integer.valueOf(tokenNode.getValue());
		return number;
	}


}
