package nth.meyn.jetstreamscalder.dom.section;

public class FourPassWideEndWithPump extends Section{

	@Override
	public int getWidthInMm() {
		return 1560;
	}

	@Override
	public int getLengthInMm() {
		return 2300;
	}

	@Override
	public int getWaterVolumeInLiter() {
		return 2395;
	}

	@Override
	public int getEffectiveScalderLengthInMm() {
		return 2110*2;
	}

}
