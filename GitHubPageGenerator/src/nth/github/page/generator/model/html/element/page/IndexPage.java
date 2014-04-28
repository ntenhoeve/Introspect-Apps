package nth.github.page.generator.model.html.element.page;

import java.io.File;

import nth.github.page.generator.Config;
import nth.github.page.generator.model.html.element.Element;
import nth.github.page.generator.model.html.element.page.content.PathFactory;

public class IndexPage extends Page {

	private Config config;

	public IndexPage(Config config) {
		this.config = config;
	}

	@Override
	public File getFile() {
		StringBuilder filePath = new StringBuilder();
		File path = PathFactory.createLocalGitHubWebSiteRepositoryPath(config);
		filePath.append(path.getAbsoluteFile());
		filePath.append("/index.html");
		File file = new File(filePath.toString());
		return file;
	}

	@Override
	public Element getElement() {

		// TODO add js script to redirect to corresponding small or large home
		// page. Something like: if (screen.width <= 1024)
		// window.location.replace("http://www.example.com/1024.html") else
		// window.location.replace("http://www.example.com/index.html")
		return null;
	}

}
