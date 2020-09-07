package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.token.component.skiprule.testexpression;

import nth.reflect.util.random.Random;

public class InsertInvalidCharactersConverter implements StringConverter{

	private static final String INVALID = "<INVALID>";

	@Override
	public String convert(String input) {
		int pos = Random.integer().forMax(input.length()).generate();
		String restult = input.substring(0, pos) + INVALID + input.substring(pos);
		return restult;
	}

}
