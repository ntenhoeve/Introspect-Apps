package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.uneven;

import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;

public class SkipUnevenColumnRule extends SkipColumnRule {

	public static final String UNEVEN_ABBREVIATION = "u";

	private static final Regex SKIP_UNEVEN_COLUMN_REGEX = new Regex().ignoreCase().beginOfLine().literal(UNEVEN_ABBREVIATION).endOfLine();

	private static final MatchRules SKIP_UNEVEN_COLUMN_ATTRIBUTE_VALUE_RULES = new MatchRules()//
			.add(TokenNodePredicate.rest(SKIP_UNEVEN_COLUMN_REGEX));


	public SkipUnevenColumnRule() {
		super(SKIP_UNEVEN_COLUMN_ATTRIBUTE_VALUE_RULES);
	}

	@Override
	protected SkipColumnNode createSkipColumnNode(MatchResults matchResults) {
		return new SkipUnevenColumnNode();
	}

}
