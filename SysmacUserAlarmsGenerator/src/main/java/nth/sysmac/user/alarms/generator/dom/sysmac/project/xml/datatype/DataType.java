package nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.datatype;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import nth.reflect.fw.generic.util.TitleBuilder;

@XmlRootElement(name = DataType.ELEMENT_NAME)
public class DataType {
	private static final String INDENT = "  ";
	private static final String NEW_LINE = "\n";
	private static final String SEPARATOR = ", ";
	public static final String ELEMENT_NAME = "DataType";
	private String name;
	private String baseType;
	private String comment;
	private List<DataType> children = new ArrayList<>();

	public String getName() {
		return name;
	}

	@XmlAttribute(name = "Name")
	public void setName(String name) {
		this.name = name;
	}

	public String getBaseType() {
		return baseType;
	}

	@XmlAttribute(name = "BaseType")
	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}

	public String getComment() {
		return comment;
	}

	@XmlAttribute(name = "Comment")
	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<DataType> getChildren() {
		return children;
	}

	@XmlElement(name = DataType.ELEMENT_NAME)
	public void setChildren(List<DataType> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder();
		title.append(DataType.ELEMENT_NAME);
		title.append(SEPARATOR + "name=", name);
		title.append(SEPARATOR + "baseType=", baseType);
		title.append(SEPARATOR + comment, comment);
		title.contact(NEW_LINE);

		for (DataType child : children) {
			String[] childStrings = child.toString().split("\\n");
			for (String childString : childStrings) {
				title.append(INDENT, childString + NEW_LINE);
			}
		}

		return title.toString();
	}

	public boolean hasChildrenWithName(String name) {
		boolean found = getChildren().stream().anyMatch(d -> d.getName().equals(name));
		return found;
	}

}
