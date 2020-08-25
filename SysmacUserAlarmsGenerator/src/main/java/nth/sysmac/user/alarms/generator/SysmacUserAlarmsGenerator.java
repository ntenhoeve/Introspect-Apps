package nth.sysmac.user.alarms.generator;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarmGenerationService;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataTypeService;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.namespace.NameSpace;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.entity.Entity;

public class SysmacUserAlarmsGenerator {

	public static void main(String[] args) {
		File projectFile = Paths.get(
				"C:/Users/nilsth/Documents/My Work Documents/Omron Projects/SysmacAlarmListGenerator/7609DE17-WLD-000-001m2008131503.smc2")
				.toFile();
		SysmacProject sysmacProject = new SysmacProject(projectFile);
//		printNameSpaces(sysmacProject);
//		printDataTypes(sysmacProject);
//		printEntities(sysmacProject);
		UserAlarmGenerationService userAlarmGenerationService=new UserAlarmGenerationService();
		userAlarmGenerationService.generateUserAlarms(sysmacProject);

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

	private static void printDataTypes(SysmacProject sysmacProject) {
		DataTypeService dataTypeService = new DataTypeService();
		List<DataType> dataTypes = dataTypeService.getDataTypes(sysmacProject);
		for (DataType dataType : dataTypes) {
			System.out.println(dataType);
		}
	}

	private static void printEntities(SysmacProject sysmacProject) {
		Entity rootEntity = sysmacProject.getRootEntity();
		System.out.println(rootEntity);
	}

	

}
