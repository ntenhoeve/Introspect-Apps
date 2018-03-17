package nth.meyn.connect.dom.module.settings;

import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.controller.UploadStream;

public class ConfigurationService {
	public void uploadConfigurationFile(UploadStream uploadStream) {
		//TODO
	}
	
	public UploadStream uploadConfigurationFileParameterFactory() {
		UploadStream uploadStream=new UploadStream("Meyn Connect configuration file", "*.xml");
		return uploadStream;
	}
	
	public MeynConnectConfigutation viewMeynConnectConfiguration() {
		//TODO
		return new MeynConnectConfigutation();
	}
	
}
