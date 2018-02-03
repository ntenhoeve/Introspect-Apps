package nth.meyn.controls.configurator.dom.function;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.description.Description;

@Description(englishDescription = "A part of equipment that is responsible for one function (e.g. transport, turning, plucking, lifting, temperature control) made out of none or more other functions or components. The functions can be seen as the branches and components can be seen as the leafs. An EquipmentTemplate has a unique reference to a FunctionTemplate. A function name is a single verb e.g. Convey, Turn, Lift, Fill, Heat, Pluck, Transfer")
public class FunctionTemplate implements FunctionOrComponent {
	private String name;
	private String abbreviation;
	private List<FunctionOrComponent> functionAndComponentTemplates;

	public FunctionTemplate() {
		functionAndComponentTemplates = new ArrayList<>();
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public List<FunctionOrComponent> getFunctionAndComponentTemplates() {
		return functionAndComponentTemplates;
	}

	public void setFunctionAndComponentTemplates(List<FunctionOrComponent> functionAndComponentTemplates) {
		this.functionAndComponentTemplates = functionAndComponentTemplates;
	}

	// TODO add attributes
	// TODO add electric schematic template (EPlan macro's)
	// TODO add plc program template
	// TODO add display program template
	// TODO add FAT template doc
	// TODO add IO

}
