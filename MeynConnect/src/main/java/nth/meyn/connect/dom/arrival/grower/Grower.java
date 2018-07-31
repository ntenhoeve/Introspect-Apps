package nth.meyn.connect.dom.arrival.grower;

import java.util.List;

import nth.meyn.connect.dom.arrival.house.House;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

@Description(englishDescription="A farmer that grows birds")
public class Grower {
	private String code;
	private String name;
	private String address;
	private String country;
	private String phoneNumber;
	private String eMailAddress;
	private List<House> houses;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String geteMailAddress() {
		return eMailAddress;
	}
	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}
	public List<House> getHouses() {
		return houses;
	}
	public void setHouses(List<House> houses) {
		this.houses = houses;
	}

	@ParameterFactory
	public void housesAddHouse(House house) {
		houses.add(house);
	}

	@ExecutionMode(mode=ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void housesRemoveHouse(House house) {
		houses.remove(house);
	}

}
