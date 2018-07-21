package nth.meyn.jetstreamscalder.dom.scalder;

public enum ShacklePitch {

	_6_INCH(6 * 25.4), _8_INCH(8 * 25.4);
	private final double distanceInMm;

	private ShacklePitch(double distanceInMm) {
		this.distanceInMm = distanceInMm;
	}

	public int getDistanceInMm() {
		return (int) distanceInMm;
	}
	
}
