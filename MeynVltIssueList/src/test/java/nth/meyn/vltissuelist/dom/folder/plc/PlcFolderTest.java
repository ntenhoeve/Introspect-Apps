package nth.meyn.vltissuelist.dom.folder.plc;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import nth.meyn.vltissuelist.dom.folder.project.ProjectFolder;
import nth.meyn.vltissuelist.dom.folder.project.ProjectFolderTest;
import nth.meyn.vltissuelist.dom.folder.software.SoftwareFolder;
import nth.meyn.vltissuelist.dom.folder.vlt.VltFolder;

import org.junit.Before;
import org.junit.Test;

public class PlcFolderTest {

	private PlcFolder plcFolder;

	@Before
	public void setUp() throws Exception {
		plcFolder=getPlcFolder5643de01();
	}

	public static PlcFolder getPlcFolder5643de01() throws FileNotFoundException {
		ProjectFolder projectFolder = ProjectFolderTest.getProjectFolder5643de01();
		SoftwareFolder softwareFolder=projectFolder.getSoftwareFolder();
		PlcFolder plcFolder = softwareFolder.getLatestPlcFolders().get(0);
		return plcFolder;
	}

	@Test
	public void testGetVltFolder() throws FileNotFoundException {
		VltFolder vltFolder = plcFolder.getVltFolder();
		assertNotNull(vltFolder);
	}

}
