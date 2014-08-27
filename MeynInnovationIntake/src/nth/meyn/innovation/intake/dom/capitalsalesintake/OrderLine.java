package nth.meyn.innovation.intake.dom.capitalsalesintake;

import java.math.BigDecimal;
import java.util.Date;

import nth.introspect.provider.domain.info.valuemodel.annotations.Format;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInTable;

public class OrderLine {
	private BigDecimal orderNumber;
	private Date orderDate;
	private BigDecimal projectNumber;
	private String productCode;
	private String description;
	private BigDecimal count;
	private String customerName;
	private String country;
	private String salesRegion;
	private BigDecimal amount;
	private BigDecimal listPrice;
	private BigDecimal minimumSalesPrice;
	private BigDecimal transferPrice;
	private OrderLineType orderLineType;

	@OrderInTable(1)
	public BigDecimal getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(BigDecimal orderNumber) {
		this.orderNumber = orderNumber;
	}

	@OrderInTable(2)
	@Format("yyyy-MM-dd")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@OrderInTable(3)
	public BigDecimal getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(BigDecimal projectNumber) {
		this.projectNumber = projectNumber;
	}

	@OrderInTable(4)
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	@OrderInTable(5)
	public ProductGroup getProductGroup() {
		return ProductGroup.getFromProductCode(productCode);
	}

	@OrderInTable(6)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OrderInTable(7)
	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	@OrderInTable(8)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@OrderInTable(9)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@OrderInTable(10)
	public String getSalesRegion() {
		return salesRegion;
	}

	public void setSalesRegion(String salesRegion) {
		this.salesRegion = salesRegion;
	}

	@OrderInTable(11)
	@Format("###,###,##0.00")
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@OrderInTable(12)
	@Format("###,###,##0.00")
	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	@OrderInTable(13)
	@Format("###,###,##0.00")
	public BigDecimal getMinimumSalesPrice() {
		return minimumSalesPrice;
	}

	public void setMinimumSalesPrice(BigDecimal minimumSalesPrice) {
		this.minimumSalesPrice = minimumSalesPrice;
	}

	@OrderInTable(14)
	@Format("###,###,##0.00")
	public BigDecimal getTransferPrice() {
		return transferPrice;
	}

	public void setTransferPrice(BigDecimal transferPrice) {
		this.transferPrice = transferPrice;
	}

	@OrderInTable(15)
	public OrderLineType getOrderLineType() {
		return orderLineType;
	}

	public void setOrderLineType(OrderLineType orderLineType) {
		this.orderLineType = orderLineType;
	}

	
	

}
