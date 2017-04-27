package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

public class InstructionMinusFactory extends InstructionCalculationFactory {

	private static final String LONG_SUFFIX = "L";
	
	public InstructionMinusFactory() {
		super("-");
	}
	
	@Override
	public List<String> getNameSuffixes() {
		return Arrays.asList( LONG_SUFFIX); 
	}

}
