package nth.meyn.jetstreamscalder.dom.section;

public class FourPassWideEndWithoutPump extends Section {

	@Override
	public int getWidthInMm() {
		return 1502;
	}

	@Override
	public int getLengthInMm() {
		return 1502;
	}

	@Override
	public int getWaterVolumeInLiter() {
		return 1300;
	}

	@Override
	public int getEffectiveScalderLengthInMm() {
		return 1220*2;
	}

}
