package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public interface LadderInstructionFactoryWith2Inputs  extends LadderInstructionFactory {

	List<LadderElement> createForInput2 (INSTRUCTION cxInstruction, IdFactory idFactory, String programName);

}
