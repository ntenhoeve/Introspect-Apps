package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.page;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule.SkipRule;

public class SkipEvenPageRule extends SkipRule {

	@Override
	public boolean appliesTo(ComponentCodeNode componentCodeNode) {
		boolean isEven = componentCodeNode.getPage() % 2 == 0;
		return isEven;
	}

	@Override
	public void goToNext(ComponentCodeNode componentCodeNode) {
		int nextColumn = 1;
		componentCodeNode.setColumn(nextColumn);
		int nextpage = componentCodeNode.getPage() + 1;
		componentCodeNode.setPage(nextpage);
	}

	@Override
	protected Object[] getFieldValues() {
		return new Object[0];
	}

}
