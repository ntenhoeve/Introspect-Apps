package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.pattern.Necessity;

public class ResultGroup {

	private final String name;
	private final int first;
	private int index;
	private final Necessity nesessity;

	public ResultGroup(String name, Necessity nesessity, int index) {
		this.name = name;
		this.nesessity = nesessity;
		this.first = index;
		this.index = index;
	}

	public String getName() {
		return name;
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
		return nesessity;
	}

	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder().setSeperator(", ");
		title.append(" groupName: ", name).append(" nesessity: ", nesessity)  .append(", first: ", first).append(", last: ", index);
		return title.toString();
	}
}
