package nth.meyn.connect.dom.module.efficiency;

import nth.meyn.connect.dom.asset.Asset;
import nth.reflect.fw.layer5provider.reflection.behavior.format.Format;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class EfficiencyOverview {
	private Asset asset;
	private EfficencyStatus status;
	private double availability;
	private double performance;
	private double quality;
	private int numberOfProductsToday;

	@Order(value = 10)
	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	@Order(value = 20)
	public EfficencyStatus getStatus() {
		return status;
	}

	public void setStatus(EfficencyStatus status) {
		this.status = status;
	}

	@Format(pattern="##0'%'")//TODO
	@Order(value = 30)
	public double getOveralEquipmentEfficiency() {
		return availability/100 * performance/100 * quality/100 *100;
	}

	@Format(pattern="##0'%'")//TODO
	@Order(value = 40)
	public double getAvailability() {
		return availability;
	}

	public void setAvailability(double availability) {
		this.availability = availability;
	}

	@Format(pattern="##0'%'")//TODO
	@Order(value = 50)
	public double getPerformance() {
		return performance;
	}

	public void setPerformance(double performance) {
		this.performance = performance;
	}

	@Format(pattern="##0'%'")//TODO
	@Order(value = 60)
	public double getQuality() {
		return quality;
	}

	public void setQuality(double quality) {
		this.quality = quality;
	}

	@Order(value = 70)
	public int getNumberOfProductsToday() {
		return numberOfProductsToday;
	}

	public void setNumberOfProductsToday(int numberOfProductsToday) {
		this.numberOfProductsToday = numberOfProductsToday;
	}

}
