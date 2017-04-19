package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class InstructionSet0Factory implements LadderElementFactory<INSTRUCTION> {

	private static final boolean SET = true;
	private static final boolean RESET = false;

	@Override
	public List<LadderElement> create(INSTRUCTION cxInstruction, IdFactory idFactory,
			String programName) {
		LadderElement ladderElement = CoilFactory.create(idFactory, programName,
				cxInstruction.getNegated() == 1, cxInstruction.getDiffUp() == 1,
				cxInstruction.getDiffDown() == 1, SET, RESET,
				InstructionFactory.getVarName(cxInstruction,1));
		return Arrays.asList(ladderElement);
	}

}
