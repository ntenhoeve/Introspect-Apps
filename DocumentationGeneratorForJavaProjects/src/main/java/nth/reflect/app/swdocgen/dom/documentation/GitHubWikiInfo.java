package nth.reflect.app.swdocgen.dom.documentation;

import java.io.File;

import nth.introspect.layer5provider.reflection.behavior.order.Order;

public class GitHubWikiInfo extends GitHubInfo {

	private File gitHubWikiProjectLocation;

	@Order(sequenceNumber=21)
	public File getGitHubWikiProjectLocation() {
		return gitHubWikiProjectLocation;
	}

	public void setGitHubWikiProjectLocation(File gitHubWikiProjectLocation) {
		this.gitHubWikiProjectLocation = gitHubWikiProjectLocation;
	}
}
