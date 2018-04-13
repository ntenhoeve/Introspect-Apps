package nth.meyn.vltissuelist.dom.folder.systems;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;

import nth.meyn.vltissuelist.dom.folder.customer.CustomerFolder;
import nth.meyn.vltissuelist.dom.folder.customer.CustomerFolderTest;
import nth.meyn.vltissuelist.dom.folder.project.ProjectFolder;

import org.junit.Before;
import org.junit.Test;

public class SystemsFolderTest {

	private SystemsFolder systemsFolder;

	@Before
	public void setUp() throws Exception {
		CustomerFolder customerFolder = CustomerFolderTest.getCustomerFolder5643();
		systemsFolder=customerFolder.getSystemsFolder();
	}

	@Test
	public void getProjectFolders() throws FileNotFoundException {
		List<ProjectFolder> projectFolders = systemsFolder.getProjectFolders();
		assertTrue(projectFolders.size()>=4);
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void getProjectWithFalseElecticalSchematicNr1() throws FileNotFoundException {
		ProjectFolder projectFolder = systemsFolder.getProjectFolder(null);
		assertNotNull(projectFolder);
	}

	@Test(expected=IllegalArgumentException.class)
	public void getProjectWithFalseElecticalSchematicNr2() throws FileNotFoundException {
		ProjectFolder projectFolder = systemsFolder.getProjectFolder("56a4301");
		assertNotNull(projectFolder);
	}
	

	@Test
	public void getProject2() throws FileNotFoundException {
		ProjectFolder projectFolder = systemsFolder.getProjectFolder("5643de01");
		assertNotNull(projectFolder);
	}

	@Test
	public void getProject3() throws FileNotFoundException {
		ProjectFolder projectFolder = systemsFolder.getProjectFolder("5643.de01");
		assertNotNull(projectFolder);
	}

	@Test
	public void getProject4() throws FileNotFoundException {
		ProjectFolder projectFolder = systemsFolder.getProjectFolder("5643DE01");
		assertNotNull(projectFolder);
	}

	@Test
	public void getProject5() throws FileNotFoundException {
		ProjectFolder projectFolder = systemsFolder.getProjectFolder("5643.DE01");
		assertNotNull(projectFolder);
	}
	
	@Test
	public void getProject6() throws FileNotFoundException {
		ProjectFolder projectFolder = systemsFolder.getProjectFolder("5643.DE01.040");
		assertNotNull(projectFolder);
	}

}
