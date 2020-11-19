package nth.meyn.project.copy;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.ui.commandline.ReflectApplicationForCommandLine;

/**
 * Command line application that copies:
 * <ul>
 * <li>all PLC project folders from a customer folder in the BESTURINGSTECHNIEK
 * folder to a local folder.</li>
 * <li>or PLC project folder from the BESTURINGSTECHNIEK folder to a local
 * folder.</li>
 * </ul>
 * 
 * Per project folder it only copies:
 * <ul>
 * <li>the pdf files in the Schematics folder</li>
 * <li>all sub folders in the Software folder, except those called old or
 * oud</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
public class ProjectCopy extends ReflectApplicationForCommandLine {

	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		launch(arguments);
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		List<Class<?>> initializer = new ArrayList<>();
		initializer.add(CopyService.class);
		return initializer;
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return new ArrayList<>();
	}
}
