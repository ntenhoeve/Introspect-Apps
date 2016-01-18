package nth.meyn.display.translate.dom.translate;

public enum TranslationType {
	DO_NOT_TRANSLATE("Do not translate"), TRANSLATE_FROM_ENGLISH(
			"Translate from English"), TRANSLATE_FROM_ENGLISH_ABBREVIATION(
			"Translate from English abbreviation"), TRANSLATE_AFTER_COMPONENT_CODES("Translate from English after component code(s)");

	private final String string;

	private TranslationType(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}
}
