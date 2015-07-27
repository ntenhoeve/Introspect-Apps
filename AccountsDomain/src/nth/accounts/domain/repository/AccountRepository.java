package nth.accounts.domain.repository;

import nth.introspect.generic.xml.XmlConverter;
import nth.introspect.infrastructure.xmlfilerepository.XmlFileRepository;
import nth.introspect.layer5provider.path.PathProvider;

public class AccountRepository extends XmlFileRepository{
	
	public AccountRepository(PathProvider pathProvider, XmlConverter xmlConverter) {
		super(pathProvider, xmlConverter, AccountRepository.class.getSimpleName(), true);
	}
}
