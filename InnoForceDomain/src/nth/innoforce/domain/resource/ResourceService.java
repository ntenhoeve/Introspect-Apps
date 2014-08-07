package nth.innoforce.domain.resource;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import nth.innoforce.domain.find.FindParameter;
import nth.introspect.Introspect;
import nth.introspect.container.inject.annotation.Inject;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.ExecutionMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.domain.info.valuemodel.annotations.Icon;

public class ResourceService {

	@Inject
	private ResourceDataAccess resourceDataAccess;

	@GenericReturnType(Resource.class)
	public List<Resource> allActiveResources() {
		return resourceDataAccess.getAllActiveResources();
	}

	@GenericReturnType(Resource.class)
	public void synchronizeResourcesWithMeynConnect() {
		// TODO Redefine Resource into Person, with and redefine properties
		// TODO rename resourceDataAccess to PersonInnoForceDataAccess
		// TODO create a datasource (PersonMeynConectDataAccess) that gets information from Meyn connect (only implement getAll)
		// TODO code to itterate to trough meynconnect persons and innoforce database persons and update innoforce persons
	}

	@ExecutionMode(ExecutionModeType.EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL)
	@GenericReturnType(Resource.class)
	@Icon("find")
	public List<Resource> findResources(FindParameter findParameter) {
		return resourceDataAccess.findResources(findParameter);
	}

	public FindParameter findResourcesParameterFactory() {
		return new FindParameter("Test");
	}

	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public Resource view(Resource resource) throws MalformedURLException {
		return resource;
	}

	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public URI openInFacebook(Resource resource) throws Exception {
		// http://facebook.meyn.nl/index.php/advanced-search/Nils%2Bten%2BHoeve?ordering=newest&searchphrase=all
		StringBuffer url = new StringBuffer("http://facebook.meyn.nl/index.php/advanced-search/");
		url.append(URLEncoder.encode(resource.getName(), "UTF-8"));
		return new URI(url.toString());
	}

	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public URI sendEmail(Resource resource) throws Exception {
		StringBuffer uri = new StringBuffer("mailto:");
		uri.append(resource.getEmailAddress());
		return new URL(uri.toString()).toURI();
	}

	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public Resource me() {
		String userName = Introspect.getAuthorizationProvider().getCurrentUserName();
		FindParameter findParameter = new FindParameter(userName);
		List<Resource> results = resourceDataAccess.findResources(findParameter);
		if (results.size() < 1) {
			throw new RuntimeException("Could not find user name:" + userName);
		} else if (results.size() > 1) {
			throw new RuntimeException("Found multiple instances of user name:" + userName);
		} else {
			return results.get(0);
		}
	}
}
