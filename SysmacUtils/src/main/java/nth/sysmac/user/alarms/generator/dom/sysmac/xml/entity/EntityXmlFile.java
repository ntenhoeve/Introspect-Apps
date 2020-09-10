package nth.sysmac.user.alarms.generator.dom.sysmac.xml.entity;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;

import nth.sysmac.user.alarms.generator.dom.sysmac.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.XmlFile;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.exception.XmlConversionException;

/**
 * An {@link XmlFile} that holds information on {@link Entity}s. The entities
 * are stored in a XML file (with file extension .oem) which is stored in a
 * {@link SysmacProject} file which is essentially a zip file.
 * 
 * @author nilsth
 *
 */
public class EntityXmlFile extends XmlFile {

	static final String OEM_EXTENSION = ".oem";

	public EntityXmlFile(SysmacProject sysmacProject) {
		super(findOemFile(sysmacProject));
	}

	private static XmlFile findOemFile(SysmacProject sysmacProject) {
		List<String> zipEntryNames = sysmacProject.getZipEntryNames();
		Optional<String> result = zipEntryNames.stream().filter(n -> n.endsWith(OEM_EXTENSION)).findFirst();
		String zipEntryName = result.orElseThrow(() -> new RuntimeException("Could not find an file with an "
				+ OEM_EXTENSION + " extension in Sysmac project file: " + sysmacProject));
		XmlFile oemFile = sysmacProject.getXmlFile(zipEntryName);
		return oemFile;
	}

	public Entity getRootEntity() {
		Node node = findNode("/sysmac/" + Entity.ELEMENT_NAME);
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
