package nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.entity;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;

import nth.sysmac.user.alarms.generator.dom.sysmac.project.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.XmlFile;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.exception.XmlConversionException;

/**
 * An {@link XmlFile} that holds information on {@link Entity}s. The entities
 * are stored in a XML file (with file extension .oem) which is stored in a
 * {@link SysmacProject} file which is essentially a zip file.
 * 
 * @author nilsth
 *
 */
public class EntityXmlFile extends XmlFile {

	public EntityXmlFile(SysmacProject sysmacProject) {
		super(findOemFile(sysmacProject));
	}

	private static XmlFile findOemFile(SysmacProject sysmacProject) {
		List<XmlFile> xmlFiles = sysmacProject.getXmlFiles();
		Optional<XmlFile> searchResult = xmlFiles.stream().filter(xmlFile -> xmlFile.hasOemExtension()).findFirst();
		XmlFile oemFile = searchResult.orElseThrow(() -> new OemFileNotFound(sysmacProject));
		return oemFile;
	}

	public Entity getRootEntity() {
		Node node = findNode("/sysmac/Entity");
		Entity rootEntity = unmarshallEntity(node);
		return rootEntity;
	}

	private Entity unmarshallEntity(Node node) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Entity.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Entity rootEntity = (Entity) jaxbUnmarshaller.unmarshal(node);
			return rootEntity;
		} catch (Throwable t) {
			throw new XmlConversionException(this, t);
		}
	}

}
