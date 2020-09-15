package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.SysmacUserAlarmsGenerator;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.column.SkipColumnParsers;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.page.SkipPageParsers;
/**
 * <h3>Skip MatchRules</h3>
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
public class SkipRuleParsers extends DelegatingSkipRuleParser{

	@Override
	protected List<SkipRuleParser> createSkipRuleParsers() {
		List<SkipRuleParser> skipRuleParsers = new ArrayList<>();
		skipRuleParsers.add(new SkipColumnParsers());
		skipRuleParsers.add(new SkipPageParsers());
		return skipRuleParsers;
	}

	@Override
	public Regex getRegex() {
		return null;
	}

	@Override
	protected List<String> split(String expression) {
//		Regex REGEX_SPLIT=new Regex().ignoreCase().literal("s").literals("cp").whiteSpace(Repetition.zeroOrMoreTimes()).literal("=").whiteSpace(Repetition.zeroOrMoreTimes());
//		Matcher matcher = REGEX_SPLIT.toMatcher(expression);
//		 while (matcher.find()) {
//		        System.out.print("Start index: " + matcher.start());
//		        System.out.print(" End index: " + matcher.end());
//		        System.out.println(" Found: " + matcher.group());
//		    }
		return null;
		
	}

}
