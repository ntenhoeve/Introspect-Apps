package nth.meyn.vltissuelist.dom.vlt;

import java.util.Arrays;

public class ParameterArrayMatcher extends ArrayMatcher {

	private static int[] sourceData = new int[] { 0x24, 0x03, 0x00, 0x00, 0x30,
			0x00, 0x00, 0x04, 0x04, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF, 0xFF,
			0x31, 0x00, 0x00, 0x03, 0xA0, 0x00, 0x00, 0x00, 0x32, 0x00, 0x00,
			0x02, 0x20, 0x00, 0x00, 0x00, 0x34, 0x00, 0x00, 0x02, 0x0C, 0x00,
			0x00, 0x00, 0x35, 0x00, 0x00, 0x04, 0x04, 0x00, 0x00, 0x00 };

	private final int parameter;
	private final int setup;

	public ParameterArrayMatcher(int parameter, int setup) {
		super(createDataToFind(parameter, setup));
		this.parameter = parameter;
		this.setup = setup;
	}

	private static int[] createDataToFind(int parameter, int setup) {
		if (setup == 0) {
			return setup0ParameterData(parameter);
		} else {
			return setup123ParameterData(parameter, setup);
		}
	}

	private static int[] setup123ParameterData(int paramater, int setup) {
		int[] data = Arrays.copyOfRange(sourceData, 20, sourceData.length);
		data[0] = setup;
		return data;
	}

	private static int[] setup0ParameterData(int paramater) {
		int[] data = Arrays.copyOf(sourceData, sourceData.length);
		data[0] = paramater & 0xFF;
		data[1] = paramater / 256;
		return data;
	}

	public int getParameter() {
		return parameter;
	}

	public int getSetup() {
		return setup;
	}

	
}
