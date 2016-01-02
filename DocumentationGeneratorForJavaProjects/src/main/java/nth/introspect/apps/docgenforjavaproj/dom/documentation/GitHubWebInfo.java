package nth.introspect.apps.docgenforjavaproj.dom.documentation;

import java.io.File;

import nth.introspect.layer5provider.reflection.behavior.order.Order;

public class GitHubWebInfo extends GitHubInfo {

	private File githubWebProjectLocation;

	@Order(sequenceNumber=21)
	public File getGithubWebProjectLocation() {
		return githubWebProjectLocation;
	}

	public void setGithubWebProjectLocation(File githubWebProjectLocation) {
		this.githubWebProjectLocation = githubWebProjectLocation;
	}
}
