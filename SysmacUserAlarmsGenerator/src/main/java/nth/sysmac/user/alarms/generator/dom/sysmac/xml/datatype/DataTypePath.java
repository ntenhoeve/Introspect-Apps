package nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype;

import java.util.ArrayList;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;

public class DataTypePath extends ArrayList<DataType> {

	/**
	 * creates a copy of the current path
	 * @param currentPath
	 */
	public DataTypePath(DataTypePath currentPath) {
		super(currentPath);
	}

	public DataTypePath() {
		super();
	}

	private static final long serialVersionUID = -7086773437631128734L;

	public String getVariableExpression(Variable eventVariable) {
		TitleBuilder varExpression=new TitleBuilder();
		varExpression.setSeperator(".");
		varExpression.append(eventVariable.getName());
		
		for (DataType dataType : this) {
			if (!dataType.getBaseType().isStruct()) {
				varExpression.append(dataType.getName());
			}
		}
		return varExpression.toString();
	}

}
