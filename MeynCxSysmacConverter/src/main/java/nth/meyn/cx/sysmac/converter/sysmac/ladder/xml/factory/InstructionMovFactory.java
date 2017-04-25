package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacConnectionPointType;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacDataType;

public class InstructionMovFactory implements LadderInstructionFactory {


//	  <LadderElement instanceID="0x00000052" ladderElementType="Function" typeName="MOVE" IsPolynomial="false" IsUserDefinedType="false">
//      <ConnectionPoint connectionPointType="input" instanceID="0x00000054" PowerPin="true">
//        <Edge instanceID="0x00000066" />
//      </ConnectionPoint>
//      <ConnectionPoint connectionPointType="input" instanceID="0x00000056" PowerPin="false">
//        <Edge instanceID="0x00000064" />
//      </ConnectionPoint>
//      <ConnectionPoint connectionPointType="output" instanceID="0x00000058" PowerPin="true">
//        <Edge instanceID="0x00000067" />
//      </ConnectionPoint>
//      <ConnectionPoint connectionPointType="output" instanceID="0x0000005A" PowerPin="false">
//        <Edge instanceID="0x00000065" />
//      </ConnectionPoint>
//      <PinViewModel IsInput="true" Name="EN" Datatype="BOOL" Comment="" Negated="false" IsInOutVariable="false" PowerPin="true" Visible="true" EdgeDirectionType="NoEdge" />
//      <PinViewModel IsInput="true" Name="In" Datatype="ANY" Comment="Move source" Negated="false" IsInOutVariable="false" PowerPin="false" Visible="true" EdgeDirectionType="NoEdge" />
//      <PinViewModel IsInput="false" Name="ENO" Datatype="BOOL" Comment="" Negated="false" IsInOutVariable="false" PowerPin="true" Visible="true" EdgeDirectionType="NoEdge" />
//      <PinViewModel IsInput="false" Name="Out" Datatype="ANY" Comment="Move destination" Negated="false" IsInOutVariable="false" PowerPin="false" Visible="true" EdgeDirectionType="NoEdge" />
//    </LadderElement>
//    <LadderElement instanceID="0x0000005B" ladderElementType="Variable" variableName="iTest1" baseVariableName="iTest1" ProgramName="SimpleThings" baseVariableDataType="BOOL">
//      <ConnectionPoint connectionPointType="output" instanceID="0x0000005C">
//        <Edge instanceID="0x00000064" />
//      </ConnectionPoint>
//    </LadderElement>
//    <LadderElement instanceID="0x0000005D" ladderElementType="Variable" variableName="oTest2" baseVariableName="oTest2" ProgramName="SimpleThings" baseVariableDataType="BOOL">
//      <ConnectionPoint connectionPointType="input" instanceID="0x0000005E">
//        <Edge instanceID="0x00000065" />
//      </ConnectionPoint>
//    </LadderElement>
	
	


	private static final String MOVE = "MOVE";

	@Override
	public List<LadderElement> createForInput1(INSTRUCTION cxInstruction, IdFactory idFactory,
			String programName) {
		
		 boolean isPolynomial=false;
		boolean isUserDefinedType=false;
		List<LadderElement> ladderElements = InstructionFactory.createFunction(idFactory, MOVE, isPolynomial, isUserDefinedType, "EN","ENO");
		
		String sourceVarName = InstructionFactory.getVarName(cxInstruction, 1);
		SysmacDataType sourceVarDataType = SysmacDataType.BOOL; //TODO;
		InstructionFactory.add(ladderElements, idFactory, programName, SysmacConnectionPointType.INPUT, "In", "Move source",SysmacDataType.ANY, sourceVarName, sourceVarDataType  );

		String destVarName = InstructionFactory.getVarName(cxInstruction, 2);
		SysmacDataType destVarDataType = SysmacDataType.BOOL; //TODO;
		InstructionFactory.add(ladderElements, idFactory, programName, SysmacConnectionPointType.OUTPUT, "Out", "Move destination",SysmacDataType.ANY, destVarName, destVarDataType  );
		
		return ladderElements;
	}

	@Override
	public List<String> getNameSuffixes() {
		return Arrays.asList("L");
	}

}
