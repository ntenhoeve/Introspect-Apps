package nth.meyn.cx.sysmac.converter;

import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import nth.meyn.cx.sysmac.converter.cx.clipboard.CxClipboard;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxLadderModel;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxLadderModelFactory;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxVariable;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxUnmarshaller;
import nth.meyn.cx.sysmac.converter.sysmac.clipboard.SysmacClipboard;
import nth.meyn.cx.sysmac.converter.sysmac.clipboard.SysmacLadderDataFactory;
import nth.meyn.cx.sysmac.converter.sysmac.clipboard.SysmacSymbolDataFactory;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.SysmacMarshaller;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.SysmacRungsFactory;

public class MeynCxSysmacConverter extends Application {

//	private static final byte MOST_SIGNIFICANT_BIT = (byte) 128;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		 convertLadderFromCxClipboardToSysmacClipboard(); 
		// testLadderSysmacExampleVersusSysmacClipboard();
//		testSysmacLength();
		System.out.println("Done");

		Platform.exit();

	}

	// private void testLadderSysmacExampleVersusSysmacClipboard() {
	//
	//
	// String sysmacLadderExampleData=SysmacLadderDataFactory.createExample();
	// String sysmacLadderClipboardData=SysmacClipboard.getLadderData();
	//
	// String data = sysmacLadderClipboardData;
	// byte[] prefix=
	// DatatypeConverter.parseHexBinary("96A79EFD133B7043A67956106BB288FB0001000000FFFFFFFF01000000000000000601000000");
	//
	// StringBuilder data2=new StringBuilder();
	// data2.append(new String(prefix));
	//
	// String xml=SysmacClipboard.getXml(data);
	// int xmlLength = xml.length()*2;
	//
	// byte lengthLowByte= (byte) (((xmlLength % 256)/2 )+128);//Apparently low
	// byte shifts one bit to the right!!! Omron= Strange Japanese programmers
	// byte lengthHighByte = (byte) (xmlLength / 256);
	// byte[] length=new byte[] {lengthLowByte, lengthHighByte};
	// data2.append(new String(length));
	//
	// data2.append(xml);
	//
	// byte[] suffix= DatatypeConverter.parseHexBinary("0B");
	// data2.append(new String(suffix));
	//
	// String data3 = data2.toString();
	//
	// System.out.println(StringUtils.indexOfDifference(data, data3));
	// }

