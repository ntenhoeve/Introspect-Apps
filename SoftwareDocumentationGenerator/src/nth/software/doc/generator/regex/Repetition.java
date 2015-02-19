package nth.software.doc.generator.regex;

public class Repetition {

	private String regex;

	private Repetition(String regex) {
		this.regex = regex;
	}

	public static Repetition zeroOrMoreTimes() {
		return new Repetition("*");
	}

	public static Repetition oneOrMoreTimes() {
		return new Repetition("+");
	}

	@Override
	public String toString() {
		return regex;
	}
	
	

}
