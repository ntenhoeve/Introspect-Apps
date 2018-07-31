package nth.meyn.connect.dom.arrival.location;

import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;

@Description(englishDescription="Physical, geographical or local grouping determined by the site where processing has started, e.g. Container Arrival System Line 1")
public class StartLocation {
private String name;
private Site site;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Site getSite() {
	return site;
}

public void setSite(Site site) {
	this.site = site;
}

}
