package nth.meyn.innovation.intake.dom.orderline;

import java.util.Date;

import nth.meyn.innovation.intake.dom.capitalsalesintake.ProductGroup;
import nth.meyn.innovation.intake.dom.product.Product;
import nth.meyn.innovation.intake.dom.product.newness.ProductNewness;
import nth.meyn.innovation.intake.dom.salesregion.SalesRegion;

public class OrderLine {
	private Date intakeDate;
	private int customerNumber;
	private String customerName;
	private String country;
	private SalesRegion salesRegion;
	private int projectNumber;
	private int orderNumber;
	private int orderPosition;
	private String productCode;
	private Product product;
	private String productDescription;
	private ProductGroup productGroup;
	private ProductNewness productNewness;
	private int quantity;
	private double listPriceInEuro;
	private double minimumSalesPriceInEuro;
	private double actualSalesPriceInEuro;
	private double transferPriceInEuro;

	public Date getIntakeDate() {
		return intakeDate;
	}

	public void setIntakeDate(Date intakeDate) {
		this.intakeDate = intakeDate;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public SalesRegion getSalesRegion() {
		return salesRegion;
	}

	public void setSalesRegion(SalesRegion salesRegion) {
		this.salesRegion = salesRegion;
	}

	public int getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getOrderPosition() {
		return orderPosition;
	}

	public void setOrderPosition(int orderPosition) {
		this.orderPosition = orderPosition;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public ProductGroup getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}

	public ProductNewness getProductNewness() {
		return productNewness;
	}

	public void setProductNewness(ProductNewness productNewness) {
		this.productNewness = productNewness;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getListPriceInEuro() {
		return listPriceInEuro;
	}

	public void setListPriceInEuro(double listPriceInEuro) {
		this.listPriceInEuro = listPriceInEuro;
	}

	public double getMinimumSalesPriceInEuro() {
		return minimumSalesPriceInEuro;
	}

	public void setMinimumSalesPriceInEuro(double minimumSalesPriceInEuro) {
		this.minimumSalesPriceInEuro = minimumSalesPriceInEuro;
	}

	public double getActualSalesPriceInEuro() {
		return actualSalesPriceInEuro;
	}

	public void setActualSalesPriceInEuro(double actualSalesPriceInEuro) {
		this.actualSalesPriceInEuro = actualSalesPriceInEuro;
	}

	public double getTransferPriceInEuro() {
		return transferPriceInEuro;
	}

	public void setTransferPriceInEuro(double transferPriceInEuro) {
		this.transferPriceInEuro = transferPriceInEuro;
	}

	public double getDiscountInEuro() {
		return listPriceInEuro - actualSalesPriceInEuro;
	}

	public double getGrossMarginInEuro() {
		return actualSalesPriceInEuro - transferPriceInEuro;
	}

}
