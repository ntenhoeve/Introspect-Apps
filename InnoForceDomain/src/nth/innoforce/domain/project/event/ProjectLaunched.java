package nth.innoforce.domain.project.event;

import java.util.Date;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;

public class ProjectLaunched extends ProjectEvent {

	private List<String> productCodes;
	
	public ProjectLaunched(Date launchDate) {
		setDate(launchDate);
	}

	@Override
	public String getSummary() {
		String text = Introspect.getLanguageProvider().getText("Product launched on: ");
		text = text + getDate();
		return text;
	}

	@GenericReturnType(String.class)
	public List<String> getProductCodes() {
		return productCodes;
	}

	public void setProductCodes(List<String> productCodes) {
		this.productCodes = productCodes;
	}
	
}
