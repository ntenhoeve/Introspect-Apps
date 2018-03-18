package nth.meyn.connect.dom.module.settings;

import nth.introspect.layer1userinterface.controller.UploadStream;

public class ConfigurationService {
	public void loadConfigurationFile(UploadStream uploadStream) {
		//TODO
	}
	
	public UploadStream loadConfigurationFileParameterFactory() {
		UploadStream uploadStream=new UploadStream("Meyn Connect configuration file", "*.xml");
		return uploadStream;
	}
	
	public MeynConnectConfigutation viewMeynConnectConfiguration() {
		//TODO
		return new MeynConnectConfigutation();
	}
	
}
