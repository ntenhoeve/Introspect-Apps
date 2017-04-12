package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.COIL;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.CONTACT;

public class CxConnectionHubs {
	private final Set<CxConnectionHub> connectionHubs;

	public CxConnectionHubs(CxLadderModel cxLadderModel) {
		connectionHubs = create(cxLadderModel);
	}

	public Set<CxConnectionHub> create(CxLadderModel cxLadderModel) {
		Set<CxConnectionHub> connectionHubs = new HashSet<>();
		for (Object object : cxLadderModel.getContactsAndCoils()) {
			CxLocation location = cxLadderModel.getLocation(object);
			
			List<Object> objectsLeftOf = cxLadderModel.findLeftOf(location.getX(), location.getY());
			if (objectsLeftOf.size() > 1) {
				CxConnectionHub connectionHub = createLeftOf(cxLadderModel, location,
						objectsLeftOf);
				connectionHubs.add(connectionHub);
			}
			List<Object> objectsRightOf = cxLadderModel.findRightOf(location.getX(),
					location.getY());
			if (objectsRightOf.size() > 1) {
				CxConnectionHub connectionHub = createRightOf(cxLadderModel, location,
						objectsRightOf);
				connectionHubs.add(connectionHub);
			}

		}
		return connectionHubs;

	}

	private CxConnectionHub createRightOf(CxLadderModel cxLadderModel, CxLocation location,
			List<Object> outputs) {
		Object output = outputs.get(0);
		CxLocation outputLocation = cxLadderModel.getLocation(output);
		List<Object> inputs = cxLadderModel.findLeftOf(outputLocation.getX(),
				outputLocation.getY());
		return new CxConnectionHub(inputs, outputs);
	}

	private CxConnectionHub createLeftOf(CxLadderModel cxLadderModel, CxLocation location,
			List<Object> inputs) {
		Object input = inputs.get(0);
		CxLocation inputLocation = cxLadderModel.getLocation(input);
		List<Object> outputs = cxLadderModel.findRightOf(inputLocation.getX(),
				inputLocation.getY());
		return new CxConnectionHub(inputs, outputs);
	}

	public CxConnectionHub findOutput(Object cxLadderObject) {
		for (CxConnectionHub connectionHub : connectionHubs) {
			if (connectionHub.getOutputs().contains(cxLadderObject)) {
				return connectionHub;
			}
		}
		return null;
	}
	
	public CxConnectionHub findInput(Object cxLadderObject) {
		for (CxConnectionHub connectionHub : connectionHubs) {
			if (connectionHub.getInputs().contains(cxLadderObject)) {
				return connectionHub;
			}
		}
		return null;
	}

	public  Set<CxConnectionHub> getAll() {
		return Collections.unmodifiableSet(connectionHubs);
	}

}
