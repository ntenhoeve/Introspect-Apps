package nth.meyn.control.system.configurator.dom.label;

import java.util.ArrayList;
import java.util.List;

public class Labels {
	private final List<Label> labels;

	public Labels() {
		labels = new ArrayList<>();
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void addEmptyLabel() {
		labels.add(new Label());
	}

}
