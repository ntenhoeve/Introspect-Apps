package nth.meyn.control.systems.dom.activity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import nth.meyn.control.systems.dom.employee.Employee;
import nth.reflect.infra.hibernate.entity.EntityId;

@Entity
@Table(name = "activitydata")
public class Activity extends EntityId  {

	private String name;
	private Employee scheduledFor;
	private Employee completedBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne()
	@JoinColumn(name = "Planned", referencedColumnName = "Initial")
	public Employee getScheduledFor() {
		return scheduledFor;
	}

	public void setScheduledFor(Employee scheduledFor) {
		this.scheduledFor = scheduledFor;
	}

	@OneToOne()
	@JoinColumn(name = "Ready", referencedColumnName = "Initial")
	public Employee getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(Employee completedBy) {
		this.completedBy = completedBy;
	}

	@Transient
	public ActivityStatus getStatus() {
		return ActivityStatus.get(this);
	}
	//
	// public void setStatus(ActivityStatus activityStatus) {
	// }

}
