package nth.meyn.control.system.configurator;

import java.util.Arrays;
import java.util.List;

import nth.meyn.control.system.configurator.dom._3area.AreaFactory;
import nth.meyn.control.system.configurator.dom._3area.AreaService;
import nth.meyn.control.system.configurator.dom.ethernetdevice.EthernetDeviceTypeService;
import nth.meyn.control.system.configurator.dom.label.LabelService;
import nth.meyn.control.system.configurator.dom.repository.MeynControlSystemRepository;
import nth.reflect.fw.gui.style.MaterialColorPalette;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.gui.style.basic.Color;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.layer5provider.reflection.behavior.applicationicon.ApplicationIcon;

@ApplicationIcon(iconURL = "reflect-class-resource://nth.meyn.control.system.configurator.MeynControlSystemConfigurator/meyn32x32.png")
public class MeynControlSystemConfigurator extends ReflectApplicationForJavaFX {

	public static void main(String[] commandLineArguments) {
		launch();
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(AreaService.class, EthernetDeviceTypeService.class, LabelService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return Arrays.asList(MeynControlSystemRepository.class, AreaFactory.class);
	}

	@Override
	public ColorProvider getColorProvider() {
		return new ColorProvider(new Color(0, 120, 91), MaterialColorPalette.orange500(), MaterialColorPalette.white());
	}

}
