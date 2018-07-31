package nth.foldersynch;

import java.util.Arrays;
import java.util.List;

import nth.foldersynch.dom.config.ConfigurationRepository;
import nth.foldersynch.dom.config.ConfigurationService;
import nth.foldersynch.dom.device.DeviceRepository;
import nth.foldersynch.dom.device.DeviceService;
import nth.reflect.fw.ui.swing.ReflecttApplicationForSwing;
import nth.reflect.infra.generic.xml.XmlConverter;
import nth.reflect.infra.xmlfilerepository.XmlFileRepository;

public class FolderSynch extends ReflecttApplicationForSwing {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(DeviceService.class, ConfigurationService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return Arrays.asList(XmlConverter.class, XmlFileRepository.class, DeviceRepository.class,
				ConfigurationRepository.class);
	}

}
