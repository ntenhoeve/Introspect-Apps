package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.skiprule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.util.regex.Regex;

/**
 * Skipping: You can skip pages and columns when using arrays. Add the following
 * suffixes after the component code (separated by spaces)
 * <ul>
 * <li>skp=20: skips page 20</li>
 * <li>skp=20: skips even pages and page 20</li>
 * <li>skp=20,30: skips page 20 and page 30</li>
 * <li>skp=20-30,33: skips page 20-30 and page 33</li>
 * <li>skp=e: skips even pages</li>
 * <li>skp=u: skips un-even pages</li>
 * <li>skp=e,20-30,33: skips even pages, page 20-30 and page 33</li>
 * <li>skc=e: skips even columns</li>
 * <li>skc=u: skips uneven columns</li>
 * <li>skpc=20.2,21.3: skips page 20, column1 and page 21, column 3</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
public class ParsableSkipRules {

	public static List<ParsableSkipRule> ALL;
	public static List<Regex> ALL_REGEX;
	static {
		List<ParsableSkipRule> rules = new ArrayList<>();
		rules.add(new SkipEvenColumnRule());
		rules.add(new SkipUnEvenColumnRule());
		ALL = Collections.unmodifiableList(rules);
		ALL_REGEX = ALL.stream().map(r -> r.getRegex()).collect(Collectors.toUnmodifiableList());
	}

	public static SkipRules parse(String skipRulesString) {
		SkipRules result = new SkipRules();
		for (ParsableSkipRule ruleType : ALL) {
			Regex regex = ruleType.getRegex();
			List<String> rules = regex.findMatches(skipRulesString);
			for (String rule : rules) {
				List<ParsableSkipRule> newRules = ruleType.parse(rule);
				result.addAll(newRules);
			}
		}
		return result;
	}

}
