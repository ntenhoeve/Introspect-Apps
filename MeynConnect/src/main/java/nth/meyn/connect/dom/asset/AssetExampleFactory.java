package nth.meyn.connect.dom.asset;

import java.util.List;

public class AssetExampleFactory {

	public static Asset create() {
		
		return createPlant();
	}

	private static Asset createPlant() {
		Asset plant=new Asset("2SFG Storteboom Kornhorn plant");
		List<Asset> departments = plant.getAssets();
		departments.add(createLairageDepartment());
		departments.add(createContainerDepartment());
		departments.add(createDefeatheringDepartment());
		departments.add(createEviscerationDepartment());
		return null;
	}

	private static MeynDepartment createEviscerationDepartment() {
		MeynDepartment eviscerationDepartment=new MeynDepartment("Evisceration");
		List<Asset> lines = eviscerationDepartment.getAssets();
		lines.add(createEviscerationLine(1));
		lines.add(createEviscerationLine(1));
		return eviscerationDepartment;
	}

	private static MeynLine createEviscerationLine(int lineNumber) {
		MeynLine defeatheringLine=new MeynLine("Evisceration "+lineNumber);
		List<Asset> equipment = defeatheringLine.getAssets();
		equipment.add(new MeynEquipment("Venter"));
		equipment.add(new MeynEquipment("Opener"));
		equipment.add(new MeynEquipment("Meastro"));
		equipment.add(new MeynEquipment("Inspection station 1")); //TODO
		equipment.add(new MeynEquipment("Bleeding"));
		equipment.add(new MeynEquipment("Bird Counter"));
		equipment.add(new MeynEquipment("Electrical Stimulation"));
		equipment.add(new MeynEquipment("Scalder"));
		equipment.add(new MeynEquipment("Tail Puller"));
		equipment.add(new MeynEquipment("Pluckers"));
		equipment.add(new MeynEquipment("Hock Pluckers"));
		equipment.add(new MeynEquipment("Plucker Camera"));
		equipment.add(new MeynEquipment("Head Puller"));
		equipment.add(new MeynEquipment("Re-hanger to Evisceration 1"));
		equipment.add(new MeynEquipment("Re-hanger to Evisceration 2"));
		equipment.add(new MeynEquipment("Feet Camera"));
		equipment.add(new MeynEquipment("Feet processing"));
		return defeatheringLine;
	}
	
	private static MeynDepartment createDefeatheringDepartment() {
		MeynDepartment defeatheringDepartment=new MeynDepartment("De-feathering");
		List<Asset> defeatheringLines = defeatheringDepartment.getAssets();
		defeatheringLines.add(createDefeatheringLine());
		return defeatheringDepartment;
	}

	private static MeynLine createDefeatheringLine() {
		MeynLine defeatheringLine=new MeynLine("De-feathering ");
		List<Asset> equipment = defeatheringLine.getAssets();
		equipment.add(new MeynEquipment("DOA counter"));
		equipment.add(new MeynEquipment("Bird Hanging"));
		equipment.add(new MeynEquipment("Quest Stunner"));
		equipment.add(new MeynEquipment("Killer"));
		equipment.add(new MeynEquipment("Bleeding"));
		equipment.add(new MeynEquipment("Bird Counter"));
		equipment.add(new MeynEquipment("Electrical Stimulation"));
		equipment.add(new MeynEquipment("Scalder"));
		equipment.add(new MeynEquipment("Tail Puller"));
		equipment.add(new MeynEquipment("Pluckers"));
		equipment.add(new MeynEquipment("Hock Pluckers"));
		equipment.add(new MeynEquipment("Plucker Camera"));
		equipment.add(new MeynEquipment("Head Puller"));
		equipment.add(new MeynEquipment("Re-hanger to Evisceration 1"));
		equipment.add(new MeynEquipment("Re-hanger to Evisceration 2"));
		equipment.add(new MeynEquipment("Feet Camera"));
		equipment.add(new MeynEquipment("Feet processing"));
		return defeatheringLine;
	}

	private static MeynDepartment createLairageDepartment() {
		MeynDepartment lairageDepartment=new MeynDepartment("Lairage");
		//TODO action methods to display waiting transports
		return lairageDepartment;
	}

	private static MeynDepartment createContainerDepartment() {
		MeynDepartment containerSystem=new MeynDepartment("Container System");
		List<Asset> containerSystems = containerSystem.getAssets();
		containerSystems.add(new MeynEquipment("Container Arrival"));
		containerSystems.add(new MeynEquipment("Controled Atmospheric Stunning"));
		containerSystems.add(new MeynEquipment("Container Tilting"));
		containerSystems.add(new MeynEquipment("Container Washer"));
		containerSystems.add(new MeynEquipment("Controled Departure"));
		return containerSystem;
	}

	
}
