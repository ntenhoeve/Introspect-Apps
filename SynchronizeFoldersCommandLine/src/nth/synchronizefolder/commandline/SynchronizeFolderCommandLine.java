package nth.synchronizefolder.commandline;

import nth.introspect.Introspect;
import nth.introspect.ui.commandline.CommandLineIntrospectInitializer;
import nth.synchronizefolder.domain.FolderService;

public class SynchronizeFolderCommandLine {

	private SynchronizeFolderCommandLine(String[] arguments) {
		// initialize introspect framework
		CommandLineIntrospectInitializer initializer = new CommandLineIntrospectInitializer(this, arguments);
		initializer.addServiceClass(FolderService.class);
		Introspect.init(initializer);
	}

	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		new SynchronizeFolderCommandLine(arguments);
	}

}
