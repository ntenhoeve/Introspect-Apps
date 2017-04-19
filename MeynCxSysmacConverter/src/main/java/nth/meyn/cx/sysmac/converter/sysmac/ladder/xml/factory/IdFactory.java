package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

public class IdFactory {

	private int instanceId;
	private static final String HEX_PREFIX = "0x";

	public IdFactory() {
		instanceId = 2;
	}

	public String createNext() {
		instanceId++;
		return getHex(instanceId);
	}

	public static String getHex(int id) {
		StringBuilder builder = new StringBuilder();
		builder.append(HEX_PREFIX);
		String hexString = Integer.toHexString(id).toUpperCase();
		int leadingZeros = 8 - hexString.length();
		for (int i = 0; i < leadingZeros; i++) {
			builder.append("0");
		}
		builder.append(hexString);
		return builder.toString();
	}

}
