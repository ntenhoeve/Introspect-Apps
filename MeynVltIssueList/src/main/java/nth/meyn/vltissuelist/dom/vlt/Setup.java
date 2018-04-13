package nth.meyn.vltissuelist.dom.vlt;

import java.util.HashMap;
import java.util.Map;

public class Setup {
	private final Map<Integer, Byte> parameters;

	public Setup() {
		this.parameters=new HashMap<>();
	}
	
	public Map<Integer, Byte> getParameters() {
		return parameters;
	}

}
