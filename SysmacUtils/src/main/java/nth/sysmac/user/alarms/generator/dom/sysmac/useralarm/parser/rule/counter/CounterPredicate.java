package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter;

import java.util.function.Predicate;

import nth.reflect.util.parser.node.matcher.predicate.NodeTypeAndMatchChildrenPredicate;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;

/**
 * {@link Predicate} that finds a {@link BraceNode} containing children that
 * match {@link #CHILD_MATCH_RULES}
 * 
 * @author nilsth
 *
 */
public class CounterPredicate extends NodeTypeAndMatchChildrenPredicate {

	public static final String COUNTER_ABBREVIATION = "cnt";

	public static final Regex REGEX_COUNTER_ABBREVIATION = new Regex().ignoreCase().beginOfLine().literal(COUNTER_ABBREVIATION)
			.endOfLine();

	private static final MatchRules ATTRIBUTE_NAME_RULES = new MatchRules()//
			.firstMatchMustBeFirstNode() //
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.add(TokenNodePredicate.rest(REGEX_COUNTER_ABBREVIATION))//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore());
	public static final MatchRules CHILD_MATCH_RULES = new MatchRules()//
			.add(ATTRIBUTE_NAME_RULES)//
//			.add(EQUALS_RULES)//
//			.add(ATTRIBUTE_VALUE_RULES);
;
	public CounterPredicate() {
		super(BraceNode.class, CHILD_MATCH_RULES);
	}
}