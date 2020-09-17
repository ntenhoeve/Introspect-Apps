package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority;

import java.util.function.Predicate;

import nth.reflect.util.parser.node.matcher.predicate.AnyNodePredicate;
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
public class PriorityPredicate extends NodeTypeAndMatchChildrenPredicate {

	private static final Regex REGEX_DETAILS_ABBREVIATION = new Regex().ignoreCase().beginOfLine().literal("p")
			.endOfLine();

	private static final MatchRules ATTRIBUTE_NAME_RULES = new MatchRules()//
			.firstMatchMustBeFirstNode() //
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.add(TokenNodePredicate.rest(REGEX_DETAILS_ABBREVIATION))//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore());
	private static final MatchRules EQUALS_RULES = new MatchRules()//
			.add(TokenNodePredicate.equals());
	public static final MatchRules ATTRIBUTE_VALUE_RULES = new MatchRules()//
			.add(new AnyNodePredicate(), Repetition.oneOrMore())//
			.lastMatchMustBeLastNode();
	public static final MatchRules CHILD_MATCH_RULES = new MatchRules()//
			.add(ATTRIBUTE_NAME_RULES)//
			.add(EQUALS_RULES)//
			.add(ATTRIBUTE_VALUE_RULES);

	public PriorityPredicate() {
		super(BraceNode.class, CHILD_MATCH_RULES);
	}
}