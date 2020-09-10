package nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;

import nth.sysmac.user.alarms.generator.dom.sysmac.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.XmlFile;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.exception.XmlConversionException;

public class VariableXmlFile extends XmlFile {

	public static final String X_PATH = "/Variables/VariableGroup" ;

	public VariableXmlFile(SysmacProject sysmacProject, String zipEntryName) {
		super( sysmacProject.getXmlFile(zipEntryName));
	}

	public List<Variable> getVariables() {
		Node node = findNode(X_PATH);
		List<Variable> variables = unmarshallEntity(node);
		return variables;
	}
	
	private List<Variable> unmarshallEntity(Node node) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(VariableGroup.class, Variable.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			VariableGroup variableGroup = (VariableGroup) jaxbUnmarshaller.unmarshal(node);
			return variableGroup.getChildren();
		} catch (Throwable t) {
			throw new XmlConversionException(this, t);
		}
	}
}
