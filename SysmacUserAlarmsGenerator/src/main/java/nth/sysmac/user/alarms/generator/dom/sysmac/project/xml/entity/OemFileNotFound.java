package nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.entity;

import nth.sysmac.user.alarms.generator.dom.sysmac.project.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.XmlFile;

public class OemFileNotFound extends RuntimeException {

	private static final long serialVersionUID = 6202765385623007411L;

	public OemFileNotFound(SysmacProject sysmacProject) {
		super("Could not find a file with extension: "+XmlFile.OEM_EXTENSION+" in Sysmac project: "+sysmacProject.getPath());
	}

}
