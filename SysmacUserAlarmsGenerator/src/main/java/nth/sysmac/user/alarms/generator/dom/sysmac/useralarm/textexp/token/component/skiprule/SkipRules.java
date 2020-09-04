package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.column.SkipEvenColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.column.SkipUnEvenColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.page.SkipEvenPageRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.page.SkipUnEvenPageRule;

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

	public void addAll(List<SkipRule> rules) {
		skipRules.addAll(rules);
		verifySkipRules();
	}

	private void verifySkipRules() {
		verifyColumnRules();
		verifyPageRules();
	}

	private void verifyColumnRules() {
		boolean containsEvenColumnRule = skipRules.stream().anyMatch(rule -> rule instanceof SkipEvenColumnRule);
		boolean containsUnEvenColumnRule = skipRules.stream().anyMatch(rule -> rule instanceof SkipUnEvenColumnRule);
		if (containsEvenColumnRule && containsUnEvenColumnRule) {
			throw new RuntimeException("Skip rules may not contain both a: " + SkipEvenColumnRule.class.getSimpleName()
					+ " and a: " + SkipUnEvenColumnRule.class.getSimpleName() + " rule.");
		}
	}

	private void verifyPageRules() {
		boolean containsEvenPageRule = skipRules.stream().anyMatch(rule -> rule instanceof SkipEvenPageRule);
		boolean containsUnEvenPageRule = skipRules.stream().anyMatch(rule -> rule instanceof SkipUnEvenPageRule);
		if (containsEvenPageRule && containsUnEvenPageRule) {
			throw new RuntimeException("Skip rules may not contain both a: " + SkipEvenPageRule.class.getSimpleName()
					+ " and a: " + SkipUnEvenPageRule.class.getSimpleName() + " rule.");
		}
	}

	@Override
	public Iterator<SkipRule> iterator() {
		return Collections.unmodifiableList(skipRules).iterator();
	}

}
