package nth.meyn.jetstreamscalder.dom.section;

public class TwoPassWideMiddle extends Section {

	@Override
	public int getWidthInMm() {
		return 827;
	}

	@Override
	public int getLengthInMm() {
		return 2600;
	}

	@Override
	public int getWaterVolumeInLiter() {
		return 1455;
	}

	@Override
	public int getEffectiveScalderLengthInMm() {
		return getLengthInMm()*2;
	}

}
