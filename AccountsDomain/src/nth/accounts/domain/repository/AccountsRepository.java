package nth.accounts.domain.repository;

public class AccountsRepository {
	private static ObjectRepositoryForDifferentTypes instance = null;

	protected AccountsRepository() {
		// Exists only to defeat instantiation.
	}

	//TODO het this class trough the Introspect.getDataAccessProvider
	public static ObjectRepositoryForDifferentTypes getInstance() {
		if (instance == null) {
			instance = new ObjectRepositoryForDifferentTypes(AccountsRepository.class.getSimpleName(), true);
		}
		return instance;
	}
}
