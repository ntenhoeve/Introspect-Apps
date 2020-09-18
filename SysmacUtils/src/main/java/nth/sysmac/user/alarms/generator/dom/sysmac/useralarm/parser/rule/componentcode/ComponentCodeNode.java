package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import nth.reflect.util.parser.node.Node;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule.MaxColumnRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule.SkipRule;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.skiprule.SkipRules;

/**
 * Represents a Component Code, see {@link ComponentCodeRule}.
 * 
 * @author nilsth
 *
 */
public class ComponentCodeNode extends Node {

	public static final int COLUMN_MIN = 1;
	public static final int COLUMN_MAX = 8;
	private int page;
	private final Character letter;
	private int column;
	private final SkipRules skipRules;

	public ComponentCodeNode(int page, char letter, int column, SkipRules skipRules) {
		verifyPage(page);
		this.page = page;
		verifyLetter(letter);
		this.letter = Character.toUpperCase(letter);
		verifyColumn(column);
		this.column = column;
		this.skipRules = skipRules;
	}

	public ComponentCodeNode(int page, char letter, int column) {
		this(page, letter, column, new SkipRules());
	}

	/**
	 * we do not verify the max column number, that will be fixed by the
	 * {@link MaxColumnRule}
	 * 
	 * @param column
	 */
	private void verifyColumn(int column) {
		if (column < COLUMN_MIN) {
			throw new RuntimeException("Column must be between 1-8, but was: " + column);
		}
	}

	private void verifyPage(int page) {
		if (page < 0) {
			throw new RuntimeException("Page must be >0, but is: " + page);
		}
	}

	private void verifyLetter(char letter) {
		if (!Character.isAlphabetic(letter)) {
			throw new RuntimeException("Not a letter: " + letter);
		}
	}

	public void goToNext() {
		int nextColumn = column + 1;
		int nextPage = page;
		ComponentCodeNode next = new ComponentCodeNode(nextPage, letter, nextColumn, skipRules);

		boolean skipped;
		do {
			skipped = false;
			for (SkipRule skipRule : skipRules) {
				if (skipRule.appliesTo(next)) {
					skipRule.goToNext(next);
					skipped = true;
				}
			}
		} while (!skipped);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Character getLetter() {
		return letter;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public SkipRules getSkipRules() {
		return skipRules;
	}

	public ComponentCodeNode getDerivedComponentCode(char letter) {
		ComponentCodeNode derivedComponentCode = new ComponentCodeNode(page, letter, column, skipRules);
		return derivedComponentCode;
	}

	@Override
	public String toString() {
		return "" + page + letter + column;
	}

	@Override
	public boolean equals(Object that) {
		if (! super.equals(that)) {
			return false;
		}
		ComponentCodeNode thatComponentCodeNode=(ComponentCodeNode) that;
		if (page!=thatComponentCodeNode.getPage()) {
			return false;
		}
		if (letter!=thatComponentCodeNode.getLetter()) {
			return false;
		}
		if (column!=thatComponentCodeNode.getColumn()) {
			return false;
		}
		return true;
		//compare rules???
	}
}
