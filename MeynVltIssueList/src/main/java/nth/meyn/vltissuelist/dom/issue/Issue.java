package nth.meyn.vltissuelist.dom.issue;

import java.io.File;
import java.util.Date;

import nth.meyn.vltissuelist.dom.folder.project.ProjectType;
import nth.reflect.fw.layer5provider.reflection.behavior.format.Format;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Issue {

	private static final String UNKNOWN = "Unknown";
	private String projectDescription;
	private String customerCity;
	private String customerCountry;
	private String customerName;
	private int customerNumber;
	private Date designDate;
	private String electricalSchematic;
	private String lineNr;
	private int numberOfVlts;
	private File projectFolder;

	@Order(value = 1)
	@Format(pattern = "####")
	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	@Order(value = 2)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Order(value = 3)
	public String getCustomerCountry() {
		return customerCountry;
	}

	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}

	@Order(value = 4)
	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	

	@Order(value = 10)
	public String getElectricalSchematic() {
		return electricalSchematic;
	}

	public void setElectricalSchematic(String electricalSchematic) {
		this.electricalSchematic = electricalSchematic;
	}

	@Order(value = 11)
	@Format(pattern = "yyyy-dd-MM")
	public Date getDesignDate() {
		return designDate;
	}

	public void setDesignDate(Date designDate) {
		this.designDate = designDate;
	}

	@Order(value = 12)
	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	
	@Order(value = 13)
	public String getElectricProjectInfo() {
		if (projectFolder==null) {
			return UNKNOWN;
		}
		return projectFolder.getName();
	}

	@Order(value = 14)
	public ProjectType getProjectType() {
		return ProjectType.valueOf(projectFolder);
	}


	@Hidden
	@Order(value = 15)
	public String getLineNr() {
		return lineNr;
	}

	@Order(value = 16)
	public boolean isParameterCorrected() {
		ProjectType projectType=getProjectType();
		if (ProjectType.RAPID==projectType) {
			return true;
		}
		if (ProjectType.WHOLE_LEG_DEBONER==projectType) {
			return true;
		}
		if (ProjectType.THIGH_DEBONER==projectType) {
			return true;
		}
		return false;
	}

	@Order(value = 17)
	public Date getParameterCorrectionDate() {
		return null;
	}
	
	@Order(value = 18)
	public String getParameterCorrectedBy() {
		return isParameterCorrected()?"Parameter 804 was correct from beginning":"";
	}
	@Order(value = 19)
	public int getNumberOfVlts() {
		return numberOfVlts;
	}

	public void setNumberOfVlts(int numberOfVlts) {
		this.numberOfVlts = numberOfVlts;
	}

	@Order(value = 20)
	public File getProjectFolder() {
		return projectFolder;
	}
	
	public void setProjectFolder(File projectFolder) {
		this.projectFolder = projectFolder;
	}





}
