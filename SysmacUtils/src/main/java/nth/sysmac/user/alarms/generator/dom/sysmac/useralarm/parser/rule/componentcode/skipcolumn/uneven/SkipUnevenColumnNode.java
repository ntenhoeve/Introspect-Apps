package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.uneven;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumnNode;
/**
 * @see SkipUnevenColumnRule
 * @author nilsth
 *
 */
public class SkipUnevenColumnNode extends SkipColumnNode<SkipUnevenColumnNode> {

	@Override
	public boolean appliesTo(ComponentCodeNode componentCodeNode) {
		boolean isEven = componentCodeNode.getColumn() % 2 == 0;
		return !isEven;
	}

	@Override
	public void goToNext(ComponentCodeNode componentCodeNode) {
		int nextColumn = componentCodeNode.getColumn() + 1;
		componentCodeNode.setColumn(nextColumn);
	}

	@Override
	protected Object[] getFieldValues() {
		return new Object[0];
	}

}
