package nth.innoforce.data.epmmapping;

import java.util.ArrayList;
import java.util.List;

import nth.innoforce.domain.project.EpmProjectMapping;
import nth.innoforce.domain.resource.EpmResourceMapping;
import nth.introspect.Introspect;
import nth.introspect.provider.domain.DomainProvider;
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

//		EntityDatabase<Object> innoForceRepository = new EntityDatabase<Object>(epmMapping.getDomainClass());// TODO we lose the records every time we create a new entity manager
//		List<Object> innoForceDomainObjects = innoForceRepository.findAll();
//
//		
//		EpmDataAccess epmRepository = (EpmDataAccess) Introspect.getDataAccessPort().getDataAccess(EpmDataAccess.class);
//
//		Statement statement = epmRepository.executeSQL(epmMapping.getSelectionQuery());
//		ResultSet resultSet = statement.getResultSet();
//		List<String> errors = new ArrayList<String>();
//		while (resultSet.next()) {
// 			DomainProvider domainPort = Introspect.getDomainPort();
//			String propertyName = epmMapping.getLinkingPropertyName();//TODO rename to linking property name and move above while
//			Object resultSetValue = resultSet.getObject(propertyName);
//			Class<?> propertyType = domainPort.getPropertyInfo(epmMapping.getDomainClass(), propertyName).getPropertyClassOrCollectionContent();//TODO rename to linkingPropertyInfo and move above while
//			Object propertyValue = convertResultSetValue(resultSetValue, propertyType);
//			Object domainObject = findOrCreatedomainObject(innoForceDomainObjects, epmMapping.getDomainClass(), epmMapping.getLinkingPropertyName(), propertyValue);
//			Object originalDomainObject = cloner.deepClone(domainObject);
//			ResultSetMetaData meta = resultSet.getMetaData();
//			int numColumns = meta.getColumnCount();
//			for (int columnNr = 1; columnNr < numColumns + 1; columnNr++) {
//				String columnName = meta.getColumnName(columnNr);
//				resultSetValue = resultSet.getObject(columnNr);
//				PropertyInfo propertyInfo = domainPort.getPropertyInfo(epmMapping.getDomainClass(), columnName);
//				if (propertyInfo == null) {
//					throw new Exception("The query returned a column named: " + columnName + " that is not specified as a property in: " + epmMapping.getDomainClass().getCanonicalName());
//				} else {
//					propertyType = propertyInfo.getReadMethod().getReturnType();
//					try {
//						propertyValue = convertResultSetValue(resultSetValue, propertyType);
//						propertyInfo.setValue(domainObject, propertyValue);
//					} catch (Throwable e) {
//						StringBuffer error = new StringBuffer("Could not convert query result value: ");
//						error.append( resultSetValue);
//						error.append(" of column: ");
//						error.append( columnName );
//						error.append( " to type: " );
//						error.append( propertyType.getCanonicalName() );
//						error.append( " , cause: ");
//						while (e.getCause() != null) {
//							e = e.getCause();
//						}
//						error.append( e.getMessage());
//						errors.add(error.toString());
//					}
//				}
//			}
//			// allow epmMapping implementations to make some final modifications
//			epmMapping.modify(originalDomainObject, domainObject);
//			innoForceRepository.saveOrUpdate(domainObject);
//		}
//		// throw error if needed
//		if (!errors.isEmpty()) {
//			StringBuffer errorText = new StringBuffer();
//			for (String error : errors) {
//				errorText.append(error);
//				errorText.append("\n");
//			}
//			throw new Exception(errorText.toString());
//		}
	}

	private static Object convertResultSetValue(Object resultSetValue, Class<?> propertyType) throws Exception {
		return null;
//		if (resultSetValue==null) {
//			return null;
//		}
//		List<EpmMapping> epmMappings = getAllEpmMappings();
//		String propertyTypeName = propertyType.getCanonicalName();
//		if (propertyTypeName.equals(String.class.getCanonicalName())) {
//			// string does not need converting
//			return resultSetValue;
//		} else if (propertyTypeName.equals(Integer.class.getCanonicalName()) || propertyTypeName.equals("int")) {
//			return Integer.parseInt((String) resultSetValue);
//		} else if (propertyTypeName.equals(Boolean.class.getCanonicalName()) || propertyTypeName.equals("boolean") ) {
//			// boolean or date does not need converting
//			return resultSetValue;
//		} else if (propertyTypeName.equals(Date.class.getCanonicalName())) {
//			return (Date)resultSetValue;
//		} else if (findEpmMapOfType(epmMappings, propertyType) != null) {
//			EpmMapping childEpmMap = findEpmMapOfType(epmMappings, propertyType);
//			// is a non java class with a property propertyName
//			EntityDatabase<Object> itemDatabase = new EntityDatabase<Object>( propertyType);
//			// try to get an existing item
//			StringBuffer query = new StringBuffer("select e from ");
//			query.append(propertyTypeName);
//			query.append(" e where e.");
//			query.append(childEpmMap.getLinkingPropertyName());
//			query.append("=");
//			if (resultSetValue instanceof String) {
//				query.append("'");
//				query.append(resultSetValue);
//				query.append("'");
//			} else {
//				query.append(resultSetValue);
//			}
//			List<Object> items = itemDatabase.executeQuery(query.toString());
//			if (items.size() > 0) {
//				return items.get(0);
//			} else {
//				// update child list
//				updateInnoForceWithEpm(childEpmMap);
//				// re-try to get the child item
//				items = itemDatabase.executeQuery(query.toString());
//				if (items.size() > 0) {
//					return items.get(0);
//				} else {
//					throw new Exception("Could not find: " + propertyTypeName + " with property: " + childEpmMap.getLinkingPropertyName() + " with value: " + resultSetValue);
//				}
//			}
//		}
//		throw new Exception("Missing code to convert the query result value");
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
			DomainProvider domainProvider = Introspect.getDomainProvider();
			PropertyInfo propertyInfo = domainProvider.getPropertyInfo(domainClass, propertyName);
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
