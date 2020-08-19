package nth.sysmac.user.alarms.generator;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

import nth.sysmac.user.alarms.generator.dom.sysmac.project.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.datatype.DataType;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.datatype.namespace.NameSpace;
import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.entity.Entity;

public class SysmacUserAlarmsGenerator {

	public static void main(String[] args) {
		File projectFile = Paths.get("C:/Users/nilsth/Documents/My Work Documents/Omron Projects/SysmacAlarmListGenerator/7609DE17-WLD-000-001m2008131503.smc2").toFile();
		SysmacProject sysmacProject = new SysmacProject(projectFile);
//		printNameSpaces(sysmacProject);
		printDataTypes(sysmacProject);
//		printEntities(sysmacProject);
		
		
////		List<ZipEntry> files = sysmacProject.findFiles("DataTypes");
//		List<ZipEntry> files = sysmacProject.findFiles("DataTypesManifest");
//		List<ZipEntry> files = sysmacProject.findFiles("Unit\\Diagnostic");
////		List<ZipEntry> files = sysmacProject.findFiles("4cc6f5a2-c800-4963-94d3-8a37d33d40d9");//DataTypesManifest file (contains name spaces) how does it relate to Data T
//		
//		
//		//TODO look at C:\Users\nilsth\Documents\My Work Documents\Omron Projects\SysmacAlarmListGenerator\7609DE17-WLD-000-001m2008131503\a4c7044f-b9fc-4ea1-a6c1-a01780ab2658\a4c7044f-b9fc-4ea1-a6c1-a01780ab2658.oem 
//		//This file has links to name spaces and id's: e.g. see namespace="Unit\Diagnostic" 
//		
//		for (ZipEntry zipEntry : files) {
//			String path = projectFile.getAbsolutePath().replace(".smc2", "\\");
//			System.out.println(path+  zipEntry.getName().replace("/", "\\"));
//		}
	}

	private static void printEntities(SysmacProject sysmacProject) {
		Entity rootEntity = sysmacProject.getRootEntity();
		System.out.println(rootEntity);
	}

	private static void printDataTypes(SysmacProject sysmacProject) {
		List<DataType> dataTypes= sysmacProject.getDataTypes();
		List<DataType> filteredDataTypes = dataTypes.stream().filter(d -> d.hasChildrenWithName("sEvent")).collect(Collectors.toList());
		for (DataType dataType : filteredDataTypes) {
			System.out.println(dataType);
		}
	}

	private static void printNameSpaces(SysmacProject sysmacProject) {
		List<NameSpace> nameSpaces= sysmacProject.getNameSpaces();
		System.out.println(nameSpaces);
	}

}
