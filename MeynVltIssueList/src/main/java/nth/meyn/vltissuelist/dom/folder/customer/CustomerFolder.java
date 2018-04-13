package nth.meyn.vltissuelist.dom.folder.customer;

import java.io.File;
import java.io.FileNotFoundException;

import nth.meyn.vltissuelist.dom.folder.systems.SystemFolderFilter;
import nth.meyn.vltissuelist.dom.folder.systems.SystemsFolder;

public class CustomerFolder {
	private final File customerFolder;

	public CustomerFolder(File customerFolder) {
		this.customerFolder = customerFolder;
	}
	
	public SystemsFolder getSystemsFolder() throws FileNotFoundException {
		File[] systemFolders = customerFolder.listFiles(new SystemFolderFilter());
		if (systemFolders.length==0) {
			throw new FileNotFoundException("Could not find systems folder in: "+customerFolder.getPath());
		}
		if (systemFolders.length>1) {
			throw new FileNotFoundException("Found multiple systems folders in: "+customerFolder.getPath());
		}
		SystemsFolder systemsFolder=new SystemsFolder(systemFolders[0]);
		return systemsFolder;
	}
	
	public String getCustomerNumber() {
		String customerNumber = customerFolder.getName().substring(0, 4);
		return customerNumber;
	}
}
