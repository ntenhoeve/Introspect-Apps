package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.skip;

import java.util.Arrays;

import nth.reflect.util.parser.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.CounterNode;


public abstract class SkipCounterNode extends Node{

	public static final String VALUE_SEPARATOR = ",";

	public abstract boolean appliesTo(CounterNode counter);

	public abstract void goToNext(CounterNode counter);

	@Override
	public boolean equals(Object other) {
		// self check
		if (this == other)
			return true;
		// null check
		if (other == null)
			return false;
		// type check and cast
		if (!(getClass()==other.getClass()))
			return false;
		SkipCounterNode otherCounterNode=(SkipCounterNode) other;
		
		Object[] thisFieldValues=getFieldValues();
		Object[] otherFieldValues=otherCounterNode.getFieldValues();
		
		boolean deepEquals = Arrays.deepEquals(thisFieldValues, otherFieldValues);
		return deepEquals;
	}

	protected abstract Object[] getFieldValues();
	

}