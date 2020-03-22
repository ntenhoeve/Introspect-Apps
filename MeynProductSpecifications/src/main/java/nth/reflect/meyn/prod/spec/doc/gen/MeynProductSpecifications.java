package nth.reflect.meyn.prod.spec.doc.gen;

import java.util.Arrays;
import java.util.List;

import nth.reflect.fw.gui.style.MaterialColorPalette;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.gui.style.basic.Color;
import nth.reflect.fw.ui.swing.ReflectApplicationForSwing;
import nth.reflect.meyn.prod.spec.doc.gen.dom.generator.ProductSpecificationDocumentGenerator;
import nth.reflect.meyn.prod.spec.doc.gen.dom.order.SalesOrderService;

public class MeynProductSpecifications extends ReflectApplicationForSwing {

	public static void main(String[] commandLineArguments) {
		launch();
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(ProductSpecificationDocumentGenerator.class, SalesOrderService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return Arrays.asList();
	}

	@Override
	public ColorProvider getColorProvider() {
		return new ColorProvider(new Color(0, 120, 91), MaterialColorPalette.orange500(), MaterialColorPalette.white());
	}

}
