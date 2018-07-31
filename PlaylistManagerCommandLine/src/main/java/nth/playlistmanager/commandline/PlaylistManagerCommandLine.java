package nth.playlistmanager.commandline;

import java.util.ArrayList;
import java.util.List;

import nth.playlistmanager.domain.PlaylistService;
import nth.reflect.fw.ui.commandline.ReflectApplicationForCommandLine;

public class PlaylistManagerCommandLine extends ReflectApplicationForCommandLine{

	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		launch(arguments);
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		List<Class<?>> initializer=new ArrayList<Class<?>>();
		initializer.add(PlaylistService.class);
		return initializer;
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return new ArrayList<Class<?>>();
	}

}
