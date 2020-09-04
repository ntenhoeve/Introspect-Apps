package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.PageColumn;

public interface SkipRule {

	boolean appliesTo(PageColumn pageColumn);

	PageColumn getNext(PageColumn pageColumn);

}