package nth.accounts.domain.account;

import java.util.List;

import nth.accounts.domain.repository.AccountsRepository;
import nth.accounts.domain.repository.ObjectRepositoryForDifferentTypes;
import nth.introspect.filter.Filter;

public class AccountDataAccess {
	private ObjectRepositoryForDifferentTypes repository;

	public AccountDataAccess() {
		repository = AccountsRepository.getInstance();
	}

	public List<Account> getAll() throws Exception {
		Filter<Object> typeFilter = new Filter<Object>() {

			@Override
			public boolean isMatch(Object obj) {
				return Account.class == obj.getClass();
			}
		};
		return (List<Account>) repository.getAll(typeFilter);
	}

	public void persist(Account account) throws Exception {
		repository.persist(account);
	}

	public void delete(Account account) throws Exception {
		repository.delete(account);
	}

}
