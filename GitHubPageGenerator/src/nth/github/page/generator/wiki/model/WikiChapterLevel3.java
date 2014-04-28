package nth.github.page.generator.wiki.model;

import nth.github.page.generator.model.text.TextChapterLevel3;

public class WikiChapterLevel3 extends WikiNodeContainer{

	
	private final String title;

	public WikiChapterLevel3(TextChapterLevel3 textChapterLevel3) {
		this.title = textChapterLevel3.getTitle();
	}

	@Override
	public String toString() {
		StringBuilder string=new StringBuilder();
		string.append("### ");
		string.append(title);
		string.append(StringConstants.NEW_LINE);
		for (WikiNode child : getChilderen()) {
			string.append(child.toString());
		}
		return title;
	}


	
	
	
}
