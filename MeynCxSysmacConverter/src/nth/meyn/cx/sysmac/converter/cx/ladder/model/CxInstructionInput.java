package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;

public class CxInstructionInput {

	private final INSTRUCTION instruction;
	private final int inputNr;

	public CxInstructionInput(INSTRUCTION instruction, int inputNr) {
		this.instruction = instruction;
		this.inputNr = inputNr;
	}

	public INSTRUCTION getInstruction() {
		return instruction;
	}

	public int getInputNr() {
		return inputNr;
	}

	
}
