package nth.github.page.generator.wiki.model;

import java.util.List;



import nth.github.page.generator.model.text.TextListItem;
import nth.github.page.generator.model.text.TextNode;

public class WikiListItem extends WikiNodeContainer {

	public WikiListItem(TextListItem textListItem) {
		for (TextNode child : textListItem.getChilderen()) {
			WikiPageFactory.populate(this, child);
		}
	}

	@Override
	public String toString() {
		StringBuilder string=new StringBuilder();
		string.append("\n* ");
		for (WikiNode child : getChilderen()) {
			string.append(child.toString());
		}
		return string.toString();
	}
	
	
}
