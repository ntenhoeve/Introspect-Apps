package nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.datatype;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.project.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.XmlFile;

public class DataTypeService {

	public List<DataType> getDataTypes(SysmacProject sysmacProject) {
		List<XmlFile> xmlFiles = sysmacProject.getXmlFiles();
		DataTypeXmlFiles dataTypeXmlFiles = new DataTypeXmlFiles(xmlFiles);
		List<DataType> dataTypes = new ArrayList<>();

		int i = 0;
		int t = 0;
		for (DataTypeXmlFile dataTypeXmlFile : dataTypeXmlFiles) {
//			try {
				t++;
				DataType dataType = dataTypeXmlFile.getDataType();
				dataTypes.add(dataType);
//			} catch (Exception e) {
//				i++;
//			}
		}
		System.out.println(t);
		System.out.println(i);

		return dataTypes;
	}

}
