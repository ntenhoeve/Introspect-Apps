package nth.introspect.innoforce.desktop;

import nth.innoforce.domain.documents.DocumentsService;
import nth.innoforce.domain.innovationrevenue.InnovationRevenueService;
import nth.innoforce.domain.product.ProductsService;
import nth.innoforce.domain.project.ProjectsService;
import nth.innoforce.domain.project.roadmap.RoadmapService;
import nth.innoforce.domain.resource.ResourceService;
import nth.introsepect.ui.swing.IntrospectInitializerForSwing;
import nth.introspect.Introspect;

public class InnoForceDesktop {

	public static void main(String[] args) {
		
		IntrospectInitializerForSwing initializer=new IntrospectInitializerForSwing(new InnoForceDesktop());
		initializer.registerFrontEndServiceClass(RoadmapService.class);
		initializer.registerFrontEndServiceClass( ProjectsService.class);
		initializer.registerFrontEndServiceClass(ProductsService.class);
		initializer.registerFrontEndServiceClass(ResourceService.class);
		initializer.registerFrontEndServiceClass(DocumentsService.class);
		initializer.registerFrontEndServiceClass(InnovationRevenueService.class);
		Introspect.init(initializer);
	}

}
