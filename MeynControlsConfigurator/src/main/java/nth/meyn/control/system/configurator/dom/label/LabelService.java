package nth.meyn.control.system.configurator.dom.label;

import nth.meyn.control.system.configurator.dom._4workcenter.WorkCenter;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class LabelService {

	@ParameterFactory
	public Labels createLabels(WorkCenter workCenter) {
		Labels labels = new Labels();
		// TODO populate labels
		// TODO create a downloadStream to download a MS Word document with
		// labels
		return labels;
	}

	public Labels emptyLabels() {
		// TODO create a downloadStream to download a MS Word document with
		// labels
		Labels labels = new Labels();
		labels.getLabels().add(new Label());
		return labels;
	}

}
