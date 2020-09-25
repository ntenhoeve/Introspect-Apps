package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn;

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

public abstract class SkipColumnRule implements NodeParserRule {

	private final MatchRules skipColumnAttributeValueRule;

	
	public SkipColumnRule(MatchRules skipColumnAttributeValueRule) {
		this.skipColumnAttributeValueRule = skipColumnAttributeValueRule;
	}

	@Override
	public MatchRules getMatchRules() {
		Predicate<Node> skipColumnAttributePredicate = createSkipColumnAttributePredicate();
		MatchRules matchRules = new MatchRules().add(skipColumnAttributePredicate);
		return matchRules;
	}

	private BracedAttributePredicate createSkipColumnAttributePredicate() {
		MatchRules skipEvenColumnRules = createSkipColumnAttributeValueRules();
		return new BracedAttributePredicate(BracedAttributeName.SKIP, skipEvenColumnRules);
	}

	private MatchRules createSkipColumnAttributeValueRules() {
		return new MatchRules()//
				.add(TokenNodePredicate.comma(), Repetition.zeroOrMore())//
				.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
				.add(skipColumnAttributeValueRule)//
				.add(TokenNodePredicate.whiteSpace(), Repetition.zeroOrMore())//
				.add(TokenNodePredicate.comma(), Repetition.zeroOrMore());
	}
	

	@Override
	public void removeOrReplace(MatchResults matchResults) {
		int bracedAttributeIndex = matchResults.getFirstNodeIndex();
		Node bracedAttribute = matchResults.getNodes().get(bracedAttributeIndex);
		replaceInBracedAttribute(bracedAttribute);
	}

	private void replaceInBracedAttribute(Node bracedAttribute) {
		List<Node> nodes = bracedAttribute.getNodes();
		NodeMatcher nodeMatcher = new NodeMatcher(skipColumnAttributeValueRule);
		MatchResults matchResults = nodeMatcher.match(nodes);
		SkipColumnNode skipColumnNode= createSkipColumnNode(matchResults);
		matchResults.replaceFoundNodesWith(skipColumnNode);
	}

	protected abstract SkipColumnNode createSkipColumnNode(MatchResults matchResults);

}
