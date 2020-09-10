package nth.sysmac.user.alarms.generator.dom.sysmac.xml.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.XmlFile;

/**
 * An Sysmac {@link Entity} are references to {@link XmlFile}s within a
 * {@link SysmacProject}
 * 
 * Important Entities: For Project: Entity type="Solution" subtype="" For
 * Device: <Entity type="Device" For communication: <Entity
 * type="CommsSettings" For vriables <Entity type="Variables" For DatatType:
 * Entity, type=Group, subtype=IecData, name=Data, For Libraries: Entity,
 * type=Library, subtype=Nex
 * 
 * @author nilsth
 *
 */
@XmlRootElement(name = Entity.ELEMENT_NAME)
public class Entity {

	private static final String NEW_LINE = "\n";
	static final String ELEMENT_NAME = "Entity";
	private String name;
	private String id;
	private String type;
	private String subtype;
	private String nameSpace;
	private List<Entity> childEntities;

	public String getName() {
		return name;
	}

	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}

	public String getSubtype() {
		return subtype;
	}

	@XmlAttribute
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	@XmlAttribute(name = "namespace")
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public List<Entity> getChildEntities() {
		return childEntities;
	}

	@XmlElementWrapper(name = "ChildEntities")
	@XmlElement(name = Entity.ELEMENT_NAME)
	public void setChildEntities(List<Entity> childEntities) {
		this.childEntities = childEntities;
	}

	@Override
	public String toString() {
		TitleBuilder titleBuilder = new TitleBuilder();
		titleBuilder.append(Entity.ELEMENT_NAME);
		titleBuilder.append(", type=", type);
		titleBuilder.append(", subtype=", subtype);
		titleBuilder.append(", name=", name);
		titleBuilder.append(", namespace=", nameSpace);
		titleBuilder.append(", id=", id);
		titleBuilder.contact(NEW_LINE);

		for (Entity child : childEntities) {
			String[] childStrings = child.toString().split("\\n");
			for (String childString : childStrings) {
				titleBuilder.append("  ", childString + NEW_LINE);
			}
		}

		return titleBuilder.toString();
	}

	public Optional<Entity> findFirst(Predicate<Entity> predicate) {
		if (predicate.test(this)) {
			return Optional.of(this);
		}

		for (Entity child : childEntities) {
			Optional<Entity> result = child.findFirst(predicate);
			if (result.isPresent()) {
				return result;
			}
		}

		return Optional.empty();
	}

	public List<Entity> findAll(Predicate<Entity> predicate) {
		List<Entity> foundEntities = new ArrayList<>();
		if (predicate.test(this)) {
			foundEntities.add(this);
		}

		for (Entity child : childEntities) {
			List<Entity> foundChildren = child.findAll(predicate);
			foundEntities.addAll(foundChildren);
		}

		return foundEntities;
	}

	public Entity findDataTypeEntity() {
		Optional<Entity> result = findFirst(e->"Group".equals(e.getType()) && "IecData".equals(e.getSubtype()) && "Data".equals(e.getName()));
		return result.orElseThrow(()-> new DataTypeEntityNotFound());
	}

}
