package nth.meyn.connect.dom.module.trackingtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.image.Image;
import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.introspect.layer5provider.reflection.behavior.icon.Icon;
import nth.introspect.layer5provider.reflection.behavior.serviceobjectchildren.ServiceObjectChildren;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;
import nth.meyn.connect.dom.arrival.flock.FlockService;
import nth.meyn.connect.dom.arrival.grower.GrowerService;
import nth.meyn.connect.dom.arrival.lot.LotService;
import nth.meyn.connect.dom.arrival.transport.TransportService;
import nth.meyn.connect.dom.module.trackingtracing.tracking.TrackingOverview;

@Icon(iconURL=FontAwesomeUrl.ARROWS_H)
@DisplayName(englishName = "Tracking & Tracing")
@ServiceObjectChildren(serviceClasses = { GrowerService.class, FlockService.class,
		TransportService.class, LotService.class })
public class TrackingAndTracingService {
	private List<Class<?>> children;

	public TrackingAndTracingService() {
		children = Arrays.asList(GrowerService.class, FlockService.class, TransportService.class,
				LotService.class);
	}

	// public List<Grower> growers() {
	// return null;
	// }
	//
	// public List<House> houses() {
	// return null;
	// }
	//
	// public List<Flock> flocks() {
	// return null;
	// }
	//
	// public List<Transport> transports(DateRange dateRange) {
	// return null;
	// }
	//
	// public List<Transport> todaysTransports() {
	// return null;
	// }
	//
	// public List<Transport> tomorrowsTransports() {
	// return null;
	// }
	//
	// public List<Lot> lots() {
	// return null;
	// }

	public List<Class<?>> getChildren() {
		return children;
	}

	public List<TrackingOverview> trackingOverviews() {
		return new ArrayList<>();
	}

	public Image backwardTracingReport() {
		return null;
		// TODDO
	}

	public Image forwardTracingReport() {
		return null;
		// TODDO
	}

}
