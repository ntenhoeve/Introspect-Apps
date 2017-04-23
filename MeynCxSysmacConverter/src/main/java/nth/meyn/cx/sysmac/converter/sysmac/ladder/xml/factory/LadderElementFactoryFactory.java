package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.StringUtils;

import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxConnection;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxInstructionInput;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxLadderModelPrinter;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Mapping;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement.ConnectionPoint;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacConnectionPointType;

public class LadderElementFactoryFactory {

	private static final String DIFF_DOWN_PREFIX = "%";
	private static final String DIFF_UP_PREFIX = "@";
	private static final String INVERSE_PREFIX = "!";
	private static final int NOT_FOUND = -1;
	private final Map<String, LadderElementFactory> ladderElementFactories;
	private final IdFactory idFactory;

	public LadderElementFactoryFactory() {
		ladderElementFactories = new HashMap<>();
		idFactory = new IdFactory();
	}

	public void createConnection(RungXML sysmacRung, Mapping mapping, CxConnection cxConnection) {

		String edgeId = idFactory.createNext();
		String connectionPointInputId = idFactory.createNext();
		String connectionPointOutputId = idFactory.createNext();

		LadderElement edge = EdgeFactory.createEdge(edgeId, connectionPointOutputId,
				connectionPointInputId, true);
		sysmacRung.getLadderElement().add(edge);

		addConnectionPointOutput(mapping, cxConnection, connectionPointOutputId, edge);

		addConnectionPointInput(mapping, cxConnection, connectionPointInputId, edge);
	}

	private void addConnectionPointOutput(Mapping mapping, CxConnection cxConnection,
			String connectionPointOutputId, LadderElement edge) {
		LadderElement source = mapping.getLadderElement(cxConnection.getInput());
		Boolean isPowerPin = null;
		if (cxConnection.getInput() instanceof INSTRUCTION) {
			isPowerPin = true;
		}
		JAXBElement<ConnectionPoint> connectionPointOutput = ConnectionPointFactory.create(
				connectionPointOutputId, edge.getInstanceID(), SysmacConnectionPointType.OUTPUT,
				isPowerPin);

		int index = indexOf(source.getContent(), SysmacConnectionPointType.OUTPUT, isPowerPin!=null);
		source.getContent().add(index, connectionPointOutput);
	}

	private void addConnectionPointInput(Mapping mapping, CxConnection cxConnection,
			String connectionPointInputId, LadderElement edge) {
		LadderElement target = mapping.getLadderElement(cxConnection.getOutput());
		Boolean isPowerPin = null;
		if (cxConnection.getOutput() instanceof INSTRUCTION) {
			isPowerPin = true;
		}
		JAXBElement<ConnectionPoint> connectionPointInput = ConnectionPointFactory.create(
				connectionPointInputId, edge.getInstanceID(), SysmacConnectionPointType.INPUT,
				isPowerPin);

		int index = indexOf(target.getContent(), SysmacConnectionPointType.INPUT, isPowerPin!=null);
		target.getContent().add(index, connectionPointInput);
	}

	/**
	 * 
	 * @param content
	 * @param type
	 * @param isPowerPin if true, returns index of first connectionPoint, if false it gets the index of  the last connectionPoint 
	 * @return
	 */
	private int indexOf(List<Serializable> content, SysmacConnectionPointType type, boolean isPowerPin) {
		int index = NOT_FOUND;
		for (Serializable xmlElement : content) {
			if (xmlElement instanceof JAXBElement) {
				JAXBElement jaxbElement = (JAXBElement) xmlElement;
				Object xmlObject = jaxbElement.getValue();
				if (xmlObject instanceof ConnectionPoint) {
					ConnectionPoint connectionPoint = (ConnectionPoint) xmlObject;
					if (connectionPoint.getConnectionPointType()
							.equals(type.toString().toLowerCase())) {
						if (isPowerPin) {
							return content.indexOf(xmlElement);
						} else {
							index = content.indexOf(xmlElement)+1;
						}	
					}
				}
			}
		}
		if (index == NOT_FOUND) {
			if (type == SysmacConnectionPointType.INPUT) {
				// no inputs so index=0
				return 0;
			} else {
				// outputs come after inputs
				return indexOf(content, SysmacConnectionPointType.INPUT, isPowerPin);
			}
		} else {
			return index;
		}
	}

	public LadderElementFactory create(String programName, Object cxLadderObject) {
		String converterClassName = createFactoryClassName(cxLadderObject);
		LadderElementFactory ladderElementFactory = ladderElementFactories.get(converterClassName);
		if (ladderElementFactory == null) {
			ladderElementFactory = create(converterClassName);
			ladderElementFactories.put(converterClassName, ladderElementFactory);
		}
		return ladderElementFactory;
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
		name.append(LadderElementFactoryFactory.class.getPackage().getName());
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
		String capatilized = StringUtils.capitalize(lowerCase);
		String withoutSpaces = capatilized.replace(" ", "");
		return withoutSpaces;
	}

	private String createNormilizedInstructionName(INSTRUCTION instruction, int input) {
		String instructionName = instruction.getOperands().getOperand().get(0).getName();
		instructionName = StringUtils.removeStart(instructionName, INVERSE_PREFIX);
		instructionName = StringUtils.removeStart(instructionName, DIFF_UP_PREFIX);
		instructionName = StringUtils.removeStart(instructionName, DIFF_DOWN_PREFIX);
		instructionName = StringUtils.removePattern(instructionName, "\\(\\d+\\)");
		instructionName = instructionName.replace("<", "LessThan");
		instructionName = instructionName.replace(">", "GreatherThan");
		instructionName = instructionName.replace("=", "Equals");
		instructionName = instructionName.replace("+", "Plus");
		instructionName = instructionName.replace("-", "Minus");
		instructionName = instructionName.replace("/", "Divide");
		instructionName = instructionName.replace("*", "Multiply");
		instructionName = createUpperCapitalCase(instructionName);

		// TODO change illegal characters
		StringBuilder result = new StringBuilder();
		result.append("Instruction");
		result.append(instructionName);
		result.append(input);
		return result.toString();
	}

	public IdFactory getIdFactory() {
		return idFactory;
	}

}
