package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION.Operands.Operand;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement.ConnectionPoint;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement.PinViewModel;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacConnectionPointType;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacDataType;

public class InstructionFactory {

	private static final int NOT_FOUND = 1;
	private static final Boolean IS_NO_POWER_POINT = false;
	private static final String VARIABLE = "Variable";
	private static final String FUNCTION = "Function";
	private static final String FUNCTION_BLOCK = "FunctionBlock";

	public static String getVarName(INSTRUCTION cxInstruction, int index) {
		Operand opperand = cxInstruction.getOperands().getOperand().get(index);
		String variableName = opperand.getName();
		return variableName;
	}

	/**
	 * creates a sysmac function<br>
	 * Than add all in and outputs with
	 * {@link #add(LadderElement, IdFactory, String, SysmacConnectionPointType, String, String, SysmacDataType, String, SysmacDataType)}
	 * 
	 * @param idFactory
	 * @param ladderElementType
	 * @param name
	 * @param isPolynomial
	 * @param isUserDefinedType
	 * @param powerPinOutName
	 * @param powerPinInName
	 * @return all elements that make up the function (first element is function
	 *         it self, followed by variable elements and edge elements), see
	 *         also
	 *         {@link #add(List, IdFactory, String, SysmacConnectionPointType, String, String, SysmacDataType, String, SysmacDataType)}
	 *
	 */

	// <LadderElement instanceID="0x00000052" ladderElementType="Function"
	// typeName="MOVE" IsPolynomial="false" IsUserDefinedType="false">
	// <ConnectionPoint connectionPointType="input" instanceID="0x00000054"
	// PowerPin="true">
	// <Edge instanceID="0x00000066" />
	// </ConnectionPoint>
	// <ConnectionPoint connectionPointType="input" instanceID="0x00000056"
	// PowerPin="false">
	// <Edge instanceID="0x00000064" />
	// </ConnectionPoint>
	// <ConnectionPoint connectionPointType="output" instanceID="0x00000058"
	// PowerPin="true">
	// <Edge instanceID="0x00000067" />
	// </ConnectionPoint>
	// <ConnectionPoint connectionPointType="output" instanceID="0x0000005A"
	// PowerPin="false">
	// <Edge instanceID="0x00000065" />
	// </ConnectionPoint>
	// <PinViewModel IsInput="true" Name="EN" Datatype="BOOL" Comment=""
	// Negated="false" IsInOutVariable="false" PowerPin="true" Visible="true"
	// EdgeDirectionType="NoEdge" />
	// <PinViewModel IsInput="true" Name="In" Datatype="ANY" Comment="Move
	// source" Negated="false" IsInOutVariable="false" PowerPin="false"
	// Visible="true" EdgeDirectionType="NoEdge" />
	// <PinViewModel IsInput="false" Name="ENO" Datatype="BOOL" Comment=""
	// Negated="false" IsInOutVariable="false" PowerPin="true" Visible="true"
	// EdgeDirectionType="NoEdge" />
	// <PinViewModel IsInput="false" Name="Out" Datatype="ANY" Comment="Move
	// destination" Negated="false" IsInOutVariable="false" PowerPin="false"
	// Visible="true" EdgeDirectionType="NoEdge" />
	// </LadderElement>
	// <LadderElement instanceID="0x0000005B" ladderElementType="Variable"
	// variableName="iTest1" baseVariableName="iTest1"
	// ProgramName="SimpleThings" baseVariableDataType="BOOL">
	// <ConnectionPoint connectionPointType="output" instanceID="0x0000005C">
	// <Edge instanceID="0x00000064" />
	// </ConnectionPoint>
	// </LadderElement>
	// <LadderElement instanceID="0x0000005D" ladderElementType="Variable"
	// variableName="oTest2" baseVariableName="oTest2"
	// ProgramName="SimpleThings" baseVariableDataType="BOOL">
	// <ConnectionPoint connectionPointType="input" instanceID="0x0000005E">
	// <Edge instanceID="0x00000065" />
	// </ConnectionPoint>
	// </LadderElement>

