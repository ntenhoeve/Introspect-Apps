package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.predicate.AnyNodePredicate;
import nth.reflect.util.parser.node.matcher.predicate.NodeExactTypePredicate;
import nth.reflect.util.parser.node.matcher.predicate.NodeTypeAndMatchChildrenPredicate;
import nth.reflect.util.parser.node.matcher.predicate.NodeTypePredicate;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.sysmac.user.alarms.generator.SysmacUserAlarmsGenerator;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarm;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeName;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumns;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;

/**
 * <h3>Hidden component codes</h3>
 * <p>
 * Hidden component codes are like Visible component codes, but they stay hidden until they are referred to in derived component codes.
 * <p>
 * Hidden component codes are preferred over visible component code's when you need to use multiple component codes. e.g. when an motor 30M1 branches of in all type of {@link UserAlarm}s that refer to other component codes like 50K1 and 110S1:
 * <p>
 * <p>
 * The {@link SysmacUserAlarmsGenerator} will generate succeeding component codes when arrays are used. 
 * TODO array example 110S7 110S7 110S8 111S1
 * <p>
 * You can add skip rules to skip columns when arrays are used. See the component code column skip section for more information.  
 * TODO array example 110S2{s=u,110.6-112.2} 110S2 110S4 112S6
 * <p>
 * 
 * <p>
 * 
 * @author nilsth
 *
 */
public class HiddenComponentCodeRule implements NodeParserRule {

	private static final Predicate<Node> ANY_NODE=new AnyNodePredicate();
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
	private static final MatchRules BRACE_NODE_ATTRIBUTE_RULES = new MatchRules()//
			.firstMatchMustBeFirstNode()//
			.add(WHITESPACE_PREDICATE,Repetition.zeroOrMore())//
			.add(COMPONENT_CODE_RULES)//
			.add(ANY_NODE)//
			.add(WHITESPACE_PREDICATE,Repetition.zeroOrMore())//
			//.add(SKIP_COLUMN_ATTRIBUTE_VALUE_PREDICATE,Repetition.zeroOrMore())//
			.add(ANY_NODE)//
			.add(WHITESPACE_PREDICATE,Repetition.zeroOrMore())//
			.lastMatchMustBeLastNode();
	private static final NodeTypeAndMatchChildrenPredicate BRACE_PREDICATE = new NodeTypeAndMatchChildrenPredicate(
			BraceNode.class, BRACE_NODE_ATTRIBUTE_RULES);
	
	private static final MatchRules MATCH_RULES = new MatchRules()//
			.add(BRACE_PREDICATE);

	@Override
	public MatchRules getMatchRules() {
		return MATCH_RULES;
	}

	@Override
	public void removeOrReplace(MatchResults matchResults) {
		HiddenComponentCodeNode hiddenComponentCodeNode = createHiddenComponentCodeNode(matchResults);
		matchResults.replaceFoundNodesWith(hiddenComponentCodeNode);
	}

	private HiddenComponentCodeNode createHiddenComponentCodeNode(MatchResults matchResults) {
		BraceNode braceNode=(BraceNode) matchResults.getFoundNodes().get(0);
		List<Node> nodes = braceNode.getNodes();
		
		NodeMatcher nodeMatcher=new NodeMatcher(BRACE_NODE_ATTRIBUTE_RULES);
		matchResults=nodeMatcher.match(nodes);
		
		ComponentCodeNode componentCode = getComponentCode(matchResults);
		SkipColumns skipColumns = getSkipColumns(matchResults);
		HiddenComponentCodeNode hiddenComponentCodeNode = new HiddenComponentCodeNode(componentCode.getPage(),
				componentCode.getLetter(), componentCode.getColumn(), skipColumns);
		return hiddenComponentCodeNode;
	}

	private ComponentCodeNode getComponentCode(MatchResults matchResults) {
		return (ComponentCodeNode) matchResults.getFoundNodes(COMPONENT_CODE_RULES).get(0);
	}

	private SkipColumns getSkipColumns(MatchResults matchResults) {
		if (matchResults.hasFoundRuleWithPredicate(BRACED_NODE_ATTRIBUTE_PREDICATE)) {
			SkipColumns skipColumns = createSkipColumns(matchResults);
			return skipColumns;
		} else {
			return new SkipColumns();
		}
	}

	private SkipColumns createSkipColumns(MatchResults matchResults) {
		BracedAttributeNode skipAttributeNode = findSkipAttribute(matchResults);
		List<SkipColumnNode> skipColumnNodes = findSkipColumnNodes(skipAttributeNode);
		SkipColumns skipColumns = new SkipColumns();
		skipColumns.addAll(skipColumnNodes);
		return skipColumns;
	}

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

	private BracedAttributeNode findSkipAttribute(MatchResults matchResults) {
		List<Node> nodes = matchResults.getFoundNodes(BRACE_NODE_ATTRIBUTE_RULES);

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


}