//	private void testSysmacLength() {
//		String sysmacLadderData = SysmacClipboard.getLadderData();
//		int prefixLength = SysmacLadderDataFactory.createPrefix().length();
//
//		String xml = sysmacLadderData.substring(sysmacLadderData.indexOf("<Rungs>"));
//		xml = xml.substring(0, xml.lastIndexOf("</Rungs>") + "</Rungs>".length());
//		int xmlLength = xml.length();
//
//		byte orgByte2 = sysmacLadderData.getBytes()[prefixLength + 2];
//		if (orgByte2 == 60) {
//			orgByte2 = 0; // first char of xml
//		}
//		byte orgByte1 = sysmacLadderData.getBytes()[prefixLength + 1];
//		byte orgByte0 = sysmacLadderData.getBytes()[prefixLength + 0];
//
//		ByteBuffer byteBuffer = ByteBuffer.allocate(4);
//		byteBuffer.putInt(xmlLength * 8);
//		byte calByte3 = byteBuffer.array()[0];
//
//		byteBuffer = ByteBuffer.allocate(4);
//		byteBuffer.putInt(xmlLength * 4);
//		byte calByte2 = byteBuffer.array()[1];
//
//		byteBuffer = ByteBuffer.allocate(4);
//		byteBuffer.putInt(xmlLength * 2);
//		byte calByte1 = byteBuffer.array()[2];
//		
//		byteBuffer = ByteBuffer.allocate(4);
//		byteBuffer.putInt(xmlLength);
//		byte calByte0 = byteBuffer.array()[3];
//		
//		byte[] bytes;
//		if (calByte3!=0) {//numBytes=4;
//			//add sign bits when there is a higher byte 
//			calByte2=(byte) (calByte2|MOST_SIGNIFICANT_BIT);
//			calByte1=(byte) (calByte1|MOST_SIGNIFICANT_BIT);
//			calByte0=(byte) (calByte0|MOST_SIGNIFICANT_BIT);
//			bytes = new byte[] {calByte0, calByte1, calByte2, calByte3};
//		} else if (calByte2!=0) {//numBytes=3;
//			//add sign bits when there is a higher byte 
//			calByte1=(byte) (calByte1|MOST_SIGNIFICANT_BIT);
//			calByte0=(byte) (calByte0|MOST_SIGNIFICANT_BIT);
//			bytes = new byte[] {calByte0, calByte1, calByte2};
//		} else if (calByte1!=0) {//numBytes=2;
//			//add sign bits when there is a higher byte 
//			calByte0=(byte) (calByte0|MOST_SIGNIFICANT_BIT);
//			bytes = new byte[] {calByte0, calByte1};
//		}else  {//numBytes=1
//			bytes = new byte[] {calByte0};
//		}
//
//	
//		// 5225 Oringineel: b2: 0 00000000 b1: 40 00101000 b0: -23 11101001
//		// 5225 Berekend: b2: 0 00000000 b1: 40 00101000 b0: 105 01101001
//
//		// 4887 Oringineel: b2: 0 00000000 b1: 38 00100110 b0: -105 10010111
//		// 4887 Berekend: b2: 0 00000000 b1: 38 00100110 b0: 23 00010111
//
//		// 4130 Oringineel: b2: 0 00000000 b1: 32 00100000 b0: -94 10100010
//		// 4130 Berekend: b2: 0 00000000 b1: 32 00100000 b0: 34 00100010
//
//		// 20512 Oringineel: b2: 1 00000001 b1: -96 10100000 b0: -96 10100000
//		// 20512 Berekend: b2: 1 00000001 b1: -96 10100000 b0: 32 00100000
//
//		System.out.format("%d Oringineel: b2:%3d %s b1: %3d %s b0: %3d %s %n", xmlLength, orgByte2,
//				toBin(orgByte2), orgByte1, toBin(orgByte1), orgByte0, toBin(orgByte0));
//		System.out.format("%d Berekend:   b2:%3d %s b1: %3d %s b0: %3d %s %n", xmlLength, calByte2,
//				toBin(calByte2), calByte1, toBin(calByte1), calByte0, toBin(calByte0));
//	}

//	private String toBin(byte value) {
//		return StringUtils.leftPad(Integer.toBinaryString(value & 0xFF), 8, "0");
//	}

	private void convertLadderFromCxClipboardToSysmacClipboard() throws JAXBException {
		List<CxLadderModel> cxLadderModels = createCxLadderModelsFromClipboard();

		String sysmacSymbolData = createSysmacVariableData(cxLadderModels);

		String sysmacLadderData = createSysmacLadderData(cxLadderModels);

		SysmacClipboard.putLadderRungs(sysmacLadderData, sysmacSymbolData);
	}

	private String createSysmacLadderData(List<CxLadderModel> cxLadderModels) throws JAXBException {
		Rungs sysmacRungs = SysmacRungsFactory.create(cxLadderModels);
		// Rungs sysmacRungs = SysmacRungsFactory.createExample();
		String sysmacLadderXml = SysmacMarshaller.createXml(sysmacRungs);
		System.out.println(sysmacLadderXml);
		String sysmacLadderData = SysmacLadderDataFactory.create(sysmacLadderXml);
		// String sysmacLadderData=SysmacLadderDataFactory.createExample();
		return sysmacLadderData;
	}

	private String createSysmacVariableData(List<CxLadderModel> cxLadderModels) {
		// Set<CxVariable> cxVariables =
		// CxLadderModelFactory.createVariables(cxLadderModels);
		Set<CxVariable> cxVariables = CxLadderModelFactory.createVariableExamples();
		String sysmacVariableData = SysmacSymbolDataFactory.create(cxVariables);
		// String sysmacVariableData=SysmacSymbolDataFactory.createExample();
		System.out.println(sysmacVariableData);
		return sysmacVariableData;
	}

	private List<CxLadderModel> createCxLadderModelsFromClipboard() throws JAXBException {
		String cxXml = CxClipboard.getLadderXml();
		CxLadderDiagram cxLadderDiagram = CxUnmarshaller.createCxLadderDiagram(cxXml);
		List<CxLadderModel> cxLadderModels = CxLadderModelFactory
				.createLadderModels(cxLadderDiagram);
		return cxLadderModels;
	}

}
