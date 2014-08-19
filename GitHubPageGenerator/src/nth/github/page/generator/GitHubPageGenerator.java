package nth.github.page.generator;

import java.util.ArrayList;
import java.util.List;

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
		return new ArrayList<Class<?>>();
	}

}
