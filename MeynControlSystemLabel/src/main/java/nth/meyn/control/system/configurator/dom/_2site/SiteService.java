package nth.meyn.control.system.configurator.dom._2site;

import java.util.ArrayList;
import java.util.List;

public class SiteService {

	// private Settings settings;
	//
	// public SiteService(SettingsRepository settingsRepository) {
	// settings = settingsRepository.getSettings();
	// }

	public List<Site> allSites() {
		List<Site> sites = new ArrayList<>();
		// sites.add(new Site(7113,"Tyson","Union City","USA"));
		return sites;
	}

	// private List<Site> readAllSitesFromLayoutFolder() {
	// File layoutFolder = new File(settings.getLayoutFolder());
	// File[] siteFolders = layoutFolder.listFiles();
	// List<Site> sites = new ArrayList<>();
	// for (File siteFolder : siteFolders) {
	// Optional<Site> result = createSite(siteFolder);
	// if (result.isPresent()) {
	// sites.add(result.get());
	// }
	// }
	// return sites;
	// }
	//
	// private Optional<Site> createSite(File siteFolder) {
	// if (!siteFolder.isDirectory()) {
	// return Optional.empty();
	// }
	// String folderName = siteFolder.getName();
	// if (folderName.length()<6 || !folderName.substring(4, 5).equals(" ")) {
	// return Optional.empty();
	// }
	// int layoutNumber;
	// try {
	// layoutNumber = Integer.parseInt(folderName.substring(0, 4));
	// } catch (Throwable e) {
	// return Optional.empty();
	// }
	// if (layoutNumber==0) {
	// return Optional.empty();
	// }
	//
	// String cityAndCountry = folderName.substring(5);
	// StringTokenizer tokenizer = new StringTokenizer(cityAndCountry,"-");
	// List<String> tokens=new ArrayList<>();
	// while (tokenizer.hasMoreTokens()) {
	// String token = tokenizer.nextToken().trim();
	// if (!token.isEmpty()) {
	// tokens.add(token);
	// }
	// }
	//
	//
	// Site site=new Site();
	// site.setNumber(layoutNumber);
	// switch (tokens.size()) {
	// case 1:
	// site.setCustomerName(tokens.get(0));
	// return Optional.of(site);
	// case 2:
	// site.setCustomerName(tokens.get(0));
	// site.setCountry(tokens.get(1));
	// return Optional.of(site);
	// case 3:
	// site.setCustomerName(tokens.get(0));
	// site.setCity(tokens.get(1));
	// site.setCountry(tokens.get(2));
	// return Optional.of(site);
	// default:
	// return Optional.empty();
	// }
	//
	//
	// }
}
