package nth.github.page.generator;

import java.util.ArrayList;
import java.util.List;

import nth.github.page.generator.dom.git.GitRepository;
import nth.github.page.generator.dom.text.service.TextDocumentFactory;
import nth.github.page.generator.dom.web.service.WebPageFactory;
import nth.github.page.generator.dom.wiki.service.WikiPageFactory;
import nth.introspect.ui.commandline.IntrospectApplicationForCommandLine;

public class GitHubPageGenerator extends IntrospectApplicationForCommandLine {

	public GitHubPageGenerator(String[] arguments) {
		super(arguments);
	}

	public static void main(String[] args) {
		//destination C:\Users\nilsth\My Java\Workspace Introspect\IntrospectGitHubPageGenerator\html
		//source= https://github.com/ntenhoeve/Introspect-Framework
		new GitHubPageGenerator(args);
	}

	@Override
	public List<Class<?>> getFrontEndServiceClasses() {
		List<Class<?>> frontEndServiceClasses = new ArrayList<Class<?>>();
		frontEndServiceClasses.add(GitHubPageService.class);
		return frontEndServiceClasses;
	}

	@Override
	public List<Class<?>> getBackEndServiceClasses() {
		List<Class<?>> backEndServiceClasses = new ArrayList<Class<?>>();
		backEndServiceClasses.add(WikiPageFactory.class);
		backEndServiceClasses.add(WebPageFactory.class);
		backEndServiceClasses.add(TextDocumentFactory .class);
		backEndServiceClasses.add(GitRepository.class);
		return backEndServiceClasses;
	}

}
