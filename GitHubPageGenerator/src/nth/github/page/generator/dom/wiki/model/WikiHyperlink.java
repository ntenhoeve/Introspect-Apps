package nth.github.page.generator.dom.wiki.model;

import java.net.URI;
import java.util.List;

import nth.github.page.generator.Config;
import nth.github.page.generator.PathFactory;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;
import nth.github.page.generator.model.text.TextChapterLevel3;
import nth.github.page.generator.model.text.TextDocument;
import nth.github.page.generator.model.text.TextHyperLink;
import nth.github.page.generator.model.text.TextNode;

public class WikiHyperlink extends WikiNode {

	private final String text;
	private final String uri;
	private TextDocument textDocument;
	private Config config;

	public WikiHyperlink(Config config, TextDocument textDocument,
			TextHyperLink textHyperLink) {
		this.config = config;
		this.textDocument = textDocument;
		this.text = textHyperLink.getText();
		this.uri = textHyperLink.getUri();
	}

	public WikiHyperlink(String text, String uri) {
		this.text = text;
		this.uri = uri;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("[");
		string.append(text.trim());
		string.append("]");

		if (uri != null && uri.trim().length() > 0) {
			string.append("(");
			string.append(uri.trim());
			string.append(")");
		} else {
			TextChapterLevel1 textChapterLevel1 = findChapterLevel1();
			TextChapterLevel2 textChapterLevel2 = findChapterLevel2();
			string.append("(");
			string.append(PathFactory.createRemoteGitHubWikiPath(config,
					textChapterLevel1, textChapterLevel2));
			string.append(")");
		}
		return string.toString();
	}

	private TextChapterLevel1 findChapterLevel1() {
		List<TextNode> chapters = textDocument
				.findChilderenOfType(TextChapterLevel1.class);
		for (TextNode chapter : chapters) {
			TextChapterLevel1 textChapterLevel1 = (TextChapterLevel1) chapter;
			if (textChapterLevel1.getTitle().equals(text)) {
				return textChapterLevel1;
			} else if (findChapterLevel2() != null) {
				return textChapterLevel1;
			}
		}
		return null;
	}

	private TextChapterLevel2 findChapterLevel2() {
		List<TextNode> subChapters = textDocument
				.findChilderenOfType(TextChapterLevel2.class);
		for (TextNode subChapter : subChapters) {
			TextChapterLevel2 textChapterLevel2 = (TextChapterLevel2) subChapter;
			if (textChapterLevel2.getTitle().equals(text)) {
				return textChapterLevel2;
			} else if (findChapterLevel3() != null) {
				return textChapterLevel2;
			}
		}
		return null;
	}

	private TextChapterLevel3 findChapterLevel3() {
		List<TextNode> subChapters = textDocument
				.findChilderenOfType(TextChapterLevel3.class);
		for (TextNode subChapter : subChapters) {
			TextChapterLevel3 textChapterLevel3 = (TextChapterLevel3) subChapter;
			if (textChapterLevel3.getTitle().equals(text)) {
				return textChapterLevel3;
			}
		}
		return null;
	}

}
