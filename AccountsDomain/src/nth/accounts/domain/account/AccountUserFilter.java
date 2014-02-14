package nth.accounts.domain.account;

import nth.accounts.domain.user.User;
import nth.introspect.filter.Filter;

public class AccountUserFilter implements Filter<Account> {

	private User userToFind;

	public AccountUserFilter(User userToFind) {
		this.userToFind = userToFind;
	}
	
	@Override
	public boolean isMatch(Account account) {
		return userToFind==account.getUser();
	}

}
