package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxConnection;
import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxInstructionInput;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Mapping;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement.ConnectionPoint;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacConnectionPointType;

public class LadderElementFactoryFactory {

	private static final String FACTORY = "Factory";
	private static final String DIFF_DOWN_PREFIX = "%";
	private static final String DIFF_UP_PREFIX = "@";
	private static final String INVERSE_PREFIX = "!";
	private static final int NOT_FOUND = -1;
	private final Map<String, LadderElementFactory> ladderElementFactories;
	private final IdFactory idFactory;
	private Map<String, String> nameReplacements;

	public LadderElementFactoryFactory() {
		ladderElementFactories = createLadderElementFactories();
		idFactory = new IdFactory();
		nameReplacements=createNameReplacements();
	}

	private Map<String, LadderElementFactory> createLadderElementFactories() {
		Map<String, LadderElementFactory> ladderElementFactories=new HashMap<>();
		try {
			ClassLoader loader = this.getClass().getClassLoader();
			ClassPath path = ClassPath.from(loader);
			String packageName = LadderElementFactoryFactory.class.getPackage().getName();
			ImmutableSet<ClassInfo> allClassesInPackage = path.getTopLevelClasses(packageName);
			for (ClassInfo classInfo : allClassesInPackage) {
				String classNameInPackage = classInfo.toString();
				Class<?> classInPackage = Class.forName(classNameInPackage);
				boolean isInterface = classInPackage.isInterface();
				boolean isAbstract = Modifier.isAbstract(classInPackage.getModifiers());
				if (!isInterface && !isAbstract) {

					if (LadderInstructionFactory.class.isAssignableFrom(classInPackage)) {
						LadderInstructionFactory ladderInstructionFactory = (LadderInstructionFactory) classInPackage
								.newInstance();
						String className = classInPackage.getName();
						List<String> nameSuffixes = ladderInstructionFactory.getNameSuffixes();
						ladderElementFactories.put(className, ladderInstructionFactory);
						for (String nameSuffix : nameSuffixes) {
							ladderElementFactories.put(createFactoryName(className, nameSuffix),
									ladderInstructionFactory);
						}
					} else if (LadderElementFactory.class.isAssignableFrom(classInPackage)) {
						LadderElementFactory ladderElementFactory = (LadderElementFactory) classInPackage
								.newInstance();
						String className = classInPackage.getName();
						ladderElementFactories.put(className, ladderElementFactory);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Could not create a LadderElementFactory.", e);
		}

		return ladderElementFactories;
	}

	private String createFactoryName(String className, String nameExtension) {
		StringBuilder factoryName=new StringBuilder();
		factoryName.append(StringUtils.removeEnd(className, FACTORY));
		factoryName.append(nameExtension.toLowerCase());
		factoryName.append(FACTORY);
		return factoryName.toString();
	}

	public void createConnection(RungXML sysmacRung, Mapping mapping, CxConnection cxConnection) {

		String edgeId = idFactory.createNextElementId();
		String connectionPointInputId = idFactory.createNextElementId();
		String connectionPointOutputId = idFactory.createNextElementId();

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

		int index = indexOf(source.getContent(), SysmacConnectionPointType.OUTPUT,
				isPowerPin != null);
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

		int index = indexOf(target.getContent(), SysmacConnectionPointType.INPUT,
				isPowerPin != null);
		target.getContent().add(index, connectionPointInput);
	}

	/**
	 * 
	 * @param content
	 * @param type
	 * @param isPowerPin
	 *            if true, returns index of first connectionPoint, if false it
	 *            gets the index of the last connectionPoint
	 * @return
	 */
	private int indexOf(List<Serializable> content, SysmacConnectionPointType type,
			boolean isPowerPin) {
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
							index = content.indexOf(xmlElement) + 1;
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

	public LadderElementFactory create(Object cxLadderObject) {
		String factoryClassName = createFactoryClassName(cxLadderObject);
		LadderElementFactory ladderElementFactory = ladderElementFactories.get(factoryClassName);
		if (ladderElementFactory == null) {
			throw new RuntimeException("There is no:" + factoryClassName);
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
			StringBuilder message=new StringBuilder("Could not create: ");
			message.append(converterClassName);
			message.append("\rAsk Nils ten Hoeve to implement this function or ensure that your PLC program only includes the following supported CX instructions:");
			for (String supportedCxInstruction : getSupportedCxInstructions()) {
				message.append(supportedCxInstruction);
				message.append("\r");
			}
			throw new RuntimeException(message.toString(),e);
		}

	}

	private String createFactoryClassName(Object cxLadderObject) {
		StringBuilder name = new StringBuilder();
		name.append(LadderElementFactoryFactory.class.getPackage().getName());
		name.append(".");
		name.append(createNormilizedFactoryName(cxLadderObject));
		name.append(FACTORY);
		return name.toString();
	}

	private Object createNormilizedFactoryName(Object cxLadderObject) {
		if (cxLadderObject instanceof INSTRUCTION) {
			INSTRUCTION instruction = (INSTRUCTION) cxLadderObject;
			return createNormilizedInstructionName(instruction);
		}

		if (cxLadderObject instanceof CxInstructionInput) {
			CxInstructionInput instructionInput = (CxInstructionInput) cxLadderObject;
			INSTRUCTION instruction = instructionInput.getInstruction();
			String name = createNormilizedInstructionName(instruction);
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

	private String createNormilizedInstructionName(INSTRUCTION instruction) {
		String instructionName = instruction.getOperands().getOperand().get(0).getName();
		instructionName = StringUtils.removeStart(instructionName, INVERSE_PREFIX);
		instructionName = StringUtils.removeStart(instructionName, DIFF_UP_PREFIX);
		instructionName = StringUtils.removeStart(instructionName, DIFF_DOWN_PREFIX);
		instructionName = StringUtils.removePattern(instructionName, "\\(\\d+\\)");
		instructionName = createUpperCapitalCase(instructionName);
		for (String toReplace : nameReplacements.keySet()) {
			String replacement = nameReplacements.get(toReplace);
			instructionName = instructionName.replace(toReplace, replacement);	
		}

		// TODO change illegal characters
		StringBuilder result = new StringBuilder();
		result.append("Instruction");
		result.append(instructionName);
		return result.toString();
	}

	public IdFactory getIdFactory() {
		return idFactory;
	}
	
	private Map<String,String> createNameReplacements() {
		 Map<String,String> replacements=new HashMap<>();
		replacements.put("<", "LessThan");
		replacements.put(">", "GreatherThan");
		replacements.put("=", "Equals");
		replacements.put("+", "Plus");
		replacements.put("-", "Minus");
		replacements.put("/", "Divide");
		replacements.put("*", "Multiply");
		return replacements;
	}

	public List<String> getSupportedCxInstructions() {
		List<String> supportedCxInstructions=new ArrayList<>();
		for (String key : ladderElementFactories.keySet()) {
			LadderElementFactory ladderElementFactory = ladderElementFactories.get(key);
			if (ladderElementFactory instanceof LadderInstructionFactory) {
				String cxInstructionName = StringUtils.removeStart(key, LadderElementFactoryFactory.class.getPackage().getName()+".Instruction");
				cxInstructionName = StringUtils.removeEnd(cxInstructionName, FACTORY);
				for (String replacement : nameReplacements.keySet()) {
					String toReplace = nameReplacements.get(replacement);
					cxInstructionName = cxInstructionName.replace( toReplace,replacement);
				}
				supportedCxInstructions.add(cxInstructionName.toUpperCase());
			}
		}
		Collections.sort(supportedCxInstructions);
		return supportedCxInstructions;
	}
	
}
