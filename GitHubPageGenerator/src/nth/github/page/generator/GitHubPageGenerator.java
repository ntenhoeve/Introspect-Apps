package nth.github.page.generator;

import nth.introspect.Introspect;
import nth.introspect.ui.commandline.CommandLineIntrospectInitializer;

public class GitHubPageGenerator {

	public GitHubPageGenerator(String[] arguments) {
		// initialize introspect framework
		CommandLineIntrospectInitializer initializer = new CommandLineIntrospectInitializer(
				this, arguments);
		initializer.addServiceClass(GitHubPageService.class);
		Introspect.init(initializer);
	}

	public static void main(String[] args) {
		//destination C:\Users\nilsth\My Java\Workspace Introspect\IntrospectGitHubPageGenerator\html
		//source= https://github.com/ntenhoeve/Introspect-Framework
		
		new GitHubPageGenerator(args);
	}

}
