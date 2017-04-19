package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.StringUtils;

import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxConnection;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxInstructionInput;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Mapping;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement.ConnectionPoint;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacConnectionPointType;

public class SysmacLadderElementFactory {

	private static final String DIFF_DOWN_PREFIX = "%";
	private static final String DIFF_UP_PREFIX = "@";
	private static final String INVERSE_PREFIX = "!";
	private final Map<String, LadderElementFactory> ladderElementFactories;
	private final IdFactory idFactory;

	public SysmacLadderElementFactory() {
		ladderElementFactories = new HashMap<>();
		idFactory = new IdFactory();
	}

	
	public void createConnection(RungXML sysmacRung, Mapping mapping, CxConnection cxConnection) {
		String edgeId = idFactory.createNext();
		String connectionPointInputId = idFactory.createNext();
		String connectionPointOutputId = idFactory.createNext();

		LadderElement edge = EdgeFactory.createEdge(edgeId, connectionPointOutputId, connectionPointInputId,true);
		sysmacRung.getLadderElement().add(edge);

		addConnectionPointOutput(mapping, cxConnection, connectionPointOutputId, edge);
		
		addConnectionPointInput(mapping, cxConnection, connectionPointInputId, edge);
	}


	private void addConnectionPointOutput(Mapping mapping, CxConnection cxConnection,
			String connectionPointOutputId, LadderElement edge) {
		LadderElement source = mapping.getLadderElement(cxConnection.getInput());
		Boolean isPowerPin=null;
		if (cxConnection.getInput() instanceof INSTRUCTION) {
			isPowerPin=true;
		}
		JAXBElement<ConnectionPoint> connectionPointOutput = ConnectionPointFactory.create(
				connectionPointOutputId, edge.getInstanceID(), SysmacConnectionPointType.OUTPUT,isPowerPin);

		int index=indexOfFirst(source.getContent(),SysmacConnectionPointType.OUTPUT);
		source.getContent().add(index,connectionPointOutput);
	}


	private void addConnectionPointInput(Mapping mapping, CxConnection cxConnection,
			String connectionPointInputId, LadderElement edge) {
		LadderElement target = mapping.getLadderElement(cxConnection.getOutput());
		Boolean isPowerPin=null;
		if (cxConnection.getOutput() instanceof INSTRUCTION) {
			isPowerPin=true;
		}
		JAXBElement<ConnectionPoint> connectionPointInput = ConnectionPointFactory.create(
				connectionPointInputId, edge.getInstanceID(), SysmacConnectionPointType.INPUT,isPowerPin);
		
		int index=indexOfFirst(target.getContent(),SysmacConnectionPointType.INPUT);
		target.getContent().add(index,connectionPointInput);
	}


	private int indexOfFirst(List<Serializable> content, SysmacConnectionPointType type) {
		for (Serializable xmlElement : content) {
			if (xmlElement instanceof JAXBElement) {
				JAXBElement jaxbElement = (JAXBElement) xmlElement;
				Object xmlObject = jaxbElement.getValue();
				if (xmlObject instanceof ConnectionPoint) {
					ConnectionPoint connectionPoint = (ConnectionPoint) xmlObject;
					if (connectionPoint.getConnectionPointType().equals(type.toString().toLowerCase())) {
						return content.indexOf(xmlElement);
					}
				}
			}
		}
		return 0;
	}


	public List<LadderElement> create(String programName, Object cxLadderObject) {
		String converterClassName = createFactoryClassName(cxLadderObject);
		LadderElementFactory ladderElementFactory = ladderElementFactories.get(converterClassName);
		if (ladderElementFactory == null) {
			ladderElementFactory = create(converterClassName);
			ladderElementFactories.put(converterClassName, ladderElementFactory);
		}
		List ladderElements = ladderElementFactory.create(cxLadderObject, idFactory,
				programName);
		return ladderElements;
	}

	private LadderElementFactory create(String converterClassName) {
		try {
			Class<? extends LadderElementFactory> ladderElementFactoryClass = (Class<? extends LadderElementFactory>) Class
					.forName(converterClassName);
			LadderElementFactory ladderElementFactory = ladderElementFactoryClass.newInstance();
			return ladderElementFactory;
		} catch (Exception e) {
			throw new RuntimeException("Could not create: " + converterClassName);
		}

	}

	private String createFactoryClassName(Object cxLadderObject) {
		StringBuilder name = new StringBuilder();
		name.append(SysmacLadderElementFactory.class.getPackage().getName());
		name.append(".");
		name.append(createNormilizedFactoryName(cxLadderObject));
		name.append("Factory");
		return name.toString();
	}

	private Object createNormilizedFactoryName(Object cxLadderObject) {
		if (cxLadderObject instanceof INSTRUCTION) {
			INSTRUCTION instruction = (INSTRUCTION) cxLadderObject;
			return createNormilizedInstructionName(instruction, 0);
		}
		
		if (cxLadderObject instanceof CxInstructionInput) {
			CxInstructionInput instructionInput = (CxInstructionInput) cxLadderObject;
			INSTRUCTION instruction = instructionInput.getInstruction();
			int inputNr = instructionInput.getInputNr();
			String name = createNormilizedInstructionName(instruction, inputNr);
			return name;
		}
		String className = cxLadderObject.getClass().getSimpleName();
		if (StringUtils.isAllUpperCase(className)) {
			return createUpperCapitalCase(className);
		}
		if (className.startsWith("Cx")) {
			className = className.substring(2);
		}
		return className;
	}

	private String createUpperCapitalCase(String text) {
		String withSpaces = text.replace("_", " ");
		String lowerCase = withSpaces.toLowerCase();
		String capatilized=StringUtils.capitalize(lowerCase);
		String withoutSpaces = capatilized.replace(" " , "");
		return withoutSpaces;
	}

	private String createNormilizedInstructionName(INSTRUCTION instruction, int input) {
		String instructionName = instruction.getOperands().getOperand().get(0).getName();
		instructionName=StringUtils.removeStart(instructionName, INVERSE_PREFIX);
		instructionName=StringUtils.removeStart(instructionName, DIFF_UP_PREFIX);
		instructionName=StringUtils.removeStart(instructionName, DIFF_DOWN_PREFIX);
		instructionName=StringUtils.removePattern(instructionName, "\\(\\d+\\)");
		
		instructionName=createUpperCapitalCase(instructionName);

		//TODO change illegal characters
		StringBuilder result=new StringBuilder();
		result.append("Instruction");
		result.append(instructionName);
		result.append(input);
		return result.toString();
	}

}
