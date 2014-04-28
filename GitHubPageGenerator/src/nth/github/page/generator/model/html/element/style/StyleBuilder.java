package nth.github.page.generator.model.html.element.style;

public class StyleBuilder {

	private final StringBuilder stringBuilder;

	public StyleBuilder() {
		stringBuilder = new StringBuilder();
	}

	@Override
	public String toString() {
		return stringBuilder.toString();
	}

	public StyleBuilder backGroundColor(HtmlColor color) {
		append("background-color", color.getColorCode());
		return this;
	}

	private void append(String name, String value) {
		stringBuilder.append(name);
		stringBuilder.append(":");
		stringBuilder.append(value);
		stringBuilder.append(";");
	}

	public StyleBuilder marginTop(String distance) {
		append("margin-top", distance);
		return this;
	}

	public StyleBuilder width(String width) {
		append("width", width);
		return this;
	}

	public StyleBuilder verticalAlign(String align) {
		append("vertical-align", align);
		return this;
	}

	public StyleBuilder padding(String distance) {
		append("padding", distance);
		return this;
	}

	public StyleBuilder borderRadius(String radius) {
		append("-webkit-border-radius", radius);
		append("-khtml-border-radius", radius);
		append("-moz-border-radius", radius);
		append("-ms-border-radius", radius);
		append("-o-border-radius", radius);
		append("border-radius", radius);
		return this;
	}

	public StyleBuilder paddingLeft(String distance) {
		append("padding-left", distance);
		return this;
	}

	public StyleBuilder position(HtmlPosition position) {
		append("position", position.toString().toLowerCase());
		return this;
	}

	public StyleBuilder top(String distance) {
		append("top", distance);
		return this;
	}

	public StyleBuilder height(String height) {
		append("height", height);
		return this;
	}

	public StyleBuilder bottom(String distance) {
		append("bottom", distance);
		return this;
	}

	public StyleBuilder right(String distance) {
		append("right", distance);
		return this;
	}

	public StyleBuilder floating(String floatDirection) {
		append("float", floatDirection);
		return this;
	}

	public StyleBuilder fontFamily(String fontFamilie) {
		append("font-family", "'" + fontFamilie + "'");
		return this;
	}

	public StyleBuilder fontSize(String fontSize) {
		append("font-size", fontSize);
		return this;
	}

	public StyleBuilder textAlign(String textAlign) {
		append("text-align", textAlign);
		return this;
	}

	public StyleBuilder color(HtmlColor color) {
		append("color", color.getColorCode());
		return this;
	}

	public StyleBuilder marginLeft(String distance) {
		append("margin-left", distance);
		return this;
	}

	public StyleBuilder marginBottom(String distance) {
		append("margin-bottom", distance);
		return this;
	}

	public StyleBuilder cursor(String cursorType) {
		append("cursor", cursorType);
		return this;
	}

	public StyleBuilder marginRight( String distance) {
		append("margin-right", distance);
		return this;		
	}

}
