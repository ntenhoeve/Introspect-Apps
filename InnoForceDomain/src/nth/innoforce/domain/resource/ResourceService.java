package nth.innoforce.domain.resource;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import nth.innoforce.domain.find.FindParameter;
import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FormMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.domain.info.valuemodel.annotations.Icon;

public class ResourceService {

	private ResourceDataAccess getResourceDataAccess() {
		return (ResourceDataAccess) Introspect.getDataAccessProvider(ResourceDataAccess.class);
	}

	@GenericReturnType(Resource.class)
	public List<Resource> allActiveResources() {
		return getResourceDataAccess().getAllActiveResources();
	}

	@GenericReturnType(Resource.class)
	public void synchronizeResourcesWithMeynConnect() {
		// TODO Redefine Resource into Person, with and redefine properties
		// TODO rename resourceDataAccess to PersonInnoForceDataAccess
		// TODO create a datasource (PersonMeynConectDataAccess) that gets information from Meyn connect (only implement getAll)
		// TODO code to itterate to trough meynconnect persons and innoforce database persons and update innoforce persons
	}

	@FormMode(FormModeType.editParameterThenExecuteMethodOrCancel)
	@GenericReturnType(Resource.class)
	@Icon("find")
	public List<Resource> findResources(FindParameter findParameter) {
		return getResourceDataAccess().findResources(findParameter);
	}

	public FindParameter findResourcesParameterFactory() {
		return new FindParameter("Test");
	}

	@FormMode(FormModeType.showParameterThenClose)
	public void view(Resource resource) throws MalformedURLException {
	}

	@FormMode(FormModeType.executeMethodDirectly)
	public URI openInFacebook(Resource resource) throws Exception {
		// http://facebook.meyn.nl/index.php/advanced-search/Nils%2Bten%2BHoeve?ordering=newest&searchphrase=all
		StringBuffer url = new StringBuffer("http://facebook.meyn.nl/index.php/advanced-search/");
		url.append(URLEncoder.encode(resource.getName(), "UTF-8"));
		return new URI(url.toString());
	}

	@FormMode(FormModeType.executeMethodDirectly)
	public URI sendEmail(Resource resource) throws Exception {
		StringBuffer uri = new StringBuffer("mailto:");
		uri.append(resource.getEmailAddress());
		return new URL(uri.toString()).toURI();
	}

	@FormMode(FormModeType.showParameterThenClose)
	public Resource me() {
		String userName = Introspect.getAuthorizationProvider().getCurrentUserName();
		FindParameter findParameter = new FindParameter(userName);
		List<Resource> results = getResourceDataAccess().findResources(findParameter);
		if (results.size() < 1) {
			throw new RuntimeException("Could not find user name:" + userName);
		} else if (results.size() > 1) {
			throw new RuntimeException("Found multiple instances of user name:" + userName);
		} else {
			return results.get(0);
		}
	}
}
