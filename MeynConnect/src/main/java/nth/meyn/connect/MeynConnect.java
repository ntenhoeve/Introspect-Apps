package nth.meyn.connect;

import java.util.Arrays;
import java.util.List;

import nth.meyn.connect.dom.arrival.flock.BirdType;
import nth.meyn.connect.dom.arrival.flock.BirdTypeService;
import nth.meyn.connect.dom.arrival.flock.FlockService;
import nth.meyn.connect.dom.arrival.grower.GrowerService;
import nth.meyn.connect.dom.arrival.location.LocationService;
import nth.meyn.connect.dom.arrival.lot.LotService;
import nth.meyn.connect.dom.arrival.transport.TransportService;
import nth.meyn.connect.dom.module.efficiency.EfficiencyService;
import nth.meyn.connect.dom.module.foodsafetyquality.FoodSafetyAndQualityService;
import nth.meyn.connect.dom.module.maintenance.MaintenanceOrderService;
import nth.meyn.connect.dom.module.maintenance.MaintenanceService;
import nth.meyn.connect.dom.module.orderprocessing.OrderProcessingService;
import nth.meyn.connect.dom.module.settings.ConfigurationService;
import nth.meyn.connect.dom.module.settings.SettingsService;
import nth.meyn.connect.dom.module.trackingtracing.TrackingAndTracingService;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.gui.style.MaterialColorPalette;
import nth.reflect.fw.gui.style.basic.Color;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;

/**
 * TODO: {@link BirdType}, {@link LotService} DataBaseSettingsService and
 * LdapSettingsService to be part of {@link SettingsService};
 * 
 * @author nilsth
 *
 */
@DisplayName(defaultEnglish = "Meyn Connect")
public class MeynConnect extends ReflectApplicationForJavaFX {

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(EfficiencyService.class, OrderProcessingService.class, TrackingAndTracingService.class,
				GrowerService.class, FlockService.class, TransportService.class, LotService.class,
				FoodSafetyAndQualityService.class, MaintenanceService.class, MaintenanceOrderService.class,
				SettingsService.class, BirdTypeService.class, LocationService.class, ConfigurationService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return null;
	}

	@Override
	public ColorProvider getColorProvider() {
		return new ColorProvider(new Color(0, 120, 91), MaterialColorPalette.orange500(), MaterialColorPalette.white());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
