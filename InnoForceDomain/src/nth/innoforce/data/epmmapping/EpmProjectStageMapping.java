package nth.innoforce.data.epmmapping;

import nth.innoforce.domain.project.stage.ProjectStage;
/**
 * @deprecated no longer use the EPM database to import projects
 * @author nilsth
 *
 */
public class EpmProjectStageMapping extends EpmMapping {

	public EpmProjectStageMapping() {
		super(ProjectStage.class, "name", "SELECT [MemberValue] as name, " + "[MemberDescription] as description " + "FROM [EPM_ProjectServer_Reporting].[dbo].[MSPLT_ProjectPhase_OlapView]");
	}

}
