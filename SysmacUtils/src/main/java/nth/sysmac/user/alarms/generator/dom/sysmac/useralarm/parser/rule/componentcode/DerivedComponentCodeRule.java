package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.predicate.AnyNodePredicate;
import nth.reflect.util.parser.node.matcher.predicate.NodeTypeAndMatchChildrenPredicate;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.reflect.util.regex.LetterTypes;
import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;

/**
 * <h3>Component code references</h3>
 * <p>
 * A component reference is a letter (in some cases followed by a number)
 * between curly brackets in a data type comment, e.g.: {Q}. They refer to
 * component code that is already defined in one (or more) data type comments
 * 
 * <p>
 * {@insert VisibleComponentCodeChangeLetterExampleTest}
 * <p>
 * TODO explain that component codes references make component codes between
 * curly brackets visible {30M1} Pump motor stop timeout {M} too warm {Q}
 * protector becomes: Pump motor stop timeout 30M1 Pump motor too warn 30Q1 Pump
 * motor protector
 * <p>
 * TODO explain that component codes references make component codes between
 * curly brackets visible {30M1} {110S1} {80K3} {80K4} Height adjustment stop
 * timeout {M} motor too warm {Q} motor protector {S} disconnect switch {K1} up
 * contactor not closed {K2} down contactor not closed becomes: ...
 * <p>
 * TODO Visible component codes are no longer displayed when a component code
 * reference is used. e.g. 30M1 110S1 Height adjustment {s} disconnect switch =>
 * 110S1 Height adjustment disconnect switch
 * <p>
 * TODO {30U1}{110S3} Line drive and ... ..{U} and {S] = Line Drive, 30U1 Line
 * drive trip and 110S3 Line drive motor switch<br>
 * 
 * @author nilsth
 *
 */

public class DerivedComponentCodeRule implements NodeParserRule {

	private static final Regex SINGLE_LETTER_REGEX = new Regex().letter(LetterTypes.ALL_CASE);
	private static final TokenNodePredicate SINGLE_LETTER_PREDICATE = TokenNodePredicate.rest(SINGLE_LETTER_REGEX);
	private static final Predicate<Node> INDEX_PREDICATE = TokenNodePredicate.unsignedInteger();
	private static final MatchRules CHILDREN_RULES = new MatchRules()//
			.firstMatchMustBeFirstNode()//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.add(SINGLE_LETTER_PREDICATE)//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.add(INDEX_PREDICATE, Repetition.zeroOrOnce())//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.lastMatchMustBeLastNode();

	private static final NodeTypeAndMatchChildrenPredicate DERIVED_COMPONENT_CODE_PREDICATE = new NodeTypeAndMatchChildrenPredicate(
			BraceNode.class, CHILDREN_RULES);

	private static final MatchRules MATCH_RULES = new MatchRules()//
			.firstMatchMustBeFirstNode()//
			.add(new AnyNodePredicate(), Repetition.zeroOrMore())//
			.add(DERIVED_COMPONENT_CODE_PREDICATE)//
			.add(new AnyNodePredicate(), Repetition.zeroOrMore())//
			.lastMatchMustBeLastNode();

	@Override
	public MatchRules getMatchRules() {
		return MATCH_RULES;
	}

	@Override
	public void removeOrReplace(MatchResults matchResults) {
		List<ComponentCodeNode> componentCodeNodes = getComponentCodes(matchResults);
		BraceNode braceNode = getBraceNode(matchResults);
		char letter = getLetter(braceNode);
		int index = getIndex(braceNode);
		DerivedComponentCodeNode derivedComponentCodeNode = new DerivedComponentCodeNode(componentCodeNodes, letter,
				index);
		matchResults.replaceFoundNodesWith(DERIVED_COMPONENT_CODE_PREDICATE, derivedComponentCodeNode);
	}

	private BraceNode getBraceNode(MatchResults matchResults) {
		List<Node> foundNodes = matchResults.getFoundNodes(DERIVED_COMPONENT_CODE_PREDICATE);
		BraceNode braceNode = (BraceNode) foundNodes.get(0);
		return braceNode;
	}

	private List<ComponentCodeNode> getComponentCodes(MatchResults matchResults) {
		List<Node> nodes = matchResults.getNodes();
		List<ComponentCodeNode> componentCodeNodes = nodes.stream()
				.filter(n -> n instanceof VisibleComponentCodeNode || n instanceof HiddenComponentCodeNode)
				.map(n -> (ComponentCodeNode) n).collect(Collectors.toList());
		return componentCodeNodes;
	}

	private int getIndex(BraceNode braceNode) {
		NodeMatcher nodeMatcher = new NodeMatcher(CHILDREN_RULES);
		MatchResults matchResults = nodeMatcher.match(braceNode.getNodes());
		if (matchResults.hasFoundRuleWithPredicate(INDEX_PREDICATE)) {
			List<Node> nodes = matchResults.getFoundNodes(INDEX_PREDICATE);
			TokenNode tokenNode = (TokenNode) nodes.get(0);
			int index = Integer.parseInt(tokenNode.getValue());
			return index;
		} else {
			return 1;
		}

	}

	private char getLetter(BraceNode braceNode) {
		NodeMatcher nodeMatcher = new NodeMatcher(CHILDREN_RULES);
		MatchResults matchResults = nodeMatcher.match(braceNode.getNodes());
		List<Node> nodes = matchResults.getFoundNodes(SINGLE_LETTER_PREDICATE);
		TokenNode tokenNode = (TokenNode) nodes.get(0);
		char letter = tokenNode.getValue().charAt(0);
		return letter;
	}

}
