package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.SysmacConstant;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacConnectionPointType;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacDataType;

public abstract class InstructionComparisonFactory implements LadderInstructionFactory {





	private static final String LONG_SUFFIX = "L";
	private static final String SIGNED_SUFFIX = "S";
	private static final String FLOAT_SUFFIX= "F";
	private static final String DOUBLE_SUFFIX="D";
	private static final String SIGNED_LONG_SUFFIX = "SL";
	private static final String NO_COMMENT = "";//Will be replaced by sysmac studio

	private final String comparison;
	
	public InstructionComparisonFactory(String comparison) {
		this.comparison = comparison;
	}

	@Override
	public List<LadderElement> createForInput1(INSTRUCTION cxInstruction, IdFactory idFactory,
			String programName) {
		
		 boolean isPolynomial=true;
		boolean isUserDefinedType=false;
		List<LadderElement> ladderElements = InstructionFactory.createFunction(idFactory, comparison, isPolynomial, isUserDefinedType, "EN","");
		
		addIn1(cxInstruction, idFactory, programName, ladderElements);

		addIn2(cxInstruction, idFactory, programName, ladderElements);
		
		return ladderElements;
	}

	private void addIn2(INSTRUCTION cxInstruction, IdFactory idFactory, String programName,
			List<LadderElement> ladderElements) {
		String opperand2 = InstructionFactory.getVarName(cxInstruction, 2);
		SysmacDataType opperand2DataType = SysmacDataType.INT; //TODO;
		if (SysmacConstant.isCxConstant(opperand2)) {
			opperand2=SysmacConstant.createForCxConstantValue(opperand2DataType, opperand2).toString();
		}
		InstructionFactory.add(ladderElements, idFactory, programName, SysmacConnectionPointType.INPUT, "In2", NO_COMMENT,SysmacDataType.ANY_ELEMENTARY_EXCEPT_BOOL, opperand2, opperand2DataType  );
	}

	private void addIn1(INSTRUCTION cxInstruction, IdFactory idFactory, String programName,
			List<LadderElement> ladderElements) {
		String opperand1 = InstructionFactory.getVarName(cxInstruction, 1);
		SysmacDataType opperand1DataType = SysmacDataType.INT; //TODO;
		if (SysmacConstant.isCxConstant(opperand1)) {
			opperand1=SysmacConstant.createForCxConstantValue(opperand1DataType, opperand1).toString();
		}
		InstructionFactory.add(ladderElements, idFactory, programName, SysmacConnectionPointType.INPUT, "In1", NO_COMMENT,SysmacDataType.ANY_ELEMENTARY_EXCEPT_BOOL, opperand1, opperand1DataType  );
	}
	
	@Override
	public List<String> getNameSuffixes() {
		return Arrays.asList(SIGNED_SUFFIX,LONG_SUFFIX, SIGNED_LONG_SUFFIX, FLOAT_SUFFIX, DOUBLE_SUFFIX); 
	}

}
