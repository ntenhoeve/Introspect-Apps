package nth.accounts.domain.account;

import java.util.ArrayList;
import java.util.List;

import nth.accounts.domain.user.User;
import nth.introspect.filter.FilterUtil;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.ExecutionMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;

public class AccountService {

	private AccountDataAccess accountDataAccess;

	public AccountService() {
		accountDataAccess = new AccountDataAccess(); // TODO
														// (DataAccessProvider<Account>)
														// Introspect.getDataAccess(AccountRepository.class);
	}

	@GenericReturnType(Account.class)
	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public List<Account> allAccountsOfUser(User userToFind) throws Exception {
		List<Account> allAccounts = allAccounts();
		AccountUserFilter filter = new AccountUserFilter(userToFind);
		return FilterUtil.filter(allAccounts, filter);
	}

	@GenericReturnType(Account.class)
	public List<Account> allAccounts() throws Exception {
		return accountDataAccess.getAll();
	}

	public void createAccount(Account account) throws Exception {
		accountDataAccess.persist(account);
	}

	public Account createAccountParameterFactory() {
		Account account = new Account();
		AccountAttribute attribute = new AccountAttribute();
		attribute.setName("password");
		attribute.setValue("secret");
		ArrayList<AccountAttribute> attributes = new ArrayList<AccountAttribute>();
		attributes.add(attribute);
		account.setAttributes(attributes);
		return account;
	}

	public void modifyAccount(Account account) throws Exception {
		accountDataAccess.persist(account);
	}

	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void deleteAccount(Account account) throws Exception {
		accountDataAccess.delete(account);
	}

	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public Account viewAccount(Account account) throws Exception {
		return account;
	}

}
