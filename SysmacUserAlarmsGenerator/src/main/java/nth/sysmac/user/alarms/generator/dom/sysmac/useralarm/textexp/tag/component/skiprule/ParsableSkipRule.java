package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.skiprule;

import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.Parser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.Token;

public interface ParsableSkipRule  extends SkipRule, Token, Parser<List<ParsableSkipRule>> {

}
