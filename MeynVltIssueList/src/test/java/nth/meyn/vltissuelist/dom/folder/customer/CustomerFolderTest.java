package nth.meyn.vltissuelist.dom.folder.customer;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;

import nth.meyn.vltissuelist.dom.folder.mcs.MeynControlSystemsFolder;
import nth.meyn.vltissuelist.dom.folder.systems.SystemsFolder;

import org.junit.Test;

public class CustomerFolderTest {

		
	public static CustomerFolder getCustomerFolder5643() {
		return new CustomerFolder(new File("//meyn.nl/Project/BESTURINGSTECHNIEK/5643-Ruoka Saarioinen Oy"));
	}

	public static CustomerFolder getCustomerFolder4017() {
		return new CustomerFolder(new File("//meyn.nl/Project/BESTURINGSTECHNIEK/4017-Hel Yasca"));
	}
	
	@Test(expected = FileNotFoundException.class)
	public void getSystemsFolderNotFound() throws FileNotFoundException {
		CustomerFolder customerFolder = new MeynControlSystemsFolder().getCustomerFolder("4564");
		customerFolder.getSystemsFolder();
	}

	@Test
	public void getCustomerNumber() throws FileNotFoundException {
		String customerNumer = getCustomerFolder5643().getCustomerNumber();
		assertNotNull(customerNumer, "5643");
	}

	@Test
	public void getSystemsFolder() throws FileNotFoundException {
		SystemsFolder systemsFolder = getCustomerFolder5643().getSystemsFolder();
		assertNotNull(systemsFolder);
	}

}
