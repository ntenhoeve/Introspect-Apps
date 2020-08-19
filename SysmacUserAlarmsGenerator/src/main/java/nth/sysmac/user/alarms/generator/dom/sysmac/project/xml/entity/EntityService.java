package nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.entity;

import nth.sysmac.user.alarms.generator.dom.sysmac.project.SysmacProject;

public class EntityService {

	public Entity getRootEntity(SysmacProject sysmacProject) {
		EntityXmlFile entityXmlFile=new EntityXmlFile(sysmacProject); 
		Entity rootEntity = entityXmlFile.getRootEntity();
		return rootEntity;
	}

}
