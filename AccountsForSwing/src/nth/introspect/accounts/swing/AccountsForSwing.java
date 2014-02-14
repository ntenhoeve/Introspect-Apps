package nth.introspect.accounts.swing;

import nth.accounts.domain.account.AccountService;
import nth.accounts.domain.user.UserService;
import nth.introsepect.ui.swing.SwingIntrospectInitializer;
import nth.introspect.Introspect;

public class AccountsForSwing {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SwingIntrospectInitializer initializer = new SwingIntrospectInitializer(new AccountsForSwing());
		initializer.addServiceClass(AccountService.class);
		initializer.addServiceClass(UserService.class);
		Introspect.init(initializer);
	}
}
