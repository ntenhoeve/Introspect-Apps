package nth.playlistmanager.commandline;

import nth.introspect.Introspect;
import nth.introspect.ui.commandline.CommandLineIntrospectInitializer;
import nth.playlistmanager.domain.PlaylistService;

public class PlaylistManagerCommandLine{

	private PlaylistManagerCommandLine(String[] arguments) {
		//initialize introspect framework
		CommandLineIntrospectInitializer initializer=new CommandLineIntrospectInitializer(this,  arguments);
		initializer.addServiceClass(PlaylistService.class);
		Introspect.init(initializer);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		new PlaylistManagerCommandLine(arguments);
	}

}
