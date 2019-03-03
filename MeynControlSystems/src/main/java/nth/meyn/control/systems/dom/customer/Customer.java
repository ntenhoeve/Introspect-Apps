package nth.meyn.control.systems.dom.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.reflection.behavior.format.Format;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenFor;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

@Entity
@Table(name = "_customers")
public class Customer implements Serializable {

	private static final long serialVersionUID = -6462915990348598074L;
	private int id;
	private int number;
	private String name;
	private String country;
	private String city;

	@Id
	@GeneratedValue
	@Hidden(propertyHiddenFor = HiddenFor.TABLES_AND_FORMS)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Order(value = 0)
	@Format(pattern = "####")
	@Column(name = "Location")
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Order(value = 1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	@Order(value = 1)
	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	@Order(value = 1)
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return TitleBuilder.getInstance().append(number).append(name).append(city).append(country).toString();
	}
}
