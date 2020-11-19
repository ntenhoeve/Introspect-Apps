package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.skip;

import java.util.List;
import java.util.function.Predicate;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeName;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
/**
 * 
 * <h3>Skipping counter values when using arrays:</h3>
 * <ul>
 * <li>{@insert SkipEvenCounterRule}</li>
 * <li>{@insert SkipUnevenCounterRule}</li>
 * <li>{@insert SkipSingleCounterRule}</li>
 * <li>{@insert SkipMaxCounterRule}</li>
 * <li>{@insert SkipMinMaxCounterRule}</li>
 * </ul>
 * You can combine the rules above by separating them with a comma, e.g.:
 * <ul>
 * <li>s=e,5: skips even counter values and counter value 5</li>
 * <li>s=2,4: skips counter value 2 and counter value 4</li>
 * <li>s=2-4,8: skips counter value 2-4 and counter value 8</li>
 * </ul>
 * 
 * @author nilsth
 *
 *
 */
public abstract class SkipCounterRule implements NodeParserRule {

	private final MatchRules skipCounterAttributeValueRule;

	
	public SkipCounterRule(MatchRules skipCounterAttributeValueRule) {
		this.skipCounterAttributeValueRule = skipCounterAttributeValueRule;
	}

	@Override
	public MatchRules getMatchRules() {
		Predicate<Node> skipCounterAttributePredicate = createSkipCounterAttributePredicate();
		MatchRules matchRules = new MatchRules().add(skipCounterAttributePredicate);
		return matchRules;
	}

	private BracedAttributePredicate createSkipCounterAttributePredicate() {
		MatchRules skipCounterRules = createSkipCounterAttributeValueRules();
		return new BracedAttributePredicate(BracedAttributeName.SKIP, skipCounterRules);
	}

	private MatchRules createSkipCounterAttributeValueRules() {
		return new MatchRules()//
				.add(TokenNodePredicate.comma(), Repetition.zeroOrMore())//
				.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
				.add(skipCounterAttributeValueRule)//
				.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
				.add(TokenNodePredicate.comma(), Repetition.zeroOrMore());
	}
	

	@Override
	public void removeOrReplace(MatchResults matchResults) {
		int bracedAttributeIndex = matchResults.getFirstResult().getNodeIndex();
		Node bracedAttribute = matchResults.getNodes().get(bracedAttributeIndex);
		replaceInBracedAttribute(bracedAttribute);
	}

	private void replaceInBracedAttribute(Node bracedAttribute) {
		List<Node> nodes = bracedAttribute.getNodes();
		NodeMatcher nodeMatcher = new NodeMatcher(skipCounterAttributeValueRule);
		MatchResults matchResults = nodeMatcher.match(nodes);
		SkipCounterNode skipCounterNode= createSkipCounterNode(matchResults);
		matchResults.replaceFoundNodesWith(skipCounterNode);
	}


	protected abstract SkipCounterNode createSkipCounterNode(MatchResults matchResults);

}
