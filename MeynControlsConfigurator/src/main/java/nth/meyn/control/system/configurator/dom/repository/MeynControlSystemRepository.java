package nth.meyn.control.system.configurator.dom.repository;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import nth.reflect.fw.layer4infrastructure.InfrastructureContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.infra.generic.xml.XmlConverter;

public class MeynControlSystemRepository extends nth.reflect.infra.xmlfilerepository.XmlFileRepository {

	public MeynControlSystemRepository(ReflectionProvider reflectionProvider, InfrastructureContainer providerContainer)
			throws MalformedURLException, URISyntaxException {
		super(createDataBaseFile(), new XmlConverter(reflectionProvider, providerContainer), true);
	}

	private static File createDataBaseFile() {
		return new File(
				"C:/Users/nilsth/Documents/My Databases/" + MeynControlSystemRepository.class.getSimpleName() + ".xml");
	}

}
