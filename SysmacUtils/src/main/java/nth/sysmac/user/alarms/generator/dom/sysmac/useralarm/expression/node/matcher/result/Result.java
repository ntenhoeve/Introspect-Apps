package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rule;

public class Result {

	private final int firstNodeIndex;
	private int lastNodeIndex;
	private final Rule rule;

	public Result(Rule rule, int index) {
		this.rule = rule;
		this.firstNodeIndex = index;
		this.lastNodeIndex = index;
	}

	public int getFirstNodeIndex() {
		return firstNodeIndex;
	}

	public int getLastNodeIndex() {
		return lastNodeIndex;
	}

	public void setLastNodeIndex(int last) {
		this.lastNodeIndex = last;
	}

	public Rule getRule() {
		return rule;
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder().setSeperator(", ");
		if (rule.getParent().isPresent()) {
			title.append(" parentId=", rule.getParent().get().hashCode());	
		}
		title.append(" rule=",rule) .append(", firstNodeIndex: ", firstNodeIndex).append(", last: ", lastNodeIndex);
		return title.toString();
	}
}
