package nth.meyn.innovation.intake.dom.projectlaunch;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FormMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;

public class ProjectLaunchService {

	private static final String PRODUCT_CODES_WHOLE_LEG_DEBONER = "4440;4441";
	private static final String PRODUCT_CODES_DVC_GRADING_SYSTEM = "3871";
	private static final String PRODUCT_CODES_JET_STREAM_SCALDER = "0561";
	private static final String PRODUCT_CODES_RAPID = "3580;3581;3582;3583;3584;3585;3586;3587;3588;3589;3591;3592;3593;3594;3595;3596";
	private static final String PRODUCT_CODES_DGS_FOOTPADS = "3865";
	private static final String PRODUCT_CODES_DISTRIBUTION_WEIGHER = "EC-1591;EJ-1591";
	private static final String PRODUCT_CODES_PAN_CONVEYOR = "0630;0632";
	private static final String PRODUCT_CODES_LEG_PROCESSOR_FCF_LINES = "4872";
	private static final String PRODUCT_CODES_PRE_CUTTER_WLD = "4445";
	private static final String PRODUCT_CODES_MLOGIC_DISTRIBUTION_MANAGER = "2200;2209";
	private static List<ProjectLaunch> projectLaunchs = null;

	@GenericReturnType(ProjectLaunch.class)
	public List<ProjectLaunch> allProjectLaunches() {
		if (projectLaunchs == null) {
			projectLaunchs = createProjectLaunches();
		}
		return projectLaunchs;
	}

