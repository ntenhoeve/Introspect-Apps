package nth.meyn.control.system.configurator.dom._3area;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import nth.meyn.control.system.configurator.dom._4workcenter.WorkCenterTemplate;
import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.PropertyActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ReadOnlyActionMethod;

@Description(englishDescription = Area.DESCRIPTION)
public class Area {
	static final String DESCRIPTION = "Physical, geographical or local grouping determined by the site, e.g.: Life Bird Handling, Slaughtering, etc";
	private String name;
	private List<WorkCenterTemplate> workCenterTemplates = new ArrayList<>();

	@ReadOnlyActionMethod
	public String info() {
		return Area.class.getSimpleName() + "= " + DESCRIPTION;
	}

	@NotBlank
	@Order(1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Order(10)
	public List<WorkCenterTemplate> getWorkCenterTemplates() {
		return workCenterTemplates;
	}

	public void setWorkCenterTemplates(List<WorkCenterTemplate> workCenterTemplates) {
		this.workCenterTemplates = workCenterTemplates;
	}

	@Order(10.1)
	@ParameterFactory
	@PropertyActionMethod("WorkCenterTemplates")
	public void addNewWorkCenterTemplate(WorkCenterTemplate workCenterTemplate) {
		workCenterTemplates.add(workCenterTemplate);
	}

	@Order(10.2)
	@PropertyActionMethod("WorkCenterTemplates")
	public void removeWorkCenterTemplate(WorkCenterTemplate workCenterTemplate) {
		workCenterTemplates.remove(workCenterTemplate);
	}

	public boolean removeWorkCenterTemplateHidden() {
		return workCenterTemplates.size() == 0;
	}

	@Order(10.3)
	@PropertyActionMethod("WorkCenterTemplates")
	public void moveWorkCenterTemplateUp(WorkCenterTemplate workCenterTemplate) {
		workCenterTemplates.add(workCenterTemplate);
	}

	public boolean moveWorkCenterTemplateUpHidden() {
		return workCenterTemplates.size() < 2;
	}

	@Order(10.4)
	@PropertyActionMethod("WorkCenterTemplates")
	public void moveWorkCenterTemplateDown(WorkCenterTemplate workCenterTemplate) {
		workCenterTemplates.add(workCenterTemplate);
	}

	public boolean moveWorkCenterTemplateDownHidden() {
		return workCenterTemplates.size() < 2;
	}

	@Override
	public String toString() {
		return TitleBuilder.getInstance().append(name).toString();
	}
}
