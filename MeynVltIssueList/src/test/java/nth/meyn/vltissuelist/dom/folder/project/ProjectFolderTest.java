package nth.meyn.vltissuelist.dom.folder.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;

import nth.meyn.vltissuelist.dom.folder.customer.CustomerFolder;
import nth.meyn.vltissuelist.dom.folder.customer.CustomerFolderTest;
import nth.meyn.vltissuelist.dom.folder.software.SoftwareFolder;
import nth.meyn.vltissuelist.dom.folder.systems.SystemsFolder;

import org.junit.Before;
import org.junit.Test;

public class ProjectFolderTest {

	private ProjectFolder projectFolder;

	@Before
	public void setUp() throws Exception {
		projectFolder=getProjectFolder5643de01();
	}

	public static ProjectFolder getProjectFolder5643de01() throws FileNotFoundException{
		CustomerFolder customerFolder = CustomerFolderTest.getCustomerFolder5643();
		SystemsFolder systemsFolder = customerFolder.getSystemsFolder();
		ProjectFolder projectFolder = systemsFolder.getProjectFolder("5643de01");
		return projectFolder;
	}
	
	
	@Test
	public void getType() {
		ProjectType type = projectFolder.getProjectType();
		assertEquals(type, ProjectType.ARRIVAL_SYSTEM);
	}

	@Test
	public void getSoftwareFolder() throws FileNotFoundException {
		SoftwareFolder softwareFolder = projectFolder.getSoftwareFolder();
		assertNotNull(softwareFolder);
	}

	@Test(expected = FileNotFoundException.class)
	public void getSoftwareFolderNotExists() throws FileNotFoundException {
		CustomerFolder customerFolder = CustomerFolderTest.getCustomerFolder4017();
		SystemsFolder systemsFolder = customerFolder.getSystemsFolder();
		ProjectFolder projectFolder = systemsFolder.getProjectFolder("0125.DE75");
		projectFolder.getSoftwareFolder();
	}

}
