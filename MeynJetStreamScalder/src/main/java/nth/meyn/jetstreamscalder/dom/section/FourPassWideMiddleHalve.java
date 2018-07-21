package nth.meyn.jetstreamscalder.dom.section;

public class FourPassWideMiddleHalve extends ThreePassWideMiddle {


	@Override
	public int getLengthInMm() {
		return super.getLengthInMm()/2;
	}

	@Override
	public int getWaterVolumeInLiter() {
		return super.getWaterVolumeInLiter()/2;
	}

	@Override
	public int getEffectiveScalderLengthInMm() {
		return getLengthInMm()*4;
	}

}
