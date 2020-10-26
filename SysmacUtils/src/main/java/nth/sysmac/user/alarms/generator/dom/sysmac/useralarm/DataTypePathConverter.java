package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import java.util.List;
import java.util.Optional;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.ParseTree;
import nth.reflect.util.parser.node.text.NodesToTextConverter;
import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenParser;
import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.NodeParserRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.acknowledge.AcknowledgeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.DerivedComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.VisibleComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.details.DetailsNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.Priority;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.PriorityNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataTypePath;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;

/**
 * Provides information to create a {@link UserAlarm} from a
 * {@link DataTypePath}
 * 
 * @author nilsth
 *
 */
public class DataTypePathConverter {

	private final Variable eventVariable;
	private final DataTypePath dataTypePath;
	private final ParseTree parseTree;

	public DataTypePathConverter(Variable eventVariable, DataTypePath dataTypePath) {
		this.eventVariable = eventVariable;
		this.dataTypePath = dataTypePath;
		this.parseTree = parse(dataTypePath);
	}

	private ParseTree parse(DataTypePath dataTypePath) {
		String textExpression = dataTypePath.getTextExpression();
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		List<Token> tokens = tokenParser.parse(textExpression);
		NodeParser nodeParser = new NodeParser(NodeParserRules.all());
		return nodeParser.parse(tokens);
	}

	
	public void goToNext() {
		//FIXME
	}
	
	public boolean canGoToNext() {
return false;
//FIXME
	}

	public String getExpression() {
		return dataTypePath.getVariableExpression(eventVariable);
	}

	public String getComponentCode() {
		Optional<Node> node = parseTree.getNodes().stream().filter(n -> n instanceof DerivedComponentCodeNode)
				.findFirst();
		if (node.isPresent()) {
			DerivedComponentCodeNode derivedComponentCodeNode = (DerivedComponentCodeNode) node.get();
			return derivedComponentCodeNode.toText();
		}
		node = parseTree.getNodes().stream().filter(n -> n instanceof VisibleComponentCodeNode).findFirst();
		if (node.isPresent()) {
			VisibleComponentCodeNode visibleComponentCodeNode = (VisibleComponentCodeNode) node.get();
			return visibleComponentCodeNode.toText();
		}
		return "";
	}

	public String getMessage() {
		String message = NodesToTextConverter.convert(parseTree.getNodes()).trim();
		String messageWithoutDoubleSpaces=message.replaceAll(new Regex().whiteSpace(Repetition.oneOrMoreTimes()).toString(), " ");
		String capitalizedMessage=StringUtil.firstCharToUpperCase(messageWithoutDoubleSpaces);
		return capitalizedMessage;
	}

	public String getComponentCodeAndMessage() {
		String componentCode = getComponentCode();
		if (componentCode.isBlank()) {
			return getMessage();
		}
		return componentCode + " " + getMessage();
	}

	public Priority getPriority() {
		Optional<Node> node = parseTree.getNodes().stream().filter(n -> n instanceof PriorityNode).findFirst();
		if (node.isPresent()) {
			PriorityNode priorityNode = (PriorityNode) node.get();
			Priority priority = priorityNode.getPriority();
			return priority;
		} else {
			return Priority.MEDIUM;
		}
	}

	public boolean getAcknowlegde() {
		return parseTree.getNodes().stream().anyMatch(n -> n instanceof AcknowledgeNode);
	}

	public String getDetails() {
		Optional<Node> node = parseTree.getNodes().stream().filter(n -> n instanceof DetailsNode).findFirst();
		if (node.isPresent()) {
			DetailsNode detailsNode = (DetailsNode) node.get();
			String details = detailsNode.toText();
			return details;
		} else {
			return "";
		}

	}

}
