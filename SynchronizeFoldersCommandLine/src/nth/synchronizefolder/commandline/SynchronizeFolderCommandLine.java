package nth.synchronizefolder.commandline;

import nth.introspect.Introspect;
import nth.introspect.ui.commandline.IntrospectInitializerForCommandLine;
import nth.synchronizefolder.domain.FolderService;

public class SynchronizeFolderCommandLine {

	private SynchronizeFolderCommandLine(String[] arguments) {
		// initialize introspect framework
		IntrospectInitializerForCommandLine initializer = new IntrospectInitializerForCommandLine(this, arguments);
		initializer.registerFrontEndServiceClass(FolderService.class);
		Introspect.init(initializer);
	}

	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		new SynchronizeFolderCommandLine(arguments);
	}

}
