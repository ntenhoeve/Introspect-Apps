package nth.software.doc.generator.service;

import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInForm;

public class GitHubInfo extends DocumentationInfo {

	private String gitHubUserName;
	private String gitHubPassword;
	
	@OrderInForm(11)
	public String getGitHubUserName() {
		return gitHubUserName;
	}
	public void setGitHubUserName(String gitHubUserName) {
		this.gitHubUserName = gitHubUserName;
	}
	@OrderInForm(12)
	public String getGitHubPassword() {
		return gitHubPassword;
	}
	public void setGitHubPassword(String gitHubPassword) {
		this.gitHubPassword = gitHubPassword;
	}
	
	
}
