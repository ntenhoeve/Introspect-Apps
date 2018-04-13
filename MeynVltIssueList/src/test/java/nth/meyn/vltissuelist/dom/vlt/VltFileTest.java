package nth.meyn.vltissuelist.dom.vlt;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import nth.meyn.vltissuelist.dom.folder.vlt.VltFolder;
import nth.meyn.vltissuelist.dom.folder.vlt.VltFolderTest;
import junit.framework.TestCase;

public class VltFileTest extends TestCase {


	public void testReadNewWith() throws IOException, URISyntaxException {
		File file=new File(VltFile.class.getResource("/New-VltParameterFile-with.ssp").toURI());
		VltFile vltFile = new VltFile(file);
		List<Vlt> vlts = vltFile.getVlts();
		assertEquals(11, vlts.size());
		assertTrue( vltFile.isAllVltsStopAndTripAfterDeviceNetError());
		assertTrue( vltFile.isContainsVltWithWiredQuickstop());
		assertEquals(new Byte((byte)4), vlts.get(0).getSetup(0).getParameters().get(Vlt.PARAMETER_515_TERMINAL33));
		assertEquals(new Byte((byte)5), vlts.get(0).getSetup(0).getParameters().get(Vlt.PARAMETER_804_CONTROL_WORD_TIME_OUT));
		assertEquals(new Byte((byte)1), vlts.get(0).getSetup(0).getParameters().get(Vlt.PARAMETER_1000_CAN_PROTOCOL));
		assertEquals(new Byte((byte)4), vlts.get(10).getSetup(0).getParameters().get(Vlt.PARAMETER_515_TERMINAL33));
		assertEquals(new Byte((byte)5), vlts.get(10).getSetup(0).getParameters().get(Vlt.PARAMETER_804_CONTROL_WORD_TIME_OUT));
		assertEquals(new Byte((byte)1), vlts.get(10).getSetup(0).getParameters().get(Vlt.PARAMETER_1000_CAN_PROTOCOL));
		
	}
	
	public void testReadNewWithout() throws IOException, URISyntaxException {
		File file=new File(VltFile.class.getResource("/New-VltParameterFile-without.ssp").toURI());
		VltFile vltFile = new VltFile(file);
		List<Vlt> vlts = vltFile.getVlts();
		assertEquals(11, vlts.size());
		assertFalse( vltFile.isAllVltsStopAndTripAfterDeviceNetError());
		assertFalse( vltFile.isContainsVltWithWiredQuickstop());
		assertEquals(new Byte((byte)0), vlts.get(0).getSetup(0).getParameters().get(Vlt.PARAMETER_515_TERMINAL33));
		assertEquals(new Byte((byte)0), vlts.get(0).getSetup(0).getParameters().get(Vlt.PARAMETER_804_CONTROL_WORD_TIME_OUT));
		assertEquals(new Byte((byte)1), vlts.get(0).getSetup(0).getParameters().get(Vlt.PARAMETER_1000_CAN_PROTOCOL));
		assertEquals(new Byte((byte)0), vlts.get(10).getSetup(0).getParameters().get(Vlt.PARAMETER_515_TERMINAL33));
		assertEquals(new Byte((byte)0), vlts.get(10).getSetup(0).getParameters().get(Vlt.PARAMETER_804_CONTROL_WORD_TIME_OUT));
		assertEquals(new Byte((byte)1), vlts.get(10).getSetup(0).getParameters().get(Vlt.PARAMETER_1000_CAN_PROTOCOL));
	}
	
	public void testCas() throws IOException, URISyntaxException {
		VltFolder vltFolder = VltFolderTest.getVltFolder5643de01();
		List<VltFile> vltFiles = vltFolder.getVltFiles();
		VltFile vltFile = vltFiles.get(0);
		assertTrue( vltFile.isReadSuccesfull());
		assertTrue(vltFile.isContainsVltWithDeviceNet());
		assertFalse( vltFile.isContainsVltWithWiredQuickstop());
		assertTrue( vltFile.isAllVltsStopAndTripAfterDeviceNetError());
	}
		
	
	
	
//	public void testReadOld() throws IOException, URISyntaxException {
//		File file=new File(VltFile.class.getResource("/Old-VltParameterFile.ssp").toURI());
//		VltFile vltFile = new VltFile(file);
//		List<Vlt> vlts = vltFile.getVlts();
//		assertEquals(11, vlts.size());
//		assertTrue( vltFile.allVltsStopAndTripAfterDeviceNetError());
//		assertTrue( vltFile.containsVltWithWiredQuickstop());
//		assertEquals(new Byte((byte)4), vlts.get(0).getSetup(0).getParameters().get(Vlt.PARAMETER_515_TERMINAL33));
//		assertEquals(new Byte((byte)5), vlts.get(0).getSetup(0).getParameters().get(Vlt.PARAMETER_804_CONTROL_WORD_TIME_OUT));
//		assertEquals(new Byte((byte)1), vlts.get(0).getSetup(0).getParameters().get(Vlt.PARAMETER_1000_CAN_PROTOCOL));
//		assertEquals(new Byte((byte)4), vlts.get(10).getSetup(0).getParameters().get(Vlt.PARAMETER_515_TERMINAL33));
//		assertEquals(new Byte((byte)5), vlts.get(10).getSetup(0).getParameters().get(Vlt.PARAMETER_804_CONTROL_WORD_TIME_OUT));
//		assertEquals(new Byte((byte)1), vlts.get(10).getSetup(0).getParameters().get(Vlt.PARAMETER_1000_CAN_PROTOCOL));
//		
//	}
		
//	public void testPrintCompare() throws IOException, URISyntaxException {
//	
//		
//		
//		File vltFile2=new File(VltFile.class.getResource("/VltFile-without.ssp").toURI());
//		List<String> results = vltFile.printCompare(vltFile2);
//		Path out = Paths.get("w:/compare.txt");
//		Files.write(out,results,Charset.defaultCharset());
//		
//		System.out.println(new ParameterArrayMatcher(515,0));
//	}

	

}
