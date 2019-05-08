package nth.meyn.control.system.configurator.dom._4workcenter;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import nth.meyn.control.system.configurator.dom._5workunit.WorkUnitRule;
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

@Description(englishDescription = WorkCenterTemplate.DESCRIPTION)
public class WorkCenterTemplate {

	static final String DESCRIPTION = "A template for a physical location on the shop floor that provides specific manufacturing capabilities. This is typically a production line, e.g.: Slaughter Line. At Meyn a Work Center is the equavilant to a Main Control Panel. A work center contains one or more work units, e.g. equipment like scalders or pluckers.";
	private String name;
	private String functionalDesignVersion;
	private String refferenceToProductDefinition;
	private String refferenceToRiskAnnalysis;
	private List<WorkUnitRule> workUnitRules = new ArrayList<>();

	@ReadOnlyActionMethod
	@FontIcon(fontIconUrl = FontAwesomeUrl.INFO_CIRCLE)
	public String info() {
		return getClass().getSimpleName() + "= " + DESCRIPTION;
	}

	@Order(value = 1)
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Order(value = 10)
	public String getFunctionalDesignVersion() {
		return functionalDesignVersion;
	}

	public void setFunctionalDesignVersion(String functionalDesignVersion) {
		this.functionalDesignVersion = functionalDesignVersion;
	}

	@Order(value = 20)
	public String getRefferenceToProductDefinition() {
		return refferenceToProductDefinition;
	}

	public void setRefferenceToProductDefinition(String refferenceToProductDefinition) {
		this.refferenceToProductDefinition = refferenceToProductDefinition;
	}

	@Order(value = 30)
	public String getRefferenceToRiskAnnalysis() {
		return refferenceToRiskAnnalysis;
	}

	public void setRefferenceToRiskAnnalysis(String refferenceToRiskAnnalysis) {
		this.refferenceToRiskAnnalysis = refferenceToRiskAnnalysis;
	}

	@Order(value = 40)
	public List<WorkUnitRule> getWorkUnitRules() {
		return workUnitRules;
	}

	public void setWorkUnitRules(List<WorkUnitRule> workUnitRules) {
		if (workUnitRules != null) {
			this.workUnitRules = workUnitRules;
		}
	}

	@Order(10.1)
	@PropertyActionMethod("WorkUnitRules")
	@ReadOnlyActionMethod
	@FontIcon(fontIconUrl = FontAwesomeUrl.EYE)
	public WorkUnitRule viewWorkUnitTemplate(WorkUnitRule workUnitRule) {
		return workUnitRule;
	}

	@Order(10.1)
	@PropertyActionMethod("WorkUnitRules")
	@FontIcon(fontIconUrl = FontAwesomeUrl.EDIT)
	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void modifyWorkUnitRule(WorkUnitRule workUnitRule) {
	}

	@Order(10.3)
	@ParameterFactory
	@PropertyActionMethod("WorkUnitRules")
	@FontIcon(fontIconUrl = FontAwesomeUrl.PLUS)
	public void addNewUnitRuleTemplate(WorkUnitRule workUnitTemplate) {
		workUnitRules.add(workUnitTemplate);
	}

	@Order(10.4)
	@PropertyActionMethod("WorkUnitRules")
	@FontIcon(fontIconUrl = FontAwesomeUrl.TRASH)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void removeWorkUnitRule(WorkUnitRule workUnitRule) {
		workUnitRules.remove(workUnitRule);
	}

	public boolean removeWorkUnitRuleHidden() {
		return workUnitRules.size() == 0;
	}

	@Order(10.5)
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_UP)
	@PropertyActionMethod("WorkUnitRules")
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void moveWorkUnitRuleUp(WorkUnitRule workUnitRule) {
		int index = workUnitRules.indexOf(workUnitRule);
		if (index > 0) {
			workUnitRules.remove(workUnitRule);
			workUnitRules.add(index - 1, workUnitRule);
		}

	}

	public boolean moveWorkUnitRuleUpHidden() {
		return workUnitRules.size() < 2;
	}

	@Order(10.6)
	@PropertyActionMethod("WorkUnitRules")
	@FontIcon(fontIconUrl = FontAwesomeUrl.ARROW_DOWN)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void moveWorkUnitRuleDown(WorkUnitRule workUnitRule) {
		int index = workUnitRules.indexOf(workUnitRule);
		if (index < workUnitRules.size() - 1) {
			workUnitRules.remove(workUnitRule);
			workUnitRules.add(index + 1, workUnitRule);
		}
	}

	public boolean moveWorkUnitRuleDownHidden() {
		return workUnitRules.size() < 2;
	}

	@Override
	public String toString() {
		return new TitleBuilder().append(name).toString();
	}
}
