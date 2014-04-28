package nth.github.page.generator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInForm;

public class Config {
	// i.e. gitHubUserName=ntenhoeve
	private String gitHubUserName;
	// i.e. gitHubRepositoryName=Introspect-Framework
	private String gitHubRepositoryName;
	// i.e. wordDocumentLocation=C:\Users\nilsth\My Git\ntenhoeve.github.io\docs\Introspect Manual.docx
	private File wordDocumentLocation;
	// i.e. localGitHubRepositoryLocation="C:\Users\nilsth\My Git"
	private File localGitHubRepositoryLocation;

	@OrderInForm(1)
	public String getGitHubUserName() {
		return gitHubUserName;
	}

	public void setGitHubUserName(String gitHubUserName) {
		this.gitHubUserName = gitHubUserName;
	}

	@OrderInForm(2)
	public String getGitHubRepositoryName() {
		return gitHubRepositoryName;
	}

	public void setGitHubRepositoryName(String gitHubRepositoryName) {
		this.gitHubRepositoryName = gitHubRepositoryName;
	}

	@OrderInForm(3)
	public File getWordDocumentLocation() {
		return wordDocumentLocation;
	}

	public void setWordDocumentLocation(File wordDocumentLocation) {
		this.wordDocumentLocation = wordDocumentLocation;
	}

	@OrderInForm(4)
	public File getLocalGitHubRepositoryLocation() {
		return localGitHubRepositoryLocation;
	}

	public void setLocalGitHubRepositoryLocation(
			File localGitHubRepositoryLocation) {
		this.localGitHubRepositoryLocation = localGitHubRepositoryLocation;
	}
	
}
