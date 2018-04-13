package nth.meyn.control.systems.dom.factoryorder;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import nth.introspect.infrastructure.hibernate.entity.EntityId;
import nth.introspect.layer5provider.reflection.behavior.hidden.Hidden;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenFor;
import nth.meyn.control.systems.dom.activity.Activity;
import nth.meyn.control.systems.dom.activity.ActivityStatus;

@Entity
@Table(name="processdata")
public class FactoryOrder extends EntityId {

	private String name;
	private boolean deleted;
	private List<Activity> activities;
	
	@Hidden(propertyHiddenFor=HiddenFor.TABLES_AND_FORMS)
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	@OneToMany()
	@JoinColumn(name="ProcessID")
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	@Transient
	public boolean isCompleted() {
		for (Activity activity : getActivities()) {
			if (activity.getStatus()!=ActivityStatus.COMPLETED && activity.getStatus()!=ActivityStatus.NOT_APPLICABLE) {
				return false;
			}
		}
		return true;
	}
	
	
}
