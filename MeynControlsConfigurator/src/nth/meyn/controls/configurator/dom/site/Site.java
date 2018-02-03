package nth.meyn.controls.configurator.dom.site;

public class Site {
	private int number;
	private String customerName;
	private String city;
	private String country;

	public Site(int number, String customerName, String city, String country) {
		this.number = number;
		this.customerName = customerName;
		this.city = city;
		this.country = country;
	}
	
	public Site(){
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
