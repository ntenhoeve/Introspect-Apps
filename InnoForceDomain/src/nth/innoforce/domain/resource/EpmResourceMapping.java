package nth.innoforce.domain.resource;

import nth.innoforce.data.epmmapping.EpmMapping;

public class EpmResourceMapping extends EpmMapping {

	public EpmResourceMapping() {
		super(nth.innoforce.domain.resource.Resource.class, "name", "SELECT [ResourceName] as name, "
				+ "       [ResourceCode] as code, "
				+ "       [ResourceEmailAddress] as emailAddress, "
				+ "       [ResourceGroup] as resourceGroup, "
				+ "       [ResourceNTAccount] as accountName, "
				+ "       [ResourceIsActive] as active "
				+ "FROM   [EPM_ProjectServer_Reporting].[dbo].[MSP_EpmResource] "
				+ "WHERE  [ResourceIsGeneric]=0 ");
	}

}
