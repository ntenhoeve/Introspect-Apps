package nth.github.page.generator.dom.wiki.model;

import nth.github.page.generator.Config;
import nth.github.page.generator.PathFactory;
import nth.github.page.generator.model.text.TextDocument;
import nth.github.page.generator.model.text.TextNode;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;
import nth.github.page.generator.model.text.TextChapterLevel3;
import nth.github.page.generator.model.text.TextHyperLink;
import nth.github.page.generator.model.text.TextList;
import nth.github.page.generator.model.text.TextListItem;
import nth.github.page.generator.model.text.TextNodeContainer;
import nth.github.page.generator.model.text.TextText;

public class WikiPageSubChapter extends WikiPage {
	private TextChapterLevel1 textChapterLevel1;
	private TextChapterLevel2 textChapterLevel2;
	private TextDocument textDocument;

	public WikiPageSubChapter(Config config, TextDocument textDocument,
			TextChapterLevel1 textChapterLevel1,
			TextChapterLevel2 textChapterLevel2) {
		super(config);
		this.textDocument = textDocument;
		this.textChapterLevel1 = textChapterLevel1;
		this.textChapterLevel2 = textChapterLevel2;
	}

	@Override
	public void addContent() {
		for (TextNode child : textChapterLevel2.getChilderen()) {
			WikiPageFactory.populate(getConfig(), textDocument, this, child);
		}
	}

	

	@Override
	public String getPath() {
		return PathFactory.createRemoteGitHubWikiPath(getConfig(), textChapterLevel1,
				textChapterLevel2);
	}

	@Override
	public String getTitle() {
		StringBuilder title=new StringBuilder();
		title.append(textChapterLevel1.getTitle());
		title.append(" ~ ");
		title.append(textChapterLevel2.getTitle());
		return title.toString();
	}
}
