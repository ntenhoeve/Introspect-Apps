package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import nth.reflect.util.parser.node.NodeParserRule;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
/**
* <h3>Component code references</h3>
* <p>
* A component reference is a letter (in some cases followed by a number) between curly brackets in a data type comment, e.g.: {Q}. They refer to component code that is already defined in one (or more) data type comments
* 
* <p>
* TODO explain component codes references may change the letter of a component: e.g.
* 30M1 Pump motor 
* {M} too warm
* {Q} protector
* becomes: 
* 30M1 Pump motor too warn
* 30Q1 Pump motor protector
* <p>
* TODO explain that component codes references make component codes between curly brackets visible
* {30M1} Pump motor 
* stop timeout
* {M} too warm
* {Q} protector
* becomes: 
* Pump motor stop timeout
* 30M1 Pump motor too warn
* 30Q1 Pump motor protector
* <p>
* TODO explain that component codes references make component codes between curly brackets visible
* {30M1} {110S1} {80K3} {80K4} Height adjustment motor 
* stop timeout
* {M} too warm
* {Q} protector
* {S} disconnect switch
* {K1} up contactor not closed
* {K2} up contactor not closed
* becomes: 
* ...
* <p>
* 
* @author nilsth
*
*/
public class ComponentCodeReferenceRule implements NodeParserRule {

	@Override
	public MatchRules getMatchRules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeOrReplace(MatchResults matchResults) {
		// TODO Auto-generated method stub

	}

}
