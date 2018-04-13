package nth.meyn.vltissuelist.dom.vlt;

public class ArrayMatcher {

	private final int[] dataToFind;

	public ArrayMatcher(int[] dataToFind) {
		this.dataToFind = dataToFind;
	}

	public boolean matches(byte[] data, int index) {
		int endPos = index + dataToFind.length;
		if (endPos > data.length) {
			return false;
		}
		for (int offset = 0; offset < dataToFind.length; offset++) {
			if ((data[index+offset] & 0xFF) != dataToFind[offset]) {
				return false;
			}
		}
		return true;
	}

	public int getLength() {
		return dataToFind.length;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		for (int value : dataToFind) {
			string.append(String.format("%02X", value));
			string.append(" \n");
		}
		return string.toString();
	}
}
