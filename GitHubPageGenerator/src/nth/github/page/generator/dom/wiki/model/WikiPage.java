package nth.github.page.generator.dom.wiki.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import nth.github.page.generator.Config;
import nth.github.page.generator.PathFactory;
import nth.github.page.generator.model.Page;

public abstract class WikiPage extends WikiNodeContainer implements Page {

	private Page previousPage;
	private Page homePage;
	private Page nextPage;
	private final Config config;


	public WikiPage(Config config) {
		this.config = config;
	}


	@Override
	public void setPreviousPage(Page previousPage) {
		this.previousPage = previousPage;

	}

	@Override
	public void setHomePage(Page homePage) {
		this.homePage = homePage;

	}

	@Override
	public void setNextPage(Page nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public String toString() {
		if (getChilderen().size()==0) {
			addNavigationBar(NavigationBarLocation.TOP);
			addContent();
			addNavigationBar(NavigationBarLocation.BOTTOM);
			
		}

		StringBuilder string = new StringBuilder();
		for (WikiNode child : getChilderen()) {
			string.append(child.toString());
		}
		return string.toString();
	}

	public abstract void addContent();

	private void addNavigationBar(NavigationBarLocation location) {
		if (location == NavigationBarLocation.BOTTOM) {
			getChilderen().add(new WikiText("\n"));
			getChilderen().add(new WikiSeparator());
		}
		if (previousPage != null) {
			getChilderen().add(
					new WikiHyperlink("&lt;Previous", previousPage
							.getPath()));
			getChilderen().add(
					new WikiText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"));
		}
		if (homePage != null) {
			getChilderen().add(
					new WikiHyperlink("&lt;Home&gt;", homePage
							.getPath()));
			getChilderen().add(
					new WikiText("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"));
		}
		if (nextPage != null) {
			getChilderen().add(
					new WikiHyperlink("Next&gt;", nextPage
							.getPath()));
		}
		if (location == NavigationBarLocation.TOP) {
			getChilderen().add(new WikiSeparator());
		}
	}

	public Config getConfig() {
		return config;
	}


	public void createFile() {
		String path = PathFactory.createLocalGitHubWikiRepositoryPath(config,getTitle());
		File wikiFile = new File(path);
		String wikiContent = toString();
		try {
			OutputStreamWriter out=new OutputStreamWriter(new FileOutputStream(wikiFile));
			out.write(wikiContent);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
