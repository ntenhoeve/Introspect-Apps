package nth.meyn.control.system.configurator.dom.alarm;

import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Alarm {

	private String name;
	private String textInEnglish;
	private AlarmSeverity severity;
	private List<AlarmText> translatedTexts;

	@Description(englishDescription = "Name as used in alarm structure of functionblock")
	@Order(value = 1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Order(value = 10)
	public String getTextInEnglish() {
		return textInEnglish;
	}

	public void setTextInEnglish(String textInEnglish) {
		this.textInEnglish = textInEnglish;
	}

	@Order(value = 20)
	public AlarmSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(AlarmSeverity severity) {
		this.severity = severity;
	}

	@Order(value = 30)
	public List<AlarmText> getTranslatedTexts() {
		return translatedTexts;
	}

	public void setTranslatedTexts(List<AlarmText> translatedTexts) {
		this.translatedTexts = translatedTexts;
	}

}
