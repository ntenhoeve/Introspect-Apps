package nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.namespace;

import org.w3c.dom.Node;

import nth.sysmac.user.alarms.generator.dom.sysmac.xml.XmlFile;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.exception.XmlConversionException;

public class NameSpaceXmlFile extends XmlFile {

	public static final String X_PATH = "/" + NameSpace.ELEMENT_NAME;

	public NameSpaceXmlFile(XmlFile xmlFile) {
		super(xmlFile);
	}

	public NameSpace getNameSpace() {
		try {
			Node node = findNode(X_PATH);
			NameSpace nameSpace = new NameSpace(node);
			return nameSpace;
		} catch (Throwable t) {
			throw new XmlConversionException(this, t);
		}

	}
}
