package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.acknowledge;

import java.util.function.Predicate;

import nth.reflect.util.parser.node.matcher.predicate.NodeTypeAndMatchChildrenPredicate;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;

/**
 * {@link Predicate} that finds a {@link BraceNode} containing optional spaces and the text "ack"
 * @author nilsth
 *
 */
public class AcknowledgePredicate extends NodeTypeAndMatchChildrenPredicate {

	private static final Regex REGEX_ACK_TEXT = new Regex().ignoreCase().beginOfLine().literal("ack").endOfLine();

	private static final MatchRules CHILD_MATCH_RULES = new MatchRules()//
			.firstMatchMustBeFirstNode() //
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.add(TokenNodePredicate.rest(REGEX_ACK_TEXT))//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.lastMatchMustBeLastNode();

	public AcknowledgePredicate() {
		super(BraceNode.class, CHILD_MATCH_RULES);
	}

	}
