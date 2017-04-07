package nth.meyn.cx.sysmac.converter;

import java.nio.ByteBuffer;
import java.util.List;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import nth.meyn.cx.sysmac.converter.cx.clipboard.CxClipboard;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxLadderModel;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxLadderModelFactory;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxUnmarshaller;
import nth.meyn.cx.sysmac.converter.sysmac.clipboard.SysmacClipboard;
import nth.meyn.cx.sysmac.converter.sysmac.clipboard.SysmacLadderDataFactory;
import nth.meyn.cx.sysmac.converter.sysmac.clipboard.SysmacSymbolDataFactory;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.SysmacMarshaller;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.SysmacRungsFactory;
import nth.meyn.cx.sysmac.converter.util.StringToArrayCodeUtil;

public class MeynCxSysmacConverter extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		convertLadderFromCxClipboardToSysmacClipboard();
//		testLadderSysmacExampleVersusSysmacClipboard();
		
		System.out.println("Done");
		
		Platform.exit();


	}

	private void testLadderSysmacExampleVersusSysmacClipboard() {
		
		
		String sysmacLadderExampleData=SysmacLadderDataFactory.createExample();
		String sysmacLadderClipboardData=SysmacClipboard.getLadderData();
		
		String data = sysmacLadderClipboardData;                                                                          
		byte[] prefix= DatatypeConverter.parseHexBinary("96A79EFD133B7043A67956106BB288FB0001000000FFFFFFFF01000000000000000601000000");

		StringBuilder data2=new StringBuilder();
		data2.append(new String(prefix));
		
		String xml=SysmacClipboard.getXml(data);
		int xmlLength = xml.length()*2;
		
		byte lengthLowByte=   (byte) (((xmlLength % 256)/2 )+128);//Apparently low byte shifts one bit to the right!!! Omron= Strange Japanese programmers 
		byte lengthHighByte = (byte) (xmlLength / 256); 
		byte[] length=new byte[] {lengthLowByte, lengthHighByte};
		data2.append(new String(length));
		
		data2.append(xml);
		
		byte[] suffix= DatatypeConverter.parseHexBinary("0B");
		data2.append(new String(suffix));

		String data3 = data2.toString();

		System.out.println(StringUtils.indexOfDifference(data, data3));
	}

	private void convertLadderFromCxClipboardToSysmacClipboard() throws JAXBException {
		String cxXml = CxClipboard.getLadderXml();
		CxLadderDiagram cxLadderDiagram = CxUnmarshaller.createCxLadderDiagram(cxXml);
		List<CxLadderModel> cxLadderModels = CxLadderModelFactory.create(cxLadderDiagram);
		print(cxLadderModels);
		Rungs sysmacRungs = SysmacRungsFactory.create(cxLadderModels);
		String sysmacLadderXml = SysmacMarshaller.createXml(sysmacRungs);
		System.out.println(sysmacLadderXml);
	
		String sysmacSymbolData=SysmacSymbolDataFactory.createExample(); 
		String sysmacLadderData=SysmacLadderDataFactory.create(sysmacLadderXml);
//		String sysmacLadderData=SysmacLadderDataFactory.createExample();
		
		System.out.println(sysmacLadderData);
		
		SysmacClipboard.putLadderRungs(sysmacLadderData, sysmacSymbolData);
	}

	private void print(List<CxLadderModel> cxLadderModels) {
		for (CxLadderModel cxLadderModel : cxLadderModels) {
			System.out.println(
					"=================================================================================================================");
			System.out.println(cxLadderModel);
		}
	}


}
