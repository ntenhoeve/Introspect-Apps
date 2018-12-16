package nth.meyn.control.systems.labels.dom.panel;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import nth.meyn.control.systems.labels.dom.system.System;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Panel {
	private int layoutNumber;
	private int panelNr;
	private boolean hasMeynConnect;
	private final List<nth.meyn.control.systems.labels.dom.system.System> systems;

	public Panel() {
		systems = new ArrayList<>();
	}

	@Order(sequenceNumber = 0)
	@Min(0)
	@Max(9999)
	public int getLayoutNumber() {
		return layoutNumber;
	}

	public void setLayoutNumber(int layoutNumber) {
		this.layoutNumber = layoutNumber;
	}

	@Order(sequenceNumber = 10)
	@Min(1)
	@Max(99)
	public int getPanelNr() {
		return panelNr;
	}

	public void setPanelNr(int panelNr) {
		this.panelNr = panelNr;
	}

	@Order(sequenceNumber = 20)
	public boolean isHasMeynConnect() {
		return hasMeynConnect;
	}

	public void setHasMeynConnect(boolean hasMeynConnect) {
		this.hasMeynConnect = hasMeynConnect;
	}

	@Order(sequenceNumber = 30)
	public List<System> getSystems() {
		return systems;
	}

}
