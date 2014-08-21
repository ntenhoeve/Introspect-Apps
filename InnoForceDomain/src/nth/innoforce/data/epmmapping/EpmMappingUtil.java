package nth.innoforce.data.epmmapping;

import java.util.ArrayList;
import java.util.List;

import nth.innoforce.domain.project.EpmProjectMapping;
import nth.innoforce.domain.resource.EpmResourceMapping;
import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.property.PropertyInfo;

import com.rits.cloning.Cloner;

/**
 * @deprecated this class needs to be merged with SqlDataSource
 * @author nilsth
 *
 */
public class EpmMappingUtil {

	private static List<EpmMapping> getAllEpmMappings() {
		List<EpmMapping> epmMappings = new ArrayList<EpmMapping>();
		epmMappings.add(new EpmResourceMapping());
		epmMappings.add(new EpmProjectStageMapping());
		epmMappings.add(new EpmProjectStatusMapping());
		epmMappings.add(new EpmProjectMapping());
		// TODO epmMappings.add(new EpmProjectTypeMapping());
		return epmMappings;
	}

	public static void updateInnoForceWithEpm() throws Exception {
		List<EpmMapping> epmMappings = getAllEpmMappings();
		for (EpmMapping epmMapping : epmMappings) {
			updateInnoForceWithEpm(epmMapping);
		}
	}

	public static void updateInnoForceWithEpm(Class<?> domainClass) throws Exception {
		List<EpmMapping> epmMappings = getAllEpmMappings();
		for (EpmMapping epmMapping : epmMappings) {
			if (epmMapping.getDomainClass().equals(domainClass)) {
				updateInnoForceWithEpm(epmMapping);
				return;
			}
		}
		throw new Exception("Could not find a Epm Mapping for class: " + domainClass.getCanonicalName());
	}

	private static void updateInnoForceWithEpm(EpmMapping epmMapping) throws Exception {
		Cloner cloner = new Cloner();

	}

	private static Object convertResultSetValue(Object resultSetValue, Class<?> propertyType) throws Exception {
		return null;
	}

	private static EpmMapping findEpmMapOfType(List<EpmMapping> epmMappings, Class<?> domainClass) {
		for (EpmMapping epmMapping : epmMappings) {
			if (epmMapping.getDomainClass().equals(domainClass)) {
				return epmMapping;
			}
		}
		return null;
	}

	private static Object findOrCreatedomainObject(List<Object> domainObjects, Class<?> domainClass, String propertyName, Object propertyValue) throws Exception {
		if (propertyValue != null) {
			DomainInfoProvider domainInfoProvider = Introspect.getDomainInfoProvider();
			PropertyInfo propertyInfo = domainInfoProvider.getPropertyInfo(domainClass, propertyName);
			for (Object domainObject : domainObjects) {
				Object propertyValue2 = propertyInfo.getValue(domainObject);
				if (propertyValue.equals(propertyValue2)) {
					return domainObject;
				}
			}
		}
		// did not find the domain object so create a new one
		return domainClass.newInstance();
	}

}
