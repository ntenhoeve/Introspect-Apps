package nth.meyn.project.copy;

import java.io.IOException;
import java.nio.file.Path;

import nth.meyn.project.copy.folder.SiteFolder;
import nth.meyn.project.copy.folder.SiteFolderFilter;
import nth.meyn.project.copy.folder.SystemFolder;
import nth.meyn.project.copy.folder.SystemFolderFilter;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class CopyService {

	@ParameterFactory
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void copy(SourceAndDestination sourceAndDestination) throws IOException {
		Path sourcePath = sourceAndDestination.getVerifiedSourcePath();
		Path destinationPath = sourceAndDestination.getVerifiedDestinationPath();

		if (new SiteFolderFilter().test(sourcePath)) {
			SiteFolder siteFolder = new SiteFolder(sourcePath);
			siteFolder.copy(destinationPath);
		} else if (new SystemFolderFilter().test(sourcePath)) {
			SystemFolder systemFolder = new SystemFolder(sourcePath);
			systemFolder.copy(destinationPath);
		} else {
			throw new RuntimeException(
					"Source: " + sourcePath.toString() + " is not a site folder or a system folder");

		}
	}

}
