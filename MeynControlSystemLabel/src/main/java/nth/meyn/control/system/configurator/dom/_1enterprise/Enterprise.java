package nth.meyn.control.system.configurator.dom._1enterprise;

import java.util.ArrayList;
import java.util.List;

import nth.meyn.control.system.configurator.dom._2site.Site;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Enterprise {
	private String name;
	private List<Site> sites = new ArrayList<>();

	@Order(10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Order(20)
	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	@Override
	public String toString() {
		return name;
	}
}
