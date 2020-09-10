package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.testexpression;

import java.util.Arrays;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component.skiprule.SkipRule;

public enum StringConverters {

	VALID(createValidConverters()), INVALID(createInvalidConverters());

	private final List<StringConverter> stringConverters;

	StringConverters(List<StringConverter> stringConverters) {
		this.stringConverters = stringConverters;
	}

	public List<StringConverter> getAll() {
		return stringConverters;
	}

	/**
	 * {@link StringConverter}s that return (part of) a valid {@link SkipRule}
	 * expression
	 */
	static List<StringConverter> createValidConverters() {
		return Arrays.asList(new NoChangeConverter(), new InsertWhiteSpacesConverter(0, 3),
				new InsertWhiteSpacesConverter(1, 3));
	}

	/**
	 * {@link StringConverter}s that return (part of) a Invalid {@link SkipRule}
	 * expression
	 */
	static List<StringConverter> createInvalidConverters() {
		InsertInvalidCharactersConverter insertInvalidCharactersConverter = new InsertInvalidCharactersConverter();
		EmptyConverter emptyConverter = new EmptyConverter();

		return Arrays.asList(insertInvalidCharactersConverter,
				new InsertWhiteSpacesConverter(0, 3, insertInvalidCharactersConverter),
				new InsertWhiteSpacesConverter(1, 3, insertInvalidCharactersConverter), emptyConverter,
				new InsertWhiteSpacesConverter(1, 5, emptyConverter));
	}

}
