package nth.innoforce.domain.project.event.gate;

import java.text.SimpleDateFormat;

import javax.validation.constraints.NotNull;

import nth.innoforce.domain.gate.decision.GateDecision;
import nth.innoforce.domain.project.event.ProjectEvent;
import nth.innoforce.domain.project.report.properties.ExpectedTurnoverProperties;
import nth.innoforce.domain.project.report.properties.PriorityInProductGroupProperty;
import nth.innoforce.domain.project.stage.ProjectStage;
import nth.innoforce.domain.resource.Resource;
import nth.introspect.provider.domain.info.property.FieldModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FieldMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.Format;

public abstract class GateReport extends ProjectEvent implements ExpectedTurnoverProperties, PriorityInProductGroupProperty {

	private double strategicFitAndImportanceScore;
	private double productAndCompetitiveAdvantageScore;
	private double marketAttractivenessScore;
	private double synergiesAndLeveragingCoreCompetenciesScore;
	private double technicalFeasibilityScore;
	private double financialRewardVersusFinancialRiskScore;
	private double externalPartyCollaborationScore;
	private double projectAttractivenessScore;
	private boolean deliverablesAvailableAndOfQuality;
	private boolean projectResourcesAvailable;
	private GateDecision decision;
	private ProjectStage newStage;
	private Integer priorityInProductGroup;
	// TODO private ProductGroup productgroup;
	private Resource projectLeaderForNextStage;
	private Resource projectManagerForNextStage;
	private String minutesOfMeeting;
	private int expectedTurnoverInEuroForYear1;
	private int expectedTurnoverInEuroForYear2;
	private int expectedTurnoverInEuroForYear3;
	public SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Format("##,#")
	public double getStrategicFitAndImportanceScore() {
		return strategicFitAndImportanceScore;
	}

	public void setStrategicFitAndImportanceScore(double strategicFitAndImportanceScore) {
		this.strategicFitAndImportanceScore = strategicFitAndImportanceScore;
	}

	@Format("##,#")
	public double getProductAndCompetitiveAdvantageScore() {
		return productAndCompetitiveAdvantageScore;
	}

	public void setProductAndCompetitiveAdvantageScore(double productAndCompetitiveAdvantageScore) {
		this.productAndCompetitiveAdvantageScore = productAndCompetitiveAdvantageScore;
	}

	@Format("##,#")
	public double getMarketAttractivenessScore() {
		return marketAttractivenessScore;
	}

	public void setMarketAttractivenessScore(double marketAttractivenessScore) {
		this.marketAttractivenessScore = marketAttractivenessScore;
	}

	@Format("##,#")
	public double getSynergiesAndLeveragingCoreCompetenciesScore() {
		return synergiesAndLeveragingCoreCompetenciesScore;
	}

	public void setSynergiesAndLeveragingCoreCompetenciesScore(double synergiesAndLeveragingCoreCompetenciesScore) {
		this.synergiesAndLeveragingCoreCompetenciesScore = synergiesAndLeveragingCoreCompetenciesScore;
	}

	@Format("##,#")
	public double getTechnicalFeasibilityScore() {
		return technicalFeasibilityScore;
	}

	public void setTechnicalFeasibilityScore(double technicalFeasibilityScore) {
		this.technicalFeasibilityScore = technicalFeasibilityScore;
	}

	@Format("##,#")
	public double getFinancialRewardVersusFinancialRiskScore() {
		return financialRewardVersusFinancialRiskScore;
	}

	public void setFinancialRewardVersusFinancialRiskScore(double financialRewardVersusFinancialRiskScore) {
		this.financialRewardVersusFinancialRiskScore = financialRewardVersusFinancialRiskScore;
	}

	@Format("##,#")
	public double getExternalPartyCollaborationScore() {
		return externalPartyCollaborationScore;
	}

	public void setExternalPartyCollaborationScore(double externalPartyCollaborationScore) {
		this.externalPartyCollaborationScore = externalPartyCollaborationScore;
	}

	@Format("##,#")
	public double getProjectAttractivenessScore() {
		return projectAttractivenessScore;
	}

	public void setProjectAttractivenessScore(double projectAttractivenessScore) {
		this.projectAttractivenessScore = projectAttractivenessScore;
	}

	public boolean isDeliverablesAvailableAndOfQuality() {
		return deliverablesAvailableAndOfQuality;
	}

	public void setDeliverablesAvailableAndOfQuality(boolean deliverablesAvailableAndOfQuality) {
		this.deliverablesAvailableAndOfQuality = deliverablesAvailableAndOfQuality;
	}

	public boolean isProjectResourcesAvailable() {
		return projectResourcesAvailable;
	}

	public void setProjectResourcesAvailable(boolean projectResourcesAvailable) {
		this.projectResourcesAvailable = projectResourcesAvailable;
	}

	@NotNull
	public GateDecision getDecision() {
		return decision;
	}

	public void setDecision(GateDecision decision) {
		this.decision = decision;
	}

	@NotNull
	public ProjectStage getNewStage() {
		return newStage;
	}

	public void setNewStage(ProjectStage newStage) {
		this.newStage = newStage;
	}

	@NotNull
	public Resource getProjectLeaderForNextStage() {
		return projectLeaderForNextStage;
	}

	public void setProjectLeaderForNextStage(Resource projectLeaderForNextStage) {
		this.projectLeaderForNextStage = projectLeaderForNextStage;
	}
	
	@NotNull
	public Resource getProjectManagerForNextStage() {
		return projectManagerForNextStage;
	}

	public void setProjectManagerForNextStage(Resource projectManagerForNextStage) {
		this.projectManagerForNextStage = projectManagerForNextStage;
	}

	@FieldMode(FieldModeType.TEXT_AREA)
	public String getMinutesOfMeeting() {
		return minutesOfMeeting;
	}

	public void setMinutesOfMeeting(String minutesOfMeeting) {
		this.minutesOfMeeting = minutesOfMeeting;
	}

	@Override
	public int getExpectedTurnoverInEuroForYear1() {
		return expectedTurnoverInEuroForYear1;
	}

	@Override
	public void setExpectedTurnoverInEuroForYear1(int expectedTurnoverInEuroForYear1) {
		this.expectedTurnoverInEuroForYear1 = expectedTurnoverInEuroForYear1;
	}

	@Override
	public int getExpectedTurnoverInEuroForYear2() {
		return expectedTurnoverInEuroForYear2;
	}

	@Override
	public void setExpectedTurnoverInEuroForYear2(int expectedTurnoverInEuroForYear2) {
		this.expectedTurnoverInEuroForYear2 = expectedTurnoverInEuroForYear2;
	}

	@Override
	public int getExpectedTurnoverInEuroForYear3() {
		return expectedTurnoverInEuroForYear3;
	}

	@Override
	public void setExpectedTurnoverInEuroForYear3(int expectedTurnoverInEuroForYear3) {
		this.expectedTurnoverInEuroForYear3 = expectedTurnoverInEuroForYear3;
	}

	@NotNull
	@Override
	public Integer getPriorityInProductGroup() {
		return priorityInProductGroup;
	}

	@Override
	public void setPriorityInProductGroup(Integer priorityInProductGroup) {
		this.priorityInProductGroup = priorityInProductGroup;
	}

}
