package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.predicate.NodeTypeAndMatchChildrenPredicate;
import nth.reflect.util.parser.node.matcher.predicate.NodeTypePredicate;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeName;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.skip.SkipCounterNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;

/**
 * <h3>Counter</h3>
 * <p>
 * You can use counters when the data type uses an array. Counters are added by
 * adding the following text to the {@link DataType} comment:
 * <p>
 * {cnt &lt;skip=skip rules&gt; &lt;array=array number&gt;}
 * <p>
 * &lt;skip=skip rules&gt; are optional and can be used to skip numbers. e.g.:
 * the counters always start at 0. Use skip=0, if you want the counter to start
 * at 1. TODO insert counter skip rules
 * <p>
 * &lt;array=array number&gt; is optional:
 * <ul>
 * <li>no array attribute or array=0: counter increases when any array is
 * increased and will not reset within the array</li>
 * <li>array=1: counter increases when the last array is increased, the counter
 * is reset if the second last array is increased</li>
 * <li>array=2: counter increases when the second last array is increased, the
 * counter is reset if the third last array is increased</li>
 * <li>etcetera</li>
 * </ul>
 * <p>
 * {insert CounterRuleExampleTest}
 * <p>
 * TODO {insert CounterRuleArrayExampleTest}
 * <p>
 * TODO {insert CounterRuleArrayExampleTest}
 * <p>
 * TODO {insert CounterRuleSkipArrayExampleTest}
 * 
 * @author nilsth
 */

public class CounterRule implements NodeParserRule {
	

	private static final Predicate<Node> SKIP_ATTRIBUTE_VALUE_PREDICATE = new NodeTypePredicate(
			CounterSkipNode.class);
	private static final MatchRules SKIP_ATTRIBUTE_VALUE_RULES = new MatchRules()
			.add(SKIP_ATTRIBUTE_VALUE_PREDICATE);
		private static final BracedAttributePredicate SKIP_ATTRIBUTE_PREDICATE = new BracedAttributePredicate(
			BracedAttributeName.SKIP, SKIP_ATTRIBUTE_VALUE_RULES);
	private static final MatchRules SKIP_ATTRIBUTE_RULES = new MatchRules().add(SKIP_ATTRIBUTE_PREDICATE);
	private static final NodeTypeAndMatchChildrenPredicate BRACED_SKIP_PREDICATE = new NodeTypeAndMatchChildrenPredicate(
			BraceNode.class, SKIP_ATTRIBUTE_RULES);

	private static final Predicate<Node> ARRAY_ATTRIBUTE_VALUE_PREDICATE = new NodeTypePredicate(
			CounterArrayNode.class);
	private static final MatchRules ARRAY_ATTRIBUTE_VALUE_RULES = new MatchRules()
			.add(ARRAY_ATTRIBUTE_VALUE_PREDICATE);
	private static final BracedAttributePredicate ARRAY_ATTRIBUTE_PREDICATE = new BracedAttributePredicate(
			BracedAttributeName.ARRAY, ARRAY_ATTRIBUTE_VALUE_RULES);
	private static final MatchRules ARRAY_ATTRIBUTE_RULES = new MatchRules().add(ARRAY_ATTRIBUTE_PREDICATE);
	private static final NodeTypeAndMatchChildrenPredicate BRACED_ARRAY_PREDICATE = new NodeTypeAndMatchChildrenPredicate(
			BraceNode.class, ARRAY_ATTRIBUTE_RULES);
	
	private static final MatchRules MATCH_RULES = new MatchRules()//
			.add(TokenNodePredicate.rest(REGEX_COUNTER_ABBREVIATION))//
			.add(WHITESPACE_PREDICATE, Repetition.zeroOrMore())//
//			.add(BRACED_SKIP_PREDICATE, Repetition.zeroOrOnce())//
			.add(BRACED_ARRAY_PREDICATE, Repetition.zeroOrOnce())//
//			.add(BRACED_SKIP_PREDICATE, Repetition.zeroOrOnce())//
			.add(WHITESPACE_PREDICATE, Repetition.zeroOrMore());

	private static final MatchRules MATCH_RULES = new MatchRules().add(new CounterPredicate());

	@Override
	public MatchRules getMatchRules() {
		return MATCH_RULES;
	}

	/**
	 * Replaces {@link Node}s that matches a {@link CounterRule#MATCH_RULES} with a
	 * {@link ComponentCodeNode}
	 */
	@Override
	public void removeOrReplace(MatchResults matchResults) {
		int array = getArray(matchResults);
		List<SkipCounterNode> skipNodes = getSkipNodes(matchResults);
		CounterNode counterNode = new CounterNode(array, skipNodes);
		matchResults.replaceFoundNodesWith(counterNode);
	}

