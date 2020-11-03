package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces;

public enum BracedAttributeName {
	SKIP("skip"); //

	private final String name;

	BracedAttributeName(String name) {
		this.name = name.toLowerCase();
	}

	public String getName() {
		return name;
	}

	static BracedAttributeName getForAbbreviation(String abbreviationToFind) {
		for (BracedAttributeName name : values()) {
			if (name.getName().equals(abbreviationToFind.toLowerCase()))
				return name;
		}
		throw new RuntimeException(abbreviationToFind+" is not a valid attribute abreviation");
	}

}