	@FormMode(FormModeType.showParameterThenClose)
	public void viewProjectLaunch(ProjectLaunch projectLaunch) {
	}
	
	
	@GenericReturnType(ProjectLaunch.class)
	private  List<ProjectLaunch> createProjectLaunches() {
		List<ProjectLaunch> projectLaunchs = new ArrayList<ProjectLaunch>();

		// 2006
		projectLaunchs.add(new ProjectLaunch(0, "Double shackle detection", 2006, 1, 1, "1473"));
		projectLaunchs.add(new ProjectLaunch(0, "Breast cap cutter Pipistrella Physic HS", 2006, 1, 1, "0515"));
		projectLaunchs.add(new ProjectLaunch(0, "Modification syncom rehanger", 2006, 1, 1, "0515;0516"));
		// 2007
		projectLaunchs.add(new ProjectLaunch(0, "Inside/ Outside Birdwasher", 2007, 1, 1, "1810;1812"));
		projectLaunchs.add(new ProjectLaunch(0, "Outside washer", 2007, 1, 1, "1790"));
		projectLaunchs.add(new ProjectLaunch(0, "Bonescan", 2007, 1, 1, "3880"));
		projectLaunchs.add(new ProjectLaunch(0, "Rapid - Mark I", 2007, 5, 1, PRODUCT_CODES_RAPID));
		projectLaunchs.add(new ProjectLaunch(0, "Rehanger Tendon Puller", 2007, 9, 1, "?"));
		projectLaunchs.add(new ProjectLaunch(0, "Rapid - Mark II", 2007, 9, 1, PRODUCT_CODES_RAPID));
		projectLaunchs.add(new ProjectLaunch(602108, "Inside/ Outside Birdwasher (Brush type)", 2007, 11, 1, "1813"));
		projectLaunchs.add(new ProjectLaunch(600308, "M4000 weighing computer", 2007, 11, 1, "2106"));
		// 2008
		projectLaunchs.add(new ProjectLaunch(602218, "Rapid - Improvement descinner", 2008, 1, 1, PRODUCT_CODES_RAPID));
		projectLaunchs.add(new ProjectLaunch(0, "Hockcutter Rehanger Turkey", 2008, 5, 1, "?"));
		projectLaunchs.add(new ProjectLaunch(602206, "Drum Thigh Separator", 2008, 5, 1, "1211"));
		projectLaunchs.add(new ProjectLaunch(602005, "Jet stream scalder", 2008, 5, 1, PRODUCT_CODES_JET_STREAM_SCALDER));
		projectLaunchs.add(new ProjectLaunch(602209, "Line Clearer", 2008, 5, 1, "2651;2655;2656"));// TODO all of them?
		projectLaunchs.add(new ProjectLaunch(602211, "Shackle (HSWS)", 2008, 5, 1, "FK-0640"));
		projectLaunchs.add(new ProjectLaunch(602212, "Whole Leg detection", 2008, 5, 1, "2835"));
		projectLaunchs.add(new ProjectLaunch(602213, "Breast cap/ FH cutter", 2008, 9, 1, "0515"));
		projectLaunchs.add(new ProjectLaunch(602216, "Neck skin cutter", 2008, 9, 1, "0239;0261;2230;2231"));// TODO all of them?
		projectLaunchs.add(new ProjectLaunch(602215, "Carcass trimmer", 2008, 9, 1, "0571"));
		projectLaunchs.add(new ProjectLaunch(602109, "Opener ECP", 2008, 9, 1, "0451"));
		projectLaunchs.add(new ProjectLaunch(602223, "Rapid - Improvement pre-scraper", 2008, 9, 1, PRODUCT_CODES_RAPID));
		projectLaunchs.add(new ProjectLaunch(602407, "Turkey rehanger with hock cutter", 2008, 9, 1, "?"));
		projectLaunchs.add(new ProjectLaunch(602204, "Wing Stretcher", 2008, 11, 1, "0390;0391"));// TODO all of them?
		projectLaunchs.add(new ProjectLaunch(602314, "Flex Line", 2008, 11, 1, "?"));
		projectLaunchs.add(new ProjectLaunch(602228, "Rapid - Hygienic Redesign Deskinner", 2008, 11, 1, PRODUCT_CODES_RAPID));
		projectLaunchs.add(new ProjectLaunch(602307, "Easy Track", 2008, 11, 1, ""));// TODO has always been booked on generic product code 9000, instead of 4000
		// 2009
		projectLaunchs.add(new ProjectLaunch(602308, "Product Counter", 2009, 6, 1, "3150;3151"));
		projectLaunchs.add(new ProjectLaunch(602225, "Rapid - Automatic Harvesting, Butterflies tender out", 2009, 6, 1, PRODUCT_CODES_RAPID));
		projectLaunchs.add(new ProjectLaunch(602321, "Reporting Tools", 2009, 6, 1, "2109"));
		projectLaunchs.add(new ProjectLaunch(602320, "DVC Back side grading", 2009, 6, 1, PRODUCT_CODES_DVC_GRADING_SYSTEM));
		projectLaunchs.add(new ProjectLaunch(603206, "Whole Leg Deboner (2D->3D and finalising issues)", 2009, 11, 1, PRODUCT_CODES_WHOLE_LEG_DEBONER));
		projectLaunchs.add(new ProjectLaunch(602217, "Rapid - finalizing Mark I,II,III", 2009, 11, 1, PRODUCT_CODES_RAPID));
		// 2010
		projectLaunchs.add(new ProjectLaunch(602005, "Jet stream scalder (interconnecting tanks)", 2010, 3, 1, PRODUCT_CODES_JET_STREAM_SCALDER));
		projectLaunchs.add(new ProjectLaunch(602225, "Rapid - Automatic Harvesting, Half filets tender out", 2010, 3, 1, PRODUCT_CODES_RAPID));
		projectLaunchs.add(new ProjectLaunch(602010, "Feet cutter", 2010, 10, 1, ""));
		projectLaunchs.add(new ProjectLaunch(602208, "Leg Processor Obdam Type II ", 2010, 10, 1, ""));
		// 2011
		projectLaunchs.add(new ProjectLaunch(604201, "Rapid - improvement Yield Wish Bone", 2011, 1, 1, PRODUCT_CODES_RAPID));
		projectLaunchs.add(new ProjectLaunch(602221, "Rapid improvement 2nd 180 carousel breast cap solution", 2011, 1, 1, PRODUCT_CODES_RAPID));
		projectLaunchs.add(new ProjectLaunch(602224, "Rapid Short Scraping", 2011, 1, 1, PRODUCT_CODES_RAPID));
		projectLaunchs.add(new ProjectLaunch(602324, "DGS 4.0 Software Upgrade and Parts Grading", 2011, 1, 1, PRODUCT_CODES_DVC_GRADING_SYSTEM));
		// 2012
		projectLaunchs.add(new ProjectLaunch(602309, "DGS Foot pads", 2012, 2, 25, PRODUCT_CODES_DGS_FOOTPADS)); 
		projectLaunchs.add(new ProjectLaunch(602225, "Rapid - Automatic Harvesting, Butterflies tender in", 2012, 9, 19, PRODUCT_CODES_RAPID));
		//2013
		projectLaunchs.add(new ProjectLaunch(602246, "Distribution Weigher", 2013, 1, 24, PRODUCT_CODES_DISTRIBUTION_WEIGHER));
		projectLaunchs.add(new ProjectLaunch(602005, "Wider and improved jet stream scalder", 2013, 10, 8, PRODUCT_CODES_JET_STREAM_SCALDER));
		projectLaunchs.add(new ProjectLaunch(602113, "Improved pan conveyor", 2013, 10, 8, PRODUCT_CODES_PAN_CONVEYOR));
		projectLaunchs.add(new ProjectLaunch(602250, "Leg Processor for FCF lines", 2013, 1, 24, PRODUCT_CODES_LEG_PROCESSOR_FCF_LINES));
		projectLaunchs.add(new ProjectLaunch(603206, "Redesign WLD", 2013, 1, 24, PRODUCT_CODES_WHOLE_LEG_DEBONER));
		projectLaunchs.add(new ProjectLaunch(602239, "Pre Cutter WLD for Physic", 2013, 5, 8, PRODUCT_CODES_PRE_CUTTER_WLD));
		// Waiting for completion of launch stage: 
		projectLaunchs.add(new ProjectLaunch(602325, "MLogic Distribution Manager M5.0", 2013, 12, 10, PRODUCT_CODES_MLOGIC_DISTRIBUTION_MANAGER));
		projectLaunchs.add(new ProjectLaunch(602017, "3-4 Pass Jet Stream Scalder", 2013, 12, 10, PRODUCT_CODES_JET_STREAM_SCALDER));
		projectLaunchs.add(new ProjectLaunch(0, "20 unit Meastro", 2013, 10, 1, "0600-E-20"));
//		projectLaunchs.add(new ProjectLaunch(602610, "ADX440", ?, ?, ?, ???));

		return projectLaunchs;
	}

}
