package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.max.SkipMaxColumn;

@SuppressWarnings("rawtypes")
public class SkipColumns implements Iterable<SkipColumnNode> {

	private final List<SkipColumnNode> skipColumnNodes;

	public SkipColumns() {
		skipColumnNodes = new ArrayList<>();
		skipColumnNodes.add(new SkipMaxColumn());
	}

	public void add(SkipColumnNode skipColumnNode) {
		skipColumnNodes.add(skipColumnNode);
	}

	public void addAll(List<SkipColumnNode> rules) {
		skipColumnNodes.addAll(rules);
	}

	@Override
	public Iterator<SkipColumnNode> iterator() {
		return Collections.unmodifiableList(skipColumnNodes).iterator();
	}

}
