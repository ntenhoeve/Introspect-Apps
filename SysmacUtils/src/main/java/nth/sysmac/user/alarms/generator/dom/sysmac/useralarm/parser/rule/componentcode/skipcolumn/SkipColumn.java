package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule;

import java.util.Arrays;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.ComponentCodeNode;

/**
 * 
 * <h3>Skipping component numbers when using arrays:</h3>
 * <ul>
 * <li>s=e: skips even columns (of all pages)</li>
 * <li>s=u: skips un-even columns (of all pages)</li>
 * <li>s=2: skips column 2 (of all pages)</li>
 * <li>s=3-5: skips columns 3 until 5 (of all pages)</li>
 * <li>s=30.2: skips column 2 of page 30</li>
 * <li>s=30.2-31.5: skips column 2 of page 30 until column 5 of page 31</li>
 * </ul>
 * You can combine the rules above by separating them with a comma, e.g.:
 * <ul>
 * <li>s=e,5: skips even columns and column 5 (of all pages)</li>
 * <li>s=2,4: skips column 2 and column 4 (of all pages)</li>
 * <li>s=2-4,8: skips column 2-4 and column 8 (of all pages)</li>
 * <li>s=e,3-7,30.2-31.5: skips even columns, columns 3-7, and column 2 of page
 * 30 until column 5 of page 31</li>
 * </ul>
 * 
 * @author nilsth
 *
 *
 */
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