package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public interface LadderElementFactoryWith3Inputs<T>  extends LadderElementFactoryWith2Inputs<T>{

	List<LadderElement> createForInput3 (INSTRUCTION cxInstruction, IdFactory idFactory, String programName);

}
