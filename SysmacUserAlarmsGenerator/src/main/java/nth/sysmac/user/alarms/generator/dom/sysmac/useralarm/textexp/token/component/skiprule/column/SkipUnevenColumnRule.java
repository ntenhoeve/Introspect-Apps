package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.column;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.PageColumn;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRule;

public class SkipUnevenColumnRule extends SkipRule {

	@Override
	public boolean appliesTo(PageColumn pageColumn) {
		boolean isEven = pageColumn.getColumn() % 2 == 0;
		return !isEven;
	}

	@Override
	public PageColumn getNext(PageColumn pageColumn) {
		int page = pageColumn.getPage();
		int nextColumn = pageColumn.getColumn() + 1;
		return new PageColumn(page, nextColumn);
	}

	@Override
	protected Object[] getFieldValues() {
		return new Object[0];
	}

}
