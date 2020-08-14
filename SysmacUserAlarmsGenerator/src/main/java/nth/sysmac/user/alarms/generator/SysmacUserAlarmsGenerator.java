package nth.sysmac.user.alarms.generator;

import java.io.File;
import java.nio.file.Paths;

import nth.sysmac.user.alarms.generator.dom.SysmacProject;

public class SysmacUserAlarmsGenerator {

	public static void main(String[] args) {
		File projectFile = Paths.get("C:/Users/nilsth/Documents/My Work Documents/Omron Projects/SysmacAlarmListGenerator/7609DE17-WLD-000-001m2008131503.smc2").toFile();
		new SysmacProject(projectFile);

	}

}
