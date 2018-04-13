package nth.meyn.vltissuelist.dom.folder.vlt;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import nth.meyn.vltissuelist.dom.folder.plc.PlcFolder;
import nth.meyn.vltissuelist.dom.folder.plc.PlcFolderTest;
import nth.meyn.vltissuelist.dom.vlt.VltFile;

import org.junit.Before;
import org.junit.Test;

public class VltFolderTest {

	private VltFolder vltFolder;

	@Before
	public void setUp() throws Exception {
		vltFolder=getVltFolder5643de01();
	}

	public static  VltFolder getVltFolder5643de01() throws FileNotFoundException {
		PlcFolder plcFolder=PlcFolderTest.getPlcFolder5643de01();
		VltFolder vltFolder =plcFolder.getVltFolder();
		return vltFolder;
	}

	@Test
	public void testGetVltFiles() throws IOException {
		List<VltFile> vltFiles = vltFolder.getVltFiles();
		assertTrue(vltFiles.size()==1);
		assertEquals(vltFiles.get(0).getFile().getName(), "CAS1.ssp");
	}

}
