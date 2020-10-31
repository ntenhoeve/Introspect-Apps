package nth.sysmac.user.alarms.generator.dom.sysmac.basetype;

import java.util.ArrayList;
import java.util.List;

public class BaseTypeArrayRange implements GoToNextListener {

	private final int min;
	private final int max;
	private final int index;
	private int value;
	private final List<GoToNextListener> goToNextListeners;

	public BaseTypeArrayRange(int min, int max, int index) {
		this.index = index;
		validate(min,max);
		this.min = min;
		this.max = max;
		this.value=min;
		this.goToNextListeners=new ArrayList<>();
	}

	private void validate(int min, int max) {
		if (min<0) {
			throw new RuntimeException("Min value must be >=0");
		}
		if (max<0) {
			throw new RuntimeException("Max value must be >=0");
		}
		if (min>max) {
			throw new RuntimeException("Min must be <=0 than max");
		}
	}
	
	public boolean canGoToNext() {
		boolean canGoToNext=value<max;
		return canGoToNext;
	}
	
	@Override
	public void goToNext(int index) {
		if (index==this.index) {
			if (canGoToNext()) {
				value++;
				invokeListeners(index);
			} else {
				if (index==0) {
					invokeListeners(0);
				}
				invokeListeners(index+1);
				value=min;
			}
		}
	}
	
	public String getValue() {
		return Integer.toString(value);
	}

	public void setValue(int value) {
		this.value = value;
	}

	private void invokeListeners(int index) {
		for (GoToNextListener goToNextListener : goToNextListeners) {
			goToNextListener.goToNext(index);
		}
	}
	
	public void addListener(GoToNextListener goToNextListener) {
		if (goToNextListener!=null) {
			goToNextListeners.add(goToNextListener);
		}
	}
	
}
