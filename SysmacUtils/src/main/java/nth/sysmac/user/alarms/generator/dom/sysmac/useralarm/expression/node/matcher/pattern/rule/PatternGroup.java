package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.Necessity;

public class PatternGroup {
	private final String name;
	private final Necessity necessity;

	public PatternGroup(String name, Necessity necessity) {
		this.name = name;
		this.necessity = necessity;
	}

	public String getName() {
		return name;
	}

	public Necessity getNecessity() {
		return necessity;
	}


}
