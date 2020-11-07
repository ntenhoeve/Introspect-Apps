package nth.sysmac.user.alarms.generator.dom.sysmac;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import nth.sysmac.user.alarms.generator.dom.sysmac.xml.XmlFile;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.entity.Entity;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.entity.EntityService;

public class SysmacProject {

	private final File file;
	private final List<String> zipEntryNames;
	private final Map<String, XmlFile> xmlFiles;
	private Entity rootEntity;

	public SysmacProject(File file) {
		this.file = file;
		zipEntryNames= findZipEntryNames();
		xmlFiles=new HashMap<>();
	}

	private List<String> findZipEntryNames() {
		try {
			ZipFile zipFile = new ZipFile(file);
			Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
			List<String> zipEntryNames = new ArrayList<>();
			while (zipEntries.hasMoreElements()) {
				ZipEntry zipEntry = zipEntries.nextElement();
				zipEntryNames.add(zipEntry.getName());
			}
			zipFile.close();
			return zipEntryNames;
		} catch (Exception e) {
			throw new RuntimeException("Error reading files from Sysmac file: " + file.getAbsolutePath(), e);
		}
	}
	
	

	public List<String> getZipEntryNames() {
		return zipEntryNames;
	}
	
	public File getFile() {
		return file;
	}

	public Entity getRootEntity() {
		if (rootEntity == null) {
			EntityService entityService = new EntityService();
			rootEntity = entityService.getRootEntity(this);
		}
		return rootEntity;
	}

	public XmlFile getXmlFile(String zipEntryName) {
		if (!xmlFiles.containsKey(zipEntryName)) {
			XmlFile xmlFile = readXmlFile(zipEntryName);
			xmlFiles.put(zipEntryName, xmlFile);
			return xmlFile;
		}
		return xmlFiles.get(zipEntryName);
	}
	
	private XmlFile readXmlFile(String zipEntryName) {
		try {
			ZipFile zipFile=new ZipFile( file);
			ZipEntry zipEntry = zipFile.getEntry(zipEntryName);
			XmlFile xmlFile=new XmlFile(zipFile, zipEntry);
			return xmlFile;
		} catch (Exception e) {
			throw new RuntimeException("Error reading xml file from Sysmac file: " + file.getAbsolutePath(), e);
		}
	}

//	public List<DataType> getDataTypes() {
//		if (dataTypes == null) {
//			DataTypeService dataTypeService = new DataTypeService();
//			dataTypes = dataTypeService.getDataTypes(this);
//		}
//		return dataTypes;
//	}
//
//	public List<NameSpace> getNameSpaces() {
//		if (nameSpaces == null) {
//			NameSpaceService nameSpaceService = new NameSpaceService();
//			nameSpaces = nameSpaceService.getNameSpaces(this);
//		}
//		return nameSpaces;
//	}
//
////	FIXME only create a xmlFileWhenNeeded
//	public List<XmlFile> getXmlFiles() {
//		if (xmlFiles == null) {
//			xmlFiles = readXmlFiles();
//		}
//		return xmlFiles;
//	}
//
//	public List<XmlFile> readXmlFiles() {
//		try {
//			List<XmlFile> xmlFiles = new ArrayList<>();
//			ZipFile zipFile = new ZipFile(file);
//			Enumeration<? extends ZipEntry> entries = zipFile.entries();
//			while (entries.hasMoreElements()) {
//				ZipEntry zipEntry = entries.nextElement();
//				if (isXmlFile(zipEntry)) {
//					try {
//						XmlFile xmlFile = new XmlFile(zipFile, zipEntry);
//						xmlFiles.add(xmlFile);
//					} catch (Exception e) {
//						// some Sysmac files are faulty, we will skip those
//					}
//				}
//			}
//			zipFile.close();
//			return xmlFiles;
//		} catch (Exception e) {
//			throw new RuntimeException("Error reading xml files from Sysmac file file: " + file.getAbsolutePath(),
//					e);
//		}
//	}

	public List<ZipEntry> findFiles(String textToFind) {
		try {
			ZipFile zipFile = new ZipFile(file);
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
			throw new RuntimeException("Error reading files from Sysmac file file: " + file.getAbsolutePath(), e);
		}
	}

	private boolean zipEntryContains(ZipFile zipFile, ZipEntry zipEntry, String textToFind) {
		try {
			InputStream inputStream = zipFile.getInputStream(zipEntry);
			StringBuilder fileContents = readFile(inputStream);
			return fileContents.indexOf(textToFind) > 0;
		} catch (Exception e) {
			throw new RuntimeException("Error reading file " + zipEntry.getName() + " from Sysmac file file: "
					+ file.getAbsolutePath(), e);
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

//	private boolean isXmlFile(ZipEntry zipEntry) {
//		boolean hasXmlExtension = zipEntry.getName().endsWith(".xml");
//		boolean hasOemExtension = zipEntry.getName().endsWith(".oem");
//		boolean hasCorrectExtension = hasXmlExtension || hasOemExtension;
//		return !zipEntry.isDirectory() && hasCorrectExtension;
//	}

	public String getPath() {
		return file.getAbsolutePath();
	}


}
