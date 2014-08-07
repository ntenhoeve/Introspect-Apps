package nth.github.page.generator.dom.wiki.model;

import nth.github.page.generator.model.text.TextChapterLevel1;

public class WikiChapterLevel1 extends WikiNodeContainer{

	
	private final String title;

	public WikiChapterLevel1(TextChapterLevel1 textChapterLevel1) {
		title=textChapterLevel1.getTitle();
	}

	@Override
	public String toString() {
		StringBuilder string=new StringBuilder();
		string.append("# ");
		string.append(title);
		string.append(StringConstants.NEW_LINE);
		for (WikiNode child : getChilderen()) {
			string.append(child.toString());
		}
		return title;
	}


	
	
	
}
