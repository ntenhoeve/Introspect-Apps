package nth.innoforce.data.epmmapping;

/**
 * @deprecated use SqlDataSource instead
 * @author nilsth
 *
 */

public abstract class EpmMapping<T> {
	private Class<T> domainClass;
	private String selectionQuery;
	private String linkingPropertyName;

	public EpmMapping(Class<T> domainClass, String linkingPropertyName, String selectionQuery) {
		super();
		this.domainClass = domainClass;
		this.selectionQuery = selectionQuery;
		this.linkingPropertyName = linkingPropertyName;
	}

	public void modify(T originalDomainObject, T domainObject) {

	}

	public Class<T> getDomainClass() {
		return domainClass;
	}

	public String getSelectionQuery() {
		return selectionQuery;
	}

	public String getLinkingPropertyName() {
		return linkingPropertyName;
	}

}
