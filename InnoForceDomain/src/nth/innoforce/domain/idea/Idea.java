package nth.innoforce.domain.idea;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import nth.innoforce.domain.resource.Resource;
import nth.introspect.dataaccess.hibernate.entity.DeletableEntity;
import nth.introspect.provider.domain.info.property.FieldModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FieldMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInForm;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInTable;

import org.hibernate.validator.constraints.NotEmpty;
@Entity 
@Table(name="Ideas")
public class Idea extends DeletableEntity {

	// private List<Person> owners;
	private String title;
	private Date submitionDate;
	private String conceptDescription;
	private String custommerBenenits;
	private String criticalSuccesRequirements;
	private List<Resource> submitters;

	// private ProjectType projectType;
	// private ProductGroup productGroup;

	// TODO targetTransferPrice, targetSalesPrice, marketPotential, targetCustomer (i.e. high/low tech with spin chiller): move to forecast? IE: Part of the world, total sales potential, our share, competitors share, etc;

	// @DomainClass(Person.class)
	// public List<Person> getOwners() {
	// return owners;
	// }
	//
	// public void setOwners(List<Person> owners) {
	// this.owners = owners;
	// }

	@NotEmpty
	@OrderInTable(0)
	@OrderInForm(0)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OrderInTable(1)
	@OrderInForm(1)
	@FieldMode(FieldModeType.DATE_TIME)
	public Date getSubmitionDate() {
		return submitionDate;
	}

	public void setSubmitionDate(Date submitionDate) {
		this.submitionDate = submitionDate;
	}

	@OrderInForm(2)
	@GenericReturnType(Resource.class)
	@ManyToMany(targetEntity = Resource.class)
	@JoinTable(name = "Ideas_Committers", joinColumns = @JoinColumn(name = "idIdea"), inverseJoinColumns = @JoinColumn(name = "idPerson"))
	public List<Resource> getSubmitters() {
		return submitters;
	}

	public void setSubmitters(List<Resource> submitters) {
		this.submitters = submitters;
	}

	@OrderInTable(3)
	@OrderInForm(3)
	@FieldMode(FieldModeType.RICH_TEXT_AREA)
	public String getConceptDescription() {
		return conceptDescription;
	}

	public void setConceptDescription(String conceptDescription) {
		this.conceptDescription = conceptDescription;
	}

	@NotEmpty
	@OrderInTable(4)
	@OrderInForm(4)
	@FieldMode(FieldModeType.RICH_TEXT_AREA)
	public String getCustommerBenenits() {
		return custommerBenenits;
	}

	public void setCustommerBenenits(String custommerBenenits) {
		this.custommerBenenits = custommerBenenits;
	}

	@NotEmpty
	@OrderInTable(5)
	@OrderInForm(5)
	@FieldMode(FieldModeType.RICH_TEXT_AREA)
	public String getCriticalSuccesRequirements() {
		return criticalSuccesRequirements;
	}

	public void setCriticalSuccesRequirements(String criticalSuccesRequirements) {
		this.criticalSuccesRequirements = criticalSuccesRequirements;
	}

	// public ProjectType getProjectType() {
	// return projectType;
	// }
	//
	// public void setProjectType(ProjectType projectType) {
	// this.projectType = projectType;
	// }
	//
	// public ProductGroup getProductGroup() {
	// return productGroup;
	// }
	//
	// public void setProductGroup(ProductGroup productGroup) {
	// this.productGroup = productGroup;
	// }

}
