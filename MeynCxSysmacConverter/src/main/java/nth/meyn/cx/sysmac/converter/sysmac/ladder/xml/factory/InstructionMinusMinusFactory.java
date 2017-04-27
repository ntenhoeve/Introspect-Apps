package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class InstructionMinusMinusFactory implements LadderInstructionFactory {

	private static final String LONG_SUFFIX = "L";
	
	
	@Override
	public List<String> getNameSuffixes() {
		return Arrays.asList( LONG_SUFFIX); 
	}


	@Override
	public List<LadderElement> createForInput1(INSTRUCTION cxInstruction, IdFactory idFactory,
			String programName) {
		StringBuilder script=new StringBuilder();
		String opperand1 = InstructionFactory.getVarName(cxInstruction, 1);
		script.append(opperand1);
		script.append(":=");
		script.append(opperand1);
		script.append("-1;");
		LadderElement ladderElement=StructuredTextFactory.create(idFactory, script.toString());
		return Arrays.asList(ladderElement);
	}

}
