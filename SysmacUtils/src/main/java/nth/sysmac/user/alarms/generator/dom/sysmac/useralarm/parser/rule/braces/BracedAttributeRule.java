package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.predicate.AnyNodePredicate;
import nth.reflect.util.parser.node.matcher.predicate.NodeTypeAndMatchChildrenPredicate;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;

/**
 * Replaces child{@link Node}'s in an {@link BraceNode} that represent a
 * {@link BracedAttributeNode} with format &lt;attribute name&gt;= &lt;attrubute
 * values&gt;
 * 
 * @author nilsth
 *
 */
public class BracedAttributeRule implements NodeParserRule {

	private static final Regex ATTRIBUTE_NAMES_REGEX = createAttributeNamesRegex();
	private static final MatchRules ATTRIBUTE_NAME_RULES = new MatchRules()//
			.add(TokenNodePredicate.rest(ATTRIBUTE_NAMES_REGEX));

	private static final MatchRules EQUAL_RULES = new MatchRules().add(TokenNodePredicate.equals());

	private static final MatchRules ATTRIBUTE_VALUE_RULES = new MatchRules()//
			.add(new AnyNodePredicate(), Repetition.onceOrMore());

	private static final MatchRules ATTRIBUTE_RULES = new MatchRules()//
			.add(ATTRIBUTE_NAME_RULES)//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.add(EQUAL_RULES)//
			.add(ATTRIBUTE_VALUE_RULES);

	private static final Predicate<Node> PREDICATE = new NodeTypeAndMatchChildrenPredicate(BraceNode.class,
			ATTRIBUTE_RULES);
	
	private static final MatchRules MATCH_RULES = new MatchRules()//
			.add(PREDICATE);

	@Override
	public MatchRules getMatchRules() {
		return MATCH_RULES;
	}

	private static Regex createAttributeNamesRegex() {
		List<Regex> attributeNameRegexes = new ArrayList<>();
		for (BracedAttributeName name : BracedAttributeName.values()) {
			Regex attributeNameRegex = new Regex().ignoreCase().beginOfLine().literal(name.getAbbreviation()).endOfLine();
			attributeNameRegexes.add(attributeNameRegex);
		}
		Regex attributeNamesRegex = new Regex().or(attributeNameRegexes);
		return attributeNamesRegex;
	}

	@Override
	public void removeOrReplace(MatchResults matchResults) {
		int braceNodeIndex = matchResults.getFirstResult().getNodeIndex();
		Node braceNode=matchResults.getNodes().get(braceNodeIndex);
		replaceInBraceNode(braceNode);
	}

	private void replaceInBraceNode(Node braceNode) {
		List<Node> nodes = braceNode.getNodes();
		NodeMatcher nodeMatcher=new NodeMatcher(ATTRIBUTE_RULES);
		MatchResults matchResults=nodeMatcher.match(nodes);
		BracedAttributeName attributeName = getAttributeName(matchResults);
		List<Node> attributeValues = getAttributeValues(matchResults);
		BracedAttributeNode attributeNode = new BracedAttributeNode(attributeName, attributeValues);
		List<Node> braceNodeChildren = braceNode.getNodes();
		replace(braceNodeChildren, attributeValues, attributeNode);
	}

	private BracedAttributeName getAttributeName(MatchResults matchResults) {
		Node attributeNameNode = matchResults.getFoundNodes(ATTRIBUTE_NAME_RULES).get(0);// should be one node
		String abbreviation = ((TokenNode) attributeNameNode).getValue();
		BracedAttributeName name = BracedAttributeName.getForAbbreviation(abbreviation);
		return name;
	}

	private void replace(List<Node> braceNodeChildren, List<Node> attributeValues, BracedAttributeNode attributeNode) {
		MatchRules matchRules = new MatchRules()//
				.add(ATTRIBUTE_NAME_RULES)//
				.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
				.add(EQUAL_RULES);
		for (Node attributeValue : attributeValues) {
			matchRules = matchRules.add(createPredicate(attributeValue));
		}
		NodeMatcher nodeMatcher = new NodeMatcher(matchRules);
		MatchResults matchResults = nodeMatcher.match(braceNodeChildren);
		matchResults.replaceFoundNodesWith(attributeNode);
	}

	private Predicate<Node> createPredicate(Node nodeToFind) {
		return new Predicate<Node>() {

			@Override
			public boolean test(Node node) {
				boolean equals = nodeToFind.equals(node);
				return equals;
			}
		};
	}

	private List<Node> getAttributeValues(MatchResults matchResults) {
		List<Node> attributeValuesWithOtherAttributes = matchResults.getFoundNodes(ATTRIBUTE_VALUE_RULES);
		List<Node> attributeValues = removeOtherAttributes(attributeValuesWithOtherAttributes);
		return attributeValues;
	}

	private List<Node> removeOtherAttributes(List<Node> attributeValuesWithOtherAttributes) {
		NodeMatcher otherAttributeMatcher = new NodeMatcher(ATTRIBUTE_RULES);
		MatchResults otherAttributeResults = otherAttributeMatcher.match(attributeValuesWithOtherAttributes);
		if (otherAttributeResults.hasResults()) {
			int firstOtherAttributeIndex = otherAttributeResults.getFirstResult().getNodeIndex();
			List<Node> attributeValues = attributeValuesWithOtherAttributes.subList(0, firstOtherAttributeIndex);
			return attributeValues;
		} else {
			List<Node> attributeValues = attributeValuesWithOtherAttributes;
			return attributeValues;
		}
	}

}
