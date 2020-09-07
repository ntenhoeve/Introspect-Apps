package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule;

import java.util.Arrays;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.PageColumn;

public abstract class SkipRule {

	public abstract boolean appliesTo(PageColumn pageColumn);

	public abstract PageColumn getNext(PageColumn pageColumn);

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