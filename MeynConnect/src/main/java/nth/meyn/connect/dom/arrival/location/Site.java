package nth.meyn.connect.dom.arrival.location;

import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;

@Description(defaultEnglish="Physical, geographical or local grouping determined by the enterprise")
public class Site {
private String name;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

}
