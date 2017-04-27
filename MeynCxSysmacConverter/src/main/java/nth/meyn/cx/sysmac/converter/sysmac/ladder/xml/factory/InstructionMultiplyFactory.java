package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

public class InstructionMultiplyFactory extends InstructionCalculationFactory {

	private static final String LONG_SUFFIX = "L";
	private static final String UNSIGNED_SUFFIX = "U";

	
	public InstructionMultiplyFactory() {
		super("*");
	}
	
	@Override
	public List<String> getNameSuffixes() {
		return Arrays.asList( LONG_SUFFIX, UNSIGNED_SUFFIX); 
	}

}
