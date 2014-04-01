package nth.meyn.innovation.intake.dom.projectlaunch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import nth.introspect.provider.domain.info.valuemodel.annotations.Format;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.meyn.innovation.intake.dom.capitalsalesintake.OrderLine;

public class ProjectLaunch {

	private String projectName;
	private Date releaseDate;
	private List<String> productCodes;
	private final Integer projectNumber;
	private Date oldProductDate;

	public ProjectLaunch(int projectNumber, String projectName, int releaseYear, int releaseMonth, int releaseDayOfMotnh, String productCodes) {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		Calendar releaseDate = Calendar.getInstance();
		releaseDate.set(releaseYear, releaseMonth-1, releaseDayOfMotnh,0,0,0);
		this.releaseDate=releaseDate.getTime();
		Calendar oldProductDate=((Calendar) releaseDate.clone());
		oldProductDate.add(Calendar.YEAR, 5);//the date after which the project is no longer considered new (5 years after release)
		this.oldProductDate=oldProductDate.getTime();
		StringTokenizer tokenizer = new StringTokenizer(productCodes, ";");
		this.productCodes = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			this.productCodes.add(tokenizer.nextToken());
		}
	}

	public String getProjectName() {
		return projectName;
	}

	@Format("yyyy-MM-dd")
	public Date getReleaseDate() {
		return releaseDate;
	}

	@GenericReturnType(String.class)
	public List<String> getProductCodes() {
		return productCodes;
	}

	public Integer getProjectNumber() {
		return projectNumber;
	}

	/**
	 * @return true if the product was ordered after the project release and before the product was considered to be old and if the product code compares with the product codes of the project release 
	 */
	public boolean orderLineCountsAsInnovationRevenue(OrderLine orderLine) {
		Date orderLineDate = orderLine.getOrderDate();
		String orderLineProductCode=orderLine.getProductCode();
		
		if (orderLineDate.after(releaseDate) && orderLineDate.before(oldProductDate)) {
			//TODO return false if customer is Meyn oostzaan? 
			for (String productCode: productCodes) {
				if (orderLineProductCode.contains(productCode)) {
					return true;
				}
			}
		}
		return false;
	}

}
