package nth.meyn.connect.dom.module.efficiency;

import java.util.ArrayList;
import java.util.List;

import nth.meyn.connect.dom.asset.Asset;

public class EfficiencyOverviewExampleFactory {

	private static final int LINE_SPEED_PER_HOUR = 15000;

	public static List<EfficiencyOverview> create() {
		List<EfficiencyOverview> overviews=new ArrayList<>();
		overviews.add(create("Poultry Processing Plant", EfficencyStatus.RunningAlmostFullCapacity, 80,100,100, 5.1*LINE_SPEED_PER_HOUR+5.2*LINE_SPEED_PER_HOUR));
		overviews.add(create("Container Arrival Line", EfficencyStatus.RunningFullCapacity, 100,100,100,2*8.2*LINE_SPEED_PER_HOUR));
		overviews.add(create("Container CAS Line 1", EfficencyStatus.RunningFullCapacity, 100,100,100,8.1*LINE_SPEED_PER_HOUR));
		overviews.add(create("Container CAS Line 2", EfficencyStatus.RunningMediumCapacity, 80,100,100,7.9*LINE_SPEED_PER_HOUR));
		overviews.add(create("Container Tilter Line 1", EfficencyStatus.RunningFullCapacity, 100,100,100,8.1*LINE_SPEED_PER_HOUR-5));
		overviews.add(create("Container Tilter Line 2", EfficencyStatus.RunningFullCapacity, 80,100,100,7.9*LINE_SPEED_PER_HOUR-5));
		overviews.add(create("Container Washer Line", EfficencyStatus.RunningFullCapacity, 100,100,100,8.1*LINE_SPEED_PER_HOUR-10));
		overviews.add(create("Container Departure Line", EfficencyStatus.StoppedReasonUnknwon, 0,100,100,7.9*LINE_SPEED_PER_HOUR-15));
		overviews.add(create("Slaughter Line 1", EfficencyStatus.RunningFullCapacity, 100,100,100,6.1*LINE_SPEED_PER_HOUR));
		overviews.add(create("Slaughter Line 2", EfficencyStatus.RunningFullCapacity, 100,100,100,6.1*LINE_SPEED_PER_HOUR));
		overviews.add(create("Evisceration Line 1", EfficencyStatus.RunningFullCapacity, 100,100,100,6.1*LINE_SPEED_PER_HOUR));
		overviews.add(create("Evisceration Line 2", EfficencyStatus.RunningFullCapacity, 100,100,100,6.2*LINE_SPEED_PER_HOUR));
		overviews.add(create("Chilling Line 1", EfficencyStatus.RunningFullCapacity, 100,100,100,5.1*LINE_SPEED_PER_HOUR));
		overviews.add(create("Chilling Line 2", EfficencyStatus.RunningFullCapacity, 100,100,100,5.2*LINE_SPEED_PER_HOUR));
		overviews.add(create("Cut up Line 1", EfficencyStatus.StoppedBreakTime, 0,100,100, 4.5*LINE_SPEED_PER_HOUR));
		overviews.add(create("Cut up Line 2", EfficencyStatus.StoppedBreakTime, 0,100,100, 4.4*LINE_SPEED_PER_HOUR));
		overviews.add(create("Cut up Line 3", EfficencyStatus.StoppedBreakTime, 0,100,100,4.0*LINE_SPEED_PER_HOUR));
		overviews.add(create("Cut up Line 4", EfficencyStatus.StoppedBreakTime, 0,100,100,4.1*LINE_SPEED_PER_HOUR));
		overviews.add(create("Whole Leg De-boner Line 1", EfficencyStatus.RunningFullCapacity, 100,100,100,2.1*LINE_SPEED_PER_HOUR));
		overviews.add(create("Whole Leg De-boner Line 2", EfficencyStatus.RunningFullCapacity, 100,100,100,2.3*LINE_SPEED_PER_HOUR));
		overviews.add(create("Whole Leg De-boner Line 3", EfficencyStatus.RunningFullCapacity, 100,100,100,2.2*LINE_SPEED_PER_HOUR));
		overviews.add(create("Rapid Line 1", EfficencyStatus.RunningFullCapacity, 100,100,100,1.3*LINE_SPEED_PER_HOUR));
		overviews.add(create("Rapid Line 2", EfficencyStatus.RunningFullCapacity, 100,100,100,1.4*LINE_SPEED_PER_HOUR));
		overviews.add(create("Rapid Line 3", EfficencyStatus.StoppedBreakdown, 100,100,100,0.5*LINE_SPEED_PER_HOUR));
		return overviews;
	}

	private static EfficiencyOverview create(String assetName, EfficencyStatus status, int availability, int performance, int quality, Double numberOfProductsToday) {
		Asset asset=new Asset();
		asset.setName(assetName);
		
		EfficiencyOverview efficiencyOverview=new EfficiencyOverview();
		efficiencyOverview.setAsset(asset);
		efficiencyOverview.setStatus(status);
		efficiencyOverview.setAvailability(availability);
		efficiencyOverview.setPerformance(performance);
		efficiencyOverview.setQuality(quality);
		efficiencyOverview.setNumberOfProductsToday(numberOfProductsToday.intValue());
		return efficiencyOverview;
	}

}
