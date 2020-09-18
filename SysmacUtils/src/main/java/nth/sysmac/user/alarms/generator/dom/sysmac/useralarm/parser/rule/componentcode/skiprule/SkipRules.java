package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SkipRules implements Iterable<SkipRule> {

	private final List<SkipRule> skipRules;

	public SkipRules() {
		skipRules = new ArrayList<>();
		skipRules.add(new MaxColumnRule());
	}

	public void add(SkipRule skipRule) {
		skipRules.add(skipRule);
	}

	public void addAll(List<SkipRule> rules) {
		skipRules.addAll(rules);
	}

	@Override
	public Iterator<SkipRule> iterator() {
		return Collections.unmodifiableList(skipRules).iterator();
	}

}
