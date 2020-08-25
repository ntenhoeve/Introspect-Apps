package nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;

import nth.sysmac.user.alarms.generator.dom.sysmac.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.XmlFile;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.exception.XmlConversionException;

public class DataTypeXmlFile extends XmlFile {

	public static final String X_PATH = "/data/" + DataType.ELEMENT_NAME;

	public DataTypeXmlFile(SysmacProject sysmacProject, String zipEntryName) {
		super(sysmacProject.getXmlFile(zipEntryName));
	}

	public DataType getDataType() {
		Node node = findNode(X_PATH);
		DataType dataType = unmarshallEntity(node);
		return dataType;
	}

	private DataType unmarshallEntity(Node node) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(DataType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			DataType dataType = (DataType) jaxbUnmarshaller.unmarshal(node);
			return dataType;
		} catch (Throwable t) {
			throw new XmlConversionException(this, t);
		}
	}
}
