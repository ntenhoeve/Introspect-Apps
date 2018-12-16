package nth.meyn.control.systems.labels.dom.label;

import nth.meyn.control.systems.labels.dom.panel.Panel;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class LabelService {

	@ParameterFactory
	public Labels labelsForPanel(Panel panel) {
		Labels labels = new Labels();
		// TODO populate labels
		return labels;
	}

	public Labels emptyLabels() {
		Labels labels = new Labels();
		labels.getLabels().add(new Label());
		return labels;
	}

	public void print(Labels labels) {
		// TODO
	}

	public void exportToMsExcel(Labels labels) {
		// TODO
	}
}
