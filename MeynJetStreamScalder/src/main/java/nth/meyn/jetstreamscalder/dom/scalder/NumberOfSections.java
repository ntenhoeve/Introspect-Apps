package nth.meyn.jetstreamscalder.dom.scalder;

public enum NumberOfSections {
	_2, _2_HALVE, _3, _3_HALVE, _4, _4_HALVE, _5, _5_HALVE, _6,  _7;//Note: _6_HALVE does not exist!

	public double getSections() {
		switch (this) {
		case _2:
			return 2;
		case _2_HALVE:
			return 2.5;
		case _3:
			return 3;
		case _3_HALVE:
			return 3.5;
		case _4:
			return 4;
		case _4_HALVE:
			return 4.5;
		case _5:
			return 5;
		case _5_HALVE:
			return 5.5;
		case _6:
			return 6;
		case _7:
			return 7;
		default:
			throw new RuntimeException("Unsupported " + this);
		}
	}
	
	public boolean hasPumpsOnBothEnds() {
		return getSections()>=3.5;
	}

	public int getNumberOfWholeSections() {
		return (int) getSections();
	}

	public boolean hasHalveSection() {
		double nrOfSections = getSections();
		return nrOfSections>Math.floor(nrOfSections) ;
	}

	public static NumberOfSections getNumberOfSections(int sections) {
		for (NumberOfSections numberOfSections: NumberOfSections.values()) {
			int sections2 = (int) numberOfSections.getSections();
			if (sections==sections2) {
				return numberOfSections;
			}
		}
		return null;
	}
}
