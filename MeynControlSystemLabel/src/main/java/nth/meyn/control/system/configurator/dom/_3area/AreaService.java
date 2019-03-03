package nth.meyn.control.system.configurator.dom._3area;

import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class AreaService {

	private final AreaFactory areaFactory;

	public AreaService(AreaFactory areaFactory) {
		this.areaFactory = areaFactory;

	}

	public List<Area> allAreas() {
		return areaFactory.getAll();
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public Area viewArea(Area area) {
		return area;
	}

	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void modifyArea(Area area) {

	}

	@ParameterFactory
	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void newArea(Area area) {

	}

}
