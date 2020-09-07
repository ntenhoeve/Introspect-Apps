package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.PageColumn;

public class MaxColumnRule extends SkipRule {

	@Override
	public boolean appliesTo(PageColumn pageColumn) {
		return pageColumn.getColumn()>PageColumn.COLUMN_MAX;
	}

	@Override
	public PageColumn getNext(PageColumn pageColumn) {
		int nextColumn = PageColumn.COLUMN_MIN;
		int nextPage = pageColumn.getPage()+1;
		PageColumn next = new PageColumn(nextPage, nextColumn);
		return next;
	}

	@Override
	protected Object[] getFieldValues() {
		return new Object[0];
	}

}
