package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CxConnectionHubs {
	private final Set<CxConnectionHub> connectionHubs;

	public CxConnectionHubs(CxLadderModel cxLadderModel) {
		connectionHubs = create(cxLadderModel);
	}

	public Set<CxConnectionHub> create(CxLadderModel cxLadderModel) {
		Set<CxConnectionHub> connectionHubs = new HashSet<>();
		List<Object> ladderObjectsExceptPowerRails = getLadderObjectsExceptPowerRails(cxLadderModel);
		//for (Object object : cxLadderModel.getContactsCoilsAndInstructions()) {
		for (Object object : ladderObjectsExceptPowerRails) {
			CxLocation location = cxLadderModel.getLocation(object);

			List<Object> inputs = cxLadderModel.findLeftOf(location.getX(), location.getY(), true);
			if (inputs.size() > 0) {
				Object input = inputs.get(0);
				CxLocation inputLocation = cxLadderModel.getLocation(input);
				List<Object> outputs = cxLadderModel.findRightOf(inputLocation.getX(),
						inputLocation.getY(), true);

				if (inputs.size() > 1 || outputs.size() > 1) {
					CxConnectionHub connectionHub = new CxConnectionHub(inputs, outputs);
					connectionHubs.add(connectionHub);
				}
			}

		}
		return connectionHubs;

	}

	private List<Object> getLadderObjectsExceptPowerRails(CxLadderModel cxLadderModel) {
		return cxLadderModel.getGrid().values().stream().filter(o -> !(o instanceof CxLeftPowerRail ||o instanceof CxRightPowerRail )).collect(Collectors.toList());
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

	public Set<CxConnectionHub> getAll() {
		return Collections.unmodifiableSet(connectionHubs);
	}

	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();
		for (CxConnectionHub connectionHub : connectionHubs) {
			reply.append(connectionHub.toString());
			reply.append("\r");
		}
		return reply.toString();
	}
}
