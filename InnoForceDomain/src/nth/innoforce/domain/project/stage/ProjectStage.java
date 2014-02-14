package nth.innoforce.domain.project.stage;


//@Entity
//@TableSection(name = "ProjectStages")
public enum ProjectStage {
	//TODO add ideation?
	ProjectConcept,
	BuildBusinessCase,
	Creative,
	Definition,
	Launch,
	SalesManufactoringAndSupport,//TODO should automatically get this after launch
	//TODO add EndOffLife? 
}
