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

	static BracedAttributeName getForAbbreviation(String abbreviationToFind) {
		for (BracedAttributeName name : values()) {
			if (name.getAbbreviation().equals(abbreviationToFind.toLowerCase()))
				return name;
		}
		throw new RuntimeException(abbreviationToFind+" is not a valid attribute abreviation");
	}

}
