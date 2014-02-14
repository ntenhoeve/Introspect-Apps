package nth.innoforce.domain.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import nth.introspect.dataaccess.hibernate.entity.BasicEntity;
import nth.introspect.provider.domain.info.valuemodel.annotations.Enabled;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInTable;
import nth.introspect.provider.domain.info.valuemodel.annotations.VisibleInForm;
import nth.introspect.provider.domain.info.valuemodel.annotations.VisibleInTable;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "ProductGroups")
public class ProductNode extends BasicEntity {
	private ProductNode parent;
	private String item;
	private String nameInEnglish;
	private String nameInDutch;
	private String signalCode; 
	//TODO private Date creationDate;

	private String salesCode;
	private List<ProductNode> children;

	// private Date endOfLifeDate;

	@Enabled(false)
	@VisibleInTable(false)
	public String getPath() {
		StringBuffer path = new StringBuffer();
		if (parent != null && parent.getPath() != null && parent.getPath().trim().length() > 0) {
			path.append(parent.getPath());
			path.append("-");
		}
		path.append(item);
		return path.toString();
	}

	public void setPath(String path) {
		// Do nothing. The path property is read only
	}

	@OrderInTable(1)
	@NotEmpty
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@OrderInTable(4)
	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	// @GenericReturnType(ProjectBenifit.class)
	// public List<ProjectBenifit> getProjectBenifits() {
	// return projectBenifits;
	// }
	//
	// public void setProjectBenifits(List<ProjectBenifit> projectBenifits) {
	// this.projectBenifits = projectBenifits;
	// }

	@VisibleInTable(false)
	@ManyToOne(cascade = CascadeType.ALL)
	public ProductNode getParent() {
		return parent;
	}

	public void setParent(ProductNode parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return getPath();
	}

	
	public void setNameInEnglish(String nameInEnglish) {
		this.nameInEnglish = nameInEnglish;
	}

	@OrderInTable(2)
	public String getNameInEnglish() {
		return nameInEnglish;
	}

	@OrderInTable(3)
	public String getNameInDutch() {
		return nameInDutch;
	}

	public void setNameInDutch(String nameInDutch) {
		this.nameInDutch = nameInDutch;
	}

	
	public void setChildren(List<ProductNode> children) {
		this.children = children;
	}

	@VisibleInForm(false)
	@OneToMany(targetEntity = ProductNode.class, cascade = CascadeType.ALL)
	@GenericReturnType(ProductNode.class)
	public List<ProductNode> getChildren() {
		if (children == null) {
			children = new ArrayList<ProductNode>();
		}
		return children;
	}

	public void setSignalCode(String signalCode) {
		this.signalCode = signalCode;
	}

	@OrderInTable(5)
	public String getSignalCode() {
		return signalCode;
	}

	// public void setEndOfLifeDate(Date endOfLifeDate) {
	// this.endOfLifeDate = endOfLifeDate;
	// }
	//
	// public Date getEndOfLifeDate() {
	// return endOfLifeDate;
	// }

}
