package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.skiprule;

import java.util.Arrays;
import java.util.List;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.PageColumn;

public class SkipEvenColumnRule implements ParsableSkipRule {

	private static final Regex REGEX_SKEC = new Regex().whiteSpace(Repetition.oneOrMoreTimes()).literal("skec"); //SKip Even Columns

	@Override
	public boolean appliesTo(PageColumn pageColumn) {
		boolean isEven = pageColumn.getColumn() %2 ==0;
		return isEven;
	}

	@Override
	public PageColumn getNext(PageColumn pageColumn) {
		int page = pageColumn.getPage();
		int nextColumn = pageColumn.getColumn()+1;
		return new PageColumn(page, nextColumn);
	}

	@Override
	public Regex getRegex() {
		return REGEX_SKEC;
	}

	@Override
	public List<ParsableSkipRule> parse(String tag) {
		return Arrays.asList(this);
	}

}
