package nth.foldersynch.dom.config;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import nth.reflect.fw.layer4infrastructure.InfrastructureContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.infra.generic.xml.XmlConverter;
import nth.reflect.infra.xmlfilerepository.XmlFileRepository;

public class ConfigurationRepository {

	private XmlFileRepository xmlFileRepository;

	public ConfigurationRepository(ReflectionProvider reflectionProvider,
			InfrastructureContainer providerContainer) throws MalformedURLException, URISyntaxException {
		XmlConverter xmlConverter = new XmlConverter(reflectionProvider, providerContainer);
		this.xmlFileRepository = new XmlFileRepository( xmlConverter, "FolderSync.xml",
				true);
	}

	public Configuration read() {
		try {
			@SuppressWarnings("unchecked")
			List<Configuration> configurations = (List<Configuration>) xmlFileRepository.getAll(Configuration.class);
			if (configurations.size() > 0) {
				return configurations.get(0);
			}
		} catch (Exception e) {
		}
		return new Configuration();
	}

	public void store(Configuration configuration) throws Exception {
		xmlFileRepository.persist(configuration);
	}
}
