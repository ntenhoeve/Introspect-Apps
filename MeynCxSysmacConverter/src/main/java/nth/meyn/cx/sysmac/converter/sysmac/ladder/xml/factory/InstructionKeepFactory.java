package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxInstructionInput;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class InstructionKeepFactory implements LadderElementFactoryWith2Inputs<INSTRUCTION> {


	/**
	 * set input of keep
	 */

	@Override
	public List<LadderElement> createForInput1(INSTRUCTION cxInstruction, IdFactory idFactory,
			String programName) {
		LadderElement ladderElement = CoilFactory.create(idFactory, programName,
				cxInstruction.getNegated() == 1, cxInstruction.getDiffUp() == 1,
				cxInstruction.getDiffDown() == 1, true, false,
				InstructionFactory.getVarName(cxInstruction, 1));
		return Arrays.asList(ladderElement);
	}

	/**
	 * reset input of keep
	 */
	@Override
	public List<LadderElement> createForInput2(INSTRUCTION cxInstruction, IdFactory idFactory,
			String programName) {
		LadderElement ladderElement = CoilFactory.create(idFactory, programName,
				cxInstruction.getNegated() == 1, cxInstruction.getDiffUp() == 1,
				cxInstruction.getDiffDown() == 1, false, true,
				InstructionFactory.getVarName(cxInstruction, 1));
		return Arrays.asList(ladderElement);
	}
}
