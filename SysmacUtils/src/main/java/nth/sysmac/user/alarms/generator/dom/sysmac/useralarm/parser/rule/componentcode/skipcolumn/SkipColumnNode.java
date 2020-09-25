package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skipcolumn.max.SkipMaxColumn;

public class SkipColumnNode implements Iterable<SkipColumn> {

	private final List<SkipColumn> skipColumns;

	public SkipColumnNode() {
		skipColumns = new ArrayList<>();
		skipColumns.add(new SkipMaxColumn());
	}

	public void add(SkipColumn skipColumn) {
		skipColumns.add(skipColumn);
	}

	public void addAll(List<SkipColumn> rules) {
		skipColumns.addAll(rules);
	}

	@Override
	public Iterator<SkipColumn> iterator() {
		return Collections.unmodifiableList(skipColumns).iterator();
	}

}
