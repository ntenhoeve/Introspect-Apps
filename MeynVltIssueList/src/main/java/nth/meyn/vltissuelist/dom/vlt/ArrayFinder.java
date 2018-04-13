package nth.meyn.vltissuelist.dom.vlt;


public class ArrayFinder {

	private byte[] data;
	private int pos;

	public ArrayFinder(byte[] data) {
		this.data = data;
		pos = 0;
	}

	public ParameterArrayMatcher findNext(
			ParameterArrayMatcher parameterArrayMatcher) {
		do {
			if (parameterArrayMatcher.matches(data, pos)) {
				pos = pos + parameterArrayMatcher.getLength();
				return parameterArrayMatcher;
			} else {
				pos++;
			}
		} while (pos < data.length);
		return null;
	}

	public Byte getNextValue() {
		return data[pos];

	}
}
