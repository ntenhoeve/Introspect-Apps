package nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import nth.reflect.fw.generic.util.TitleBuilder;

@XmlRootElement(name = VariableGroup.ELEMENT_NAME)
public class VariableGroup {
	public static final String ELEMENT_NAME = "VariableGroup";
	private static final String INDENT = "  ";
	private static final String NEW_LINE = "\n";
	private List<Variable> children = new ArrayList<>();

	public List<Variable> getChildren() {
		return children;
	}

	@XmlElement(name = Variable.ELEMENT_NAME)
	public void setChildren(List<Variable> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		TitleBuilder title=new TitleBuilder();
		for (Variable child : children) {
			String[] childStrings = child.toString().split("\\n");
			for (String childString : childStrings) {
				title.append(INDENT, childString + NEW_LINE);
			}
		}
		return title.toString();
	}




}
