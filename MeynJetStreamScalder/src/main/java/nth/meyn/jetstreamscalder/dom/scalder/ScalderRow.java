package nth.meyn.jetstreamscalder.dom.scalder;

import java.util.List;

import nth.meyn.jetstreamscalder.dom.section.Section;
import nth.meyn.jetstreamscalder.dom.section.SectionFactory;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class ScalderRow {

	private RowType rowType;
	private NumberOfSections numberOfSections;
	private List<Section> sections;
	private final NotificationProvider notificationProvider;

	public ScalderRow(NotificationProvider notificationProvider) {
		this.notificationProvider = notificationProvider;
		// init field to defaults
		rowType = RowType._2_PASS_STANDARD;
		numberOfSections = NumberOfSections._4;
		sections = SectionFactory.create(rowType, numberOfSections);
	}

	@Order(sequenceNumber=1)
	public RowType getRowType() {
		return rowType;
	}

	public void setRowType(RowType rowType) {
		if (rowType != null) {
			this.rowType = rowType;
			sections = SectionFactory.create(rowType, numberOfSections);
		}
		notificationProvider.refreshUserInterface();
	}

	@Order(sequenceNumber=2)
	public NumberOfSections getNumberOfSections() {
		return numberOfSections;
	}

	public void setNumberOfSections(NumberOfSections numberOfSections) {
		if (numberOfSections != null) {
			if (RowType._2_PASS_STANDARD != rowType
					&& NumberOfSections._7 == numberOfSections) {
				numberOfSections = NumberOfSections._6;
			}

			if ((RowType._2_PASS_STANDARD == rowType || RowType._2_PASS_WIDE == rowType)
					&& numberOfSections.hasHalveSection()) {
				numberOfSections = NumberOfSections
						.getNumberOfSections(numberOfSections
								.getNumberOfWholeSections());
			}

			this.numberOfSections = numberOfSections;

			sections = SectionFactory.create(rowType, numberOfSections);
		}
		notificationProvider.refreshUserInterface();
	}

	@Order(sequenceNumber=3)
	public int getEffectiveScaldingLengthInMm() {
		int effectiveScaldingLengthInMm = 0;
		for (Section section : sections) {
			effectiveScaldingLengthInMm += section
					.getEffectiveScalderLengthInMm();
		}
		return effectiveScaldingLengthInMm;
	}

	@Order(sequenceNumber=4)
	public double getLengthInMm() {
		int lengthInMm = 0;
		for (Section section : sections) {
			lengthInMm += section.getLengthInMm();
		}
		return lengthInMm;
	}

	@Order(sequenceNumber=5)
	public double getWidthInMm() {
		if (sections.size() > 0) {
			return sections.get(0).getWidthInMm();
		} else {
			return 0;
		}
	}

	@Order(sequenceNumber=6)
	public double getWaterVolumeInLiters() {
		int volumeInLiters = 0;
		for (Section section : sections) {
			volumeInLiters += section.getWaterVolumeInLiter();
		}
		return volumeInLiters;
	}

	public List<Section> getSections() {
		return sections;
	}


}
