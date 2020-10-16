package nth.sysmac.user.alarms.generator;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.OmronBaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarm;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarmGroup;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.UserAlarmGroupFactory;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataTypeService;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.entity.Entity;



/**
 * <h1>{@link SysmacUserAlarmsGenerator}</h1>
 * <h2>Why</h2>
 * The {@link SysmacUserAlarmsGenerator} let's you update the HMI's
 * {@link UserAlarm}s of an {@link SysmacProject}.
 * <p>
 * The goal is to always generate all the {@link UserAlarm}s, while not making any manual
 * changes afterwards so that:
 * <ul>
 * <li>The {@link UserAlarm}s accurately match the PLC program (data types)</li>
 * <li>All {@link UserAlarm} texts are accurate and consistent</li>
 * <li>Creating or updating the {@link UserAlarm}s is less labor intensive</li>
 * </ul>
 * <p>
 * <h2>How</h2>
 * The {@link UserAlarm}s are generated from an exported {@link SysmacProject} file (*.scm).
 * So first step in creating or updating {@link UserAlarm}s is exporting the
 * latest {@link SysmacProject}.
 * <p>
 * Then start the {@link SysmacUserAlarmsGenerator} and select the
 * {@link SysmacProject} file (*.scm).
 * <p>
 * The {@link SysmacUserAlarmsGenerator} will scan the project file for:
 * <ul>
 * <li>a {@link DataType} named "sEvent" in the root of the data structure
 * (without namespace). It will scan this data structure looking for members of
 * {@link OmronBaseType#BOOL}. Each found data type will be converted to an
 * {@link UserAlarm}. Each member that refers to an other
 * {@link BaseType#STRUCT} will automatically become a {@link UserAlarmGroup}.
 * Note that memberNames that start the same (e.g. Transport and TransportVfd)
 * will be put in the same {@link UserAlarmGroup}.</li>
 * <li>a HMI variable name that ends with the \sEvent base type. This variable
 * name combined with the {@link DataType}s will be used to generate the
 * {@link UserAlarm} expression.</li>
 * <li>existing {@link UserAlarm} texts. The {@link SysmacUserAlarmsGenerator}
 * will generate the English alarm texts using the {@link DataType}'s in the
 * {@link SysmacProject}. We assume that an {@link UserAlarm} text of other
 * languages needs to be retranslated if the English text has changed. In this
 * case it will mark the texts in other languages with a ! to indicate that the
 * text might need to be verified by the translator agency. Note that the
 * component code is automatically is updated automatically if this is the only
 * thing that has changed. New alarm texts are marked with a # to indicate that
 * these texts need to be translated by the translator agency. The translator
 * agencies need to remove the ! and # characters once these texts have been
 * processed.</li>
 * </ul>
 * <p>
 * The result will be stored in an M$ Excel file, in the same folder and
 * starting with the same file name as the {@link SysmacProject} file. Open this
 * file and scan all alarms to verify the all {@link UserAlarm} text's and
 * component codes are as expected. If not, fix the texts in the
 * {@link DataType}'s of the {@link SysmacProject} and repeat the process again.
 * <p>
 * Open the {@link SysmacProject} file and delete all alarms in the HMI, when
 * all {@link UserAlarm} texts in the M$ Excel file are correct. Now the new
 * {@link UserAlarm}s can be inserted by importing the M$ Excel file.
 * <p>
 * {@insert AcknowledgeRuleExampleTest}
 * <p>
 * {@insert UserAlarmGroupExampleTest}
 * <p>
 */
public class SysmacUserAlarmsGenerator {

	public static void main(String[] args) {
//		File projectFile = Paths.get(
//				"C:/Users/nilsth/Documents/My Work Documents/Omron Projects/SysmacAlarmListGenerator/7609DE17-WLD-000-001m2008131503.smc2")
//				.toFile();
		
		
		File projectFile = Paths.get(
				"C:/Users/nilsth/Documents/My Work Documents/Omron Projects/SysmacAlarmListGenerator/7609DE19-WLD-000-001m2008281547.smc2")
				.toFile();
		
		SysmacProject sysmacProject = new SysmacProject(projectFile);
//		printNameSpaces(sysmacProject);
//		printDataTypes(sysmacProject);
//		printEntities(sysmacProject);
		UserAlarmGroupFactory userAlarmGroupFactory = new UserAlarmGroupFactory();
		userAlarmGroupFactory.create(sysmacProject);

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
