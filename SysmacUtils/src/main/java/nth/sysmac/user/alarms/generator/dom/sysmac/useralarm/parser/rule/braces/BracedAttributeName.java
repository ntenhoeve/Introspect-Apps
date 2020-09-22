package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces;

public enum BracedAttributeName {
	SKIP("s"); //

	private final String abbreviation;

	BracedAttributeName(String abbreviation) {
		this.abbreviation = abbreviation.toLowerCase();
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	static BracedAttributeName getForAbbreviation(String abbreviation) {
		for (BracedAttributeName name : values()) {
			if (name.getAbbreviation().equals(abbreviation.toLowerCase()))
				return name;
		}
		throw new RuntimeException("Could not find attribute abbreviation: " + abbreviation);
	}

}
