package nth.introspect.accounts.swing;

import java.util.ArrayList;
import java.util.List;

import nth.accounts.domain.account.AccountService;
import nth.accounts.domain.user.UserService;
import nth.introspect.ui.swing.IntrospectApplicationForSwing;

public class AccountsForSwing extends IntrospectApplicationForSwing {

	public AccountsForSwing(String[] args) {
		super(args);
	}

	public static void main(String[] args) {
		new AccountsForSwing(args);
	}

	public List<Class<?>> getFrontEndServiceClasses() {
		List<Class<?>> frontEndServiceClasses = new ArrayList<Class<?>>();
		frontEndServiceClasses.add(AccountService.class);
		frontEndServiceClasses.add(UserService.class);
		return frontEndServiceClasses;
	}

	public List<Class<?>> getBackEndServiceClasses() {
		return new ArrayList<Class<?>>();
	}
}
