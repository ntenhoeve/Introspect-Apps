package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public interface LadderInstructionFactoryWith3Inputs<T>  extends LadderInstructionFactoryWith2Inputs{

	List<LadderElement> createForInput3 (INSTRUCTION cxInstruction, IdFactory idFactory, String programName);

}
