package nth.meyn.connect.dom.arrival.lot;

import java.time.LocalDateTime;

import nth.meyn.connect.dom.arrival.location.StartLocation;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;

@Description(defaultEnglish = "A group of products (e.g. live birds, breast caps, fillets) that are being processed in one sequence on the same line or equipment and have the same origin (transport)")
public class Lot {
	private String code;
	private LocalDateTime startDateTime;
	private StartLocation startLocation;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public StartLocation getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(StartLocation startLocation) {
		this.startLocation = startLocation;
	}

}
