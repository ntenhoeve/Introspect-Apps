package nth.meyn.control.system.configurator.dom._3area;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import nth.meyn.control.system.configurator.dom._4workcenter.WorkCenterTemplate;
import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
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
	@FontIcon(fontIconUrl = FontAwesomeUrl.INFO_CIRCLE)
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
	@PropertyActionMethod("WorkCenterTemplates")
	@ReadOnlyActionMethod
	@FontIcon(fontIconUrl = FontAwesomeUrl.EYE)
	public WorkCenterTemplate viewWorkCenterTemplate(WorkCenterTemplate workCenterTemplate) {
		return workCenterTemplate;
	}

	@Order(10.1)
	@PropertyActionMethod("WorkCenterTemplates")
	@FontIcon(fontIconUrl = FontAwesomeUrl.EDIT)
	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void modifyWorkCenterTemplate(WorkCenterTemplate workCenterTemplate) {
	}

	@Order(10.3)
	@ParameterFactory
	@PropertyActionMethod("WorkCenterTemplates")
	@FontIcon(fontIconUrl = FontAwesomeUrl.PLUS)
	public void addNewWorkCenterTemplate(WorkCenterTemplate workCenterTemplate) {
		workCenterTemplates.add(workCenterTemplate);
	}

	@Order(10.4)
	@PropertyActionMethod("WorkCenterTemplates")
	@FontIcon(fontIconUrl = FontAwesomeUrl.TRASH)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void removeWorkCenterTemplate(WorkCenterTemplate workCenterTemplate) {
		workCenterTemplates.remove(workCenterTemplate);
	}

	public boolean removeWorkCenterTemplateHidden() {
		return workCenterTemplates.size() == 0;
	}

	@Order(10.5)
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_UP)
	@PropertyActionMethod("WorkCenterTemplates")
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void moveWorkCenterTemplateUp(WorkCenterTemplate workCenterTemplate) {
		int index = workCenterTemplates.indexOf(workCenterTemplate);
		if (index > 0) {
			workCenterTemplates.remove(workCenterTemplate);
			workCenterTemplates.add(index - 1, workCenterTemplate);
		}

	}

	public boolean moveWorkCenterTemplateUpHidden() {
		return workCenterTemplates.size() < 2;
	}

	@Order(10.6)
	@PropertyActionMethod("WorkCenterTemplates")
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_DOWN)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void moveWorkCenterTemplateDown(WorkCenterTemplate workCenterTemplate) {
		int index = workCenterTemplates.indexOf(workCenterTemplate);
		if (index < workCenterTemplates.size() - 1) {
			workCenterTemplates.remove(workCenterTemplate);
			workCenterTemplates.add(index + 1, workCenterTemplate);
		}
	}

	public boolean moveWorkCenterTemplateDownHidden() {
		return workCenterTemplates.size() < 2;
	}

	@Override
	public String toString() {
		return TitleBuilder.getInstance().append(name).toString();
	}
}
