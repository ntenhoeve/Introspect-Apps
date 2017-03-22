package nth.accounts.domain.account;

import nth.accounts.domain.tag.Tag;
import nth.introspect.generic.filter.Filter;

public class AccountUserFilter implements Filter<Account> {

	private Tag userToFind;

	public AccountUserFilter(Tag userToFind) {
		this.userToFind = userToFind;
	}
	
	@Override
	public boolean isMatch(Account account) {
		return userToFind==account.getUser();
	}

}
