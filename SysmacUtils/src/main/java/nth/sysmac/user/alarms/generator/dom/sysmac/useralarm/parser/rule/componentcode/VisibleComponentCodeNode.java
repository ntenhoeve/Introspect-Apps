package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.SkipColumns;

/**
 * @see {@link VisibleComponentCodeRule}.
 * @author nilsth
 *
 */
public class VisibleComponentCodeNode extends ComponentCodeNode {

	public VisibleComponentCodeNode(int page, char letter, int column) {
		super(page, letter, column);
	}

	public VisibleComponentCodeNode(int page, char letter, int column, SkipColumns skipColumns) {
		super(page, letter, column, skipColumns);
	}
	
}
