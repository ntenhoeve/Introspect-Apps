package nth.meyn.jetstreamscalder.dom.section;

public class ThreePassWideMiddle extends Section {

	@Override
	public int getWidthInMm() {
		return 1502;
	}

	@Override
	public int getLengthInMm() {
		return 2600;
	}

	@Override
	public int getWaterVolumeInLiter() {
		return 3090;
	}

	@Override
	public int getEffectiveScalderLengthInMm() {
		return getLengthInMm()*3;
	}

}
