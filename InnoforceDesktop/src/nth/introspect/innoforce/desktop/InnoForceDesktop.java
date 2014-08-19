package nth.introspect.innoforce.desktop;

import java.util.ArrayList;
import java.util.List;

import nth.innoforce.domain.documents.DocumentsService;
import nth.innoforce.domain.innovationrevenue.InnovationRevenueService;
import nth.innoforce.domain.product.ProductsService;
import nth.innoforce.domain.project.ProjectsService;
import nth.innoforce.domain.project.roadmap.RoadmapService;
import nth.innoforce.domain.resource.ResourceService;
import nth.introspect.ui.swing.IntrospectApplicationForSwing;

public class InnoForceDesktop extends IntrospectApplicationForSwing {

	public InnoForceDesktop(String[] args) {
		super(args);
	}

	public static void main(String[] args) {
		new InnoForceDesktop(args);
	}

	@Override
	public List<Class<?>> getFrontEndServiceClasses() {
		List<Class<?>> frontEndServiceClasses = new ArrayList<>();
		frontEndServiceClasses.add(RoadmapService.class);
		frontEndServiceClasses.add( ProjectsService.class);
		frontEndServiceClasses.add(ProductsService.class);
		frontEndServiceClasses.add(ResourceService.class);
		frontEndServiceClasses.add(DocumentsService.class);
		frontEndServiceClasses.add(InnovationRevenueService.class);
		return frontEndServiceClasses;
	}

	@Override
	public List<Class<?>> getBackEndServiceClasses() {
		return new ArrayList<>();
	}

}
