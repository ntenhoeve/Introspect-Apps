package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.ack.AcknowledgeToken;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.array.ArrayToken;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.ComponentCodeWithBracketsToken;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.ComponentCodeWithoutBracketsToken;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.detail.DetailsToken;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.priority.PriorityToken;

public class TokenDefinitions {
	public final static Collection<TokenDefinition> ALL;

	static {
		List<TokenDefinition> definitions=new ArrayList<>();
		definitions.add(new ComponentCodeWithoutBracketsToken());
		definitions.add(new ComponentCodeWithBracketsToken());
		definitions.add(new AcknowledgeToken());
		definitions.add(new DetailsToken());
		definitions.add(new ArrayToken());
		definitions.add(new PriorityToken());
		ALL=Collections.unmodifiableCollection(definitions);
	}

	
	
}
