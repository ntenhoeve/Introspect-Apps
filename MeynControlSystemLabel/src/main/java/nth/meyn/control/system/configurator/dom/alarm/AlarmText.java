package nth.meyn.control.system.configurator.dom.alarm;

import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class AlarmText {
	private Language language;
	private String text;

	@Order(value = 1)
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Order(value = 10)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
