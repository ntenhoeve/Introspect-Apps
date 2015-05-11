package nth.accounts.domain.repository;

import nth.introspect.infrastructure.xmlfilerepository.XmlFileRepository;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.util.xml.XmlConverter;

public class AccountRepository extends XmlFileRepository{
	
	public AccountRepository(PathProvider pathProvider, XmlConverter xmlConverter) {
		super(pathProvider, xmlConverter, AccountRepository.class.getSimpleName(), true);
	}
}
