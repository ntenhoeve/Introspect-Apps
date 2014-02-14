package nth.innoforce.domain.find;

public class FindParameter {
	private String textToFind;

	public FindParameter() {
	}

	
	public FindParameter(String textToFind) {
		this.textToFind = textToFind;
	}

	public String getTextToFind() {
		return textToFind;
	}

	public void setTextToFind(String textToFind) {
		this.textToFind = textToFind;
	}

}
