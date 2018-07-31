package nth.meyn.jetstreamscalder.dom.scalder;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenFor;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public enum ScaldingMethod {

	EPIDERMIS_ON(180,150,200,50,53),
	EPIDERMIS_OFF_PINK_BREAST(120,90,150,52,57),
	EPIDERMIS_OFF_WHITE_BREAST(90,90,150,56,60);
	
	private static final String SECONDS = "s";
	private static final String DASH = "-";
	private static final String DEGREE_CELSIUS = "°C";
	private static final String DEGREE_FARENHEID = "°F";
	private final  int recommandedTimeInSeconds;
	private final int minTimeInSeconds;
	private final int maxTimeInSeconds;
	private final int minTempInCelsius;
	private final int maxTempInCelsius;

	ScaldingMethod(int recommandedTimeInSeconds, int minTimeInSeconds,int maxTimeInSeconds, int minTempInCelcius,int maxTempInCelcius) {
		this.recommandedTimeInSeconds = recommandedTimeInSeconds;
		this.minTimeInSeconds = minTimeInSeconds;
		this.maxTimeInSeconds = maxTimeInSeconds;
		this.minTempInCelsius = minTempInCelcius;
		this.maxTempInCelsius = maxTempInCelcius;
	}
	
	@Order(sequenceNumber=1)
	public String getMethodName() {
		String methodName=StringUtil.eliphantCaseToNormal(name());
		return methodName;
	};


	@Order(sequenceNumber=2)
	public String getTemperature() {
		StringBuilder temp = new StringBuilder();
		temp.append(minTempInCelsius);
		temp.append(DEGREE_CELSIUS);
		temp.append(DASH);
		temp.append(maxTempInCelsius);
		temp.append(DEGREE_CELSIUS);
		temp.append("  ");
		temp.append(Math.round((minTempInCelsius * 9 / 5.0) + 32));
		temp.append(DEGREE_FARENHEID);
		temp.append(DASH);
		temp.append(Math.round((maxTempInCelsius * 9 / 5.0) + 32));
		temp.append(DEGREE_FARENHEID);
		return temp.toString();
	}

	@Order(sequenceNumber=3)
	public String getDuration() {
		StringBuilder duration = new StringBuilder();
			duration.append(recommandedTimeInSeconds);
			duration.append(SECONDS);
			duration.append(" ");
		duration.append("(");
		duration.append(minTimeInSeconds);
		duration.append(SECONDS);
		duration.append(DASH);
		duration.append(maxTimeInSeconds);
		duration.append(SECONDS);
		duration.append(")");
		return duration.toString();
	}

	@Override
	public String toString() {
		StringBuilder title=new StringBuilder(getMethodName());
		title.append(": ");
		title.append(getDuration());
//		MaterialAppBarTitle.append(" ");
//		MaterialAppBarTitle.append(getTemperature());
		return title.toString();
	}

	@Hidden(propertyHiddenFor= HiddenFor.TABLES)
	public int getRecommandedTimeInSeconds() {
		return recommandedTimeInSeconds;
	}

	
	
}
