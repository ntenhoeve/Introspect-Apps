package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component;

import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.SkipRules;

/**
 * Almost all alarm messages start with one or component codes so that it can
 * easily be located in the electric schematic or in the field.<br>
 * The format of a component code is: 30M3, where:
 * <ul>
 * <li>30 = page number</li>
 * <li>M = Indicator of type of component, e.g.: F=Fuse, Q=Motor protector,
 * S=Switch, M=Motor, K=Relay, U=electronic device</li>
 * <li>3 = Column number</li>
 * 
 * 
 * @author nilsth
 *
 */
public class ComponentCode extends PageColumn  {

	private static final Regex REGEX_PAGE = new Regex().digit(Repetition.minMax(1, 7));
	private static final Regex REGEX_LETTER = new Regex().letter();
	private static final Regex REGEX_COLUMN=new Regex().digit();
	public static final Regex REGEX=new Regex().append(REGEX_PAGE).append(REGEX_LETTER).append(REGEX_COLUMN);
	public static final Regex REGEX_FIND_PAGE=new Regex().group(REGEX_PAGE).append(REGEX_LETTER).append(REGEX_COLUMN);
	public static final Regex REGEX_FIND_LETTER=new Regex().append(REGEX_PAGE).group(REGEX_LETTER).append(REGEX_COLUMN);
	public static final Regex REGEX_FIND_COLUMN=new Regex().append(REGEX_PAGE).append(REGEX_LETTER).group(REGEX_COLUMN);
	
	private final Character letter;

	public ComponentCode(int page, char letter, int column, SkipRules skipRules) {
		super(page, column, skipRules);
		verifyLetter(letter);
		this.letter = Character.toUpperCase(letter);
	}

	public ComponentCode(int page, char letter, int column) {
		this(page, letter, column, new SkipRules());
	}
	
	private void verifyLetter(char letter) {
		if (!Character.isAlphabetic(letter)) {
			throw new RuntimeException("Not a letter: " + letter);
		}
	}

	public Character getLetter() {
		return letter;
	}
	
	public ComponentCode getDerivedComponentCode(char letter) {
		ComponentCode derivedComponentCode=new ComponentCode(page,letter,column, skipRules );
		return derivedComponentCode;
	}

	@Override
	public String toString() {
		return ""+page + letter + column;
	}

	public void goToNext() {
		PageColumn pageColumn = getNext();
		page=pageColumn.getPage();
		column=pageColumn.getColumn();
	}
	
}
