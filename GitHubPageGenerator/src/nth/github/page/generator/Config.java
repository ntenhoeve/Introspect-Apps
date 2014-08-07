package nth.github.page.generator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import nth.introspect.provider.domain.info.valuemodel.annotations.OrderInForm;

public class Config {
	// i.e. gitHubUserName=ntenhoeve
	private String gitHubUserName;
	// i.e. gitHubPassword=secret
	private String gitHubPassword;
	// i.e. gitHubRepositoryName=Introspect-Framework
	private String gitHubRepositoryName;
	// i.e. wordDocumentLocation=C:\Users\nilsth\My Git\ntenhoeve.github.io\docs\Introspect Manual.docx
	private File wordDocumentLocation;
	// i.e. localGitHubRepositoryLocation="C:\Users\nilsth\My Git"
	private File localGitRepositoryLocation;

	@OrderInForm(1)
	public File getWordDocumentLocation() {
		return wordDocumentLocation;
	}

	public void setWordDocumentLocation(File wordDocumentLocation) {
		this.wordDocumentLocation = wordDocumentLocation;
	}

	
	@OrderInForm(2)
	public String getGitHubUserName() {
		return gitHubUserName;
	}

	public void setGitHubUserName(String gitHubUserName) {
		this.gitHubUserName = gitHubUserName;
	}

	@OrderInForm(3)
	public String getGitHubPassword() {
		return gitHubPassword;
	}

	public void setGitHubPassword(String gitHubPassword) {
		this.gitHubPassword = gitHubPassword;
	}

	
	@OrderInForm(4)
	public String getGitHubRepositoryName() {
		return gitHubRepositoryName;
	}

	public void setGitHubRepositoryName(String gitHubRepositoryName) {
		this.gitHubRepositoryName = gitHubRepositoryName;
	}


	@OrderInForm(5)
	public File getLocalGitRepositoryLocation() {
		return localGitRepositoryLocation;
	}

	public void setLocalGitRepositoryLocation(
			File localGitRepositoryLocation) {
		this.localGitRepositoryLocation = localGitRepositoryLocation;
	}

	
}
