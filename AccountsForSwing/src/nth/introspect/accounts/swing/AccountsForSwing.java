package nth.introspect.accounts.swing;

import nth.accounts.domain.account.AccountService;
import nth.accounts.domain.user.UserService;
import nth.introsepect.ui.swing.IntrospectInitializerForSwing;
import nth.introspect.Introspect;

public class AccountsForSwing {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IntrospectInitializerForSwing initializer = new IntrospectInitializerForSwing(new AccountsForSwing());
		initializer.registerFrontEndServiceClass(AccountService.class);
		initializer.registerFrontEndServiceClass(UserService.class);
		Introspect.init(initializer);
	}
}
