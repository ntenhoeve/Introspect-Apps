package nth.meyn.display.translate.dom.translate;

import java.io.PrintStream;

public class TranslatePrinter {

	public static void print(TranslateRecord translateRecord, PrintStream out) {
		boolean firstValue=true;
		for (String value:translateRecord.getValues()) {
			if (!firstValue) {
				out.print(",");
			}
			firstValue=false;
			if (value!=null) {
				out.print('"');
				out.print(value);
				out.print('"');
			}
		}
		out.print("\n");
	}

}
