package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxInstructionInput;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class InstructionKeep1Factory implements LadderElementFactory<CxInstructionInput> {

	private static final boolean SET = false;
	private static final boolean RESET = true;

	/**
	 * reset input of keep
	 */

	@Override
	public List<LadderElement> create(CxInstructionInput cxInstructionInput, IdFactory idFactory,
			String programName) {
		INSTRUCTION cxInstruction = cxInstructionInput.getInstruction();
		LadderElement ladderElement = CoilFactory.create(idFactory, programName,
				cxInstruction.getNegated() == 1, cxInstruction.getDiffUp() == 1,
				cxInstruction.getDiffDown() == 1, SET, RESET,
				InstructionFactory.getVarName(cxInstruction, 1));
		return Arrays.asList(ladderElement);
	}

}
