package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.page;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.PageColumn;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRule;

public class SkipUnEvenPageRule implements SkipRule {

	@Override
	public boolean appliesTo(PageColumn pageColumn) {
		boolean isEven = pageColumn.getPage() % 2 == 0;
		return !isEven;
	}

	@Override
	public PageColumn getNext(PageColumn pageColumn) {
		int page = pageColumn.getPage() + 1;
		int nextColumn = 1;
		return new PageColumn(page, nextColumn);
	}

}
