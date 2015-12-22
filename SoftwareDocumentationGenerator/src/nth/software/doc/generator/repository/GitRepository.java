package nth.software.doc.generator.repository;

import java.io.File;

import nth.software.doc.generator.SoftwareDocumentationGenerator;
import nth.software.doc.generator.service.GitHubInfo;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class GitRepository {
	
	public void commitAndPush(GitHubInfo gitHubInfo, File locationLocalGitRepository) {

		try {

			StringBuilder path = new StringBuilder();
			path.append(locationLocalGitRepository.getAbsolutePath());
			path.append("/.git");
			Git git = new Git(new FileRepository(path.toString()));

			git.add().addFilepattern(".").call();

			String commitMessage = createCommitMessage();
			git.commit().setMessage(commitMessage).call();

			UsernamePasswordCredentialsProvider user = new UsernamePasswordCredentialsProvider(
					gitHubInfo.getGitHubUserName(), gitHubInfo.getGitHubPassword());
			git.push().setPushAll().setRemote("origin")
					.setCredentialsProvider(user).call();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private String createCommitMessage() {
		StringBuilder commitMessage =new StringBuilder();
		commitMessage.append("Generated new pages with ");
		commitMessage.append( SoftwareDocumentationGenerator.class.getSimpleName());
		return commitMessage.toString();
	}

	public void deleteFolderContents(File folder) {
		File[] files = folder.listFiles();
		if (files != null) { // some JVMs return null for empty dirs
			for (File file : files) {
				if (file.isDirectory() && !file.getName().equals(".git")) {
					deleteFolderContents(file);
				}
				file.delete();
			}
		}
	}


}
