package nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import nth.sysmac.user.alarms.generator.dom.sysmac.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.XmlFile;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.entity.Entity;

public class VariableService {

	private static final String S_EVENT = "\\sEvent";

	public List<Variable> getGlobalVariables(SysmacProject sysmacProject) {
		Entity globalVariableEntity = getGlobalHmiVariableEntity(sysmacProject);
		String id = globalVariableEntity.getId();
		VariableXmlFile variableXmlFile = find(sysmacProject, id);
		List<Variable> variables = variableXmlFile.getVariables();
		return variables;
	}

	public Variable getGlobalHmiEventVariable(SysmacProject sysmacProject) {
		List<Variable> globalVariables = getGlobalVariables(sysmacProject);
		List<Variable> eventVariables = globalVariables.stream().filter(v -> v.getDataTypeName().endsWith(S_EVENT))
				.collect(Collectors.toList());
		if (eventVariables.isEmpty()) {
			throw new RuntimeException("Could not find any global HMI variables with type ending with " + S_EVENT);
		}
		if (eventVariables.size()==1) {
			throw new RuntimeException("Expected only one global HMI variable a type ending with " + S_EVENT);
		}
		return eventVariables.get(1);
	}

	private Entity getGlobalHmiVariableEntity(SysmacProject sysmacProject) {
		Entity rootEntity = sysmacProject.getRootEntity();
		Optional<Entity> globalHmiVariableEntity = rootEntity.findFirst(e -> "Variables".equals(e.getType())
				&& "MemoryVariables".equals(e.getSubtype()) && "Global Variables".equals(e.getName()));
		return globalHmiVariableEntity.orElseThrow(() -> new RuntimeException(
				"Could not find the global variable entity in: " + sysmacProject.getFile().getAbsolutePath()));
	}

	private VariableXmlFile find(SysmacProject sysmacProject, String idToFind) {
		String fileNameToFind = idToFind + XmlFile.XML_EXTENSION;
		String zipEntryName = findZipEntryName(sysmacProject, fileNameToFind);
		VariableXmlFile variableXmlFile = new VariableXmlFile(sysmacProject, zipEntryName);
		return variableXmlFile;
	}

	private String findZipEntryName(SysmacProject sysmacProject, String fileNameToFind) {
		List<String> zipEntryNames = sysmacProject.getZipEntryNames();
		Optional<String> result = zipEntryNames.stream().filter(n -> n.endsWith(fileNameToFind)).findAny();
		String zipEntryName = result.orElseThrow(() -> new RuntimeException("Could not find file: " + fileNameToFind));
		return zipEntryName;
	}

}
