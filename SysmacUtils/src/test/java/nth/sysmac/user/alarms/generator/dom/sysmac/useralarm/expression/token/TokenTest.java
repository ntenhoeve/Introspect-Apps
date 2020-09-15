package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import nth.reflect.util.parser.token.parser.Rest;
import nth.reflect.util.parser.token.parser.Token;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.Dash;

class TokenTest {

	private static final String REST = "rest";
	private static final String DASH = "-";

	@Test
	void testToString_givenTokenWithValue_returnsValueBetweenBoxBrackets() {
		Token given=new Token(new Rest(), REST);
		assertThat(given.toString() ).isEqualTo("["+REST+"]");
	}
	
	@Test
	void testToString_givenTokenWithoutValue_returnsValueBetweenBraces() {
		Token given=new Token(new Dash(), DASH);
		assertThat(given.toString() ).isEqualTo("{"+Dash.class.getSimpleName()+"}");
	}

}
