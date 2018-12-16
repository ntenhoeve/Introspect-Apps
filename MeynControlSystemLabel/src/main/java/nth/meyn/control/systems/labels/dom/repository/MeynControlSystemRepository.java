package nth.meyn.control.systems.labels.dom.repository;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import nth.reflect.fw.layer4infrastructure.InfrastructureContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.infra.generic.xml.XmlConverter;

public class MeynControlSystemRepository extends nth.reflect.infra.xmlfilerepository.XmlFileRepository {

	public MeynControlSystemRepository(ReflectionProvider reflectionProvider, InfrastructureContainer providerContainer)
			throws MalformedURLException, URISyntaxException {
		super(new XmlConverter(reflectionProvider, providerContainer),
				MeynControlSystemRepository.class.getSimpleName(), true);
	}

}
