package nth.meyn.control.systems.dom.employee;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import nth.introspect.infrastructure.hibernate.entity.EntityId;

@Entity
@Table(name = "_resources")
public class Employee extends EntityId implements Serializable {
	private static final long serialVersionUID = -755707656797022659L;
	private String name;
	private String initial;
	private boolean active;
	private String function;
	private Date created;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	@Column(name = "EnableResource")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Transient()
	public boolean isPerson() {
		return !isNotAssigned() && !isNotApplicable();
	}

	@Transient()
	public boolean isNotApplicable() {
		return name.equals("Not Applicable");
	}

	@Transient()
	public boolean isNotAssigned() {
		return name.equals("-");
	}

	@Override
	public String toString() {
		return name;
	}

}
