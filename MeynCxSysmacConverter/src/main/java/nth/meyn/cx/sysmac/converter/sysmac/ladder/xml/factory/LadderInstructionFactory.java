package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;

public interface  LadderInstructionFactory extends LadderElementFactory<INSTRUCTION> {
	
	/**
	 * 
	 * @return instruction name suffixes, empty list if
	 *         there are none. E.g.: The {@link InstructionMovFactory} returns
	 *         extension "L", so that is represents both MOV and MOVL
	 *         instructions
	 */
	List<String> getNameSuffixes();
}
