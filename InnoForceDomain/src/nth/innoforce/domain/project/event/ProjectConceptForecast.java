package nth.innoforce.domain.project.event;

import java.util.Date;

import nth.innoforce.domain.project.report.properties.ExpectedLaunchDateProperty;
import nth.innoforce.domain.project.report.properties.ExpectedTurnoverProperties;
import nth.innoforce.domain.resource.Resource;

public class ProjectConceptForecast extends ProjectEvent implements ExpectedTurnoverProperties, ExpectedLaunchDateProperty {

	// TODO
	private Resource createdBy;
	private int expectedTurnoverInEuroForYear1;
	private int expectedTurnoverInEuroForYear2;
	private int expectedTurnoverInEuroForYear3;
	private Date expectedLaunchDate;

	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return super.getSummary();
	}

	public Resource getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Resource createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public int getExpectedTurnoverInEuroForYear1() {
		return expectedTurnoverInEuroForYear1 ;
	}

	@Override
	public void setExpectedTurnoverInEuroForYear1(int expectedTurnoverInEuroForYear1) {
		this.expectedTurnoverInEuroForYear1 = expectedTurnoverInEuroForYear1;
	}

	@Override
	public int getExpectedTurnoverInEuroForYear2() {
		return expectedTurnoverInEuroForYear2 ;
	}

	@Override
	public void setExpectedTurnoverInEuroForYear2(int expectedTurnoverInEuroForYear2) {
		this.expectedTurnoverInEuroForYear2 = expectedTurnoverInEuroForYear2;
	}

	@Override
	public int getExpectedTurnoverInEuroForYear3() {
		return expectedTurnoverInEuroForYear3 ;
	}

	@Override
	public void setExpectedTurnoverInEuroForYear3(int expectedTurnoverInEuroForYear3) {
		this.expectedTurnoverInEuroForYear3 = expectedTurnoverInEuroForYear3;
	}

	@Override
	public Date getExpectedLaunchDate() {
		return expectedLaunchDate;
	}

	@Override
	public void setExpectedLaunchDate(Date expectedLaunchDate) {
		this.expectedLaunchDate = expectedLaunchDate;
		
	}


	
}
