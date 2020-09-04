package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRules;

public class PageColumn {

	public static final int COLUMN_MIN = 1;
	public static final int COLUMN_MAX = 8;
	protected int page;
	protected int column;
	protected final SkipRules skipRules;

	public PageColumn(int page, int column, SkipRules skipRules) {
		this.skipRules = skipRules;

		verifyPage(page);
		this.page = page;

		verify(column);
		this.column = column;
	}

	private void verify(int column) {
		if (column < COLUMN_MIN || column > COLUMN_MAX) {
			throw new RuntimeException("Column must be between 1-8, but was: " + column);
		}
	}

	private void verifyPage(int page) {
		if (page < 0) {
			throw new RuntimeException("Page must be >0, but is: " + page);
		}
	}

	public PageColumn(int page, int column) {
		this(page, column, new SkipRules());
	}

	public int getPage() {
		return page;
	}

	public int getColumn() {
		return column;
	}

	public PageColumn getNext() {
		int nextColumn = column + 1;
		int nextPage = page;
		PageColumn next = new PageColumn(nextPage, nextColumn);

		boolean skipped;
		do {
			skipped = false;
			for (SkipRule skipRule : skipRules) {
				if (skipRule.appliesTo(next)) {
					next = skipRule.getNext(next);
					skipped = true;
				}
			}
		} while (!skipped);
		return next;
	}

	@Override
	public String toString() {
		return page + "." + column;
	}

}
