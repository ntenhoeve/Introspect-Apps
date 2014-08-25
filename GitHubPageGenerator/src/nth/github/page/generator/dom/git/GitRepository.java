package nth.github.page.generator.dom.git;

import java.io.File;

import nth.github.page.generator.Config;
import nth.github.page.generator.GitHubPageGenerator;
import nth.github.page.generator.PathFactory;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class GitRepository {
	
	private void commitAndPush(Config config, File locationLocalGitRepository) {

		try {

			StringBuilder path = new StringBuilder();
			path.append(locationLocalGitRepository.getAbsolutePath());
			path.append("/.git");
			Git git = new Git(new FileRepository(path.toString()));

			git.add().addFilepattern(".").call();

			String commitMessage = "Generated new pages with "
					+ GitHubPageGenerator.class.getSimpleName();
			git.commit().setMessage(commitMessage).call();

			UsernamePasswordCredentialsProvider user = new UsernamePasswordCredentialsProvider(
					config.getGitHubUserName(), config.getGitHubPassword());
			git.push().setPushAll().setRemote("origin")
					.setCredentialsProvider(user).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commitAndPushWebSiteRepository(Config config) {
		commitAndPush(config,
				PathFactory.createLocalGitHubWebSiteRepositoryPath(config));
	}

	public void commitAndPushWebWiki(Config config) {
		commitAndPush(config,
				PathFactory.createLocalGitHubWikiRepositoryPath(config));
	}

	// public static void main(String[] args) {
	//
	// // TODO create folowing in methods and call these from GitServce
	// // TODO authorization from con fig File. class
	//
	//
	//
	// GitService gitService=new GitService();
	//
	// Config config=new Config();
	//
	// gitService.c
	// gitService.commitAndPush(config,
	// PathFactory.createLocalGitHubWebSiteRepositoryPath(config));
	//
	// gitService.commitAndPush(config,
	// PathFactory.createLocalGitHubWikiRepositoryPath(config));
	//
	// }

}
