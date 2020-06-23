package nth.meyn.cx.sysmac.converter;

import java.util.List;

import javax.xml.bind.JAXBException;

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

public class MeynCxSysmacConverter {// extends Application {

//	public static void main(String[] args) {
//		launch(args);
//	}
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//
//		 convertLadderFromCxClipboardToSysmacClipboard(); 
//		 System.out.println("Done");
//
//		Platform.exit();
//
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
//		 Set<CxVariable> cxVariables =CxVariableFactory.createVariables(cxLadderModels);
//		 Set<CxVariable> cxVariablesWithoutSystemVariables = cxVariables.stream().filter(v-> !v.getName().startsWith("P_")).collect(Collectors.toSet());

//		Set<CxVariable> cxVariablesWithoutSystemVariables = CxVariableFactory.createVariableExamples();
//		String sysmacVariableData = SysmacSymbolDataFactory.createSysmacClipboardData(cxVariablesWithoutSystemVariables);
		String sysmacVariableData = SysmacSymbolDataFactory.createExample();
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
