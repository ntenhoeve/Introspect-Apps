package nth.meyn.connect.dom.asset;

import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;

@Description(defaultEnglish="A Meyn line such as a container system, De-feathering line, evisceration line, spin chiller, deboning machines, etc...")
public class MeynEquipment extends Asset {

	private String meynMachineNr;//needed to link (upload) manuals, part lists, (preventive) maintenance schema's

	public MeynEquipment(String name) {
		super(name);
	}

	public String getMeynMachineNr() {
		return meynMachineNr;
	}

	public void setMeynMachineNr(String meynMachineNr) {
		this.meynMachineNr = meynMachineNr;
	}
	
	
}
