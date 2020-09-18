package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.predicate;

import java.util.function.Predicate;

import com.google.common.base.Optional;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.parser.token.parser.Rest;
import nth.reflect.util.parser.token.parser.TokenRule;
import nth.reflect.util.regex.Regex;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.Equal;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.UnsignedInteger;

public class TokenNodePredicate implements Predicate<Node> {

	private final TokenRule tokenRuleToFind;
	private final Optional<Regex> regex;

	public TokenNodePredicate(TokenRule ruleToFind, Optional<Regex> regex) {
		this.tokenRuleToFind = ruleToFind;
		this.regex = regex;
	}

	public TokenNodePredicate(TokenRule tokenRuleToFind) {
		this(tokenRuleToFind, Optional.absent());
	}

	@Override
	public boolean test(Node node) {
		if (!(node instanceof TokenNode)) {
			return false;
		}
		TokenNode tokenNode = (TokenNode) node;
		TokenRule tokenRule = tokenNode.getRule();
		boolean identicalDataTypes = tokenRule.getClass() == tokenRuleToFind.getClass();
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
		return new TokenNodePredicate(TokenRules.OPEN_BRACE.get());
	}

	public static TokenNodePredicate closeBrace() {
		return new TokenNodePredicate(TokenRules.CLOSE_BRACE.get());
	}

	public static TokenNodePredicate whiteSpace() {
		return new TokenNodePredicate(TokenRules.WHITESPACE.get());
	}

	public static TokenNodePredicate rest(Regex regex) {
		return new TokenNodePredicate(new Rest(), Optional.of(regex));
	}

	public static Predicate<Node> unsignedInteger() {
		return new TokenNodePredicate(new UnsignedInteger());
	}
	
	public static Predicate<Node> equals() {
		return new TokenNodePredicate(new Equal());
	}


	
	@Override
	public String toString() {
		TitleBuilder title = new TitleBuilder();
		title.append(TokenNodePredicate.class.getSimpleName());
		title.append(" tokenRuleToFind=", tokenRuleToFind.getClass().getSimpleName());
		if (regex.isPresent()) {
			title.append(", regex=", regex.get());
		}
		return title.toString();
	}



}
