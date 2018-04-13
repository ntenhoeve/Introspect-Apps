package nth.meyn.vltissuelist.dom.folder.mcs;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.List;

import nth.meyn.vltissuelist.dom.folder.customer.CustomerFolder;

import org.junit.Before;
import org.junit.Test;

public class MeynControlSystemsFolderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected=InvalidParameterException.class)
	public void getCustomerFolderInvalidArgument1() throws FileNotFoundException {
		new MeynControlSystemsFolder().getCustomerFolder(null);
	}
	
	@Test(expected=InvalidParameterException.class)
	public void getCustomerFolderInvalidArgument2() throws FileNotFoundException {
		new MeynControlSystemsFolder().getCustomerFolder("abc!");
	}
	
	@Test(expected=InvalidParameterException.class)
	public void getCustomerFolderInvalidArgument3() throws FileNotFoundException {
		new MeynControlSystemsFolder().getCustomerFolder("123");
	}
	
	
	@Test(expected=FileNotFoundException.class)
	public void getCustomerFolderFileNotFound() throws FileNotFoundException {
		new MeynControlSystemsFolder().getCustomerFolder("9999");
	}

	
	@Test
	public void getCustomerFolderInvalidArgument4() throws FileNotFoundException {
		CustomerFolder customerFolder= new MeynControlSystemsFolder().getCustomerFolder("5643");
		assertNotNull(customerFolder);
	}

	@Test
	public void getCustomerFolders() throws FileNotFoundException {
		List<CustomerFolder> customerFolders = new MeynControlSystemsFolder().getCustomerFolders();
		assertTrue(customerFolders.size()>1000);
	}
	

}
