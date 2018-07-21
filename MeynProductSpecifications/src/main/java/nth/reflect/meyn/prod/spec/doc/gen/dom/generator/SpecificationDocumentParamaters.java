package nth.reflect.meyn.prod.spec.doc.gen.dom.generator;

import nth.introspect.layer5provider.reflection.behavior.order.Order;

public class SpecificationDocumentParamaters {
	private boolean includeTechnicalSpecifications;
	private boolean includeRiskAnalysis;
	private boolean includeSequentialFlowChart;
	private boolean includeMaterialFoodContactList;
	private boolean includeTestingSignoffBoxes;

	@Order(sequenceNumber=1)
	public boolean isIncludeTechnicalSpecifications() {
		return includeTechnicalSpecifications;
	}
	public void setIncludeTechnicalSpecifications(boolean includeTechnicalSpecifications) {
		this.includeTechnicalSpecifications = includeTechnicalSpecifications;
	}
	@Order(sequenceNumber=2)
	public boolean isIncludeRiskAnalysis() {
		return includeRiskAnalysis;
	}
	public void setIncludeRiskAnalysis(boolean includeRiskAnalysis) {
		this.includeRiskAnalysis = includeRiskAnalysis;
	}
	@Order(sequenceNumber=3)
	public boolean isIncludeSequentialFlowChart() {
		return includeSequentialFlowChart;
	}
	public void setIncludeSequentialFlowChart(boolean includeSequentialFlowChart) {
		this.includeSequentialFlowChart = includeSequentialFlowChart;
	}
	@Order(sequenceNumber=4)
	public boolean isIncludeMaterialFoodContactList() {
		return includeMaterialFoodContactList;
	}
	public void setIncludeMaterialFoodContactList(boolean includeMaterialFoodContactList) {
		this.includeMaterialFoodContactList = includeMaterialFoodContactList;
	}
	@Order(sequenceNumber=5)
	public boolean isIncludeTestingSignoffBoxes() {
		return includeTestingSignoffBoxes;
	}
	public void setIncludeTestingSignoffBoxes(boolean includeTestingSignoffBoxes) {
		this.includeTestingSignoffBoxes = includeTestingSignoffBoxes;
	}



}
