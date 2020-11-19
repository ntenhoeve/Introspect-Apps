package nth.meyn.project.copy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import nth.meyn.project.copy.folder.ControlSystemsFolder;
import nth.meyn.project.copy.folder.SiteFolder;
import nth.meyn.project.copy.folder.SystemFolder;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class SourceAndDestination {
	private Path source;
	private Path destination;

	@Order(value = 1)
	@Description(defaultEnglish = "Source can be a site number (e.g. 4321) or a system number(e.g. 432105 or 4321DE05) or a site folder (e.g.:\\\\meyn.nl\\project\\BESTURINGSTECHNIEK\\4321-Maple Leaf London ON-Canada) or a system folder (e.g.:\\\\meyn.nl\\project\\BESTURINGSTECHNIEK\\4321-Maple Leaf London ON-Canada\\Systems\\432105-813326-108364-Offal 1)")
	public Path getSource() {
		return source;
	}

	public void setSource(Path source) {
			ControlSystemsFolder controlSystemsFolder = new ControlSystemsFolder();
			String number = source.getName(source.getNameCount()-1).toString();
			Optional<SystemFolder> optionalSystemFolder=controlSystemsFolder.getSystemFolder(number);
			if (optionalSystemFolder.isPresent()) {
				source=optionalSystemFolder.get().getPath();
			} else  {
				Optional<SiteFolder> optionalSiteFolder = controlSystemsFolder.getSiteFolder(number);
				if (optionalSiteFolder.isPresent()) {
					source=optionalSiteFolder.get().getPath();
				}
			}
		this.source = source;
		
	}


	private void verifySource() {
		if (!Files.exists(source)) {
			throw new RuntimeException("Source: " + source + " does not exist");
		}
		if (!Files.isDirectory(source)) {
			throw new RuntimeException("Source: " + source + " is not a folder");
		}
	}

	@Order(value = 2)
	@Description(defaultEnglish = "Destination path to where the files need to be copied to")
	public Path getDestination() {
		return destination;
	}

	public void setDestination(Path destination) {
		this.destination = destination;
	}

	private void verifyDestination() {
		if (!Files.exists(destination)) {
			throw new RuntimeException("Destination: " + destination + " does not exist");
		}
		if (!Files.isDirectory(destination)) {
			throw new RuntimeException("Destination: " + destination + " is not a folder");
		}
	}

	public void verify()  {
		verifySource();
		verifyDestination();
	}

	
}
