package nth.meyn.vltissuelist.dom.folder.software;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;

import nth.meyn.vltissuelist.dom.folder.plc.PlcFolder;
import nth.meyn.vltissuelist.dom.folder.project.ProjectFolder;
import nth.meyn.vltissuelist.dom.folder.project.ProjectFolderTest;

import org.junit.Before;
import org.junit.Test;

public class SoftwareFolderTest {

	private SoftwareFolder softwareFolder;

	@Before
	public void setUp() throws Exception {
		ProjectFolder projectFolder = ProjectFolderTest.getProjectFolder5643de01();
		softwareFolder = projectFolder.getSoftwareFolder();
	}

	@Test
	public void testGetOrderedPlcFolders() throws FileNotFoundException {
		List<PlcFolder> plcFolders = softwareFolder.getOrderedPlcFolders();
		assertTrue(plcFolders.size()>=12);
		for (PlcFolder plcFolder : plcFolders) {
			assertFalse(plcFolder.getName().toLowerCase().contains("old"));
		}
	}

	@Test
	public void testGetLatestPlcFolder() throws FileNotFoundException {
		PlcFolder latestPlcFolder = softwareFolder.getLatestPlcFolders().get(0);
		assertNotNull(latestPlcFolder);
	}

}
