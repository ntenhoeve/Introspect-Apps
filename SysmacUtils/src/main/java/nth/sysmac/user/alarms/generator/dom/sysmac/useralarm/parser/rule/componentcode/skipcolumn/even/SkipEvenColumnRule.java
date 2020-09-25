package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.even;

import java.util.List;
import java.util.function.Predicate;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.matcher.NodeMatcher;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeName;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;

public class SkipEvenColumnRule implements NodeParserRule {

	public static final String EVEN_ABBREVIATION = "e";

	private static final Regex SKIP_EVEN_COLUMN_REGEX = new Regex().ignoreCase().beginOfLine().literal(EVEN_ABBREVIATION).endOfLine();

	private static final MatchRules SKIP_EVEN_COLUMN_ATTRIBUTE_VALUE_RULE = new MatchRules()//
			.add(TokenNodePredicate.rest(SKIP_EVEN_COLUMN_REGEX));

	private static final MatchRules SKIP_EVEN_COLUMN_RULES = new MatchRules()//
			.add(TokenNodePredicate.comma(), Repetition.zeroOrMore())//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.add(SKIP_EVEN_COLUMN_ATTRIBUTE_VALUE_RULE)//
			.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
			.add(TokenNodePredicate.comma(), Repetition.zeroOrMore());
			
	
	private static final Predicate<Node> SKIP_ATTRIBUTE_PREDICATE = new BracedAttributePredicate(
			BracedAttributeName.SKIP, SKIP_EVEN_COLUMN_RULES);
	
	private static final MatchRules MATCH_RULES = new MatchRules()//
			.add(SKIP_ATTRIBUTE_PREDICATE);

	@Override
	public MatchRules getMatchRules() {
		return MATCH_RULES;
	}

	@Override
	public void removeOrReplace(MatchResults matchResults) {
		int bracedAttributeIndex = matchResults.getFirstNodeIndex();
		Node bracedAttribute = matchResults.getNodes().get(bracedAttributeIndex);
		replaceInBracedAttribute(bracedAttribute);
	}

	private void replaceInBracedAttribute(Node bracedAttribute) {
		List<Node> nodes = bracedAttribute.getNodes();
		NodeMatcher nodeMatcher=new NodeMatcher(SKIP_EVEN_COLUMN_ATTRIBUTE_VALUE_RULE);
		MatchResults matchResults = nodeMatcher.match(nodes);
		SkipEvenColumnNode skipEvenColumnNode=new SkipEvenColumnNode();
		matchResults.replaceFoundNodesWith(skipEvenColumnNode);
	}

}
