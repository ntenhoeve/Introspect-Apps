package nth.meyn.control.system.configurator.dom._4workcenter;

import java.util.List;

import javax.validation.constraints.NotBlank;

import nth.meyn.control.system.configurator.dom._5workunit.WorkUnitRule;
import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

@Description(englishDescription = "A template for a physical location on the shop floor that provides specific manufacturing capabilities. This is typically a production line, e.g.: Slaughter Line. At Meyn a Work Center is the equavilant to a Main Control Panel. A work center contains one or more work units, e.g. equipment like scalders or pluckers.")
public class WorkCenterTemplate {

	private String name;
	private String functionalDesignVersion;
	private String refferenceToProductDefinition;
	private String refferenceToRiskAnnalysis;
	private List<WorkUnitRule> workUnitRules;

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
		this.workUnitRules = workUnitRules;
	}

	@Override
	public String toString() {
		return TitleBuilder.getInstance().append(name).toString();
	}
}
