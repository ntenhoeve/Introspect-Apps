package nth.github.page.generator.dom.wiki.model;

import nth.github.page.generator.model.text.TextChapterLevel2;

public class WikiChapterLevel2 extends WikiNodeContainer{

	
	private final String title;

	public WikiChapterLevel2(TextChapterLevel2 textChapterLevel2) {
		this.title = textChapterLevel2.getTitle();
	}

	@Override
	public String toString() {
		StringBuilder string=new StringBuilder();
		string.append("## ");
		string.append(title);
		string.append(StringConstants.NEW_LINE);
		for (WikiNode child : getChilderen()) {
			string.append(child.toString());
		}
		return title;
	}


	
	
	
}
