package nth.github.page.generator.model.html.element.page;

import java.io.File;

import nth.github.page.generator.Config;
import nth.github.page.generator.model.html.element.AttributeType;
import nth.github.page.generator.model.html.element.Element;
import nth.github.page.generator.model.html.element.ElementType;
import nth.github.page.generator.model.html.element.Html5Element;
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

//		 <script type="text/javascript">
//	        function codeAddress() {
//	            alert('ok');
//	        }
//	        window.onload = codeAddress;
//	        </script>

		
		
		// TODO add js script to redirect to corresponding small or large home
		// page. Something like: if (screen.width <= 1024)
		// window.location.replace("http://www.example.com/1024.html") else
		// window.location.replace("http://www.example.com/index.html")
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
		javaScript.append("  if (screen.width <= 1024) {\n");
		//javaScript.append("    window.location.replace(\"http://www.example.com/1024.html\")\n");
		javaScript.append("    alert('small');\n");
		javaScript.append("  } else {\n");
//		javaScript.append("    window.location.replace(\"http://www.example.com/1024.html\")\n");
		javaScript.append("    alert('wide');\n");
		javaScript.append("  }");
		javaScript.append("}");
//		javaScript.append("   window.onload = redirect;");
		script.addText(javaScript.toString());

		Element body = html5.addElement(ElementType.BODY);
		body.addAttribute(AttributeType.ONLOAD,"redirect();");
		
		System.out.println(html5.toString());
		
		return html5.toString();
	}

}
