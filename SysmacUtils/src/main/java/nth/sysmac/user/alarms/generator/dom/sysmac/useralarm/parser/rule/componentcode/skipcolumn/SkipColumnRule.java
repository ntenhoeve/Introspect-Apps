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
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange.SkipSingleColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.even.SkipEvenColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.uneven.SkipUnevenColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
/**
 * 
 * <h3>Skipping component numbers when using arrays:</h3>
 * <ul>
 * <li>{@link SkipEvenColumnRule}</li>
 * <li>{@link SkipUnevenColumnRule}</li>
 * <li>@link {@link SkipSingleColumnRule}</li>
 * <li>s=-5: skips columns 1 until 5 (of all pages)</li>
 * <li>s=3-5: skips columns 3 until 5 (of all pages)</li>
 * <li>s=30.2: skips column 2 of page 30</li>
 * <li>s=-30.2: skips all columns up and including column 2 of page 30</li>
 * <li>s=30.2-31.5: skips column 2 of page 30 until column 5 of page 31</li>
 * </ul>
 * You can combine the rules above by separating them with a comma, e.g.:
 * <ul>
 * <li>s=e,5: skips even columns and column 5 (of all pages)</li>
 * <li>s=2,4: skips column 2 and column 4 (of all pages)</li>
 * <li>s=2-4,8: skips column 2-4 and column 8 (of all pages)</li>
 * <li>s=e,3-7,30.2-31.5: skips even columns, columns 3-7, and column 2 of page
 * 30 until column 5 of page 31</li>
 * </ul>
 * 
 * @author nilsth
 *
 *
 */
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
