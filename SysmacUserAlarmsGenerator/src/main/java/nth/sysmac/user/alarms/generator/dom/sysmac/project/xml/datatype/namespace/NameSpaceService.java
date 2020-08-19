package nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.datatype.namespace;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.project.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.XmlFile;

public class NameSpaceService {

	public List<NameSpace> getNameSpaces(SysmacProject sysmacProject) {
		List<XmlFile> xmlFiles = sysmacProject.getXmlFiles();
		NameSpaceXmlFiles nameSpaceFiles=new NameSpaceXmlFiles(xmlFiles);
		List<NameSpace> nameSpaces = new ArrayList<>();

		for (NameSpaceXmlFile nameSpaxeXmlFile : nameSpaceFiles) {
			nameSpaces.add(nameSpaxeXmlFile.getNameSpace());
		}
		
		return nameSpaces;
	}


}
