package nth.meyn.connect.dom.arrival.flock;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class BirdTypeService {
	public List<BirdType> allBirdTypes() {
		// TODO
		return new ArrayList<>();
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public BirdType viewBirdType(BirdType birdType) {
		return birdType;
	}

	@ParameterFactory
	public void createBirdType(BirdType birdType) {
		// TODO
	}

	public void modifyBirdType(BirdType birdType) {
		// TODO
	}

	public void deleteBirdType(BirdType birdType) {
		// TODO
	}

}
