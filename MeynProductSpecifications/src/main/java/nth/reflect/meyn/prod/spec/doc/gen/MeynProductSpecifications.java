package nth.reflect.meyn.prod.spec.doc.gen;


import java.util.Arrays;
import java.util.List;

import nth.introspect.ui.swing.IntrospectApplicationForSwing;
import nth.reflect.meyn.prod.spec.doc.gen.dom.generator.ProductSpecificationDocumentGenerator;
import nth.reflect.meyn.prod.spec.doc.gen.dom.order.SalesOrderService;

public class MeynProductSpecifications extends IntrospectApplicationForSwing{

	public static void main(String[] commandLineArguments) {
		launch();
	}
	
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(ProductSpecificationDocumentGenerator.class, SalesOrderService.class);
	}

	public List<Class<?>> getInfrastructureClasses() {
		return Arrays.asList();
	}

	

}
