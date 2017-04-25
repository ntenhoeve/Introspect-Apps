package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.SysmacConstant;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacConnectionPointType;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacDataType;

public abstract class InstructionComparisonFactory implements LadderElementFactory<INSTRUCTION> {





	private static final String COMPARISON_DATA = "Comparison data";
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
		
		String opperand1 = InstructionFactory.getVarName(cxInstruction, 1);
		SysmacDataType opperand1DataType = SysmacDataType.INT; //TODO;
		if (SysmacConstant.isCxConstant(opperand1)) {
			opperand1=SysmacConstant.createForCxConstantValue(opperand1DataType, opperand1).toString();
		}
		InstructionFactory.add(ladderElements, idFactory, programName, SysmacConnectionPointType.INPUT, "In1", COMPARISON_DATA,SysmacDataType.ANY_ELEMENTARY_EXCEPT_BOOL, opperand1, opperand1DataType  );

		String opperand2 = InstructionFactory.getVarName(cxInstruction, 2);
		SysmacDataType opperand2DataType = SysmacDataType.INT; //TODO;
		if (SysmacConstant.isCxConstant(opperand2)) {
			opperand2=SysmacConstant.createForCxConstantValue(opperand2DataType, opperand2).toString();
		}
		InstructionFactory.add(ladderElements, idFactory, programName, SysmacConnectionPointType.INPUT, "In2", COMPARISON_DATA,SysmacDataType.ANY_ELEMENTARY_EXCEPT_BOOL, opperand2, opperand2DataType  );
		
		return ladderElements;
	}

}
