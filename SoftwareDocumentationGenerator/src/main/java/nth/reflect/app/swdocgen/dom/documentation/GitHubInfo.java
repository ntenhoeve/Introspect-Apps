package nth.reflect.app.swdocgen.dom.documentation;

import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class GitHubInfo extends DocumentationInfo {

	private String gitHubUserName;
	private String gitHubPassword;
	
	@Order(sequenceNumber=11)
	public String getGitHubUserName() {
		return gitHubUserName;
	}
	public void setGitHubUserName(String gitHubUserName) {
		this.gitHubUserName = gitHubUserName;
	}
	@Order(sequenceNumber=12)
	public String getGitHubPassword() {
		return gitHubPassword;
	}
	public void setGitHubPassword(String gitHubPassword) {
		this.gitHubPassword = gitHubPassword;
	}
	
	
}
