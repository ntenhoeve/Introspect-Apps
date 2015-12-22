package nth.accounts.domain.account;

import java.util.Collections;
import java.util.List;

import nth.accounts.domain.repository.AccountRepository;
import nth.accounts.domain.user.User;
import nth.introspect.generic.filter.FilterUtil;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;

public class AccountService {

	private final AccountRepository accountRepository;
	private final NotificationProvider notificationProvider;

	public AccountService(AccountRepository accountRepository, NotificationProvider notificationProvider) {
		this.accountRepository = accountRepository;
		this.notificationProvider = notificationProvider;
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public List<Account> allAccountsOfUser(User userToFind) throws Exception {
		List<Account> allAccounts = allAccounts();
		AccountUserFilter filter = new AccountUserFilter(userToFind);
		return FilterUtil.filter(allAccounts, filter);
	}

	public List<Account> allAccounts() throws Exception {
		return (List<Account>) accountRepository.getAll(Account.class);
	}

	public void createAccount(Account account) throws Exception {
		accountRepository.persist(account);
	}

	public Account createAccountParameterFactory() {
		Account account = new Account();
		return account;
	}

	public void modifyAccount(Account account) throws Exception {
		accountRepository.persist(account);
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void deleteAccount(Account account) throws Exception {
		accountRepository.delete(account);
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public Account viewAccount(Account account) throws Exception {
		return account;
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void moveAccountUp(Account account) throws Exception {
		List<Account> accounts = (List<Account>) accountRepository.getAll(Account.class);
		int index = accounts.lastIndexOf(account);
		if (index > 0 && accounts.size()>1) {
			Collections.swap(accounts, index, index-1);
		}//TODO does not work item is moved but not persisted
		accountRepository.persistAll();
		notificationProvider.refreshUserInterface();
	}
	
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void moveAccountDown(Account account) throws Exception {
		List<Account> accounts = (List<Account>) accountRepository.getAll(Account.class);
		int index = accounts.lastIndexOf(account);
		if (index <= accounts.size() && accounts.size()>1) {
			Collections.swap(accounts, index, index+1);
		}//TODO does not work item is moved but not persisted
		accountRepository.persistAll();
		notificationProvider.refreshUserInterface();
	}

}
