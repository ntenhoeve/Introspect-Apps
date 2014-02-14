package nth.accounts.domain.account;

import java.util.ArrayList;
import java.util.List;

import com.sun.istack.internal.NotNull;

import nth.accounts.domain.user.User;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FormMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInForm;

public class Account {
	private String accountName;
	private User user;
	private List<AccountAttribute> attributes;

	public Account() {
		attributes=new ArrayList<AccountAttribute>();
	}
	
	@NotNull
	@OrderInForm(1)
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@OrderInForm(2)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OrderInForm(3)
	@GenericReturnType(AccountAttribute.class)
	public List<AccountAttribute> getAttributes() {
		return attributes;
	}

	public void userPutUser(User user) {
		setUser(user);
	}
	
	public void userClearUser() {
		setUser(null);
	}
	
	public void setAttributes(List<AccountAttribute> attributes) {
		this.attributes = attributes;
	}


	public void attributesAddEmailAttribute(EmailAddress emailAddress) {
		AccountAttribute attribute=new AccountAttribute();
		attribute.setName("e-mail");
		attribute.setValue(emailAddress.getEmailAddress());
		attributes.add(attribute);
	}
	
	//TODO public void attributesAddUserNameAttribute (like attributesAddEmailAttribute)

	//TODO public void attributesAddPasswordAttribute (like attributesAddEmailAttribute)
	
	//TODO public void attributesAddUrlAttribute (like attributesAddEmailAttribute)
	
	
	public void attributesAddCustomAttribute(AccountAttribute accountAttribute) {
		AccountAttribute attribute=new AccountAttribute();
		attribute.setName(accountAttribute.getName());
		attribute.setValue(accountAttribute.getValue());
		attributes.add(attribute);
	}
	
	@FormMode(FormModeType.executeMethodAfterConformation)
	public void attributesRemoveAttribute(AccountAttribute attribute) {
		attributes.remove(attribute);
	}

	@FormMode(FormModeType.editParameterThenExecuteMethodOrCancel)
	public void attributesModifyAttribute(AccountAttribute attribute) {
	}

	@Override
	public String toString() {
		StringBuffer title=new StringBuffer();
		String userName = (user==null)?null: user.getName();
		if (userName!=null && userName.trim().length()>0) {
			title.append(user.getName().trim());
			title.append("-");
		}
		title.append(accountName);
		return title.toString();
	}
	
	
	
}
