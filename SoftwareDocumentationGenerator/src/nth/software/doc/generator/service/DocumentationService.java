package nth.software.doc.generator.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import nth.software.doc.generator.factory.DocumentationReader;
import nth.software.doc.generator.framer.HtmlSingleFileFramer;
import nth.software.doc.generator.javafile.MultipleFileException;
import nth.software.doc.generator.model.DocumentationModel;
import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.parser.JavaDocParser;
import nth.software.doc.generator.repository.GitRepository;
import nth.software.doc.generator.tokenizer.FoundToken;
import nth.software.doc.generator.tokenizer.JavaDocTokenizer;

/**
 * TODO update
 * 
 * • Create a GitHub account via https://github.com/join • Create a GitHub
 * project via https://github.com/new • Clone the GitHub project repository in
 * eclipse (i.e. https://github.com/ntenhoeve/Introspect-Framework.git) • Clone
 * the Github wiki repository in eclipse (i.e.
 * https://github.com/ntenhoeve/Introspect-Framework.wiki.git) • Create a GitHub
 * website repository called (USERNAME.github.io) via https://github.com/new
 * (see https://pages.github.com/) • Clone the GitHub USERNAME.github.io project
 * repository in eclipse (i.e.
 * https://github.com/ntenhoeve/ntenhoeve.github.io.git)
 * 
 * Then create a M$ Word document that documents your project. You can use: •
 * Normal text • Code text (use “code” style which you need to create your self)
 * • Chapter titles (use heading styles up to 3 levels deep) • Lists (use
 * bullets. Only one level deep) • Hyperlinks (external or references to
 * chapters within the word document)
 * 
 * Good examples of chapters that you put in your M$ Word document are: •
 * Introduction • Documentation • Getting started • Demo’s • Downloads • Roadmap
 * • Issues • About the developer(s)
 * 
 * Every time you update the M$ Word document start the GitHubPageGenerator. The
 * GitHubPageGenerator will parse the M$ Word document and generate web pages
 * wiki pages for GitHub into the local repositories.
 * 
 * The GitHubPageGenerator then commit and push these repositories to the GitHub
 * server, effectively updating the web site and the wiki: • Web page:
 * http://USERNAME.github.io • Wiki: https://github.com/USERNAME/REPOSITORY/wiki
 * 
 * @author nilsth
 *
 */

public class DocumentationService {
	
	private final GitRepository gitRepository;

	public DocumentationService(GitRepository gitRepository) {
		this.gitRepository = gitRepository;
	}
	
	public void createGitHubWikiDocumentation(GitHubWikiInfo gitHubWikiInfo) {
		// TODO see createGitHubHtmlDocumentation for insperation
	}

	public void createGitHubHtmlDocumentation(GitHubHtmlInfo gitHubHtmlInfo)
			throws IOException, MultipleFileException {
		String javaDoc = DocumentationReader
				.readAllDocumentation(gitHubHtmlInfo);

		JavaDocParser javaDocParser = new JavaDocParser(javaDoc);
		List<Node> nodes = javaDocParser.parse();
		DocumentationModel documentationModel = new DocumentationModel(
				gitHubHtmlInfo.getProjectsFolder(), nodes);


		deleteFolderContents(gitHubHtmlInfo.getGithubHtmlProjectLocation());

		HtmlSingleFileFramer htmlSingleFileFramer = new HtmlSingleFileFramer(
				documentationModel, gitHubHtmlInfo, gitHubHtmlInfo.getGithubHtmlProjectLocation());
		htmlSingleFileFramer.frame();

		gitRepository.commitAndPush(gitHubHtmlInfo, gitHubHtmlInfo.getGithubHtmlProjectLocation());
	}

	// TODO does not work
	// public String createGitHubHtmlDocumentationDescription() {
	// return "TODO";
	// }

	public void createHtmlDocumentation(HtmlInfo htmlInfo) throws IOException,
			MultipleFileException {
		String javaDoc = DocumentationReader.readAllDocumentation(htmlInfo);

		JavaDocParser javaDocParser = new JavaDocParser(javaDoc);
		List<Node> nodes = javaDocParser.parse();
		DocumentationModel documentationModel = new DocumentationModel(
				htmlInfo.getProjectsFolder(), nodes);

		deleteFolderContents(htmlInfo.getDestinationFolder());

		HtmlSingleFileFramer htmlSingleFileFramer = new HtmlSingleFileFramer(
				documentationModel, htmlInfo, htmlInfo.getDestinationFolder());
		htmlSingleFileFramer.frame();
	}

	private void deleteFolderContents(File folder) {
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
