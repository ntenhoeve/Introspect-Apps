package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.even;

import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;

/**
 * s=e: skips even columns (of all pages)
 * 
 * @author nilsth
 * @see SkipColumnRule
 */
public class SkipEvenColumnRule extends SkipColumnRule {

	public static final String EVEN_ABBREVIATION = "e";

	private static final Regex SKIP_EVEN_COLUMN_REGEX = new Regex().ignoreCase().beginOfLine()
			.literal(EVEN_ABBREVIATION).endOfLine();

	private static final MatchRules SKIP_EVEN_COLUMN_ATTRIBUTE_VALUE_RULES = new MatchRules()//
			.add(TokenNodePredicate.rest(SKIP_EVEN_COLUMN_REGEX));

	public SkipEvenColumnRule() {
		super(SKIP_EVEN_COLUMN_ATTRIBUTE_VALUE_RULES);
	}

	@Override
	protected SkipColumnNode createSkipColumnNode(MatchResults matchResults) {
		return new SkipEvenColumnNode();
	}

}