	private List<SkipCounterNode> getSkipNodes(MatchResults matchResults) {
		if (matchResults.hasFoundRuleWithPredicate(SKIP_COLUMN_PREDICATE)) {
			List<SkipCounterNode> skipNodes = createSkipNodes(matchResults);
			return skipNodes;
		} else {
			return new ArrayList<>();
		}
	}

	private List<SkipCounterNode> createSkipNodes(MatchResults matchResults) {
		BracedAttributeNode skipAttributeNode = findSkipAttribute(matchResults);
		List<SkipCounterNode> skipNodes = findSkipColumnNodes(skipAttributeNode);
		return skipNodes;
	}

	private List<SkipCounterNode> findSkipColumnNodes(BracedAttributeNode skipAttributeNode) {
		List<Node> nodes = skipAttributeNode.getNodes();

		@SuppressWarnings("unchecked")
		List<SkipColumnNode> skipColumnNodes = (List<SkipColumnNode>) ((Object) nodes.stream()
				.filter(n -> SKIP_COLUMN_ATTRIBUTE_VALUE_PREDICATE.test(n)).collect(Collectors.toList()));

		List<Node> illegalNodes = nodes.stream().filter(n -> !SKIP_COLUMN_ATTRIBUTE_VALUE_PREDICATE.test(n)
				&& !WHITESPACE_PREDICATE.test(n) && !COMMA_PREDICATE.test(n)).collect(Collectors.toList());
		if (!illegalNodes.isEmpty()) {
			throw new RuntimeException("Expecting component code skip information, e.g. " + SKIP_ATTRIBUTE_EXAMPLE
					+ " but found illegal characters:" + illegalNodes);
		}
		return skipColumnNodes;
	}

	private BracedAttributeNode findSkipAttribute(MatchResults matchResults) {
		List<Node> nodes = matchResults.getFoundNodes(SKIP_COLUMN_PREDICATE);

		List<Node> skipAttributeNodes = nodes.stream().filter(n -> SKIP_COLUMN_PREDICATE.test(n))
				.collect(Collectors.toList());
		if (skipAttributeNodes.size() == 0) {
			throw new RuntimeException("Could not find s (skip) attribute.");
		}
		if (skipAttributeNodes.size() > 1) {
			throw new RuntimeException("Found more than 1 s (skip) attributes.");
		}
		List<Node> illegalNodes = nodes.stream()
				.filter(n -> !SKIP_COLUMN_PREDICATE.test(n) && WHITESPACE_PREDICATE.test(n) && !COMMA_PREDICATE.test(n))
				.collect(Collectors.toList());
		if (!illegalNodes.isEmpty()) {
			throw new RuntimeException("Expecting component code skip information, e.g. " + SKIP_ATTRIBUTE_EXAMPLE
					+ ", but found illegal characters:" + illegalNodes);
		}
		return (BracedAttributeNode) nodes.get(0);
	}

	private int getArray(MatchResults matchResults) {
		List<Node> nodes = matchResults.getFoundNodes();
		if (nodes.size() != 1) {
			return 0;
		}
		Node node = nodes.get(0);
		if (node instanceof BraceNode) {
			BraceNode braceNode = (BraceNode) node;
			return getArray(braceNode);
		}
		return 0;
	}

	private int getArray(BraceNode braceNode) {
		List<Node> nodes = braceNode.getNodes();
		MatchRules matchRules = new MatchRules().add(new CounterArrayPredicate());
		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResult = nodeMatcher.match(nodes);
		if (!matchResult.hasResults()) {
			return 0;
		}
		BracedAttributeNode bracedAttributeNode = (BracedAttributeNode) matchResult.getFoundNodes().get(0);
		return getArray(bracedAttributeNode);
	}

	private int getArray(BracedAttributeNode bracedAttributeNode) {
		List<Node> nodes = bracedAttributeNode.getNodes();
		MatchRules matchRules = new MatchRules().add(TokenNodePredicate.unsignedInteger());
		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResult = nodeMatcher.match(nodes);
		if (!matchResult.hasResults()) {
			return 0;
		}
		TokenNode tokenNode = (TokenNode) matchResult.getFoundNodes().get(0);
		String value = tokenNode.getValue();
		int array = Integer.valueOf(value);
		return array;
	}

}
