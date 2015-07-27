package nth.software.doc.generator.service;

import java.io.File;

import nth.introspect.layer5provider.domain.info.valuemodel.annotations.OrderInForm;

public class GitHubHtmlInfo extends GitHubInfo {

	private File githubHtmlProjectLocation;

	@OrderInForm(21)
	public File getGithubHtmlProjectLocation() {
		return githubHtmlProjectLocation;
	}

	public void setGithubHtmlProjectLocation(File githubHtmlProjectLocation) {
		this.githubHtmlProjectLocation = githubHtmlProjectLocation;
	}
}
