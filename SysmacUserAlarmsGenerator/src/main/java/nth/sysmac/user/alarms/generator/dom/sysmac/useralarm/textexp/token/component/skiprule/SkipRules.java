package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule;

/**
 * <h3>Skip Rules</h3>
 * The {@link SysmacUserAlarmsGenerator} will generate the component code's when
 * using arrays (e.g. when using an array of line drive's). In some cases you
 * need to specify how these component codes need to be generated. Often some
 * columns and or pages need to be skipped, e.g.: Variable Frequency Drives often skip on column.
 * <p>
 * {@insert SkipColumnParser}
 * <p>
 * {@insert SkipPageParser} 
 *  
 * @author nilsth
 *
 */

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
