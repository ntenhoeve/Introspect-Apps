package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.braces;

public enum BracedAttributeName {
	SKIP; //


	public String lowerCase() {
		return this.name().toLowerCase();
	}

	static BracedAttributeName getForAbbreviation(String abbreviationToFind) {
		for (BracedAttributeName name : values()) {
			if (name.lowerCase().equals(abbreviationToFind.toLowerCase()))
				return name;
		}
		throw new RuntimeException(abbreviationToFind+" is not a valid attribute abreviation");
	}

}
