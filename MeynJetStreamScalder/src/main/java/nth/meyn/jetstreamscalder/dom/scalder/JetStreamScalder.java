package nth.meyn.jetstreamscalder.dom.scalder;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.introspect.layer5provider.reflection.behavior.order.Order;

public class JetStreamScalder {
	private static final int SECONDS_IN_AN_HOUR = 3600;
	private String customer;
	private ShacklePitch shacklePitch;
	private int lineSpeedInBph;
	private List<ScalderRow> scalderRows;
	private ScaldingMethod scaldingMethod;
	private final NotificationProvider notificationProvider;

	public JetStreamScalder(NotificationProvider notificationProvider ) {
		this.notificationProvider = notificationProvider;
		// initialize fields to default
		shacklePitch = ShacklePitch._6_INCH;
		lineSpeedInBph = 12000;
		scalderRows = new ArrayList<>();
		scalderRows.add(new ScalderRow(notificationProvider));
		scalderRows.add(new ScalderRow(notificationProvider));
		setScaldingMethod(ScaldingMethod.EPIDERMIS_OFF_WHITE_BREAST);
	}

	@Order(sequenceNumber=1)
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@Order(sequenceNumber=2)
	public ShacklePitch getShacklePitch() {
		return shacklePitch;
	}

	public void setShacklePitch(ShacklePitch shacklePitch) {
		if (shacklePitch != null) {
			this.shacklePitch = shacklePitch;
		}
		notificationProvider.refreshUserInterface();

	}

	@Order(sequenceNumber=3)
	public int getLineSpeedInBph() {
		return lineSpeedInBph;
	}

	public void setLineSpeedInBph(int lineSpeedInBph) {
		this.lineSpeedInBph = lineSpeedInBph;
		notificationProvider.refreshUserInterface();
	}

	@Order(sequenceNumber=4)
	public ScaldingMethod getScaldingMethod() {
		return scaldingMethod;
	}

	public void setScaldingMethod(ScaldingMethod scaldingMethod) {
		if (scaldingMethod != null) {
			this.scaldingMethod = scaldingMethod;
		}
		notificationProvider.refreshUserInterface();
	}

	@Order(sequenceNumber=5)
	public List<ScalderRow> getScalderRows() {
		return scalderRows;
	}

	public void setScalderRows(List<ScalderRow> scalderRows) {
		this.scalderRows = scalderRows;
	}

	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void addScalderRows(ScalderRow row) {
		this.scalderRows.add(row);

		verifyScalderRowTypes();

		// no need to refresh the user interface
	}

	public ScalderRow addScalderRowsParameterFactory() {
		return new ScalderRow(notificationProvider);
	}

	
	private void verifyScalderRowTypes() {
		// any of the rows wide?
		boolean containsWideRow = false;
		for (ScalderRow scalderRow : scalderRows) {
			if (scalderRow.getRowType() != RowType._2_PASS_STANDARD) {
				containsWideRow = true;
				break;
			}
		}

		// if so: convert 2 pass standard rows into 2 pass wide
		if (containsWideRow) {
			for (ScalderRow scalderRow : scalderRows) {
				if (scalderRow.getRowType() == RowType._2_PASS_STANDARD) {
					scalderRow.setRowType(RowType._2_PASS_WIDE);
				}
			}
		}
	}


	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void modifyScalderRows(ScalderRow row) {
		verifyScalderRowTypes();
		
		// no addition work needed. Object is already modified
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void deleteScalderRows(ScalderRow row) {
		scalderRows.remove(row);
		// no need to refresh the user interface
	}


	public boolean deleteScalderRowsDisabled() {
		return scalderRows.size() < 2;
	}

	private int getEffectiveScalderLengthInMm() {
		int effectiveScalderLengthInMm = 0;
		for (ScalderRow scalderRow : scalderRows) {
			effectiveScalderLengthInMm += scalderRow
					.getEffectiveScaldingLengthInMm();
		}
		return effectiveScalderLengthInMm;
	}

	public String getEffectiveScalderLength() {
		StringBuilder scalderLength = new StringBuilder();
		scalderLength.append(Math
				.round(getEffectiveScalderLengthInMm() / 100.0) / 10.0);
		scalderLength.append("m (");
		scalderLength.append("required length=");
		scalderLength
				.append(Math.round(getRequiredScalderLengthInMm() / 100.0) / 10.0);
		scalderLength.append("m)");
		return scalderLength.toString();
	}

	public String getEffectiveScalderTimePerRow() {
		StringBuilder scalderTimePerRow = new StringBuilder();
		for (ScalderRow scalderRow : scalderRows) {
			scalderTimePerRow.append("Row");
			scalderTimePerRow.append(scalderRows.indexOf(scalderRow) + 1);
			scalderTimePerRow.append(": ");
			int scaldingTime = calculateScaldingTime(scalderRow
					.getEffectiveScaldingLengthInMm());
			scalderTimePerRow.append(scaldingTime);
			scalderTimePerRow.append("s  ");
		}
		return scalderTimePerRow.toString();
	}

	public String getEffectiveScalderTime() {
		StringBuilder scalderTime = new StringBuilder();
		int scaldingTime = calculateScaldingTime(getEffectiveScalderLengthInMm());
		scalderTime.append(scaldingTime);
		scalderTime.append("s (required time=");
		scalderTime.append(scaldingMethod.getDuration());
		scalderTime.append(")");
		return scalderTime.toString();
	}

	private int calculateScaldingTime(int scaldingLingethInMm) {
		double scaldingTime = scaldingLingethInMm
				/ (getLineSpeedInBph() / 3600.0 * getShacklePitch()
						.getDistanceInMm());
		return (int) Math.round(scaldingTime);
	}

	private int getRequiredScalderLengthInMm() {
		return lineSpeedInBph * scaldingMethod.getRecommandedTimeInSeconds()
				* shacklePitch.getDistanceInMm() / SECONDS_IN_AN_HOUR;
	}

}
