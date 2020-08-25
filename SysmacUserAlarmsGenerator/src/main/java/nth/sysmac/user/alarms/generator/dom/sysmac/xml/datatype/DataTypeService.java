package nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nth.sysmac.user.alarms.generator.dom.sysmac.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.XmlFile;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.entity.Entity;

public class DataTypeService {

	public List<DataType> getDataTypes(SysmacProject sysmacProject) {

		List<Entity> dataTypeEntities = getDataTypeEntities(sysmacProject);

		List<DataType> dataTypes = new ArrayList<>();

		for (Entity dataTypeEntity : dataTypeEntities) {
			String id = dataTypeEntity.getId();

			String nameSpace = dataTypeEntity.getNameSpace();
			try {
			DataTypeXmlFile dataTypeXmlFile = find(sysmacProject, id);

			DataType dataType = dataTypeXmlFile.getDataType();

			dataType.setNameSpace(nameSpace);
			dataTypes.add(dataType);
			} catch (Exception e) {
				//Some id's can not be found. Let's skip them for now
			}
		}

		for (DataType dataType : dataTypes) {
			dataType.addReferencedChilderen(dataTypes);
		}

		return dataTypes;
	}


	private List<Entity> getDataTypeEntities(SysmacProject sysmacProject) {
		Entity rootEntity = sysmacProject.getRootEntity();
		//Entity dataTypeEntity = rootEntity.findDataTypeEntity();
		List<Entity> dataTypeEntities = rootEntity.findAll(e -> "DataType".equals(e.getType()));
		return dataTypeEntities;
	}

	private DataTypeXmlFile find(SysmacProject sysmacProject, String idToFind) {
		String fileNameToFind = idToFind + XmlFile.XML_EXTENSION;
		String zipEntryName = findZipEntryName(sysmacProject, fileNameToFind);
		DataTypeXmlFile dataTypeXmlFile = new DataTypeXmlFile(sysmacProject, zipEntryName);
		return dataTypeXmlFile;
	}

	private String findZipEntryName(SysmacProject sysmacProject, String fileNameToFind) {
		List<String> zipEntryNames = sysmacProject.getZipEntryNames();
		Optional<String> result = zipEntryNames.stream().filter(n -> n.endsWith(fileNameToFind)).findAny();
		String zipEntryName = result.orElseThrow(() -> new RuntimeException("Could not find file: " + fileNameToFind));
		return zipEntryName;
	}

}
