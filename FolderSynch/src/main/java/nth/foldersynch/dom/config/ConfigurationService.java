package nth.foldersynch.dom.config;

import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;

@DisplayName(englishName="Configuration")
public class ConfigurationService {

	private ConfigurationRepository configurationRepository;

	public ConfigurationService(ConfigurationRepository configurationRepository) {
		this.configurationRepository = configurationRepository;
	}

	public void modifyConfiguration(Configuration configuration) throws Exception {
		configurationRepository.store(configuration);
		testConfiguration(configuration);
	}

	private void testConfiguration(Configuration configuration) {
		// TODO throw exception if the FTP connection can not be opened
	}

	public Configuration modifyConfigurationParameterFactory() throws Exception {
		return configurationRepository.read();
	}
}
