package nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.datatype;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.XmlFile;

public class DataTypeXmlFiles extends ArrayList<DataTypeXmlFile> {

	private static final long serialVersionUID = -5950954209975268527L;

	public DataTypeXmlFiles(List<XmlFile> xmlFiles) {
		for ( XmlFile xmlFile : xmlFiles) {
			boolean isDataTypeXmlFile = xmlFile.containsNode(DataTypeXmlFile.X_PATH);
			if ( isDataTypeXmlFile) {
				DataTypeXmlFile dataTypeXmlFile=new DataTypeXmlFile(xmlFile);
				add(dataTypeXmlFile);
			}
		}
	}
}
