package nth.innoforce.data.epmmapping;

import nth.innoforce.domain.project.status.ProjectStatus;
/**
 * @deprecated no longer use the EPM database to import projects
 * @author nilsth
 *
 */
public class EpmProjectStatusMapping extends EpmMapping {

	public EpmProjectStatusMapping() {
		super(ProjectStatus.class, "name", "SELECT [MemberValue] as name,[MemberDescription] as description FROM [EPM_ProjectServer_Reporting].[dbo].[MSPLT_Open/Closed_OlapView]");
	}

}
