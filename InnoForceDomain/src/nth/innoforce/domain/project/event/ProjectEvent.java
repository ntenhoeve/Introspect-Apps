package nth.innoforce.domain.project.event;

import java.util.Date;

import nth.introspect.Introspect;
import nth.introspect.dataaccess.hibernate.entity.VersionedEntity;
import nth.introspect.provider.domain.info.classinfo.ClassInfo;
import nth.introspect.provider.domain.info.valuemodel.annotations.Format;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInForm;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInTable;
import nth.introspect.provider.domain.info.valuemodel.annotations.VisibleInForm;

public class ProjectEvent extends VersionedEntity {

	private Date date;
	private String summary;

	@OrderInForm(1)
	@OrderInTable(1)
	@Format("yyyy-MM-dd")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@VisibleInForm(false)
	@OrderInTable(2)
	public String getType() {
		ClassInfo classInfo = Introspect.getDomainProvider().getClassInfo(this.getClass());
		return classInfo.getText();
	}

	public void setType(String type) {
		// does nothing
	}

	@VisibleInForm(false)
	@OrderInTable(3)
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
