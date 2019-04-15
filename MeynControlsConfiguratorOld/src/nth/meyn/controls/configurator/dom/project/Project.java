package nth.meyn.controls.configurator.dom.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nth.meyn.controls.configurator.dom.equipment.Equipment;
import nth.meyn.controls.configurator.dom.site.Site;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;

/**
 * @author nilsth
 *
 */
@Description(englishDescription = "An implementation of a project template to generate e.g. a evisceration line 2 panel or whole leg deboner 3 for a specific site")
public class Project {

	private ProjectTemplate projectTemplate;
	private LocalDateTime version;
	private int lineOrMachineNumber;
	private Site site;
	private int panelNumber;
	private int salesOrder;
	private int factoryOrder;
	private ElectricalNorm electricalNorm;
	private int mainVoltage;
	private int mainFrequency;
	private List<Equipment> equipment;

	//TODO changeHistory (date-time and whom)
	//TODO Panels
	//TODO IO
	
	
	public Project() {
		lineOrMachineNumber = 1;
		equipment = new ArrayList<>();
	}

	public ProjectTemplate getProjectTemplate() {
		return projectTemplate;
	}

	public void setProjectTemplate(ProjectTemplate projectTemplate) {
		this.projectTemplate = projectTemplate;
	}

	public LocalDateTime getVersion() {
		return version;
	}

	public void setVersion(LocalDateTime version) {
		this.version = version;
	}

	public int getLineOrMachineNumber() {
		return lineOrMachineNumber;
	}

	public void setLineOrMachineNumber(int lineOrMachineNumber) {
		this.lineOrMachineNumber = lineOrMachineNumber;
	}

	public String getName() {
		if (projectTemplate == null) {
			return "";
		} else {
			StringBuilder name = new StringBuilder();
			name.append(projectTemplate.getName());
			name.append(" ");
			name.append(lineOrMachineNumber);
			return name.toString();
		}
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public int getPanelNumber() {
		return panelNumber;
	}

	public void setPanelNumber(int panelNumber) {
		this.panelNumber = panelNumber;
	}

	public int getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(int salesOrder) {
		this.salesOrder = salesOrder;
	}

	public int getFactoryOrder() {
		return factoryOrder;
	}

	public void setFactoryOrder(int factoryOrder) {
		this.factoryOrder = factoryOrder;
	}

	
	public ElectricalNorm getElectricalNorm() {
		return electricalNorm;
	}

	public void setElectricalNorm(ElectricalNorm electricalNorm) {
		this.electricalNorm = electricalNorm;
	}

	public int getMainVoltage() {
		return mainVoltage;
	}

	public void setMainVoltage(int mainVoltage) {
		this.mainVoltage = mainVoltage;
	}

	public int getMainFrequency() {
		return mainFrequency;
	}

	public void setMainFrequency(int mainFrequency) {
		this.mainFrequency = mainFrequency;
	}

	public List<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}

	

}
