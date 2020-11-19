package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.skip.even;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.CounterNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.skip.SkipCounterNode;

/**
 * See {@link SkipEvenCounterRule}
 * 
 * @author nilsth
 *
 */
public class SkipEvenCounterNode extends SkipCounterNode {

	@Override
	public boolean appliesTo(CounterNode counter) {
		boolean isEven = counter.getValue() % 2 == 0;
		return isEven;
	}

	@Override
	public void goToNext(CounterNode counter) {
		int nextValue = counter.getValue() + 1;
		counter.setValue(nextValue);
	}

	@Override
	protected Object[] getFieldValues() {
		return new Object[0];
	}

}
