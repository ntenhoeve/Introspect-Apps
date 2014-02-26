package nth.accounts.domain.account;

import java.util.ArrayList;
import java.util.List;

import com.sun.istack.internal.NotNull;

import nth.accounts.domain.user.User;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FormMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInForm;
import nth.introspect.util.TitleBuilder;

public class Account {
	private String accountName;
	private User user;
	private List<AccountAttribute> attributes;

	public Account() {
		attributes = new ArrayList<AccountAttribute>();
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
		throw new RuntimeException("Test");
		//setUser(null);
	}

	public void setAttributes(List<AccountAttribute> attributes) {
		this.attributes = attributes;
	}

	public void attributesAddEmailAttribute(AccountAttribute emailAttribute) {
		attributes.add(emailAttribute);
	}

	public AccountAttribute attributesAddEmailAttributeParameterFactory() {
		AccountAttribute attribute=new AccountAttribute();
		attribute.setName("E-Mail");
		return attribute;
	}

	public void attributesAddUserNameAttribute(AccountAttribute userNameAttribute) {
		attributes.add(userNameAttribute);
	}

	public AccountAttribute attributesAddUserNameAttributeParameterFactory() {
		AccountAttribute attribute=new AccountAttribute();
		attribute.setName("User name");
		return attribute;
	}
	
	public void attributesAddPasswordAttribute(AccountAttribute passwordAttribute) {
		attributes.add(passwordAttribute);
	}

	public AccountAttribute attributesAddPasswordAttributeParameterFactory() {
		AccountAttribute attribute=new AccountAttribute();
		attribute.setName("Password");
		return attribute;
	}
	
	public void attributesAddUrlAttribute(AccountAttribute urlAttribute) {
		attributes.add(urlAttribute);
	}

	public AccountAttribute attributesAddUrlAttributeParameterFactory() {
		AccountAttribute attribute=new AccountAttribute();
		attribute.setName("URL");
		return attribute;
	}


	@FormMode(FormModeType.editParameterThenExecuteMethodOrCancel)
	public void attributesAddCustomAttribute(AccountAttribute newAttribute) {
		attributes.add(newAttribute);
	}

	public AccountAttribute attributesAddCustomAttributeParameterFactory() {
		return new AccountAttribute();
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
		TitleBuilder titleBuilder = new TitleBuilder("-");
		if (user != null) {
			titleBuilder.append(user.getName());
		}
		titleBuilder.append(accountName);
		return titleBuilder.toString();
	}

}
