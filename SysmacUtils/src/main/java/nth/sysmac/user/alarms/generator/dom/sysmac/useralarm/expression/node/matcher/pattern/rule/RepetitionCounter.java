package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.rule;

public class RepetitionCounter {

	private final int min;
	private final int max;
	private int counter;

	public RepetitionCounter(Repetition repetition) {
		this.min=repetition.getMin();
		this.max=repetition.getMax();
		counter=0;
	}
	
	public void next() {
		counter++;
	}
	
	public boolean canGoToNextRule() {
		return counter>=min;
	}

	public boolean mustGoToNextRule() {
		if (max==Integer.MAX_VALUE) {
			return false;
		}
		return counter>=max;
	}

	

}
