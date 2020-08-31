package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.skiprule;

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
		verifySkipRules();
	}

	public void addAll(List<ParsableSkipRule> rules) {
		for (ParsableSkipRule rule : rules) {
			skipRules.add(rule);
		}
		verifySkipRules();
	}

	private void verifySkipRules() {
		boolean containsEvenColumnRule = skipRules.stream().anyMatch(rule -> rule instanceof SkipEvenColumnRule);
		boolean containsUnEvenColumnRule = skipRules.stream().anyMatch(rule -> rule instanceof SkipUnEvenColumnRule);
		if (containsEvenColumnRule && containsUnEvenColumnRule) {
			throw new RuntimeException("Skip rules may not contain both a: " + SkipEvenColumnRule.class.getSimpleName()
					+ " and a: " + SkipUnEvenColumnRule.class.getSimpleName() + " rule.");
		}
	}

	@Override
	public Iterator<SkipRule> iterator() {
		return Collections.unmodifiableList(skipRules).iterator();
	}

}
