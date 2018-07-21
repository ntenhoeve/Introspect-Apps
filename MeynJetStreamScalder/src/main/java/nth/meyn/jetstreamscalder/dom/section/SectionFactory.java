package nth.meyn.jetstreamscalder.dom.section;

import java.util.ArrayList;
import java.util.List;

import nth.meyn.jetstreamscalder.dom.scalder.NumberOfSections;
import nth.meyn.jetstreamscalder.dom.scalder.RowType;

public class SectionFactory {
	public static List<Section> create(RowType rowType,
			NumberOfSections numberOfSections) {
		switch (rowType) {
		case _2_PASS_STANDARD:
			return create2PassStandardSections(numberOfSections);
		case _2_PASS_WIDE:
			return create2PassWideSections(numberOfSections);
		case _3_PASS_WIDE:
			return create3PassWideSections(numberOfSections);
		case _4_PASS_WIDE:
			return create4PassWideSections(numberOfSections);
		default:
			throw new UnsupportedOperationException();
		}
	}

	private static List<Section> create2PassStandardSections(
			NumberOfSections numberOfSections) {
		List<Section> sections = new ArrayList<>();
		sections.add(new TwoPassStandardBegin());

		for (int i = 0; i < numberOfSections.getNumberOfWholeSections(); i++) {
			sections.add(new TwoPassStandardMiddle());
		}

		if (numberOfSections.hasPumpsOnBothEnds()) {
			sections.add(new TwoPassStandardEndWithPump());
		} else {
			sections.add(new TwoPassStandardEndWithoutPump());
		}

		return sections;
	}

	private static List<Section> create2PassWideSections(
			NumberOfSections numberOfSections) {
		List<Section> sections = new ArrayList<>();
		sections.add(new TwoPassWideBegin());

		for (int i = 0; i < numberOfSections.getNumberOfWholeSections(); i++) {
			sections.add(new TwoPassWideMiddle());
		}

		if (numberOfSections.hasPumpsOnBothEnds()) {
			sections.add(new TwoPassWideEndWithPump());
		} else {
			sections.add(new TwoPassWideEndWithoutPump());
		}

		return sections;
	}

	private static List<Section> create3PassWideSections(
			NumberOfSections numberOfSections) {
		List<Section> sections = new ArrayList<>();
		sections.add(new ThreePassWideBegin());

		for (int i = 0; i < numberOfSections.getNumberOfWholeSections(); i++) {
			sections.add(new ThreePassWideMiddle());
		}

		if (numberOfSections.hasHalveSection()) {
			sections.add(new ThreePassWideMiddleHalve());
		}

		if (numberOfSections.hasPumpsOnBothEnds()) {
			sections.add(new ThreePassWideEndWithPump());
		} else {
			sections.add(new ThreePassWideEndWithoutPump());
		}

		return sections;
	}

	private static List<Section> create4PassWideSections(
			NumberOfSections numberOfSections) {
		List<Section> sections = new ArrayList<>();
		sections.add(new FourPassWideBegin());

		for (int i = 0; i < numberOfSections.getNumberOfWholeSections(); i++) {
			sections.add(new FourPassWideMiddle());
		}

		if (numberOfSections.hasHalveSection()) {
			sections.add(new FourPassWideMiddleHalve());
		}

		if (numberOfSections.hasPumpsOnBothEnds()) {
			sections.add(new FourPassWideEndWithPump());
		} else {
			sections.add(new FourPassWideEndWithoutPump());
		}

		return sections;
	}

}
