package nth.github.page.generator.wiki.model;

import java.util.List;

public class WikiListItem extends WikiNodeContainer {

	public WikiListItem(List<WikiNode> childen) {
		getChilderen().addAll(childen);
	}

	@Override
	public String toString() {
		StringBuilder string=new StringBuilder();
		string.append("* ");
		for (WikiNode child : getChilderen()) {
			string.append(child.toString());
		}
		return super.toString();
	}
	
	
}
