package nth.meyn.control.systems.labels;

import java.util.Arrays;
import java.util.List;

import nth.meyn.control.systems.labels.dom.label.LabelService;
import nth.meyn.control.systems.labels.dom.productgroup.ProductGroupRepository;
import nth.meyn.control.systems.labels.dom.productgroup.ProductGroupService;
import nth.meyn.control.systems.labels.dom.repository.MeynControlSystemRepository;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.layer5provider.reflection.behavior.applicationicon.ApplicationIcon;
import nth.reflect.fw.ui.style.MaterialColorPalette;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.basic.Color;

@ApplicationIcon(iconURL = "reflect-class-resource://nth.meyn.control.systems.labels.MeynControlSystemsLabels/meyn32x32.png")
public class MeynControlSystemsLabels extends ReflectApplicationForJavaFX {

	public static void main(String[] commandLineArguments) {
		launch();
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(LabelService.class, ProductGroupService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return Arrays.asList(MeynControlSystemRepository.class, ProductGroupRepository.class);
	}

	@Override
	public ReflectColors getColors() {
		return new ReflectColors(new Color(0, 120, 91), MaterialColorPalette.orange500(), MaterialColorPalette.white());
	}

}
