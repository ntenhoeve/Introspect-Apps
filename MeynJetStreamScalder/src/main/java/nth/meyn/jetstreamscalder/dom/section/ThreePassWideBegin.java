package nth.meyn.jetstreamscalder.dom.section;

public class ThreePassWideBegin extends Section {

	@Override
	public int getWidthInMm() {
		return 1560;
	}

	@Override
	public int getLengthInMm() {
		return 2880;
	}

	@Override
	public int getWaterVolumeInLiter() {
		return 2400;
	}

	@Override
	public int getEffectiveScalderLengthInMm() {
		return 2110;//TODO verify
	}

}
