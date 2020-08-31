package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.skiprule;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.PageColumn;

public interface SkipRule {

	boolean appliesTo(PageColumn pageColumn);

	PageColumn getNext(PageColumn pageColumn);

}