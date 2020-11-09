package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter;

import java.util.function.Predicate;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.matcher.predicate.NodeTypeAndMatchChildrenPredicate;
import nth.reflect.util.parser.node.matcher.predicate.NodeTypePredicate;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;
import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BraceNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributeName;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces.BracedAttributePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate.TokenNodePredicate;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;

/**
 * <h3>Counter</h3>
 * <p>
 * You can use counters when the data type uses an array. Counters are added by
 * adding the following text to the {@link DataType} comment:
 * <p>
 * {cnt &lt;skip=skip rules&gt; &lt;array=array number&gt;}
 * <p>
 * &lt;skip=skip rules&gt; are optional and can be used to skip numbers. e.g.:
 * the counters always start at 0. Use skip=0, if you want the counter to start
 * at 1. TODO insert counter skip rules
 * <p>
 * &lt;array=array number&gt; is optional:
 * <ul>
 * <li>no array attribute or array=0: counter increases when any array is
 * increased and will not reset within the array</li>
 * <li>array=1: counter increases when the last array is increased, the counter
 * is reset if the second last array is increased</li>
 * <li>array=2: counter increases when the second last array is increased, the
 * counter is reset if the third last array is increased</li>
 * <li>etcetera</li>
 * </ul>
 * <p>
 * {insert CounterRuleExampleTest}
 * <p>
 * TODO {insert CounterRuleSkipExampleTest}
 * <p>
 * TODO {insert CounterRuleArrayExampleTest}
 * <p>
 * TODO {insert CounterRuleSkipArrayExampleTest}
 * 
 * @author nilsth
 */

public class CounterRule implements NodeParserRule {
//	public static final String COUNTER_ABREVIATION = "cnt";
//	private static final Regex REGEX_COUNTER_ABBREVIATION = new Regex().ignoreCase().beginOfLine().literal(COUNTER_ABREVIATION)
//			.endOfLine();
//	private static final Predicate<Node> WHITESPACE_PREDICATE = TokenNodePredicate.whiteSpace();
//	
	
//	private static final Predicate<Node> SKIP_ATTRIBUTE_VALUE_PREDICATE = new NodeTypePredicate(
//			CounterSkipNode.class);
//	private static final MatchRules SKIP_ATTRIBUTE_VALUE_RULES = new MatchRules()
//			.add(SKIP_ATTRIBUTE_VALUE_PREDICATE);
//		private static final BracedAttributePredicate SKIP_ATTRIBUTE_PREDICATE = new BracedAttributePredicate(
//			BracedAttributeName.SKIP, SKIP_ATTRIBUTE_VALUE_RULES);
//	private static final MatchRules SKIP_ATTRIBUTE_RULES = new MatchRules().add(SKIP_ATTRIBUTE_PREDICATE);
//	private static final NodeTypeAndMatchChildrenPredicate BRACED_SKIP_PREDICATE = new NodeTypeAndMatchChildrenPredicate(
//			BraceNode.class, SKIP_ATTRIBUTE_RULES);
	
//	private static final Predicate<Node> ARRAY_ATTRIBUTE_VALUE_PREDICATE = new NodeTypePredicate(
//			CounterArrayNode.class);
//	private static final MatchRules ARRAY_ATTRIBUTE_VALUE_RULES = new MatchRules()
//			.add(ARRAY_ATTRIBUTE_VALUE_PREDICATE);
//	private static final BracedAttributePredicate ARRAY_ATTRIBUTE_PREDICATE = new BracedAttributePredicate(
//			BracedAttributeName.ARRAY, ARRAY_ATTRIBUTE_VALUE_RULES);
//	private static final MatchRules ARRAY_ATTRIBUTE_RULES = new MatchRules().add(ARRAY_ATTRIBUTE_PREDICATE);
//	private static final NodeTypeAndMatchChildrenPredicate BRACED_ARRAY_PREDICATE = new NodeTypeAndMatchChildrenPredicate(
//			BraceNode.class, ARRAY_ATTRIBUTE_RULES);
//	
//	private static final MatchRules MATCH_RULES = new MatchRules()//
//			.add(TokenNodePredicate.rest(REGEX_COUNTER_ABBREVIATION))//
//			.add(WHITESPACE_PREDICATE, Repetition.zeroOrMore())//
////			.add(BRACED_SKIP_PREDICATE, Repetition.zeroOrOnce())//
//			.add(BRACED_ARRAY_PREDICATE, Repetition.zeroOrOnce())//
////			.add(BRACED_SKIP_PREDICATE, Repetition.zeroOrOnce())//
//			.add(WHITESPACE_PREDICATE, Repetition.zeroOrMore());
	
	
	private static final MatchRules MATCH_RULES =new MatchRules().add(new CounterPredicate());


	@Override
	public MatchRules getMatchRules() {
		return MATCH_RULES;
	}

	/**
	 * Replaces {@link Node}s that matches a {@link CounterRule#MATCH_RULES} with a
	 * {@link ComponentCodeNode}
	 */
	@Override
	public void removeOrReplace(MatchResults matchResults) {
		int array=getArray(matchResults);
		//TODO skiprules;
		CounterNode counterNode=new CounterNode(array);
		matchResults.replaceFoundNodesWith(counterNode);
	}

	private int getArray(MatchResults matchResults) {
		// TODO Auto-generated method stub
		return 0;
	}

}
