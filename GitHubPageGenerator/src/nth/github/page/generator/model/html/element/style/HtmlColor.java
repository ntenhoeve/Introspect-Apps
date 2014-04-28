package nth.github.page.generator.model.html.element.style;

public enum HtmlColor {
	DARK_GRAY("#0F0F0F"), LIGHT_GRAY("#F0F0F0"), GRAY("#909090"), WHITE("#FFFFFF");
	
	private String colorCode;

	private HtmlColor(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorCode() {
		return colorCode;
	}

}
