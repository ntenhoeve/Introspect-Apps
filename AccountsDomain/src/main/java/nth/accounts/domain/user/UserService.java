package nth.accounts.domain.user;

import java.util.List;

import nth.accounts.domain.repository.AccountRepository;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;

public class UserService {

	private AccountRepository userDataAccess;

	public UserService(AccountRepository accountRepository) {
		this.userDataAccess = accountRepository;
	}

	public List<User> allUsers() throws Exception {
		return (List<User>) userDataAccess.getAll(User.class);
	}

	public void modifyUser(User user) throws Exception {
		userDataAccess.persist(user);
	}

	public void createUser(User user) throws Exception {
		userDataAccess.persist(user);
	}

	public User createUserParameterFactory() {
		return new User();
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void deleteUser(User user) throws Exception {
		// TODO throw exception if one of the accounts holds a reference to the
		// user to be deleted
		userDataAccess.delete(user);
	}

}
