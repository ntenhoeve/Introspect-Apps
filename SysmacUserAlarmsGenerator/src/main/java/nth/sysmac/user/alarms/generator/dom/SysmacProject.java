package nth.sysmac.user.alarms.generator.dom;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class SysmacProject {

	public SysmacProject(File project) {
		readDataTypes(project);
	}

	private void readDataTypes(File project) {
		try {
			ZipFile zipFile = new ZipFile(project);
			readDataTypes(zipFile);
			zipFile.close();
		} catch (Exception e) {
			throw new RuntimeException("Error reading Sysmac project file: " + project.getAbsolutePath(), e);
		}
	}

	private void readDataTypes(ZipFile zipFile)  {
		Enumeration<? extends ZipEntry> entries = zipFile.entries();

		while (entries.hasMoreElements()) {
			ZipEntry zipEntry = entries.nextElement();
			if (isXmlFile(zipEntry)) {
				try {
					readDataTypes(zipFile, zipEntry);
				} catch (Exception e) {
					// some Sysmac files are faulty, we will skip those
				}
				
			}
		}
	}

	private void readDataTypes(ZipFile zipFile, ZipEntry zipEntry) {
		XmlFile xmlFile = new XmlFile(zipFile, zipEntry);
		if (xmlFile.containsDataTypes()) {
			System.out.println("Found: "+zipEntry.getName());
		}
	}



	private boolean isXmlFile(ZipEntry zipEntry) {
		return zipEntry.getName().endsWith(".xml") && !zipEntry.isDirectory();
	}
}
