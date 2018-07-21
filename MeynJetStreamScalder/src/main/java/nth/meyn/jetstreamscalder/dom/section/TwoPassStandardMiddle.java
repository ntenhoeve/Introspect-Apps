package nth.meyn.jetstreamscalder.dom.section;

public class TwoPassStandardMiddle extends Section {

	@Override
	public int getWidthInMm() {
		return 712;
	}

	@Override
	public int getLengthInMm() {
		return 2600;
	}

	@Override
	public int getWaterVolumeInLiter() {
		return 1085;
	}

	@Override
	public int getEffectiveScalderLengthInMm() {
		return getLengthInMm()*2;
	}

}
