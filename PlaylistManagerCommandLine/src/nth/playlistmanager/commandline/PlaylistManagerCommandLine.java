package nth.playlistmanager.commandline;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.ui.commandline.IntrospectApplicationForCommandLine;
import nth.playlistmanager.domain.PlaylistService;

public class PlaylistManagerCommandLine extends IntrospectApplicationForCommandLine{

	private PlaylistManagerCommandLine(String[] arguments) {
		super(arguments);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		new PlaylistManagerCommandLine(arguments);
	}

	@Override
	public List<Class<?>> getFrontEndServiceClasses() {
		List<Class<?>> initializer=new ArrayList<Class<?>>();
		initializer.add(PlaylistService.class);
		return initializer;
	}

	@Override
	public List<Class<?>> getBackEndServiceClasses() {
		return new ArrayList<Class<?>>();
	}

}
