package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node;

import java.util.function.Predicate;

import com.google.common.base.Optional;

import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.Rest;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.TokenDefinition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.token.impl.TokenDefinitions;

public class TokenNodePredicate implements Predicate<Node> {

	private final TokenDefinition tokenDefinitionToFind;
	private final Optional<Regex> regex;

	public TokenNodePredicate(TokenDefinition tokenDefinitionToFind, Optional<Regex> regex) {
		this.tokenDefinitionToFind = tokenDefinitionToFind;
		this.regex = regex;
	}

	public TokenNodePredicate(TokenDefinition tokenDefinitionToFind) {
		this(tokenDefinitionToFind, Optional.absent());
	}

	@Override
	public boolean test(Node node) {
		if (!(node instanceof TokenNode)) {
			return false;
		}
		TokenNode tokenNode = (TokenNode) node;
		TokenDefinition tokenDefinition = tokenNode.getDefinition();
		boolean identicalDataTypes = tokenDefinition.getClass() == tokenDefinitionToFind.getClass();
		if (!identicalDataTypes) {
			return false;
		}

		if (regex.isPresent()) {
			boolean matches = regex.get().hasMatchIn(tokenNode.getValue());
			return matches;
		} else {
			return true;
		}
	}

	public static TokenNodePredicate openBrace() {
		return new TokenNodePredicate(TokenDefinitions.OPEN_BRACE.get());
	}

	public static TokenNodePredicate closeBrace() {
		return new TokenNodePredicate(TokenDefinitions.CLOSE_BRACE.get());
	}

	public static TokenNodePredicate whiteSpace() {
		return new TokenNodePredicate(TokenDefinitions.WHITESPACE.get());
	}

	public static TokenNodePredicate rest(Regex regex) {
		return new TokenNodePredicate(new Rest(), Optional.of(regex));
	}

}
