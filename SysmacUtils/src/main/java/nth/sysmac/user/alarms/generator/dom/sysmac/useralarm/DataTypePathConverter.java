package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm;

import java.util.List;

import nth.reflect.util.parser.node.NodeParser;
import nth.reflect.util.parser.node.ParseTree;
import nth.reflect.util.parser.node.text.NodesToTextConverter;
import nth.reflect.util.parser.token.parser.Token;
import nth.reflect.util.parser.token.parser.TokenParser;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.NodeParserRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.TokenRules;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataTypePath;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;

/**
 * Provides information to create a {@link UserAlarm} from a {@link DataTypePath}
 * @author nilsth
 *
 */
public class DataTypePathConverter {

	private final Variable eventVariable;
	private final DataTypePath dataTypePath;
	private int arrayIndex;
	private final ParseTree parseTree;
	private int arrayIndexMax;

	public DataTypePathConverter(Variable eventVariable, DataTypePath dataTypePath) {
		this.eventVariable = eventVariable;
		this.dataTypePath = dataTypePath;
		this.arrayIndex=dataTypePath.getArrayMin();
		this.arrayIndexMax=dataTypePath.getArrayMax();
		this.parseTree=parse(dataTypePath);
	}

	private ParseTree parse(DataTypePath dataTypePath) {
		String textExpression=dataTypePath.getTextExpression();
		TokenParser tokenParser=new TokenParser(TokenRules.all());
		List<Token> tokens = tokenParser.parse(textExpression);
		NodeParser nodeParser=new NodeParser(NodeParserRules.all());
		return nodeParser.parse(tokens);
	}

	public void next() {
		arrayIndex ++;
	}

	public String getExpression() {
		return dataTypePath.getVariableExpression(eventVariable, arrayIndex);
	}
	
	public String getMessage() {
		return NodesToTextConverter.convert(parseTree.getNodes());
	}

	public String getPriority() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getAcknowlegde() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasNext() {
		return arrayIndex>=arrayIndexMax;
	}
	

}
