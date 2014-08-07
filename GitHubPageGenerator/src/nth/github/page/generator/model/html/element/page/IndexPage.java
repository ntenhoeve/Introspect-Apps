package nth.github.page.generator.model.html.element.page;

import java.io.File;
import java.util.List;

import nth.github.page.generator.Config;
import nth.github.page.generator.PathFactory;
import nth.github.page.generator.model.html.element.AttributeType;
import nth.github.page.generator.model.html.element.Element;
import nth.github.page.generator.model.html.element.ElementType;
import nth.github.page.generator.model.html.element.Html5Element;
import nth.github.page.generator.model.pagefactories.HtmlSmallPageFactory;
import nth.github.page.generator.model.text.TextDocument;

public class IndexPage extends Page {

	private Config config;
	private String fistSmallPageUri;

	public IndexPage(Config config, TextDocument textDocument) {
		this.config = config;
		
		List<Page> smallPages = HtmlSmallPageFactory.createPages(config, textDocument);
		Page firstSmallPage = smallPages.get(0);
		fistSmallPageUri = PathFactory.createRemoteGitHubWebSiteUri(config,firstSmallPage);
		
//		List<Page> widePages = HtmlWidePageFactory.createPages(config, textDocument);
//		Page firstWidePage = widePages.get(0);
//		String wideSmallPageUri = PathFactory.createRemoteGitHubWebSiteUri(config,firstWidePage);
		
	}

	@Override
	public String getPath() {
		StringBuilder filePath = new StringBuilder();
		File path = PathFactory.createLocalGitHubWebSiteRepositoryPath(config);
		filePath.append(path.getAbsoluteFile());
		filePath.append("/index.html");
		return filePath.toString();
	}

	@Override
	public Element getElement() {
		return null;
	}

	@Override
	public String toString() {
		Html5Element html5=new Html5Element();
		
		Element head = html5.addElement(ElementType.HEAD);
		
		Element script = head.addElement(ElementType.SCRIPT);
		script.addAttribute(AttributeType.TYPE, "text/javascript");
		StringBuilder javaScript = new StringBuilder();
		javaScript.append("function redirect() {\n");
		javaScript.append("  if (window.innerWidth <= 1024) {\n");
		javaScript.append("    window.location.replace('");
		javaScript.append(fistSmallPageUri);
		javaScript.append("');\n");
		javaScript.append("  } else {\n");
//		javaScript.append("    window.location.replace(\"http://www.example.com/1024.html\")\n");
		javaScript.append("    alert('wide');\n");
		javaScript.append("  }");
		javaScript.append("}");
//		javaScript.append("   window.onload = redirect;");
		script.addText(javaScript.toString());

		Element body = html5.addElement(ElementType.BODY);
		body.addAttribute(AttributeType.ONLOAD,"redirect();");
		
		return html5.toString();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPreviousPage(
			nth.github.page.generator.model.Page previousPage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHomePage(nth.github.page.generator.model.Page homePage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNextPage(nth.github.page.generator.model.Page nextPage) {
		// TODO Auto-generated method stub
		
	}

}
