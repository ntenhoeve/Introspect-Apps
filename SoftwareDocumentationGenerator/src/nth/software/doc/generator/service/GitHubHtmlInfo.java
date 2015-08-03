package nth.software.doc.generator.service;

import java.io.File;

import nth.introspect.layer5provider.reflection.behavior.order.Order;

public class GitHubHtmlInfo extends GitHubInfo {

	private File githubHtmlProjectLocation;

	@Order(sequenceNumber=21)
	public File getGithubHtmlProjectLocation() {
		return githubHtmlProjectLocation;
	}

	public void setGithubHtmlProjectLocation(File githubHtmlProjectLocation) {
		this.githubHtmlProjectLocation = githubHtmlProjectLocation;
	}
}
