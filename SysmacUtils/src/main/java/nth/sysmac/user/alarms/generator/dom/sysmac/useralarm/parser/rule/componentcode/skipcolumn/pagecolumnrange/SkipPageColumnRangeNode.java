package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.pagecolumnrange;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;

public class SkipPageColumnRangeNode extends SkipColumnNode {
	private final int minPage;
	private final int minColumn;
	private final int maxPage;
	private final int maxColumn;

	public SkipPageColumnRangeNode(int minPage, int minColumn, int maxPage, int maxColumn) {
		validateMinColumn(minColumn);
		this.minPage = minPage;
		this.minColumn = minColumn;
		validateMaxColumn(maxColumn);
		this.maxPage = maxPage;
		this.maxColumn = maxColumn;
		validateMinIsSmallerOrEqualThanMaxPage(minPage, maxPage);
	}

	private void validateMinIsSmallerOrEqualThanMaxPage(int minPage2, int maxPage2) {
		if (minPage > maxPage) {
			throw new RuntimeException(
					"The minimum page number must be smaller or equal than the maximum page number.");
		}
	}


	private void validateMaxColumn(int maxColumn) {
		if (maxColumn < 2 || maxColumn > 8) {
			throw new RuntimeException(
					"The maximum column number must be between 2 and 8, and can not be: " + maxColumn);
		}
	}

	private void validateMinColumn(int minColumn) {
		if (minColumn < 1 || minColumn > 7) {
			throw new RuntimeException(
					"The minimum column number must be between 1 and 7, and can not be: " + minColumn);
		}
	}

	@Override
	public boolean appliesTo(ComponentCodeNode componentCode) {
		int page = componentCode.getPage();
		int column = componentCode.getColumn();
		if (page<minPage) {
			return false;
		} else if (page==minPage && column<minColumn) {
			return false;
		} else if (page>maxPage) {
			return false;
		} else if (page==maxPage && column>maxColumn) {
			return false;
		}
		return true;
	}

	@Override
	public void goToNext(ComponentCodeNode componentCode) {
		int page=maxPage;
		int column = maxColumn + 1;
		componentCode.setPage(page);
		componentCode.setColumn(column);
	}

	@Override
	protected Object[] getFieldValues() {
		return new Integer[] { minPage, minColumn, maxPage, maxColumn };
	}

	@Override
	public String toString() {
		TitleBuilder titleBuilder = new TitleBuilder();
		titleBuilder.append(SkipPageColumnRangeNode.class.getSimpleName());
		titleBuilder.append(" minPage", minPage);
		titleBuilder.append(", minColumn", minColumn);
		titleBuilder.append(" maxPage", maxPage);
		titleBuilder.append(", maxColumn", maxColumn);
		return titleBuilder.toString();
	}
}
