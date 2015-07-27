package nth.software.doc.generator.service;

import java.io.File;

import nth.introspect.layer5provider.domain.info.valuemodel.annotations.OrderInForm;

public class GitHubWikiInfo extends GitHubInfo {

	private File gitHubWikiProjectLocation;

	@OrderInForm(21)
	public File getGitHubWikiProjectLocation() {
		return gitHubWikiProjectLocation;
	}

	public void setGitHubWikiProjectLocation(File gitHubWikiProjectLocation) {
		this.gitHubWikiProjectLocation = gitHubWikiProjectLocation;
	}
}
