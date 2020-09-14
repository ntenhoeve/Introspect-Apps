package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule;

public class Group {
	private final String name;
	private final Necessity necessity;

	public Group(String name, Necessity necessity) {
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
