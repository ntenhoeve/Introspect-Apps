package nth.innoforce.domain.fileutil;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class FileUtil {

	private static File IBO_FOLDER = new File("S:\\STARDOCUMENTS\\IBO\\Projects");
	private static File R_AND_D_FOLDER = new File("S:\\RENDTOTAAL\\Projecten");
	private static RegExpFileNameFilter CONTAINS_BUSINESS_CASE = new RegExpFileNameFilter(".*business.*case.*");
	private static RegExpFileNameFilter CONTAINS_PROJECT_BRIEF = new RegExpFileNameFilter(".*project.*brief.*");
	private static RegExpFileNameFilter CONTAINS_HIGH_LIGHT_REPORT = new RegExpFileNameFilter(".*high.*light.*report.*");
	private static final SimpleFileNameFilter MATCHES_PROJECTS_FINISHED = new SimpleFileNameFilter("Projects Finished");
	private static final SimpleFileNameFilter MATCHES_PROJECTS_RUNNING = new SimpleFileNameFilter("Projects Running");
	private static final SimpleFileNameFilter MATCHES_PROJECTS_STOPPED = new SimpleFileNameFilter("Projects Stopped");
	private static final SimpleFileNameFilter MATCHES_PROJECTS_ON_HOLD = new SimpleFileNameFilter("Projects On Hold");

	public static File getIboFolder() {
		return IBO_FOLDER;
	}

	public static File getResearchAndDevelopmentFolder() {
		return R_AND_D_FOLDER;
	}

	public static List<File> findProjectFolders(File source, Integer projectNumber) {
		List<File> projectFolders = new ArrayList<File>();
		if (projectNumber == null) {
			return projectFolders;//return empty collection
		}
		String formatedProjectNumber = new DecimalFormat("000000").format(projectNumber);

		

		List<File> parentFolders = new ArrayList<File>();
		parentFolders.addAll(Arrays.asList(source.listFiles(MATCHES_PROJECTS_RUNNING)));
		parentFolders.addAll(Arrays.asList(source.listFiles(MATCHES_PROJECTS_ON_HOLD)));
		parentFolders.addAll(Arrays.asList(source.listFiles(MATCHES_PROJECTS_STOPPED)));
		parentFolders.addAll(Arrays.asList(source.listFiles(MATCHES_PROJECTS_FINISHED)));

		for (File parentFolder : parentFolders) {
			File[] children = parentFolder.listFiles();
			if (children != null) {
				for (File child : children) {
					if (child.isDirectory() && child.getName().contains(formatedProjectNumber)) {
						projectFolders.add(child);
					}
				}
			}
		}
		return projectFolders;
	}

	public static List<File> findProjectFiles(File source) {
		List<File> projectFiles = new ArrayList<File>();
		File[] children = source.listFiles();
		for (File child : children) {
			if (child.isDirectory()) {
				projectFiles.addAll(findProjectFiles(child));
			} else {
				if (CONTAINS_BUSINESS_CASE.accept(child) || CONTAINS_PROJECT_BRIEF.accept(child) || CONTAINS_HIGH_LIGHT_REPORT.accept(child)) {
					projectFiles.add(child);
				}
			}
		}
		return projectFiles;
	}

	public static boolean containsBusinessCase(List<File> projectFiles) {
		for (File projectFile : projectFiles) {
			if (CONTAINS_BUSINESS_CASE.accept(projectFile)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsProjectBrief(List<File> projectFiles) {
		for (File projectFile : projectFiles) {
			if (CONTAINS_PROJECT_BRIEF.accept(projectFile)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsHighLightReport(List<File> projectFiles) {
		for (File projectFile : projectFiles) {
			if (CONTAINS_HIGH_LIGHT_REPORT.accept(projectFile)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsUpToDateHighLightReports(List<File> projectFiles) {
		Calendar cal = Calendar.getInstance();
		// cal.setTime();
		cal.add(Calendar.MONTH, -1);// minus one month
		long lastMonth = cal.getTimeInMillis();
		for (File projectFile : projectFiles) {
			if (CONTAINS_HIGH_LIGHT_REPORT.accept(projectFile) && projectFile.lastModified() > lastMonth) {
				return true;
			}
		}
		return false;

	}

}
