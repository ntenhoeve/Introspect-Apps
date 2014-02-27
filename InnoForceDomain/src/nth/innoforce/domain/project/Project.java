package nth.innoforce.domain.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import nth.innoforce.domain.gatereport.Gate;
import nth.innoforce.domain.product.ProductNode;
import nth.innoforce.domain.project.event.ProjectEvent;
import nth.innoforce.domain.project.event.ProjectLaunched;
import nth.innoforce.domain.project.event.gate.GateReport;
import nth.innoforce.domain.project.event.gate.GoToCreativeGate;
import nth.innoforce.domain.project.event.gate.GoToDefinitionGate;
import nth.innoforce.domain.project.event.gate.GoToLaunchGate;
import nth.innoforce.domain.project.report.properties.PriorityInProductGroupProperty;
import nth.innoforce.domain.project.stage.ProjectStage;
import nth.innoforce.domain.project.status.ProjectStatus;
import nth.innoforce.domain.project.type.ProjectType;
import nth.innoforce.domain.resource.Resource;
import nth.introspect.dataaccess.hibernate.entity.DeletableEntity;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.property.FieldModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.ColumnWidth;
import nth.introspect.provider.domain.info.valuemodel.annotations.FieldMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.ExecutionMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.Format;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.domain.info.valuemodel.annotations.Icon;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInForm;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInTable;
import nth.introspect.provider.domain.info.valuemodel.annotations.Visible;
import nth.introspect.provider.domain.info.valuemodel.annotations.VisibleInForm;
import nth.introspect.provider.domain.info.valuemodel.annotations.VisibleInTable;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Projects")
public class Project extends DeletableEntity implements PriorityInProductGroupProperty {
	private String name;
	private Integer number;
	private String epmId;
	private String description;
	private Resource projectManager;
	private Resource projectLeader;
	private ProductNode productNode;
	private Integer priorityInProductGroup;
	private ProjectType projectType;
	private ProjectStage stage;
	private ProjectStatus status;
	private Date startOfProject;
	private List<ProjectEvent> history;

	public void setNumber(Integer number) {
		this.number = number;
	}

	@VisibleInTable(false)
	@NotNull
	@OrderInForm(1)
	public Integer getNumber() {
		return number;
	}

	@OrderInTable(1)
	@OrderInForm(5)
	@ManyToOne()
	public ProductNode getProductGroup() {
		return productNode;
	}

	public void setProductGroup(ProductNode productNode) {
		this.productNode = productNode;
	}

	// TODO public MultiLanguageText productGroupValidation() {
	// if (productGroup == null && (ProjectType.ProductModification.equals(projectType) || ProjectType.ProductImprovement.equals(projectType)|| ProjectType.ProductInnovation.equals(projectType))) {
	// return new MultiLanguageText("may not be empty");
	// }
	// return null;// value is valid
	// }

	@ColumnWidth(200)
	@OrderInForm(2)
	@OrderInTable(2)
	@NotEmpty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@VisibleInTable(false)
	@OrderInForm(3)
	@NotEmpty
	@FieldMode(FieldModeType.TEXT_AREA)
	public String getDescription() {
		return description;
	}

	@Override
	@OrderInTable(3)
	@OrderInForm(8)
	@ColumnWidth(10)
	public Integer getPriorityInProductGroup() {
		ProjectEvent latestReport = getLatestEvent(PriorityInProductGroupProperty.class);
		if (latestReport == null) {
			return priorityInProductGroup;// no gate reports so get value from database
		} else {
			return ((PriorityInProductGroupProperty) latestReport).getPriorityInProductGroup();// get value from latest gate report
		}
	}

	@Override
	public void setPriorityInProductGroup(Integer priorityInProductGroup) {
		this.priorityInProductGroup = priorityInProductGroup;
	}

	// TODO @OrderInDetails(9)
	// public Double getLatestGateScore() {
	// return null;
	// }

	@OrderInTable(3)
	@OrderInForm(4)
	@Enumerated(EnumType.STRING)
	@NotNull
	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	@OrderInTable(4)
	@OrderInForm(7)
	@Enumerated(EnumType.STRING)
	public ProjectStage getStage() {
		boolean isLaunched = getLatestEvent(ProjectLaunched.class) != null;
		if (isLaunched) {
			return ProjectStage.SalesManufactoringAndSupport;
		}
		GateReport latestGate = (GateReport) getLatestEvent(GateReport.class);
		if (latestGate == null) {
			return stage;// use value from database
		} else {
			// get value from latest gate meeting decision
			return latestGate.getNewStage();
		}
	}

	public void setStage(ProjectStage stage) {
		this.stage = stage;
	}

	// TODOpublic boolean stageEnabled() {
	// return gates == null || gates.size() == 0;
	// }

	// TODO public MultiLanguageText stageValidation() {
	// if (stage == null && (ProjectType.ProductInnovation.equals(projectType) || ProjectType.ProductImprovement.equals(projectType) || ProjectType.ProductModification.equals(projectType))) {
	// return new MultiLanguageText("may not be empty");
	// }
	// return null;// value is valid
	// }

