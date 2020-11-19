package nth.meyn.project.copy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import nth.meyn.project.copy.folder.ControlSystemsFolder;
import nth.meyn.project.copy.folder.SiteFolder;
import nth.meyn.project.copy.folder.SystemFolder;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class SourceAndDestination {
	private File source;
	private File destination;

	@Order(value = 1)
	@Description(defaultEnglish = "Source can be a site number (e.g. 4321) or a system number(e.g. 432105 or 4321DE05) or a site folder (e.g.:\\\\meyn.nl\\project\\BESTURINGSTECHNIEK\\4321-Maple Leaf London ON-Canada) or a system folder (e.g.:\\\\meyn.nl\\project\\BESTURINGSTECHNIEK\\4321-Maple Leaf London ON-Canada\\Systems\\432105-813326-108364-Offal 1)")
	public File getSource() {
		return source;
	}

	public void setSource(File source) {
			ControlSystemsFolder controlSystemsFolder = new ControlSystemsFolder();
			Optional<SystemFolder> optionalSystemFolder=controlSystemsFolder.getSystemFolder(source.getName());
			if (optionalSystemFolder.isPresent()) {
				source=optionalSystemFolder.get().getPath().toFile();
			} else  {
				Optional<SiteFolder> optionalSiteFolder = controlSystemsFolder.getSiteFolder(source.getName());
				if (optionalSiteFolder.isPresent()) {
					source=optionalSiteFolder.get().getPath().toFile();
				}
			}
		this.source = source;
		
	}

	public Path getVerifiedSourcePath() throws IOException {
		verifySource();
		return source.toPath();
	}

	private void verifySource() {
		if (!source.exists()) {
			throw new RuntimeException("Source: " + source.getAbsolutePath() + " does not exist");
		}
		if (!source.isDirectory()) {
			throw new RuntimeException("Source: " + source.getAbsolutePath() + " is not a folder");
		}
	}

	@Order(value = 2)
	@Description(defaultEnglish = "Destination path to where the files need to be copied to")
	public File getDestination() {
		return destination;
	}

	public void setDestination(File destination) {
		this.destination = destination;
	}

	public Path getVerifiedDestinationPath() {
		verifyDestination();
		return destination.toPath();
	}

	private void verifyDestination() {
		if (!destination.exists()) {
			throw new RuntimeException("Destination: " + destination.getAbsolutePath() + " does not exist");
		}
		if (!destination.isDirectory()) {
			throw new RuntimeException("Destination: " + destination.getAbsolutePath() + " is not a folder");
		}
	}

}
