package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule;

import java.util.Arrays;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;

public abstract class ComponentCodeSkipRule {

	public static final String VALUE_SEPARATOR = ",";

	public abstract boolean appliesTo(ComponentCodeNode next);

	public abstract void goToNext(ComponentCodeNode next);

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
		
		Object[] thisFieldValues=getFieldValues();
		Object[] otherFieldValues=getFieldValues();
		
		boolean deepEquals = Arrays.deepEquals(thisFieldValues, otherFieldValues);
		return deepEquals;
	}

	protected abstract Object[] getFieldValues();
	

}