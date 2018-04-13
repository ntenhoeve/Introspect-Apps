package nth.meyn.vltissuelist.dom.vlt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vlt {

	public static final int PARAMETER_515_TERMINAL33 = 515;
	public static final int PARAMETER_804_CONTROL_WORD_TIME_OUT = 804;
	public static final int PARAMETER_1000_CAN_PROTOCOL = 1000;
	// public static final Integer NEXT_VLT_PATTERN = new ArrayMatcher(
	// );
	// public static final ArrayMatcher PARAMETER_804 = new ArrayMatcher(new
	// int[] {
	// 0x24,
	// 0x03,
	// 0x00,
	// 0x00,
	// 0x30,
	// 0x00,
	// 0x00,
	// 0x04,
	// 0x04,
	// 0x00,
	// 0x00,
	// 0x00,
	// 0xFF,
	// 0xFF,
	// 0xFF,
	// 0xFF,
	// 0x31,
	// 0x00,
	// 0x00,
	// 0x03,
	// 0xA0,
	// 0x00,
	// 0x00,
	// 0x00,
	// 0x32,
	// 0x00,
	// 0x00,
	// 0x02,
	// 0x20,
	// 0x00,
	// 0x00,
	// 0x00,
	// 0x34,
	// 0x00,
	// 0x00,
	// 0x02,
	// 0x0C,
	// 0x00,
	// 0x00,
	// 0x00,
	// 0x35,
	// 0x00,
	// 0x00,
	// 0x04,
	// 0x04,
	// 0x00,
	// 0x00,
	// 0x00
	//
	//
	// });
	// public static final ArrayMatcher PARAMETER_514 = new ArrayMatcher(new
	// int[] {
	// 0x03,
	// 0x02,
	// 0x00,
	// 0x00,
	// 0x30,
	// 0x00,
	// 0x00,
	// 0x04,
	// 0x04,
	// 0x00,
	// 0x00,
	// 0x00,
	// 0xFF,
	// 0xFF,
	// 0xFF,
	// 0xFF,
	// 0x31,
	// 0x00,
	// 0x00,
	// 0x03,
	// 0xA0,
	// 0x00,
	// 0x00,
	// 0x00,
	// 0x32,
	// 0x00,
	// 0x00,
	// 0x02,
	// 0x20,
	// 0x00,
	// 0x00,
	// 0x00,
	// 0x34,
	// 0x00,
	// 0x00,
	// 0x02,
	// 0x0C,
	// 0x00,
	// 0x00,
	// 0x00,
	// 0x35,
	// 0x00,
	// 0x00,
	// 0x04,
	// 0x04,
	// 0x00,
	// 0x00,
	// 0x00
	// });
	private final List<Setup> setups;

	public Vlt() {
		setups = new ArrayList<>();
		setups.add(new Setup());
		setups.add(new Setup());
		setups.add(new Setup());
		setups.add(new Setup());
	}

	public Setup getSetup(int setupNr) {
		return setups.get(setupNr);
	}

	public boolean usesDeviceNet() {
		Map<Integer, Byte> parametersSetup1 = setups.get(0).getParameters();
		if (parametersSetup1.containsKey(PARAMETER_1000_CAN_PROTOCOL)) {
			Byte canProtocol = parametersSetup1
					.get(PARAMETER_1000_CAN_PROTOCOL);
			return canProtocol == 1;
		} else {
			return false;
//			throw new RuntimeException("Parameter "
//					+ PARAMETER_1000_CAN_PROTOCOL + " is unknown at this time.");
		}
	}

	public boolean stopsAndTrips() {
		Map<Integer, Byte> parametersSetup1 = setups.get(0).getParameters();
		if (parametersSetup1.containsKey(PARAMETER_804_CONTROL_WORD_TIME_OUT)) {
			Byte canProtocol = parametersSetup1
					.get(PARAMETER_804_CONTROL_WORD_TIME_OUT);
			return canProtocol == 5;
		} else {
			return false;
//			throw new RuntimeException("Parameter "
//					+ PARAMETER_804_CONTROL_WORD_TIME_OUT
//					+ " is unknown at this time.");
		}
	}

	public boolean usesQuickStopFromPlc() {
		Map<Integer, Byte> parametersSetup1 = setups.get(0).getParameters();
		if (parametersSetup1.containsKey(PARAMETER_515_TERMINAL33)) {
			Byte canProtocol = parametersSetup1.get(PARAMETER_515_TERMINAL33);
			return canProtocol !=0;
		} else {
			return false;
//			throw new RuntimeException("Parameter " + PARAMETER_515_TERMINAL33
//					+ " is unknown at this time.");
		}
	}

}
