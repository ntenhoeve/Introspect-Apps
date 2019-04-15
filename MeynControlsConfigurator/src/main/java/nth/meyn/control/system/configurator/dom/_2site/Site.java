package nth.meyn.control.system.configurator.dom._2site;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import nth.meyn.control.system.configurator.dom._1enterprise.Enterprise;
import nth.meyn.control.system.configurator.dom._4workcenter.WorkCenter;
import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Site {
	private int number;
	private Enterprise owner;
	private List<Enterprise> previousOwners = new ArrayList<>();
	private String city;
	private String country;
	private List<WorkCenter> workCenters = new ArrayList<>();

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
	public Enterprise getOwner() {
		return owner;
	}

	public void setOwner(Enterprise owner) {
		this.owner = owner;
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
	public List<Enterprise> getPreviousOwners() {
		return previousOwners;
	}

	public void setPreviousOwners(List<Enterprise> previousOwners) {
		this.previousOwners = previousOwners;
	}

	@Order(60)
	public List<WorkCenter> getWorkCenters() {
		return workCenters;
	}

	public void setWorkCenters(List<WorkCenter> workCenters) {
		this.workCenters = workCenters;
	}

	@Override
	public String toString() {
		return TitleBuilder.getInstance("-").append(number).append(owner).append(city).append(country).toString();
	}
}