	public static List<LadderElement> createFunction(IdFactory idFactory, String name,
			boolean isPolynomial, boolean isUserDefinedType, String powerPinInName,
			String powerPinOutName) {
		LadderElement ladderElement = new LadderElement();
		ladderElement.setInstanceID(idFactory.createNext());
		ladderElement.setLadderElementType(FUNCTION);
		ladderElement.setTypeName(name);
		ladderElement.setIsPolynomial(Boolean.FALSE.toString());
		ladderElement.setIsUserDefinedType(Boolean.FALSE.toString());

		JAXBElement<PinViewModel> pinViewModelIn = PinViewModelFactory.create(
				SysmacConnectionPointType.INPUT, powerPinInName, "", SysmacDataType.BOOL, true);
		ladderElement.getContent().add(pinViewModelIn);

		JAXBElement<PinViewModel> pinViewModelOut = PinViewModelFactory.create(
				SysmacConnectionPointType.OUTPUT, powerPinOutName, "", SysmacDataType.BOOL, true);
		ladderElement.getContent().add(pinViewModelOut);

		List<LadderElement> ladderElements = new ArrayList<>();
		ladderElements.add(ladderElement);
		return ladderElements;
	}

	/**
	 * creates a standard sysmac functionblock<br>
	 * Than add all in and outputs with
	 * {@link #add(LadderElement, IdFactory, String, SysmacConnectionPointType, String, String, SysmacDataType, String, SysmacDataType)}
	 * 
	 * @param idFactory
	 * @param ladderElementType
	 * @param name
	 * @param isPolynomial
	 * @param isUserDefinedType
	 * @param powerPinOutName
	 * @param powerPinInName
	 * @return all elements that make up the function (first element is function
	 *         it self, followed by variable elements and edge elements), see
	 *         also
	 *         {@link #add(List, IdFactory, String, SysmacConnectionPointType, String, String, SysmacDataType, String, SysmacDataType)}
	 *
	 */

	public static List<LadderElement> createStandardFunctionBlock(IdFactory idFactory,
			String functionBlockName, String instanceName, boolean isUserDefinedType,
			String powerPinInName, String powerPinOutName) {
		LadderElement ladderElement = new LadderElement();
		ladderElement.setInstanceID(idFactory.createNext());
		ladderElement.setLadderElementType(FUNCTION_BLOCK);
		ladderElement.setTypeName(functionBlockName);
		ladderElement.setIsUserDefinedType(Boolean.FALSE.toString());
		ladderElement.setVariableName(instanceName);
		ladderElement.setBaseVariableDataType(instanceName);

		JAXBElement<PinViewModel> pinViewModelIn = PinViewModelFactory.create(
				SysmacConnectionPointType.INPUT, powerPinInName, "", SysmacDataType.BOOL, true);
		ladderElement.getContent().add(pinViewModelIn);

		JAXBElement<PinViewModel> pinViewModelOut = PinViewModelFactory.create(
				SysmacConnectionPointType.OUTPUT, powerPinOutName, "", SysmacDataType.BOOL, true);
		ladderElement.getContent().add(pinViewModelOut);

		List<LadderElement> ladderElements = new ArrayList<>();
		ladderElements.add(ladderElement);
		return ladderElements;
	}

