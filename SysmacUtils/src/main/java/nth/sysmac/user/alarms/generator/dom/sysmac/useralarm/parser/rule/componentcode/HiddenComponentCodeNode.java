package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumns;

/**
 * @see {@link HiddenComponentCodeRule}.
 * @author nilsth
 *
 */
public class HiddenComponentCodeNode extends ComponentCodeNode {

	public HiddenComponentCodeNode(int page, char letter, int column) {
		super(page, letter, column);
	}

	public HiddenComponentCodeNode(int page, char letter, int column, SkipColumns skipColumns) {
		super(page, letter, column, skipColumns);
	}
	
}
