package nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import nth.reflect.fw.generic.util.TitleBuilder;

@XmlRootElement(name = Variable.ELEMENT_NAME)
public class Variable {
	private static final String NEW_LINE = "\n";
	private static final String SEPARATOR = ", ";
	public static final String ELEMENT_NAME = "Variable";
	private String name;
	private String dataTypeName;

	public String getName() {
		return name;
	}

	@XmlAttribute(name = "Name")
	public void setName(String name) {
		this.name = name;
	}


	public String getDataTypeName() {
		return dataTypeName;
	}

	@XmlAttribute(name = "DataTypeName")
	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	
	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder();
		title.append(Variable.ELEMENT_NAME);
		title.append(SEPARATOR + "name=", name);
		title.append(SEPARATOR + "dataTypeName=", dataTypeName);
		title.contact(NEW_LINE);
		return title.toString();
	}


}
