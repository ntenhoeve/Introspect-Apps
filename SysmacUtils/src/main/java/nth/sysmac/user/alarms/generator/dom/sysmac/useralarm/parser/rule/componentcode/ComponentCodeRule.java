package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import java.util.List;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.text.NodesToTextConverter;
import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;

/**
 * <h3>Component Code</h3> Almost all alarm messages start with one or component
 * codes so that it can easily be located in the electric schematic or in the
 * field.<br>
 * The format of a component code is:
 * <p>
 * 30M3, where:
 * <ul>
 * <li>30 = page number</li>
 * <li>M = Indicator of type of component, e.g.: F=Fuse, Q=Motor protector,
 * S=Switch, M=Motor, K=Relay, U=electronic device</li>
 * <li>3 = Column number</li>
 * </ul>
 * 
 * @author nilsth
 */

public class ComponentCodeRule implements NodeParserRule {

	private static final Regex REGEX_LETTER = new Regex().ignoreCase().letter();

	private static final MatchRules PAGE_NUMBER_RULES = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());
	private static final MatchRules COMPONENT_LETTER = new MatchRules()//
			.add(TokenNodePredicate.rest(REGEX_LETTER));
	public static final MatchRules COLUMN_NUMBER_RULES = new MatchRules()//
			.add(TokenNodePredicate.unsignedInteger());
	public static final MatchRules MATCH_RULES = new MatchRules()//
			.add(PAGE_NUMBER_RULES)//
			.add(COMPONENT_LETTER)//
			.add(COLUMN_NUMBER_RULES);

	@Override
	public MatchRules getMatchRules() {
		return MATCH_RULES;
	}

	/**
	 * Replaces {@link Node}s that matches a {@link ComponentCodeRule#MATCH_RULES} with a
	 * {@link ComponentCodeNode}
	 */
	@Override
	public void removeOrReplace(MatchResults matchResults) {
		int pageNumber = getPageNumber(matchResults);
		Character componentLetter = getComponentLetter(matchResults);
		int columnNumber = getColumnNumber(matchResults);
		ComponentCodeNode componentCodeNode = new ComponentCodeNode(pageNumber, componentLetter, columnNumber);
		matchResults.replaceMatchingNodesWith(componentCodeNode);
	}

	private int getColumnNumber(MatchResults matchResults) {
		 List<Node> nodes = matchResults.getFoundNodes(COLUMN_NUMBER_RULES);
		 String numberAsString=NodesToTextConverter.convert(nodes);
		 Integer columnNumber=Integer.valueOf(numberAsString);
		return columnNumber;

	}

	private Character getComponentLetter(MatchResults matchResults) {
		 List<Node> nodes = matchResults.getFoundNodes(COMPONENT_LETTER);
		 String letter=NodesToTextConverter.convert(nodes);
		 Character componentLetter=letter.trim().toLowerCase().charAt(0);
		return componentLetter;

	}

	private int getPageNumber(MatchResults matchResults) {
		 List<Node> nodes = matchResults.getFoundNodes(PAGE_NUMBER_RULES);
		 String numberAsString=NodesToTextConverter.convert(nodes);
		 Integer pageNumber=Integer.valueOf(numberAsString);
		return pageNumber;
	}
}
