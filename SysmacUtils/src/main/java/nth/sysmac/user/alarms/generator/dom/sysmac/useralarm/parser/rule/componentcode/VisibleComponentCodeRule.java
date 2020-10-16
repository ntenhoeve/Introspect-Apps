package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.matcher.predicate.NodeExactTypePredicate;
import nth.reflect.util.parser.node.matcher.predicate.NodeTypeAndMatchChildrenPredicate;
import nth.reflect.util.parser.node.matcher.predicate.NodeTypePredicate;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.sysmac.user.alarms.generator.SysmacUserAlarmsGenerator;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeName;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumns;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;

/**
 * <h3>Visible component codes</h3>
 * <p>
 * You can put one or more component code's in the comment of a data type. These
 * will all be collected by the {@link SysmacUserAlarmsGenerator} and be put in
 * the beginning of the alarm message.
 * <p>
 * If you need to add multiple component codes you might want to consider using
 * HiddenComponentCode's and ComponentCodeReferences (see next chapters)
 * <p>
 * {@insert VisbleComponentCodeSimpleExampleTest}
 * <p>
 * {@insert VisbleComponentCodeGroupExampleTest}
 * <p>
 * TODO array example 110S2{s=u,110.6-112.2} 110S2 110S4 112S6
 * <p>
 * 
 * @author nilsth
 *
 */
public class VisibleComponentCodeRule implements NodeParserRule {
	private static final String SKIP_ATTRIBUTE_EXAMPLE = "{s=e,3-5,100.1}";
	private static final Predicate<Node> WHITESPACE_PREDICATE = TokenNodePredicate.whiteSpace();
	private static final Predicate<Node> COMMA_PREDICATE = TokenNodePredicate.comma();
	private static final NodeExactTypePredicate COMPONENT_CODE_PREDICATE = new NodeExactTypePredicate(
			ComponentCodeNode.class);
	private static final MatchRules COMPONENT_CODE_RULES = new MatchRules().add(COMPONENT_CODE_PREDICATE);
	private static final Predicate<Node> SKIP_COLUMN_ATTRIBUTE_VALUE_PREDICATE = new NodeTypePredicate(
			SkipColumnNode.class);
	private static final MatchRules SKIP_COLUMN_ATTRIBUTE_VALUE_RULES = new MatchRules()
			.add(SKIP_COLUMN_ATTRIBUTE_VALUE_PREDICATE, Repetition.zeroOrMore());
	private static final BracedAttributePredicate BRACED_NODE_ATTRIBUTE_PREDICATE = new BracedAttributePredicate(
			BracedAttributeName.SKIP, SKIP_COLUMN_ATTRIBUTE_VALUE_RULES);
	private static final MatchRules BRACE_NODE_ATTRIBUTE_RULES = new MatchRules().add(BRACED_NODE_ATTRIBUTE_PREDICATE);
	private static final NodeTypeAndMatchChildrenPredicate BRACE_PREDICATE = new NodeTypeAndMatchChildrenPredicate(
			BraceNode.class, BRACE_NODE_ATTRIBUTE_RULES);
	private static final MatchRules MATCH_RULES = new MatchRules()//
			.add(COMPONENT_CODE_RULES)//
			.add(WHITESPACE_PREDICATE, Repetition.zeroOrMore())//
			.add(BRACE_PREDICATE, Repetition.zeroOrOnce());

	@Override
	public MatchRules getMatchRules() {
		return MATCH_RULES;
	}

	@Override
	public void removeOrReplace(MatchResults matchResults) {
		VisibleComponentCodeNode visibleComponentCodeNode = createVisibleComponentCodeNode(matchResults);
		matchResults.replaceFoundNodesWith(visibleComponentCodeNode);
	}

	private VisibleComponentCodeNode createVisibleComponentCodeNode(MatchResults matchResults) {
		ComponentCodeNode componentCode = getComponentCode(matchResults);
		SkipColumns skipColumns = getSkipColumns(matchResults);
		VisibleComponentCodeNode visibleComponentCodeNode = new VisibleComponentCodeNode(componentCode.getPage(),
				componentCode.getLetter(), componentCode.getColumn(), skipColumns);
		return visibleComponentCodeNode;
	}

	private ComponentCodeNode getComponentCode(MatchResults matchResults) {
		return (ComponentCodeNode) matchResults.getFoundNodes(COMPONENT_CODE_RULES).get(0);
	}

	private SkipColumns getSkipColumns(MatchResults matchResults) {
		if (matchResults.hasFoundRuleWithPredicate(BRACE_PREDICATE)) {
			SkipColumns skipColumns = createSkipColumns(matchResults);
			return skipColumns;
		} else {
			return new SkipColumns();
		}
	}

	@SuppressWarnings("rawtypes")
	private SkipColumns createSkipColumns(MatchResults matchResults) {
		BraceNode braceNode = findBraceNode(matchResults);
		BracedAttributeNode skipAttributeNode = findSkipAttribute(braceNode);
		List<SkipColumnNode> skipColumnNodes = findSkipColumnNodes(skipAttributeNode);
		SkipColumns skipColumns = new SkipColumns();
		skipColumns.addAll(skipColumnNodes);
		return skipColumns;
	}

	@SuppressWarnings("rawtypes")
	private List<SkipColumnNode> findSkipColumnNodes(BracedAttributeNode skipAttributeNode) {
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

	private BracedAttributeNode findSkipAttribute(BraceNode braceNode) {
		List<Node> nodes = braceNode.getNodes();

		List<Node> skipAttributeNodes = nodes.stream().filter(n -> BRACED_NODE_ATTRIBUTE_PREDICATE.test(n))
				.collect(Collectors.toList());
		if (skipAttributeNodes.size() == 0) {
			throw new RuntimeException("Could not find s (skip) attribute.");
		}
		if (skipAttributeNodes.size() > 1) {
			throw new RuntimeException("Found more than 1 s (skip) attributes.");
		}
		List<Node> illegalNodes = nodes.stream().filter(n -> !BRACED_NODE_ATTRIBUTE_PREDICATE.test(n)
				&& WHITESPACE_PREDICATE.test(n) && !COMMA_PREDICATE.test(n)).collect(Collectors.toList());
		if (!illegalNodes.isEmpty()) {
			throw new RuntimeException("Expecting component code skip information, e.g. " + SKIP_ATTRIBUTE_EXAMPLE
					+ ", but found illegal characters:" + illegalNodes);
		}
		return (BracedAttributeNode) nodes.get(0);
	}

	private BraceNode findBraceNode(MatchResults matchResults) {
		List<Node> foundNodes = matchResults.getFoundNodes(BRACE_PREDICATE);
		BraceNode braceNode = (BraceNode) foundNodes.get(0);
		return braceNode;
	}

}