	@OrderInTable(5)
	@OrderInForm(6)
	@Enumerated(EnumType.STRING)
	@NotNull
	public ProjectStatus getStatus() {
		boolean isLaunched = getLatestEvent(ProjectLaunched.class) != null;
		if (isLaunched) {
			return ProjectStatus.Launched;
		}
		ProjectEvent latestReport = getLatestEvent(GateReport.class);
		if (latestReport == null || ((GateReport) latestReport).getDecision() == null) {
			return status;// use value form database
		} else {
			GateReport latestGate = (GateReport) latestReport;
			// get value from latest gate meeting decision
			switch (latestGate.getDecision()) {
			case Go:
				return ProjectStatus.Running;
			case Kill:
				return ProjectStatus.Killed;
			case Hold:
				return ProjectStatus.OnHold;
			case Recycle:
				return ProjectStatus.Running;
			default:
				return null;
			}
		}
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	// public boolean statusEnabled() {
	// return gates == null || gates.size() == 0;
	// }

	@OrderInTable(6)
	@OrderInForm(10)
	@ManyToOne()
	public Resource getProjectLeader() {
		return projectLeader;

	}

	public void setProjectLeader(Resource projectLeader) {
		this.projectLeader = projectLeader;
	}

	@OrderInTable(7)
	@OrderInForm(11)
	@ManyToOne()
	public Resource getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Resource projectManager) {
		this.projectManager = projectManager;
	}

	@OneToOne(targetEntity = Gate.class)
	@Visible(false)
	public ProjectEvent getLatestEvent(Class<?> classToFind) {
		List<ProjectEvent> events = getHistory();
		if (events == null || events.size() == 0) {
			return null;
		}
		ProjectEvent latestEvent = null;
		for (ProjectEvent event : events) {
			if (classToFind.isAssignableFrom(event.getClass())) {
				if (latestEvent == null) {
					latestEvent = event;
				} else if (event.getDate().after(latestEvent.getDate())) {
					latestEvent = event;
				}
			}
		}
		return latestEvent;
	}

	@VisibleInTable(false)
	@VisibleInForm(false)
	public String getEpmId() {
		return epmId;
	}

	public void setEpmId(String epmId) {
		this.epmId = epmId;
	}

	// public void setIssues(List<String> issues) {
	// this.issues = issues;
	// }
	//
	//

//	public void setReports(List<ProjectEvent> reports) {
//		this.reports = reports;
//	}
//
//	@OrderInDetails(15)
//	@OneToMany(targetEntity = Gate.class, cascade = CascadeType.ALL)
//	@GenericReturnType(ProjectEvent.class)
//	// TODO D.R.Y.: get from @OneToMany target entity
//	public List<ProjectEvent> getReports() {
//		if (reports == null) {
//			reports = new ArrayList<ProjectEvent>();
//		}
//		if (!historyContains(ProjectStart.class) && startOfProject != null) {
//			reports.add(new ProjectStart(startOfProject));
//		}
//		if (!historyContains(ProjectLaunched.class) && launchOfProject != null) {
//			reports.add(new ProjectLaunched(launchOfProject));
//		}
//		return reports;
//	}

	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	@Icon("view")
	public ProjectEvent viewEventItem(ProjectEvent report) {
		// Do nothing, just show details to user
		return report;
	}

	// public void addGate(Gate gate) {
	// gates.add(gate);
	// }
	//
	// public Gate addGateParameterFactory() {
	// return new Gate();
	// }
	//
	// @DetailMode(DetailModeType.showDetailsThenExecuteMethod)
	// public void removeGate(Gate gate) {
	// gates.remove(gate);
	// }
	//
	// @DetailMode(DetailModeType.showDetailsThenClose)
	// public void viewGate(Gate gate) {
	// // Do nothing, just show details to user
	// }

//	@Visible(false)
//	private boolean historyContains(Class<?> classToFind) {
//		for (ProjectEvent event : getHistory()) {
//			if (event.getClass() == classToFind) {
//				return true;
//			}
//		}
//		return false;
//	}

	@VisibleInTable(false)
	@Format("yyyy-MM-dd")
	public Date getStartOfProject() {
		return startOfProject;
	}

	public void setStartOfProject(Date startOfProject) {
		this.startOfProject = startOfProject;
	}


	
	
	public void historyAddGoToCreativeGate(GoToCreativeGate goToCreativeGate) {
		history.add(goToCreativeGate);
	}

	public GoToCreativeGate historyAddGoToCreativeGateParameterFactory() {
		GoToCreativeGate goToCreativeGate = new GoToCreativeGate();
		goToCreativeGate.setDate(new Date());
		return goToCreativeGate;
	}

	public void historyAddGoToDefinitionGate(GoToDefinitionGate goToDefinitionGate) {
		history.add(goToDefinitionGate);
	}

	public GoToDefinitionGate historyAddGoToDefinitionGateParameterFactory() {
		GoToDefinitionGate goToDefinitionGate = new GoToDefinitionGate();
		goToDefinitionGate.setDate(new Date());
		return goToDefinitionGate;
	}
	
	public void historyAddGoToLaunchGate(GoToLaunchGate goToLaunchGate) {
		history.add(goToLaunchGate);
	}

	public GoToLaunchGate historyAddGoToLaunchGateParameterFactory() {
		GoToLaunchGate goToLaunchGate = new GoToLaunchGate();
		goToLaunchGate.setDate(new Date());
		return goToLaunchGate;
	}

	
	@OrderInForm(15)
	@OneToMany(targetEntity = Gate.class, cascade = CascadeType.ALL)
	@GenericReturnType(ProjectEvent.class)
	public List<ProjectEvent> getHistory() {
		if (history==null) {
			history=new ArrayList<ProjectEvent>();
		}
		return history;
	}

	public void setHistory(List<ProjectEvent> history) {
		this.history = history;
	}

}
