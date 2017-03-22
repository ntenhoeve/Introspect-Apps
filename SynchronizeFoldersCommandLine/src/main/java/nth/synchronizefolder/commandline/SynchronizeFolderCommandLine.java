package nth.synchronizefolder.commandline;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.url.UrlProvider;
import nth.introspect.ui.commandline.IntrospectApplicationForCommandLine;
import nth.synchronizefolder.domain.FolderService;

public class SynchronizeFolderCommandLine extends IntrospectApplicationForCommandLine{


	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		launch(arguments);
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
