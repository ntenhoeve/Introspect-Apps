package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule.column;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule.ComponentCodeSkipRule;

public class SkipEvenColumnRule extends ComponentCodeSkipRule {

	@Override
	public boolean appliesTo(ComponentCodeNode componentCodeNode) {
		boolean isEven = componentCodeNode.getColumn() % 2 == 0;
		return isEven;
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
