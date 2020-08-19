package nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.datatype.namespace;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.exception.InvalidNodeNameException;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.exception.MissingAttributeException;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.exception.XmlConversionException;

public class NameSpace {

	static final String ELEMENT_NAME = "DataTypesManifest";
	private static final String NAME = "Name";
	private final String name;
	private final List<NameSpace> children;

	public NameSpace(Node node) {
		verifyIsDataType(node);
		name = getAttributeValue(node, NAME);
		children = getChildren(node);
	}

	private void verifyIsDataType(Node node) {
		if ( !ELEMENT_NAME.equals(node.getNodeName())) {
			throw new InvalidNodeNameException(ELEMENT_NAME);
		}
	}

	private List<NameSpace> getChildren(Node node) {
		NodeList childNodes = node.getChildNodes();
		List<NameSpace> children = new ArrayList<>();
		for (int i = 0; i < childNodes.getLength(); i++) {
			try {
			Node childNode = childNodes.item(i);
			NameSpace child = new NameSpace(childNode);
			children.add(child);
			} catch (XmlConversionException e) {
				// skip
			} catch (MissingAttributeException  e) {
				// skip
			}
		}
		return children;
	}

	private String getAttributeValue(Node node, String attributeName) {
		try {
			return node.getAttributes().getNamedItem(attributeName).getNodeValue();
		} catch (Exception e) {
			throw new MissingAttributeException(attributeName,e);
		}
	}

	public String getName() {
		return name;
	}


	public List<NameSpace> getChildren() {
		return children;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(ELEMENT_NAME);
//		if (name!=null && !name.isBlank()) {
//			result.append(SEPARATOR);
//			result.append(NAME);
//			result.append("=");
//			result.append(name);
//		}
//		if (baseType!=null && !baseType.isBlank()) {
//			result.append(SEPARATOR);
//			result.append(BASE_TYPE);
//			result.append("=");
//			result.append(baseType);
//		}
//		if (arrayType!=null && !arrayType.isBlank()) {
//			result.append(SEPARATOR);
//			result.append(ARRAY_TYPE);
//			result.append("=");
//			result.append(arrayType);
//		}
//		if (length!=null && !length.isBlank()) {
//			result.append(SEPARATOR);
//			result.append(LENGTH);
//			result.append("=");			
//			result.append(length);
//		}
//		if (comment!=null && !comment.isBlank()) {
//			result.append(SEPARATOR);
//			result.append(COMMENT);
//			result.append("=");			
//			result.append(comment.replace("/n", "<br>"));
//		}
//		result.append(NEW_LINE);
//
//		for (NameSpace child : children) {
//			String[] childStrings = child.toString().split("\\n");
//			for (String childString : childStrings) {
//				result.append(INDENT);
//				result.append(childString);
//				result.append(NEW_LINE);
//			}
//		}
		return result.toString();
	}

	public boolean hasChildrenWithName(String name) {
		boolean result = children.stream().anyMatch(d -> d.getName().equals(name));
		return result;
	}

}
