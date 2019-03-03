package nth.meyn.control.system.configurator.dom.label;

import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Label {
	private String line1;
	private String line2;
	private String line3;
	private String line4;

	@Order(value = 1)
	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	@Order(value = 2)
	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	@Order(value = 3)
	public String getLine3() {
		return line3;
	}

	public void setLine3(String line3) {
		this.line3 = line3;
	}

	@Order(value = 4)
	public String getLine4() {
		return line4;
	}

	public void setLine4(String line4) {
		this.line4 = line4;
	}

}
