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

//	private void testLadderSysmacExampleVersusSysmacClipboard() {
//		
//		
//		String sysmacLadderExampleData=SysmacLadderDataFactory.createExample();
//		String sysmacLadderClipboardData=SysmacClipboard.getLadderData();
//		
//		String data = sysmacLadderClipboardData;                                                                          
//		byte[] prefix= DatatypeConverter.parseHexBinary("96A79EFD133B7043A67956106BB288FB0001000000FFFFFFFF01000000000000000601000000");
//
//		StringBuilder data2=new StringBuilder();
//		data2.append(new String(prefix));
//		
//		String xml=SysmacClipboard.getXml(data);
//		int xmlLength = xml.length()*2;
//		
//		byte lengthLowByte=   (byte) (((xmlLength % 256)/2 )+128);//Apparently low byte shifts one bit to the right!!! Omron= Strange Japanese programmers 
//		byte lengthHighByte = (byte) (xmlLength / 256); 
//		byte[] length=new byte[] {lengthLowByte, lengthHighByte};
//		data2.append(new String(length));
//		
//		data2.append(xml);
//		
//		byte[] suffix= DatatypeConverter.parseHexBinary("0B");
//		data2.append(new String(suffix));
//
//		String data3 = data2.toString();
//
//		System.out.println(StringUtils.indexOfDifference(data, data3));
//	}

	private void convertLadderFromCxClipboardToSysmacClipboard() throws JAXBException {
		List<CxLadderModel> cxLadderModels = createCxLadderModelsFromClipboard();

		String sysmacSymbolData = createSysmacVariableData(cxLadderModels); 
		
		String sysmacLadderData = createSysmacLadderData(cxLadderModels);
		
		SysmacClipboard.putLadderRungs(sysmacLadderData, sysmacSymbolData);
	}

	private String createSysmacLadderData(List<CxLadderModel> cxLadderModels) throws JAXBException {
		Rungs sysmacRungs = SysmacRungsFactory.create(cxLadderModels);
//		Rungs sysmacRungs = SysmacRungsFactory.createExample();
		String sysmacLadderXml = SysmacMarshaller.createXml(sysmacRungs);
		System.out.println(sysmacLadderXml);
		String sysmacLadderData=SysmacLadderDataFactory.create(sysmacLadderXml);
//		String sysmacLadderData=SysmacLadderDataFactory.createExample();
		return sysmacLadderData;
	}

	private String createSysmacVariableData(List<CxLadderModel> cxLadderModels) {
//		Set<CxVariable> cxVariables = CxLadderModelFactory.createVariables(cxLadderModels);
		Set<CxVariable> cxVariables = CxLadderModelFactory.createVariableExamples(); 
		String sysmacVariableData=SysmacSymbolDataFactory.create(cxVariables);  
//		String sysmacVariableData=SysmacSymbolDataFactory.createExample();
		System.out.println(sysmacVariableData);
		return sysmacVariableData;
	}

	private List<CxLadderModel> createCxLadderModelsFromClipboard() throws JAXBException {
		String cxXml = CxClipboard.getLadderXml();
		CxLadderDiagram cxLadderDiagram = CxUnmarshaller.createCxLadderDiagram(cxXml);
		List<CxLadderModel> cxLadderModels = CxLadderModelFactory.createLadderModels(cxLadderDiagram);
		return cxLadderModels;
	}



}
