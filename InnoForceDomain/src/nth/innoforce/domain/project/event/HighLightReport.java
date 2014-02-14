package nth.innoforce.domain.project.event;

import java.util.Locale;

import nth.innoforce.domain.project.stage.ProjectStage;
import nth.innoforce.domain.resource.Resource;
import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.property.FieldModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FieldMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInForm;
import nth.introspect.provider.domain.info.valuemodel.annotations.VisibleInForm;
import nth.introspect.provider.language.LanguageProvider;

public class HighLightReport extends ProjectEvent {

	private Integer percentageOfCurrentStageCompleted;
	private ProjectStage currentProjectStage;
	private Resource creator;
	private String source;
	private String highLights;

	@VisibleInForm(false)
	@Override
	public String getSummary() {
		if (currentProjectStage == null) {
			return "";
		} else {
			StringBuffer summary = new StringBuffer();
			LanguageProvider languagePort = Introspect.getLanguageProvider();
			String key = languagePort.getKey(currentProjectStage);
			String defaultValue = languagePort.getDefaultValue(key);
			summary.append("Project stage: ");
			summary.append(languagePort.getText(Locale.ENGLISH, key, defaultValue));
			if (percentageOfCurrentStageCompleted != null) {
				summary.append(" (");
				summary.append(percentageOfCurrentStageCompleted);
				summary.append("% completed)");
			}
			return summary.toString();
		}
	}

	@OrderInForm(3)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@OrderInForm(4)
	public Resource getCreator() {
		return creator;
	}

	public void setCreator(Resource creator) {
		this.creator = creator;
	}

	@OrderInForm(5)
	public ProjectStage getCurrentProjectStage() {
		return currentProjectStage;
	}

	public void setCurrentProjectStage(ProjectStage currentProjectStage) {
		this.currentProjectStage = currentProjectStage;
	}

	@OrderInForm(6)
	//@Format("0%")
	public Integer getPercentageOfCurrentStageCompleted() {
		return percentageOfCurrentStageCompleted;
	}

	public void setPercentageOfCurrentStageCompleted(Integer percentageOfCurrentStageCompleted) {
		this.percentageOfCurrentStageCompleted = percentageOfCurrentStageCompleted;
	}
	
	@OrderInForm(7)
	@FieldMode(FieldModeType.TEXT_AREA)
	public String getHighLights() {
		return highLights;
	}

	public void setHighLights(String highLights) {
		this.highLights = highLights;
	}

	

	
}
