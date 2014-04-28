package nth.github.page.generator.model.html.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nth.github.page.generator.model.html.element.style.StyleBuilder;



/**
 * Simple implementation of a HTML element.<br>
 * @author nilsth
 *@Depricated Use JSoup instead
 */
public class Element  {
	protected static final String INDENT = " ";
	private static final String EQUALS = "=";
	private static final String LESS_THAN = "<";
	private static final String GREATER_THAN = ">";
	private static final Object QUOTE = "\"";
	private static final Object SLASH = "/";
	protected static final Object NEW_LINE = "\n";

	public static final Element TABLE = null;
	private final String name;
	private Map<String, String> attributes;
	private List<Element> children;

	public List<Element> getChildren() {
		return children;
	}

	public Element(ElementType elementType) {
		this(elementType.toString().toLowerCase());
	}
	
	public Element(String name) {
		this.name = name;
		attributes = new HashMap<>();
		children = new ArrayList<>();
	}

	public void addAttribute(AttributeType attributeType, String value) {
		addAttribute(attributeType.toString().toLowerCase(), value);
	}
	
	public void addAttribute(String name, String value) {
		attributes.put(name, value);
	}


	public void addAttribute( StyleBuilder styleBuilder) {
		addAttribute(AttributeType.STYLE, styleBuilder.toString());
		
	}

	
	public void addElement(Element child) {
		children.add(child);
	}

	public Element addElement(ElementType elementType) {
		Element element=new Element(elementType);
		children.add(element);
		return element;
	}

	
	public List<String> print() {
		List<String> lines = new ArrayList<>();
		StringBuilder line = new StringBuilder();
		// start of element
		line.append(LESS_THAN);
		line.append(name);
		// element attributes
		if (attributes.size() > 0) {
			for (String attributeName : attributes.keySet()) {
				String attributeValue = attributes.get(attributeName);
				line.append(INDENT);
				line.append(attributeName);
				line.append(EQUALS);
				line.append(QUOTE);
				line.append(attributeValue);
				line.append(QUOTE);
			}
		}
		line.append(GREATER_THAN);
		
		if (children.size() == 0) {
			// no children:
			line.append(LESS_THAN);
			line.append(SLASH);
			line.append(name);
			line.append(GREATER_THAN);
			// add begin and end of element line
			lines.add(line.toString());
		} else {
			// has children:

			// add begin of element line
			lines.add(line.toString());
			// add lines of child elements
			for (Element child : children) {
				List<String> childLines = child.print();
				for (String childLine : childLines) {
					lines.add(INDENT + childLine);
				}
			}
			// add end of element line
			line = new StringBuilder();
			line.append(LESS_THAN);
			line.append(SLASH);
			line.append(name);
			line.append(GREATER_THAN);
			lines.add(line.toString());
		}
		return lines;
	}

	

	@Override
	public String toString() {
		List<String> lines = print();
		StringBuilder result = new StringBuilder();
		for (String line : lines) {
			result.append(line);
			result.append(NEW_LINE);
		}
		return result.toString();
	}

	public void addText(String textBlock) {
		addElement(new TextBlock(textBlock));
	}


}
