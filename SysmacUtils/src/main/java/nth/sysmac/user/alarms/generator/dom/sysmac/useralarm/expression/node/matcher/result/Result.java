package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Necessity;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.rule.Rule;

public class Result {

	private final int first;
	private int index;
	private final Rule rule;

	public Result(Rule rule, int index) {
		this.rule = rule;
		this.first = index;
		this.index = index;
	}

	public String getGroupName() {
		return rule.getGroup().getName();
	}

	public int getFirst() {
		return first;
	}

	public int getLast() {
		return index;
	}

	public void setLast(int last) {
		this.index = last;
	}

	public Necessity getNesessity() {
		return rule.getGroup().getNecessity();
	}

	public Rule getRule() {
		return rule;
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder().setSeperator(", ");
		title.append(" groupName: ", getGroupName()).append(" nesessity: ", getNesessity())  .append(", first: ", first).append(", last: ", index);
		return title.toString();
	}
}
