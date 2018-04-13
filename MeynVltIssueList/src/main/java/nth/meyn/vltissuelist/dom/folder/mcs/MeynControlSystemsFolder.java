package nth.meyn.vltissuelist.dom.folder.mcs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nth.meyn.vltissuelist.dom.folder.customer.CustomerFolder;
import nth.meyn.vltissuelist.dom.folder.customer.CustomerFolderFilter;

public class MeynControlSystemsFolder {

	private final File meynControlSystemsFolder;

	public MeynControlSystemsFolder() {
		meynControlSystemsFolder = new File("//meyn.nl/Project/BESTURINGSTECHNIEK");
	}

	public Map<String,CustomerFolder> getCustomerFoldersMap() throws FileNotFoundException {
		Map<String,CustomerFolder> map = new HashMap<>();
		List<CustomerFolder> customerFolders = getCustomerFolders();
		for (CustomerFolder customerFolder : customerFolders) {
			String customerNumber = customerFolder.getCustomerNumber();
			map.put(customerNumber, customerFolder);
		}
		return map;
	}
	
	public List<CustomerFolder> getCustomerFolders() throws FileNotFoundException {
		File[] foundFolders = meynControlSystemsFolder.listFiles(new CustomerFolderFilter());
		if (foundFolders.length == 0) {
			throw new FileNotFoundException("Could not find a customer folder in folder: "
					+ meynControlSystemsFolder);
		}
		List<CustomerFolder> customerFolders = new ArrayList<>();
		for (File foundFolder : foundFolders) {
			CustomerFolder customerFolder = new CustomerFolder(foundFolder);
			customerFolders.add(customerFolder);
		}
		return customerFolders;
	}

	public CustomerFolder getCustomerFolder(String customerNumber) throws FileNotFoundException {
		File[] foundFolders = meynControlSystemsFolder.listFiles(new CustomerFolderFilter(
				customerNumber));
		if (foundFolders.length == 0) {
			throw new FileNotFoundException(
					"Could not find a customer folder with customer number: " + customerNumber
							+ " in folder: " + meynControlSystemsFolder);
		}
		if (foundFolders.length > 1) {
			throw new FileNotFoundException(
					"Found multiple customer folders with customer number: " + customerNumber
							+ " in folder: " + meynControlSystemsFolder);
		}
		CustomerFolder customerFolder = new CustomerFolder(foundFolders[0]);
		return customerFolder;
	}
}
