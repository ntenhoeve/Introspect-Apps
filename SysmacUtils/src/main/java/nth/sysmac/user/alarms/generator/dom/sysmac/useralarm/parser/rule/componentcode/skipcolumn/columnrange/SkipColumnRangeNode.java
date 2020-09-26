package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;

public class SkipColumnRangeNode extends SkipColumnNode {
	private final int minColumn;
	private final int maxColumn;

	public SkipColumnRangeNode(int minColumn, int maxColumn) {
		validateMinColumn(minColumn);
		this.minColumn = minColumn;
		validateMaxColumn(maxColumn);
		this.maxColumn = maxColumn;
	}

	private void validateMaxColumn(int maxColumn) {
		if (maxColumn<2 || maxColumn>8) {
			throw new RuntimeException("The maximum column number must be between 2 and 8, and can not be: "+maxColumn);
		}
	}

	private void validateMinColumn(int minColumn) {
		if (minColumn<1 || minColumn>7) {
			throw new RuntimeException("The minimum column number must be between 1 and 7, and can not be: "+minColumn);
		}
	}

	@Override
	public boolean appliesTo(ComponentCodeNode componentCode) {
		int column = componentCode.getColumn();
		boolean inRange = column >= minColumn && column <= maxColumn;
		return inRange;
	}

	@Override
	public void goToNext(ComponentCodeNode componentCode) {
		int column = maxColumn + 1;
		componentCode.setColumn(column);
	}

	@Override
	protected Object[] getFieldValues() {
		return new Integer[] { minColumn, maxColumn };
	}
	
	@Override
	public String toString() {
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.append(SkipColumnRangeNode.class.getSimpleName());
		titleBuilder.append(" minColumn", minColumn);
		titleBuilder.append(", maxColumn", maxColumn);
		return titleBuilder.toString();
	}
}
