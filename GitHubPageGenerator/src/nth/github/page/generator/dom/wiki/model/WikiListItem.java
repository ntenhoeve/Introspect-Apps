package nth.github.page.generator.dom.wiki.model;

import java.util.List;



import nth.github.page.generator.model.text.TextListItem;
import nth.github.page.generator.model.text.TextNode;

public class WikiListItem extends WikiNodeContainer {

	/**
	 * No need to popute the children because this is done by the {@link WikiPageFactory} since this class extends {@link WikiNodeContainer}
	 * @param textListItem
	 */
	public WikiListItem(TextListItem textListItem) {
	}

	@Override
	public String toString() {
		StringBuilder string=new StringBuilder();
		string.append(StringConstants.NEW_LINE);
		string.append("* ");
		for (WikiNode child : getChilderen()) {
			string.append(child.toString());
		}
		return string.toString();
	}
	
	
}
