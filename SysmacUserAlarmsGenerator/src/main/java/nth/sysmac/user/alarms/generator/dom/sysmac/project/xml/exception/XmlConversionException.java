package nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.exception;

import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.XmlFile;

public class XmlConversionException extends RuntimeException {

	private static final long serialVersionUID = -5219903677212824955L;

	public XmlConversionException(XmlFile xmlFile, Throwable throwable) {
		super("Error converting: "+xmlFile.getZipPath(), throwable);
	}

}
