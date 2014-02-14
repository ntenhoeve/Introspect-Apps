package nth.introspect.innoforce.desktop;

import nth.innoforce.domain.documents.DocumentsService;
import nth.innoforce.domain.innovationrevenue.InnovationRevenueService;
import nth.innoforce.domain.product.ProductsService;
import nth.innoforce.domain.project.ProjectsService;
import nth.innoforce.domain.project.roadmap.RoadmapService;
import nth.innoforce.domain.resource.ResourceService;
import nth.introsepect.ui.swing.SwingIntrospectInitializer;
import nth.introspect.Introspect;

public class InnoForceDesktop {

	public static void main(String[] args) {
		
		SwingIntrospectInitializer initializer=new SwingIntrospectInitializer(new InnoForceDesktop());
		initializer.addServiceClass( RoadmapService.class);
		initializer.addServiceClass( ProjectsService.class);
		initializer.addServiceClass(ProductsService.class);
		initializer.addServiceClass(ResourceService.class);
		initializer.addServiceClass(DocumentsService.class);
		initializer.addServiceClass(InnovationRevenueService.class);
		Introspect.init(initializer);
	}

}
