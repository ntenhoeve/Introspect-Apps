package nth.accounts.domain.account;

import java.util.List;

import nth.accounts.domain.user.User;
import nth.introspect.filter.FilterUtil;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FormMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;

public class AccountService {

	private AccountDataAccess accountDataAccess;

	public AccountService() {
		accountDataAccess = new AccountDataAccess(); //TODO (DataAccessProvider<Account>) Introspect.getDataAccess(AccountRepository.class);
	}
	
	@GenericReturnType(Account.class)
	@FormMode(FormModeType.executeMethodDirectly)
	public List<Account> allAccountsOfUser(User userToFind) throws Exception {
		List<Account> allAccounts = allAccounts();
		AccountUserFilter filter=new AccountUserFilter(userToFind);
		return FilterUtil.filter(allAccounts, filter);
	}
	
	@GenericReturnType(Account.class)
	public List<Account> allAccounts() throws Exception {
		return accountDataAccess.getAll();
	}
	
	
	
	public void  createAccount(Account account) throws Exception {
		accountDataAccess.persist(account);
	}

	public Account createAccountParameterFactory() {
		return new Account();
	}
	
	public void  modifyAccount(Account account) throws Exception {
		accountDataAccess.persist(account);
	}
	
	@FormMode(FormModeType.executeMethodAfterConformation)
	public void  deleteAccount(Account account) throws Exception {
		accountDataAccess.delete(account);
	}

	@FormMode(FormModeType.showParameterThenClose)
	public void  viewAccount(Account account) throws Exception {
	}

	
}
