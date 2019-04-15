package nth.meyn.control.system.configurator.dom._3area;

import java.util.ArrayList;
import java.util.List;

public class AreaFactory {

	private final List<Area> areas;

	public AreaFactory() {
		areas = new ArrayList<>();
		areas.add(createArea("Life Bird Handling"));
		areas.add(createArea("Slaughtering"));
		areas.add(createArea("Evisceration"));
		areas.add(createArea("Chilling"));
		areas.add(createArea("Cut up"));
		areas.add(createArea("Deboning"));
		areas.add(createArea("Packaging and Dispatch"));
		areas.add(createArea("By product handling"));
		areas.add(createArea("Plant Automatization and Logistics"));
	}

	public List<Area> getAll() {
		return areas;
	}

	private Area createArea(String name) {
		Area area = new Area();
		area.setName(name);
		return area;
	}
}
