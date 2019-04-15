package nth.meyn.controls.configurator.dom.site;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Site {
	private int number;
	private String customerName;
	private List<String> oldCustommerNames = new ArrayList<>();
	private String city;
	private String country;

	public Site(int number, String customerName, String city, String country) {
		this.number = number;
		this.customerName = customerName;
		this.city = city;
		this.country = country;
	}

	public Site() {
	}

	@Order(10)
	@Min(0)
	@Max(9999)
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@NotBlank
	@Order(20)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@NotBlank
	@Order(30)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@NotBlank
	@Order(40)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Order(50)
	public List<String> getOldCustommerNames() {
		return oldCustommerNames;
	}

	public void setOldCustommerNames(List<String> oldCustommerNames) {
		this.oldCustommerNames = oldCustommerNames;
	}

}
