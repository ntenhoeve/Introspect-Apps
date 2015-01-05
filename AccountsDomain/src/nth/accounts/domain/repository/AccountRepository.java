package nth.accounts.domain.repository;

import nth.introspect.provider.path.PathProvider;

public class AccountRepository extends XmlFileRepository{
	
	public AccountRepository(PathProvider pathProvider) {
		super(pathProvider, AccountRepository.class.getSimpleName(), true);
	}
}
