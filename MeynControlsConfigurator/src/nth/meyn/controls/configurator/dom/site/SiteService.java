package nth.meyn.controls.configurator.dom.site;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nth.meyn.controls.configurator.dom.settings.Settings;
import nth.meyn.controls.configurator.dom.settings.SettingsRepository;

public class SiteService {

	private Settings settings;

	public SiteService(SettingsRepository settingsRepository) {
		settings = settingsRepository.getSettings();
	}

	public List<Site> allSites() {
		File layoutFolder = new File(settings.getLayoutFolder());
		File[] siteFolders = layoutFolder.listFiles();
		List<Site> sites = new ArrayList<>();
		for (File siteFolder : siteFolders) {
			Optional<Site> result = createSite(siteFolder);
			if (result.isPresent()) {
				sites.add(result.get());
			}
		}
		return sites;
	}

	private Optional<Site> createSite(File siteFolder) {
		if (!siteFolder.isDirectory()) {
			return Optional.empty();
		}
		String folderName = siteFolder.getName();
		System.out.println(folderName);
		return Optional.empty();
	}
}
