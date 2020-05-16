package nth.foldersynch;

import java.util.Arrays;
import java.util.List;

import nth.foldersynch.dom.config.ConfigurationRepository;
import nth.foldersynch.dom.config.ConfigurationService;
import nth.foldersynch.dom.device.DeviceRepository;
import nth.foldersynch.dom.device.DeviceService;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.gui.style.MaterialColorPalette;
import nth.reflect.fw.swing.ReflectApplicationForSwing;
import nth.reflect.infra.generic.xml.XmlConverter;
import nth.reflect.infra.xmlfilerepository.XmlFileRepository;

public class FolderSynch extends ReflectApplicationForSwing {

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

	@Override
	public ColorProvider getColorProvider() {
		return new ColorProvider(MaterialColorPalette.blue700(), MaterialColorPalette.orange500(),
				MaterialColorPalette.white());
	}

}
