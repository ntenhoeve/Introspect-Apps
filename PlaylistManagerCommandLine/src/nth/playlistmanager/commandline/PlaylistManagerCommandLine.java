package nth.playlistmanager.commandline;

import nth.introspect.Introspect;
import nth.introspect.ui.commandline.IntrospectInitializerForCommandLine;
import nth.playlistmanager.domain.PlaylistService;

public class PlaylistManagerCommandLine{

	private PlaylistManagerCommandLine(String[] arguments) {
		//initialize introspect framework
		IntrospectInitializerForCommandLine initializer=new IntrospectInitializerForCommandLine(this,  arguments);
		initializer.registerFrontEndServiceClass(PlaylistService.class);
		Introspect.init(initializer);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		new PlaylistManagerCommandLine(arguments);
	}

}
