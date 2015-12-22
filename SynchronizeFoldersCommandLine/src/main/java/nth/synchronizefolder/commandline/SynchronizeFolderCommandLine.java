package nth.synchronizefolder.commandline;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.ui.commandline.IntrospectApplicationForCommandLine;
import nth.synchronizefolder.domain.FolderService;

public class SynchronizeFolderCommandLine extends IntrospectApplicationForCommandLine{

	private SynchronizeFolderCommandLine(String[] arguments) {
		super(arguments);
	}

	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		new SynchronizeFolderCommandLine(arguments);
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		List<Class<?>> initializer=new ArrayList<>();
		initializer.add(FolderService.class);
		return initializer;
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return new ArrayList<>();
	}

}