	/**
	 * Adds an input or output to the instruction<br>
	 * See also {@link #create(IdFactory, String, String, boolean, boolean)}
	 * Note: add all function inputs first and in the order as is defined in
	 * Sysmac documentation. Skip the first input power pin (that connect to the
	 * ladder, often named EN for enable) because the {@link PinViewModel} is
	 * already added ande the {@link ConnectionPoint} and edge will be added
	 * later by the
	 * {@link SysmacLadderElementFactory#createConnection(nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML, nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Mapping, nth.meyn.cx.sysmac.converter.cx.ladder.model.CxConnection)}<br>
	 * Note: then add all function outputs and in the order as is defined in
	 * Sysmac documentation. Skip the first output power pin (that connect to
	 * the ladder, often named ENO for enable out) because the
	 * {@link PinViewModel} is already added ande the {@link ConnectionPoint}
	 * and edge will be added later by the
	 * {@link SysmacLadderElementFactory#createConnection(nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML, nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Mapping, nth.meyn.cx.sysmac.converter.cx.ladder.model.CxConnection)}<br>
	 * 
	 * @param ladderElements
	 *            all elements that make up the function (first element is
	 *            function it self, followed by variable elements and edge
	 *            elements)
	 */
	public static void add(List<LadderElement> ladderElements, IdFactory idFactory,
			String programName, SysmacConnectionPointType type, String pinName, String pinComment,
			SysmacDataType pinDataType, String varName, SysmacDataType varDataType) {

		LadderElement functionElement = ladderElements.get(0);

		String functionConnectionPointId = idFactory.createNext();
		String edgeId = idFactory.createNext();
		JAXBElement<ConnectionPoint> connectionPoint = ConnectionPointFactory
				.create(functionConnectionPointId, edgeId, type, IS_NO_POWER_POINT);
		int index = findNextConnectionPointIndex(functionElement, type);
		functionElement.getContent().add(index, connectionPoint);

		JAXBElement<PinViewModel> pinViewModel = PinViewModelFactory.create(type, pinName,
				pinComment, pinDataType, false);
		index = findNextPinViewModelIndex(functionElement, type);
		functionElement.getContent().add(index, pinViewModel);

		if (varName != null) {
			LadderElement varElement = new LadderElement();
			varElement.setInstanceID(idFactory.createNext());
			varElement.setLadderElementType(VARIABLE);
			varElement.setVariableName(varName);
			varElement.setBaseVariableName(varName);
			varElement.setProgramName(programName);
			varElement.setBaseVariableDataType(varDataType.toString());

			String variableConnectionPointId = idFactory.createNext();
			connectionPoint = ConnectionPointFactory.create(variableConnectionPointId, edgeId,
					type.getInverse(), null);
			varElement.getContent().add(connectionPoint);

			index = findNextVarElementIndex(ladderElements);
			ladderElements.add(index, varElement);

			String connectionPointSourceId = (type == SysmacConnectionPointType.INPUT)
					? variableConnectionPointId : functionConnectionPointId;
			String connectionPointTargetId = (type == SysmacConnectionPointType.INPUT)
					? functionConnectionPointId : variableConnectionPointId;
			LadderElement edgeElement = EdgeFactory.createEdge(edgeId, connectionPointSourceId,
					connectionPointTargetId, false);
			index = ladderElements.size();
			ladderElements.add(index, edgeElement);
		}
	}

	private static int findNextVarElementIndex(List<LadderElement> ladderElements) {
		int index = 1;
		for (LadderElement ladderElement : ladderElements) {
			if (ladderElement.getLadderElementType().equals(VARIABLE)) {
				index = ladderElements.indexOf(ladderElement) + 1;
			}
		}
		return index;
	}

	private static int findNextPinViewModelIndex(LadderElement functionElement,
			SysmacConnectionPointType typeToFind) {
		String isInput = Boolean.toString(typeToFind == SysmacConnectionPointType.INPUT);
		int index = 0;
		for (Serializable xmlElement : functionElement.getContent()) {
			if (xmlElement instanceof JAXBElement) {
				JAXBElement jaxbElement = (JAXBElement) xmlElement;
				Object xmlObject = jaxbElement.getValue();
				if (xmlObject instanceof PinViewModel) {
					PinViewModel pinViewModel = (PinViewModel) xmlObject;
					if (pinViewModel.getIsInput().equals(isInput)) {
						index = functionElement.getContent().indexOf(xmlElement) + 1;
					}
				}
			}
		}
		return index;
	}

	private static int findNextConnectionPointIndex(LadderElement functionElement,
			SysmacConnectionPointType typeToFind) {
		String connectionPointTypeToFind = typeToFind.toString().toLowerCase();
		int index = NOT_FOUND;
		for (Serializable xmlElement : functionElement.getContent()) {
			if (xmlElement instanceof JAXBElement) {
				JAXBElement jaxbElement = (JAXBElement) xmlElement;
				Object xmlObject = jaxbElement.getValue();
				if (xmlObject instanceof ConnectionPoint) {
					ConnectionPoint connectionPoint = (ConnectionPoint) xmlObject;
					if (connectionPoint.getConnectionPointType()
							.equals(connectionPointTypeToFind)) {
						index = functionElement.getContent().indexOf(xmlElement) + 1;
					}
				}
			}
		}
		if (index == NOT_FOUND) {
			for (Serializable xmlElement : functionElement.getContent()) {
				if (xmlElement instanceof JAXBElement) {
					JAXBElement jaxbElement = (JAXBElement) xmlElement;
					Object xmlObject = jaxbElement.getValue();
					if (xmlObject instanceof PinViewModel) {
						return functionElement.getContent().indexOf(xmlElement);
					}
				}
			}
		}

		return index;
	}

}
