package nth.meyn.connect.dom.arrival.house;

import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;

@Description(englishDescription="A building where birds are grown")
public class House {
	private String code;
	private String address;
	private String country;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
