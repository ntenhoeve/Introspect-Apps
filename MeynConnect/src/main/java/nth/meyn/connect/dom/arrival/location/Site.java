package nth.meyn.connect.dom.arrival.location;

import nth.introspect.layer5provider.reflection.behavior.description.Description;

@Description(englishDescription="Physical, geographical or local grouping determined by the enterprise")
public class Site {
private String name;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

}
