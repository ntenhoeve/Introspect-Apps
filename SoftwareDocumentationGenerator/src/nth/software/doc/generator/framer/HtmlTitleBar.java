package nth.software.doc.generator.framer;


public class HtmlTitleBar   {

//	protected static final String FULL_WIDTH = "100%";
//	protected static final String TITLE_BAR_HEIGHT = "100px";
//	private static final String BUTTON_SIZE = "25px";
//	protected Config config;
//	protected final Element contentElement;
//	private final Element menuElement;
//	protected final TextChapterLevel1 chapterLevel1;
//	protected final TextChapterLevel2 chapterLevel2;
//	private nth.github.page.generator.model.Page previousPage;
//	private nth.github.page.generator.model.Page homePage;
//	private nth.github.page.generator.model.Page nextPage;
//
//	public HtmlTitleBar(Config config, Element menuElement,
//			Element contentElement, TextChapterLevel1 chapterLevel1,
//			TextChapterLevel2 chapterLevel2) {
//		super();
//		this.config = config;
//		this.menuElement = menuElement;
//		this.contentElement = contentElement;
//		this.chapterLevel1 = chapterLevel1;
//		this.chapterLevel2 = chapterLevel2;
//	}
//
//	@Override
//	public Element getElement() {
//		Element html = new Element(ElementType.HTML);
//		StyleBuilder styleBuilder = new StyleBuilder()
//				.backGroundColor(HtmlColor.DARK_GRAY);
//		html.addAttribute(AttributeType.STYLE, styleBuilder.toString());
//
//		// TODO add javascript to redirect to corresponding small page,
//		// something like if (screen.width <= 1024)
//		// window.location.replace("http://www.example.com/1024.html")
//
//		// TODO add javascript so that google can search the pages
//
//		// TODO add search book that opens google:
//		// https://www.google.com/?gfe_rd=cr&ei=DcVWU5PIEoPk-gbAzYDABQ#q=test+site:www.nu.nl
//		createHead(html);
//		createBody(html);
//		return html;
//	}
//
//	protected void createBody(Element html) {
//		Element body = html.addElement(ElementType.BODY);
//		createTitleBar(body);
//
//		Element table = body.addElement(ElementType.TABLE);
//		StyleBuilder styleBuilder = new StyleBuilder();
//		styleBuilder.marginTop(TITLE_BAR_HEIGHT);
//		styleBuilder.width(FULL_WIDTH);
//		table.addAttribute(styleBuilder);
//
//		Element tableRow = table.addElement(ElementType.TR);
//
//		Element menuCell = tableRow.addElement(ElementType.TD);
//
//		styleBuilder = new StyleBuilder();
//		styleBuilder.width("25%");
//		styleBuilder.verticalAlign("top");
//		menuCell.addAttribute(styleBuilder);
//
//		Element menuDiv = menuCell.addElement(ElementType.DIV);
//		menuDiv.addAttribute(AttributeType.ID, "menu");
//		styleBuilder = new StyleBuilder();
//		styleBuilder.backGroundColor(HtmlColor.LIGHT_GRAY);
//		styleBuilder.padding("7px");
//		styleBuilder.borderRadius("10px");
//		menuDiv.addAttribute(styleBuilder);
//		menuDiv.addElement(menuElement);
//
//		Element contentCell = tableRow.addElement(ElementType.TD);
//		styleBuilder = new StyleBuilder();
//		styleBuilder.width("75%");
//		styleBuilder.paddingLeft("10px");
//		styleBuilder.verticalAlign("top");
//		contentCell.addAttribute(styleBuilder);
//
//		Element contentDiv = contentCell.addElement(ElementType.DIV);
//		contentDiv.addAttribute(AttributeType.ID, "content");
//		styleBuilder = new StyleBuilder();
//		styleBuilder.backGroundColor(HtmlColor.LIGHT_GRAY);
//		styleBuilder.padding("7px");
//		styleBuilder.borderRadius("10px");
//		contentDiv.addAttribute(styleBuilder);
//		contentDiv.addElement(contentElement);
//	}
//
//	protected void createTitleBar(Element body) {
//		Element titleBar = body.addElement(ElementType.DIV);
//		titleBar.addAttribute(AttributeType.ID, "titleBar");
//		StyleBuilder styleBuilder = new StyleBuilder();
//		styleBuilder.position(HtmlPosition.FIXED);
//		styleBuilder.top("0");
//		styleBuilder.width(FULL_WIDTH);
//		styleBuilder.height(TITLE_BAR_HEIGHT);
//		styleBuilder.backGroundColor(HtmlColor.DARK_GRAY);
//		titleBar.addAttribute(styleBuilder);
//
//		Element title=titleBar.addElement(ElementType.H1);
//		title.addAttribute(AttributeType.ALIGN, "center");
//		styleBuilder=new StyleBuilder();
//		styleBuilder.color(HtmlColor.LIGHT_GRAY);
//		title.addAttribute(styleBuilder);
//		title.addText(config.getGitHubRepositoryName());
//		title.addAttribute(AttributeType.TITLE,config.getGitHubRepositoryName());
//		
//		Element buttonBar = titleBar.addElement(ElementType.DIV);
//		buttonBar.addAttribute(AttributeType.ID, "buttonBar");
//		styleBuilder = new StyleBuilder();
//		styleBuilder.position(HtmlPosition.ABSOLUTE);
//		styleBuilder.bottom("0");
//		styleBuilder.right("9px");
//		styleBuilder.width("110px");
//		buttonBar.addAttribute(styleBuilder);
//
//		styleBuilder = new StyleBuilder();
//		styleBuilder.floating("right");
//		styleBuilder.backGroundColor(HtmlColor.GRAY);
//		styleBuilder.width(BUTTON_SIZE);
//		styleBuilder.height(BUTTON_SIZE);
//		styleBuilder.marginBottom("5px");
//		styleBuilder.borderRadius("5px");
//		styleBuilder.color(HtmlColor.WHITE);
//		styleBuilder.fontFamily("Arial Black");
//		styleBuilder.fontSize("18px");
//		styleBuilder.textAlign("center");
//		styleBuilder.cursor("pointer");
//		styleBuilder.marginRight("10px");
//
//		if (nextPage != null) {
//			Element a = buttonBar.addElement(ElementType.A);
//			a.addAttribute(AttributeType.HREF,
//					PathFactory.createRemoteGitHubWebSiteUri(config,  nextPage));
//
//			Element nextButton = a.addElement(ElementType.DIV);
//			nextButton.addAttribute(styleBuilder);
//			nextButton.addAttribute(AttributeType.TITLE, "Next page");
//			nextButton.addText("&gt;");
//		}
//
//		if (homePage != null) {
//			Element a = buttonBar.addElement(ElementType.A);
//			a.addAttribute(AttributeType.HREF,
//					PathFactory.createRemoteGitHubWebSiteUri(config, homePage));
//
//			Element homeButton = a.addElement(ElementType.DIV);
//			homeButton.addAttribute(styleBuilder);
//			homeButton.addAttribute(AttributeType.TITLE, "Home page");
//			homeButton.addText("^");
//		}
//
//		if (previousPage != null) {
//			Element a = buttonBar.addElement(ElementType.A);
//			a.addAttribute(AttributeType.HREF, PathFactory
//					.createRemoteGitHubWebSiteUri(config, previousPage));
//
//			Element previousButton = a.addElement(ElementType.DIV);
//			previousButton.addAttribute(styleBuilder);
//			previousButton.addAttribute(AttributeType.TITLE, "Previous page");
//			previousButton.addText("&lt;");
//		}
//
//	}
//
//	public Width getWidth() {
//		return Width.WIDE;
//	}
//
//	protected void createHead(Element html) {
//		Element head = html.addElement(ElementType.HEAD);
//
//		Element meta = head.addElement(ElementType.META);
//		meta.addAttribute("http-equiv", "Content-Type");
//		meta.addAttribute("content", "text/html; charset=UTF-8");
//
//		Element title = head.addElement(ElementType.TITLE);
//		title.addText(this.getTitle());
//	}
//
//	public String getTitle() {
//		StringBuilder title = new StringBuilder();
//		title.append(chapterLevel1.getTitle());
//		if (chapterLevel2 != null) {
//			title.append("-");
//			title.append(chapterLevel2.getTitle());
//		}
//		return title.toString();
//	}
//
//	@Override
//	public String getPath() {
//		String path = PathFactory.createWideHtmlPagePath(config, chapterLevel1,
//				chapterLevel2);
//		return path;
//	}
//
//	@Override
//	public void setPreviousPage(
//			nth.github.page.generator.model.Page previousPage) {
//		this.previousPage = previousPage;
//
//	}
//
//	@Override
//	public void setHomePage(nth.github.page.generator.model.Page homePage) {
//		this.homePage = homePage;
//	}
//
//	@Override
//	public void setNextPage(nth.github.page.generator.model.Page nextPage) {
//		this.nextPage = nextPage;
//	}
//

}
