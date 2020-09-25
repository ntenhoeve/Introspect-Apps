package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ComponentCodeSkipRulesNode implements Iterable<ComponentCodeSkipRule> {

	private final List<ComponentCodeSkipRule> componentCodeSkipRules;

	public ComponentCodeSkipRulesNode() {
		componentCodeSkipRules = new ArrayList<>();
		componentCodeSkipRules.add(new MaxColumnRule());
	}

	public void add(ComponentCodeSkipRule componentCodeSkipRule) {
		componentCodeSkipRules.add(componentCodeSkipRule);
	}

	public void addAll(List<ComponentCodeSkipRule> rules) {
		componentCodeSkipRules.addAll(rules);
	}

	@Override
	public Iterator<ComponentCodeSkipRule> iterator() {
		return Collections.unmodifiableList(componentCodeSkipRules).iterator();
	}

}
