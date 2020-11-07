package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import java.util.ArrayList;
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
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseTypeArray;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseTypeArrayRange;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.GoToNextListener;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.NodeParserRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.acknowledge.AcknowledgeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.DerivedComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode.VisibleComponentCodeNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.details.DetailsNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.Priority;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.priority.PriorityNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;
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
		addParseTreeGoToNextListenersToDataTypeArrayRanges();
	}

	private void addParseTreeGoToNextListenersToDataTypeArrayRanges() {
		List<GoToNextListener> goToNextlisteners=findParseTreeListeners(parseTree);
		Optional<BaseTypeArray> result = findArray();
		if (result.isPresent()) {
			BaseTypeArray array = result.get();
			List<BaseTypeArrayRange> ranges = array.getArrayRanges();
			for (BaseTypeArrayRange range : ranges) {
				for (GoToNextListener goToNextListener : goToNextlisteners) {
					range.addListener(goToNextListener);					
				}
			}
		}
	}

	private List<GoToNextListener> findParseTreeListeners(Node node) {
		List<GoToNextListener> goToNextListeners=new ArrayList<>();
		if (node instanceof GoToNextListener) {
			GoToNextListener goToNextListener=(GoToNextListener) node;
			goToNextListeners.add(goToNextListener);
		}
		for (Node child : node.getNodes()) {
			goToNextListeners.addAll(findParseTreeListeners(child));//recursive call
		}
		return goToNextListeners;
	}

	private ParseTree parse(DataTypePath dataTypePath) {
		String textExpression = dataTypePath.getTextExpression();
		TokenParser tokenParser = new TokenParser(TokenRules.all());
		List<Token> tokens = tokenParser.parse(textExpression);
		NodeParser nodeParser = new NodeParser(NodeParserRules.all());
		return nodeParser.parse(tokens);
	}

	public boolean canGoToNext() {
		Optional<BaseTypeArray> result = findArray();
		if (result.isPresent()) {
			BaseTypeArray array = result.get();
			return array.canGoToNext();
		} else {
			return false;
		}
	}
	
	public void goToNext() {
		Optional<BaseTypeArray> result = findArray();
		if (result.isPresent()) {
			BaseTypeArray array = result.get();
			array.goToNext();
		}
	}


	public Optional<BaseTypeArray> findArray() {
		for (int i = dataTypePath.size() - 1; i > 0; i--) {
			DataType dataType = dataTypePath.get(i);
			Optional<BaseTypeArray> optionalArray = dataType.getBaseType().getArray();
			if (optionalArray.isPresent()) {
				BaseTypeArray array = optionalArray.get();
				return Optional.of(array);
			}
		}
		return Optional.empty();
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
		String messageWithoutDoubleSpaces = message
				.replaceAll(new Regex().whiteSpace(Repetition.oneOrMoreTimes()).toString(), " ");
		String capitalizedMessage = StringUtil.firstCharToUpperCase(messageWithoutDoubleSpaces);
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

	public DataTypePath getDataTypePath() {
		return dataTypePath;
	}

}
