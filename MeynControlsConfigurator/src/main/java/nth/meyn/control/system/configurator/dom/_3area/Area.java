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
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ReadOnlyActionMethod;

@Description(defaultEnglish = Area.DESCRIPTION)
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
	@ReadOnlyActionMethod
	@FontIcon(fontIconUrl = FontAwesomeUrl.EYE)
	public WorkCenterTemplate workCenterTemplatesView(WorkCenterTemplate workCenterTemplate) {
		return workCenterTemplate;
	}

	@Order(10.1)
	@FontIcon(fontIconUrl = FontAwesomeUrl.EDIT)
	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void workCenterTemplatesModify(WorkCenterTemplate workCenterTemplate) {
	}

	@Order(10.3)
	@ParameterFactory
	@FontIcon(fontIconUrl = FontAwesomeUrl.PLUS)
	public void WorkCenterTemplatesAddNew(WorkCenterTemplate workCenterTemplate) {
		workCenterTemplates.add(workCenterTemplate);
	}

	@Order(10.4)
	@FontIcon(fontIconUrl = FontAwesomeUrl.TRASH)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void workCenterTemplatesRemove(WorkCenterTemplate workCenterTemplate) {
		workCenterTemplates.remove(workCenterTemplate);
	}

	public boolean workCenterTemplatesRemoveHidden() {
		return workCenterTemplates.size() == 0;
	}

	@Order(10.5)
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_UP)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void workCenterTemplatesMoveUp(WorkCenterTemplate workCenterTemplate) {
		int index = workCenterTemplates.indexOf(workCenterTemplate);
		if (index > 0) {
			workCenterTemplates.remove(workCenterTemplate);
			workCenterTemplates.add(index - 1, workCenterTemplate);
		}

	}

	public boolean workCenterTemplatesMoveUpHidden() {
		return workCenterTemplates.size() < 2;
	}

	@Order(10.6)
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_DOWN)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void workCenterTemplatesMoveDown(WorkCenterTemplate workCenterTemplate) {
		int index = workCenterTemplates.indexOf(workCenterTemplate);
		if (index < workCenterTemplates.size() - 1) {
			workCenterTemplates.remove(workCenterTemplate);
			workCenterTemplates.add(index + 1, workCenterTemplate);
		}
	}

	public boolean workCenterTemplatesMoveDownHidden() {
		return workCenterTemplates.size() < 2;
	}

	@Override
	public String toString() {
		return new TitleBuilder().append(name).toString();
	}
}
