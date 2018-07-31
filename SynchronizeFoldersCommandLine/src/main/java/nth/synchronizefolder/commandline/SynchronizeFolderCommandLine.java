package nth.synchronizefolder.commandline;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.ui.commandline.ReflectApplicationForCommandLine;
import nth.synchronizefolder.domain.FolderService;

public class SynchronizeFolderCommandLine extends ReflectApplicationForCommandLine{


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
