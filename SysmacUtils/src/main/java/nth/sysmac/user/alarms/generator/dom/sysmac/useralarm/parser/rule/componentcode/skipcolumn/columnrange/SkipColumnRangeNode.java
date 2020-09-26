package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.columnrange;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;

public class SkipColumnRangeNode extends SkipColumnNode {
	private final int minColumn;
	private final int maxColumn;

	public SkipColumnRangeNode(int minColumn, int maxColumn) {
		this.minColumn = minColumn;
		this.maxColumn = maxColumn;
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
