package nth.github.page.generator;

import nth.introspect.Introspect;
import nth.introspect.ui.commandline.IntrospectInitializerForCommandLine;

public class GitHubPageGenerator {

	public GitHubPageGenerator(String[] arguments) {
		// initialize introspect framework
		IntrospectInitializerForCommandLine initializer = new IntrospectInitializerForCommandLine(
				this, arguments);
		initializer.registerFrontEndServiceClass(GitHubPageService.class);
		Introspect.init(initializer);
	}

	public static void main(String[] args) {
		//destination C:\Users\nilsth\My Java\Workspace Introspect\IntrospectGitHubPageGenerator\html
		//source= https://github.com/ntenhoeve/Introspect-Framework
		
		new GitHubPageGenerator(args);
	}

}
