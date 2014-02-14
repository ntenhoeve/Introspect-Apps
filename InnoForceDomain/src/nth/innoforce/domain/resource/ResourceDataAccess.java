package nth.innoforce.domain.resource;

import java.util.HashMap;
import java.util.List;

import nth.innoforce.dataaccess.InnoforceDataAccess;
import nth.innoforce.domain.find.FindParameter;

public class ResourceDataAccess extends InnoforceDataAccess<Resource> {

	@SuppressWarnings("unchecked")
	public List<Resource> getAllActiveResources() {
		StringBuffer query = new StringBuffer("select e from ");
		query.append(Resource.class.getCanonicalName());
		query.append(" e where e.active=true");
		List<?> results = getPersistenceUnit().executeQuery(query.toString());
		return (List<Resource>) results;
	}

	@SuppressWarnings("unchecked")
	public List<Resource> findResources(FindParameter findParameter) {
		String TEXT_TO_FIND="textToFind";
		StringBuffer query = new StringBuffer("select e from ");
		query.append(Resource.class.getCanonicalName());
		query.append(" e where e.active=true and e.name like :");
		query.append(TEXT_TO_FIND);
		HashMap<String, Object> queryParameters=new HashMap<String, Object>();
		queryParameters.put(TEXT_TO_FIND,"%"+findParameter.getTextToFind()+"%");
		List<?> results = getPersistenceUnit().executeQuery(query.toString(),queryParameters);
		return (List<Resource>) results;

	}

}
