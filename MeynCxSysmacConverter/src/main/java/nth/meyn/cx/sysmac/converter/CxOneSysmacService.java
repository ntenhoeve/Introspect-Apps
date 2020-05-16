package nth.meyn.cx.sysmac.converter;

import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import nth.meyn.cx.sysmac.converter.cx.clipboard.CxClipboard;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxLadderModel;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxLadderModelFactory;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxVariable;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxVariableFactory;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxUnmarshaller;
import nth.meyn.cx.sysmac.converter.sysmac.clipboard.SysmacLadderDataFactory;
import nth.meyn.cx.sysmac.converter.sysmac.clipboard.SysmacSymbolDataFactory;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.SysmacMarshaller;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.SysmacRungsFactory;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;

/**
 * {@link ServiceObject} that also is an JavaFx application so we can use the JavaFX clipboard classes which are more adavnced than those of Swing
 * @author nilsth
 *
 */
public class CxOneSysmacService extends Application {

	public CxOneSysmacService() {
		initializeJavaFxEnvironment(); 
	}

	private void initializeJavaFxEnvironment() {
		new JFXPanel();
	}
	
	@Hidden()
	@Override
	public void init() throws Exception {
		super.init();
	}
	
	
	
	public void convertClipboardToSysmacRungs() {
		 
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	List<CxLadderModel> cxLadderModels;
				try {
					cxLadderModels = createCxLadderModelsFromClipboard();
	        		String sysmacLadderData = createSysmacLadderData(cxLadderModels);
//	    TODO    		SysmacClipboard.putLadderRungs(sysmacLadderData);

				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

              }
        });
		

	}

	public void convertClipboardToInternalVariables() {
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	List<CxLadderModel> cxLadderModels;
				try {
					cxLadderModels = createCxLadderModelsFromClipboard();
					Set<CxVariable> cxVariables = CxVariableFactory.createVariables(cxLadderModels);
					String table = SysmacSymbolDataFactory.createAsTextTable(cxVariables);
//		    TODO  		SysmacClipboard.putVariables(table);

				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

              }
        });

	}

	public void convertClipboardToInternalInOutVariables() {

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


	private List<CxLadderModel> createCxLadderModelsFromClipboard() throws JAXBException {
		String cxXml = CxClipboard.getLadderXml();
		CxLadderDiagram cxLadderDiagram = CxUnmarshaller.createCxLadderDiagram(cxXml);
		List<CxLadderModel> cxLadderModels = CxLadderModelFactory
				.createLadderModels(cxLadderDiagram);
		return cxLadderModels;
	}

	@Hidden()
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}

}
