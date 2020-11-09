package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter;

import nth.reflect.util.parser.node.Node;

public class CounterArrayNode extends Node {

	private final int array;
	
	public CounterArrayNode(int array) {
		this.array=array;
	}

	public int getArray() {
		return array;
	}

	@Override
	public boolean equals(Object that) {

		if (!super.equals(that)) {
			return false;
		}
		CounterArrayNode thatCounterArrayNode = (CounterArrayNode) that;
		boolean equals = array==thatCounterArrayNode.getArray();
		return equals;
	}

	@Override
	public String toString() {
		return CounterArrayNode.class.getSimpleName()+" array="+array;
	}

}
