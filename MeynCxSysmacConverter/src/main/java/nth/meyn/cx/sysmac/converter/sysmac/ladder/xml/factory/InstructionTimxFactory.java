package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.SysmacConstant;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacConnectionPointType;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacDataType;

public class InstructionTimxFactory implements LadderInstructionFactory {

//	<Rungs>
//	  <RungXML Comment="" Label="" Height="87.800000000000011" Width="1065.7619047619046">
//	    <LadderElement instanceID="0x00000037" ladderElementType="FunctionBlock" typeName="TON" IsUserDefinedType="false" variableName="fOnDelay" baseVariableName="fOnDelay" ProgramName="SimpleThings">
//	      <ConnectionPoint connectionPointType="input" instanceID="0x00000039" PowerPin="true">
//	        <Edge instanceID="0x00000048" />
//	      </ConnectionPoint>
//	      <ConnectionPoint connectionPointType="input" instanceID="0x0000003B" PowerPin="false">
//	        <Edge instanceID="0x00000049" />
//	      </ConnectionPoint>
//	      <ConnectionPoint connectionPointType="output" instanceID="0x0000003D" PowerPin="true">
//	        <Edge instanceID="0x0000004C" />
//	      </ConnectionPoint>
//	      <ConnectionPoint connectionPointType="output" instanceID="0x0000003F" PowerPin="false" />
//	      <PinViewModel IsInput="true" Name="In" Datatype="BOOL" Comment="Timer input" Negated="false" IsInOutVariable="false" PowerPin="true" Visible="true" EdgeDirectionType="NoEdge" />
//	      <PinViewModel IsInput="true" Name="PT" Datatype="TIME" Comment="Set time" Negated="false" IsInOutVariable="false" PowerPin="false" Visible="true" EdgeDirectionType="NoEdge" />
//	      <PinViewModel IsInput="false" Name="Q" Datatype="BOOL" Comment="Timer output" Negated="false" IsInOutVariable="false" PowerPin="true" Visible="true" EdgeDirectionType="NoEdge" />
//	      <PinViewModel IsInput="false" Name="ET" Datatype="TIME" Comment="Elapsed time" Negated="false" IsInOutVariable="false" PowerPin="false" Visible="true" EdgeDirectionType="NoEdge" />
//	    </LadderElement>
//	    <LadderElement instanceID="0x00000040" ladderElementType="Variable" variableName="T#10s" baseVariableName="T#10s" ProgramName="SimpleThings">
//	      <ConnectionPoint connectionPointType="output" instanceID="0x00000041">
//	        <Edge instanceID="0x00000049" />
//	      </ConnectionPoint>
//	    </LadderElement>
//	    <LadderElement instanceID="0x00000044" ladderElementType="Connection" IsLeftPowerRail="true" IsRightPowerRail="false">
//	      <ConnectionPoint connectionPointType="output" instanceID="0x00000045">
//	        <Edge instanceID="0x00000048" />
//	      </ConnectionPoint>
//	    </LadderElement>
//	    <LadderElement instanceID="0x00000046" ladderElementType="Connection" IsLeftPowerRail="false" IsRightPowerRail="true">
//	      <ConnectionPoint connectionPointType="input" instanceID="0x00000047">
//	        <Edge instanceID="0x0000004C" />
//	      </ConnectionPoint>
//	    </LadderElement>
//	    <LadderElement instanceID="0x00000048" ladderElementType="Edge" sourceID="0x00000045" targetID="0x00000039" Focusable="true" />
//	    <LadderElement instanceID="0x00000049" ladderElementType="Edge" sourceID="0x00000041" targetID="0x0000003B" Focusable="false" />
//	    <LadderElement instanceID="0x0000004C" ladderElementType="Edge" sourceID="0x0000003D" targetID="0x00000047" Focusable="true" />
//	  </RungXML>
//	</Rungs>
	
	private static final String TON = "TON";

	@Override
	public List<LadderElement> createForInput1(INSTRUCTION cxInstruction, IdFactory idFactory,
			String programName) {

		boolean isUserDefinedType = false;
		String timerName = InstructionFactory.getVarName(cxInstruction, 1).trim();

		List<LadderElement> ladderElements = InstructionFactory.createStandardFunctionBlock(
				idFactory, TON, timerName, isUserDefinedType, "IN", "Q");

		String timerSetPoint = InstructionFactory.getVarName(cxInstruction, 2);
		if (timerSetPoint.startsWith("&")) {
			int tenthsOfSeconds = Integer.parseInt(timerSetPoint.substring(1));
			timerSetPoint = SysmacConstant.createTime(tenthsOfSeconds).toString();
		}
		SysmacDataType sourceVarDataType = SysmacDataType.TIME;
		InstructionFactory.add(ladderElements, idFactory, programName,
				SysmacConnectionPointType.INPUT, "PT", "Set time", SysmacDataType.ANY,
				timerSetPoint, sourceVarDataType);

		SysmacDataType destVarDataType = SysmacDataType.TIME;
		InstructionFactory.add(ladderElements, idFactory, programName,
				SysmacConnectionPointType.OUTPUT, "ET", "Elapsed time", SysmacDataType.ANY, null,
				destVarDataType);

		return ladderElements;
	}

	@Override
	public List<String> getNameSuffixes() {
		return Arrays.asList();
	}

}
