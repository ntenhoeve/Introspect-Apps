package nth.meyn.jetstreamscalder.dom.scalder;


public enum RowType {
	_2_PASS_STANDARD, _2_PASS_WIDE, _3_PASS_WIDE, _4_PASS_WIDE;

	public int getNumberOfPasses() {
		switch (this) {
		case _2_PASS_STANDARD:
			return 2;
		case _2_PASS_WIDE:
			return 2;
		case _3_PASS_WIDE:
			return 3;
		case _4_PASS_WIDE:
			return 4;
		default:
			throw new RuntimeException("Unsupported " + this);
		}
	}
}
