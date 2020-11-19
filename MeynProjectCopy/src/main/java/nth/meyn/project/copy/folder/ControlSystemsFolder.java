package nth.meyn.project.copy.folder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class ControlSystemsFolder extends Folder {

	public ControlSystemsFolder() {
		super(Paths.get("\\\\meyn.nl\\project\\BESTURINGSTECHNIEK"));
	}

	public Optional<SystemFolder> getSystemFolder(String systemNumber) {
		if (SystemFolderFilter.isSystemFolderNumber(systemNumber)) {
			String siteNumber = systemNumber.substring(0, 4);
			Optional<SiteFolder> optionalSiteFolder = getSiteFolder(siteNumber);
			if (optionalSiteFolder.isPresent()) {
				SiteFolder siteFolder = optionalSiteFolder.get();
				Optional<SystemFolder> systemFolder = siteFolder.getSystemFolder(systemNumber);
				return systemFolder;
			}
		}
		return Optional.empty();
	}

	public Optional<SiteFolder> getSiteFolder(String number) {
		if (SiteFolderFilter.isSiteFolderNumber(number)) {
			SiteFolderFilter siteFolderFilter = new SiteFolderFilter(number);
			try {
				Optional<SiteFolder> result = Files.walk(getPath(), 1)//
						.filter(siteFolderFilter)//
						.map(p -> new SiteFolder(p))//
						.findFirst();
				return result;
			} catch (IOException e) {
				return Optional.empty();
			}
		} else {
			return Optional.empty();
		}
	}

}
