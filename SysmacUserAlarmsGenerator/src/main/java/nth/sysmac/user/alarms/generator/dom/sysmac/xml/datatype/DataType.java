package nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseType;

@XmlRootElement(name = DataType.ELEMENT_NAME)
public class DataType {
	private static final String INDENT = "  ";
	private static final String NEW_LINE = "\n";
	private static final String SEPARATOR = ", ";
	public static final String ELEMENT_NAME = "DataType";
	private String name;
	private String baseTypeExpression;
	private String comment;
	private List<DataType> children = new ArrayList<>();
	private String nameSpace;
	private BaseType baseType;

	public String getName() {
		return name;
	}

	@XmlAttribute(name = "Name")
	public void setName(String name) {
		this.name = name;
	}

	public String getBaseTypeExpression() {
		return baseTypeExpression;
	}

	@XmlAttribute(name = "BaseType")
	public void setBaseTypeExpression(String baseTypeExpression) {
		baseType = new BaseType(baseTypeExpression);
		this.baseTypeExpression = baseTypeExpression;
	}

	public BaseType getBaseType() {
		return baseType;
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

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
		for (DataType child : children) {
			child.setNameSpace(nameSpace);
		}
	}

	public String getNameSpace() {
		return nameSpace;
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder();
		title.append(DataType.ELEMENT_NAME);
		title.append(SEPARATOR + "nameSpace=", nameSpace);
		title.append(SEPARATOR + "name=", name);
		title.append(SEPARATOR + "reference=", getReference());
		title.append(SEPARATOR + "baseType=", baseTypeExpression);
		title.append(SEPARATOR + "comment=", comment);
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

	public Optional<DataType> find(Predicate<DataType> predicate) {
		if (predicate.test(this)) {
			return Optional.of(this);
		}
		for (DataType child : children) {
			Optional<DataType> result = child.find(predicate);
			if (result.isPresent()) {
				return result;
			}
		}
		return Optional.empty();
	}

	/**
	 * Adds children for data types that have a reference to other data types e.g.
	 * {@link BaseType#STRUCT}ures or {@link BaseType#ENUM}s
	 * 
	 * @param dataTypes
	 */
	public void addReferencedChilderen(List<DataType> dataTypes) {
		baseType.getReference().ifPresent(reference -> {
			try {
				DataType referedDataType = find(dataTypes, reference);
				children.add(referedDataType);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
		// COULD RESULT IN ENDLESS LOOP!!!
		for (DataType child : children) {
			child.addReferencedChilderen(dataTypes);
		}
	}

	private DataType find(List<DataType> dataTypes, String referenceToFind) {
		for (DataType dataType : dataTypes) {
			Optional<DataType> result=dataType.find(referenceToFind);
			if (result.isPresent()) {
				return result.get();
			}
		}
		throw new RuntimeException("Could not find a DataType with reference: " + referenceToFind);
	}

	private String getReference() {
		TitleBuilder title = new TitleBuilder();
		title.append(nameSpace);
		title.append("\\", name);
		return title.toString();
	}

	public Optional<DataType> find(String referenceToFind) {
		String reference = getReference();
		if (referenceToFind.equals(reference)) {
			return Optional.of(this);
		} else if (referenceToFind.startsWith(reference)) {
			for (DataType child : children) {
				Optional<DataType> result = child.find(referenceToFind);
				if (result.isPresent()) {
					return result;
				}
			}
		}
		return Optional.empty();
	}

	public List<DataTypePath> findPaths(Predicate<DataType> predicate) {
		return findPaths(predicate, new DataTypePath());
	}
	
	public List<DataTypePath> findPaths(Predicate<DataType> predicate, DataTypePath currentPath) {
		List<DataTypePath> foundPaths=new ArrayList<>();
		currentPath.add(this);
		DataTypePath currentPathCopy = new DataTypePath(currentPath);
		if (predicate.test(this)) {
			foundPaths.add(currentPathCopy);
		}
		for (DataType child : children) {
			foundPaths.addAll(child.findPaths(predicate, currentPathCopy));
		}
		return foundPaths;
	}

	public boolean isLeaf() {
		boolean isLeaf=getChildren().isEmpty();
		return isLeaf;
	}

}
