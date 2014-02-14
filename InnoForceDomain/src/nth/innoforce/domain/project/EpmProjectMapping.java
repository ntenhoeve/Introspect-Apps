package nth.innoforce.domain.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nth.innoforce.data.epmmapping.EpmMapping;
/**
 * @deprecated no longer use the EPM database to import projects
 * @author nilsth
 *
 */
public class EpmProjectMapping extends EpmMapping<Project> {

	public EpmProjectMapping() {
		super(Project.class, "number", "SELECT dbo.MSP_EpmProject_OlapView.ProjectName AS name, dbo.[MSPLT_FO number_OlapView].MemberValue AS number,"
				//+ "       dbo.[MSPLT_FO number_OlapView].MemberDescription AS description, dbo.[MSPLT_Poultry process_OlapView].MemberValue AS productGroup,"
				//+ "       dbo.MSPLT_ProjectPhase_OlapView.MemberValue AS stage," 
				//+ "       dbo.[MSPLT_Open/Closed_OlapView].MemberValue AS status, "
				+ "       dbo.MSP_EpmProject_OlapView.[ProjectUID] as epmId, "
				+ "       dbo.MSP_EpmProject_OlapView.ProjectStartDate as startOfProject, "
				// TODO + "       dbo.MSP_EpmProject_OlapView.ProjectFinishDate as finishDate, "
				+ "       dbo.MSP_EpmResource_OlapView.ResourceName AS projectManager "
				+ "FROM   dbo.MSP_EpmProject_OlapView INNER JOIN"
				+ "       dbo.[MSPLT_Poultry process_OlapView] ON"
				+ "       dbo.MSP_EpmProject_OlapView.[Poultry Process] = dbo.[MSPLT_Poultry process_OlapView].LookupMemberUID INNER JOIN"
				+ "       dbo.[MSPLT_FO number_OlapView] ON "
				+ "       dbo.MSP_EpmProject_OlapView.[project no] = dbo.[MSPLT_FO number_OlapView].LookupMemberUID INNER JOIN"
				+ "       dbo.MSPLT_ProjectPhase_OlapView ON "
				+ "       dbo.MSP_EpmProject_OlapView.Projectphase = dbo.MSPLT_ProjectPhase_OlapView.LookupMemberUID INNER JOIN"
				+ "       dbo.[MSPLT_Open/Closed_OlapView] ON "
				+ "       dbo.MSP_EpmProject_OlapView.[State of project] = dbo.[MSPLT_Open/Closed_OlapView].LookupMemberUID INNER JOIN"
				+ "       dbo.MSPLT_YesNo_OlapView ON dbo.MSP_EpmProject_OlapView.WBSO = dbo.MSPLT_YesNo_OlapView.LookupMemberUID INNER JOIN"
				+ "       dbo.MSP_EpmResource_OlapView ON dbo.MSP_EpmProject_OlapView.ProjectOwnerResourceUID = dbo.MSP_EpmResource_OlapView.ResourceUID");
	}

	@Override
	public void modify(Project originalProject, Project newProject) {
		// EPM administrators use a naming convention: the project name is preceded by the project number. We use 2 separate fields, so we can remove the number in the name
		String oldName = newProject.getName();
		StringBuffer newName = new StringBuffer();
		Pattern pattern=Pattern.compile("^\\d{6}\\s?-?\\s*"); // 6 digit number, followed by an optional space , followed by an optional -, followed optional spaces
		Matcher matcher = pattern.matcher(oldName);
		while (matcher.find()) {
		    matcher.appendReplacement(newName, "");
		}
		matcher.appendTail(newName);
		newProject.setName(newName.toString());
	}
		
}
