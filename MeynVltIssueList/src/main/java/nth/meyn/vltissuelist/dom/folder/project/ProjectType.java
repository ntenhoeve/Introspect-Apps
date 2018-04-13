package nth.meyn.vltissuelist.dom.folder.project;

import java.io.File;

import nth.introspect.generic.util.StringUtil;

public enum ProjectType {
	ARRIVAL_SYSTEM("aanvoer", "arrival", "cas", "container", "departure", "tilt","crate","washer"), DEFEATHERING_LINE(
			"slacht", "defeathering"), SCALDER("jet", "scald", "broeier"), DRIP_LINE(
			"drip"), EVISCERATION_LINE("panklaar", "ev", "thor"), GIBLET_HARVESTING(
			"giblet", "magic","mags"), CHILLING_LINE("koel", "chill"), SPIRAL_FREEZER(
			"spiraal"), SORTING_LINE("sort","weeg"), CUT_UP_LINE("delen", "flex",
			"cut", "physic"), RAPID("rapid"), WHOLE_LEG_DEBONER("wld", "poot"), THIGH_DEBONER(
			"thigh","td"), TRANSPORT_SYSTEM("transport"), UNKOWN("");

	private String[] partialNames;

	ProjectType(String... partialNames) {
		this.partialNames = partialNames;
	}

	@Override
	public String toString() {
		return StringUtil.convertToNormalCase(this.name());
	}

	public static ProjectType valueOf(File file) {
		if (file == null) {
			return ProjectType.UNKOWN;
		}
		String fileName = file.getName().toLowerCase();
		for (ProjectType projectType : values()) {
			if (projectType.matches(fileName)) {
				return projectType;
			}
		}
		System.out.println("Could not find project type for:" + fileName);
		return ProjectType.UNKOWN;
	}

	private boolean matches(String fileName) {
		for (String partialName : partialNames) {
			if (fileName.contains(partialName)) {
				return true;
			}
		}
		return false;
	}
}
