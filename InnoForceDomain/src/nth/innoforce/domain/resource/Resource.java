package nth.innoforce.domain.resource;

import javax.persistence.Entity;
import javax.persistence.Table;

import nth.introspect.dataaccess.hibernate.entity.BasicEntity;

@Entity
@Table(name="Resources")
public class Resource extends BasicEntity {
	private String name;
	private String code;
	private String emailAddress;
	private String resourceGroup;
	private String accountName;
	private boolean isActive; 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getResourceGroup() {
		return resourceGroup;
	}

	public void setResourceGroup(String resourceGroup) {
		this.resourceGroup = resourceGroup;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	

}
