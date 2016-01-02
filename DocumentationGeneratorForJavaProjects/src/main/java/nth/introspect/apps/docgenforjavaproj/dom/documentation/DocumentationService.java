package nth.introspect.apps.docgenforjavaproj.dom.documentation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nth.introspect.apps.docgenforjavaproj.dom.javadoc.JavaDocFactory;
import nth.introspect.apps.docgenforjavaproj.dom.javafile.JavaFile;
import nth.introspect.apps.docgenforjavaproj.dom.javafile.JavaFileFactory;
import nth.introspect.apps.docgenforjavaproj.dom.page.web.GitHubWebPageFactory;
import nth.introspect.apps.docgenforjavaproj.dom.page.wiki.GitHubWikiPageFactory;
import nth.introspect.apps.docgenforjavaproj.dom.page.wiki.WikiPage;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <h3>Creating GitHub Repositories</h3>
 * <p>
 * Getting started from scratch:
 * <ul>
 * <li>Create a GitHub account via https://github.com/join</li>
 * <li>Create a GitHub project via https://github.com/new</li>
 * <li>Clone the GitHub project repository (
 * https://github.com/{GIT_HUB_USER_NAME}/{REPOSITORY_NAME}.git) with your <a
 * href="https://nl.wikipedia.org/wiki/Integrated_development_environment"
 * >IDE</a> to your local hard disk</li>
 * </ul>
 * </p>
 * <h3>Writing or update documentation</h3>
 * <ul>
 * <li>
 * Add documentation in the JavaDoc of your classes.
 * <li>Create a class or interface that ties all the documentation together.
 * This Class or Interface file must contain JavaDoc with {@insert} tags to
 * refer to other Classes or Interfaces with JavaDoc that needs to be included
 * into the documentation. See the JavaDoc of the
 * {@link SoftwareDocumentationGenerator} or {@link DocumentationService} as an
 * example</li>
 * <li>
 * (Re)publish the documentation to the GitHub server when the JavaDoc has been
 * updated (see next chapters).</li>
 * </ul>
 * 
 * Examples of chapters (class or interface files with JavaDoc) for your
 * documentation are:
 * <ul>
 * <li>
 * Introduction</li>
 * <li>Documentation</li>
 * <li>Getting started</li>
 * <li>Demo�s</li>
 * <li>Downloads</li>
 * <li>Roadmap</li>
 * <li>Issues</li>
 * <li>About the developer(s)</li>
 * </ul>
 * </p> <h3>(Re)publishing documentation to GitHub Wiki</h3>
 * <p>
 * First you need to clone your Github Wiki repository (e.g.
 * https://github.com/{GIT_HUB_USER_NAME}/{REPORITORY_NAME}.wiki.git) with your
 * <a href="https://nl.wikipedia.org/wiki/Integrated_development_environment"
 * >IDE</a> to your local hard disk. This you only need to do once.
 * </p>
 * <p>
 * Then you can (re)publish the documentation to the GitHub Wiki by running the
 * {@link SoftwareDocumentationGenerator} with the following parameters (see
 * also
 * {@link DocumentationService#createGitHubWikiDocumentation(GitHubWikiInfo)}):
 * </p>
 * 
 * <pre>
 * createGitHubWikiDocumentation {WORKSPACE_FOLDER} {CLASS_NAME_OF_DOCUMENTATION_ROOT} {GIT_HUB_USER_NAME} {GIT_HUB_PASSWORD} {FILE_LOCATION_LOCAL_GIT_HUB_WIKI_REPOSITORY}
 * </pre>
 * 
 * Where:
 * <ul>
 * <li>{WORKSPACE_FOLDER}: the location where all the required java projects can
 * be found. E.g.: "M:/My Git/Introspect-Framework"</li>
 * <li>{CLASS_NAME_OF_DOCUMENTATION_ROOT}: The name of the class or interface
 * that is the root of your documentation that ties in all this other
 * documentation classes or interfaces with help of the {@insert ...} tag.
 * E.G."IntrospectDocumentation"</li>
 * <li>{GIT_HUB_USER_NAME}: User name of GitHub account. E.g.:"ntenhoeve"</li>
 * <li>{GIT_HUB_PASSWORD}: Password of GitHub account. E.g.:"Password123"</li>
 * <li>{FILE_LOCATION_LOCAL_GIT_HUB_WIKI_REPOSITORY}: Local file location of the
 * GitHub repository for the Wiki pages. E.g.:"M:\My
 * Git\Introspect-Framework.wiki"</li>
 * </ul>
 * <p>
 * The {@link SoftwareDocumentationGenerator} will then parse the JavaDoc of the
 * {CLASS_NAME_OF_DOCUMENTATION_ROOT} and generate Wiki pages into the local
 * GitHub {FILE_LOCATION_LOCAL_GIT_HUB_WIKI_REPOSITORY}. This GitHub repository
 * will then be committed and pushed onto the GitHub server (effectively
 * publishing the Wiki documentation).
 * </p>
 * The (re)published GitHub Wiki documentation can then be found at
 * https://github.com/{GIT_HUB_USER_NAME}/{REPOSITORY_NAME}/wiki
 * 
 * <h3>(Re)publishing documentation to GitHub web page</h3>
 * <p>
 * First you need to create a GitHub web site repository called
 * {GIT_HUB_USER_NAME}.github.io via https://github.com/new (see
 * https://pages.github.com/) and then clone the GitHub
 * {GIT_HUB_USER_NAME}.github.io repository (e.g.
 * https://github.com/ntenhoeve/ntenhoeve.github.io.git) in your <a href
 * ="https://nl.wikipedia.org/wiki/Integrated_development_environment">IDE</a>
 * to your local hard disk. This you only need to do once.
 * </p>
 * <p>
 * Than you can (re)publish the documentation to the GitHub web page by running
 * the {@link SoftwareDocumentationGenerator} with the following parameters (see
 * also
 * {@link DocumentationService#createGitHubHtmlDocumentation(GitHubWebInfo)
 * (GitHubWikiInfo)}):
 * 
 * <pre>
 * createGitHubHtmlDocumentation {WORKSPACE_FOLDER} {CLASS_NAME_OF_DOCUMENTATION_ROOT} {GIT_HUB_USER_NAME} {GIT_HUB_PASSWORD} {FILE_LOCATION_LOCAL_GIT_HUB_WEB_REPOSITORY}
 * </pre>
 * 
 * Where:
 * <ul>
 * <li>{WORKSPACE_FOLDER}: the location where all the required java projects can
 * be found. E.g.: "M:/My Git/Introspect-Framework"</li>
 * <li>{CLASS_NAME_OF_DOCUMENTATION_ROOT}: The name of the class or interface
 * that is the root of your documentation that ties in all this other
 * documentation classes or interfaces with help of the {@insert ...} tag.
 * E.G."IntrospectDocumentation"</li>
 * <li>{GIT_HUB_USER_NAME}: User name of GitHub account. E.g.:"ntenhoeve"</li>
 * <li>{GIT_HUB_PASSWORD}: Password of GitHub account. E.g.:"Password123"</li>
 * <li>{FILE_LOCATION_LOCAL_GIT_HUB_WEB_REPOSITORY}: Local file location of the
 * GitHub repository for the Web page. E.g.:"M:\My Git\ntenhoeve.github.io"</li>
 * </ul>
 * <p>
 * The {@link SoftwareDocumentationGenerator} will then parse the JavaDoc of the
 * {CLASS_NAME_OF_DOCUMENTATION_ROOT} and generate a web page into the local
 * GitHub {FILE_LOCATION_LOCAL_GIT_HUB_WEB_REPOSITORY}. This GitHub repository
 * will then be committed and pushed onto the GitHub server (effectively
 * publishing the Web documentation).
 * </p>
 * 
 * The (re)published GitHub Web documentation can then be found at
 * http://{GIT_HUB_USER_NAME}.github.io
 * 
 * 
 * @author Nils ten Hoeve
 *
 */

public class DocumentationService {

	private final GitHubWebPageFactory gitHubWebPageFactory;
	private final GitHubWikiPageFactory gitHubWikiPageFactory;

	public DocumentationService(GitHubWebPageFactory gitHubWebPageFactory, GitHubWikiPageFactory gitHubWikiPageFactory) {
		this.gitHubWebPageFactory = gitHubWebPageFactory;
		this.gitHubWikiPageFactory = gitHubWikiPageFactory;
	}

	public void createGitHubWebPages(GitHubWebInfo info) throws IOException {
		gitHubWebPageFactory.createGitHubWebPages(info);
	}
	
	public void createGitHubWikiPages(GitHubWikiInfo info) throws IOException {
		gitHubWikiPageFactory.createGitHubWikiPages(info);
	}

}