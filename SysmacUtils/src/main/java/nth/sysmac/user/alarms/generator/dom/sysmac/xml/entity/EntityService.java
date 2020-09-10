package nth.sysmac.user.alarms.generator.dom.sysmac.xml.entity;

import nth.sysmac.user.alarms.generator.dom.sysmac.SysmacProject;

public class EntityService {

	public Entity getRootEntity(SysmacProject sysmacProject) {
		EntityXmlFile entityXmlFile=new EntityXmlFile(sysmacProject); 
		Entity rootEntity = entityXmlFile.getRootEntity();
		return rootEntity;
	}

}
