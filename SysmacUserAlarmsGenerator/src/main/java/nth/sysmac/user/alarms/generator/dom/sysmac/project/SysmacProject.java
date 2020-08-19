package nth.sysmac.user.alarms.generator.dom.sysmac.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.XmlFile;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.datatype.DataType;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.datatype.DataTypeService;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.datatype.namespace.NameSpace;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.datatype.namespace.NameSpaceService;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.entity.Entity;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.entity.EntityService;

public class SysmacProject {

//	TODO 

	// read Entities

	// use jaxb https://www.javatpoint.com/jaxb-unmarshalling-example
	// rename DataType to DataTypeFromXml
	// rename NameSpace to DataTypeManifestFromXml

	// create dom
	// DataTypeNameSpace extends DataTypeNode that has dataTypes
	// DataType

	// {M=40M1 Q=40Q1 K1=80K1 K2=80K2}
//	{Q} motor protection
// {K1} motor contactor not activated

	private final File project;
	private List<DataType> dataTypes = null;
	private List<NameSpace> nameSpaces;
	private List<XmlFile> xmlFiles;
	private Entity rootEntity;

	public SysmacProject(File project) {
		this.project = project;
	}

	public Entity getRootEntity() {
		if (rootEntity == null) {
			EntityService entityService = new EntityService();
			rootEntity = entityService.getRootEntity(this);
		}
		return rootEntity;
	}

	public List<DataType> getDataTypes() {
		if (dataTypes == null) {
			DataTypeService dataTypeService = new DataTypeService();
			dataTypes = dataTypeService.getDataTypes(this);
		}
		return dataTypes;
	}

	public List<NameSpace> getNameSpaces() {
		if (nameSpaces == null) {
			NameSpaceService nameSpaceService = new NameSpaceService();
			nameSpaces = nameSpaceService.getNameSpaces(this);
		}
		return nameSpaces;
	}

	public List<XmlFile> getXmlFiles() {
		if (xmlFiles == null) {
			xmlFiles = readXmlFiles();
		}
		return xmlFiles;
	}

	public List<XmlFile> readXmlFiles() {
		try {
			List<XmlFile> xmlFiles = new ArrayList<>();
			ZipFile zipFile = new ZipFile(project);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();
				if (isXmlFile(zipEntry)) {
					try {
						XmlFile xmlFile = new XmlFile(zipFile, zipEntry);
						xmlFiles.add(xmlFile);
					} catch (Exception e) {
						// some Sysmac files are faulty, we will skip those
					}
				}
			}
			zipFile.close();
			return xmlFiles;
		} catch (Exception e) {
			throw new RuntimeException("Error reading xml files from Sysmac project file: " + project.getAbsolutePath(),
					e);
		}
	}

	public List<ZipEntry> findFiles(String textToFind) {
		try {
			ZipFile zipFile = new ZipFile(project);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			List<ZipEntry> foundFiles = new ArrayList<>();
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();
				if (zipEntryContains(zipFile, zipEntry, textToFind)) {
					foundFiles.add(zipEntry);
				}
			}
			zipFile.close();
			return foundFiles;
		} catch (Exception e) {
			throw new RuntimeException("Error reading files from Sysmac project file: " + project.getAbsolutePath(), e);
		}
	}

	private boolean zipEntryContains(ZipFile zipFile, ZipEntry zipEntry, String textToFind) {
		try {
			InputStream inputStream = zipFile.getInputStream(zipEntry);
			StringBuilder fileContents = readFile(inputStream);
			return fileContents.indexOf(textToFind) > 0;
		} catch (Exception e) {
			throw new RuntimeException("Error reading file " + zipEntry.getName() + " from Sysmac project file: "
					+ project.getAbsolutePath(), e);
		}
	}

	private StringBuilder readFile(InputStream inputStream) throws IOException {
		StringBuilder fileContents = new StringBuilder();
		try (Reader reader = new BufferedReader(
				new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
			int c = 0;
			while ((c = reader.read()) != -1) {
				fileContents.append((char) c);
			}
		}
		return fileContents;
	}

	private boolean isXmlFile(ZipEntry zipEntry) {
		boolean hasXmlExtension = zipEntry.getName().endsWith(".xml");
		boolean hasOemExtension = zipEntry.getName().endsWith(".oem");
		boolean hasCorrectExtension = hasXmlExtension || hasOemExtension;
		return !zipEntry.isDirectory() && hasCorrectExtension;
	}

	public String getPath() {
		return project.getAbsolutePath();
	}

}
