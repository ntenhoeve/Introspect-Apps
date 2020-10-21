package nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype;

import java.util.ArrayList;

import nth.reflect.fw.generic.util.TitleBuilder;
import nth.sysmac.user.alarms.generator.SysmacUserAlarmsGenerator;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.variable.Variable;

public class DataTypePath extends ArrayList<DataType> {

	/**
	 * creates a copy of the current path
	 * 
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
		TitleBuilder varExpression = new TitleBuilder();
		varExpression.setSeperator(".");
		varExpression.append(eventVariable.getName());

		for (DataType dataType : this) {
			if (!dataType.getBaseType().isStruct()) {
				varExpression.append(dataType.getName());
				if (dataType.getBaseType().getArray().isPresent()) {
					varExpression.append(dataType.getBaseType().getArray().get());
				}
			}
		}
		return varExpression.toString();
	}

	public String getTextExpression() {
		TitleBuilder varExpression = new TitleBuilder();
		varExpression.setSeperator(" ");

		for (DataType dataType : this) {
			if (!dataType.getBaseType().isStruct()) {
				String comment = dataType.getComment().trim();
				varExpression.append(comment);
			}
		}
		return varExpression.toString();
	}

	public void verifyOnlyOneArray() {
		int dataTypesWithArray = dataTypesWithArray();
		if (dataTypesWithArray > 1) {
			throw new RuntimeException("DataType path: " + this + " has multipe arrays. This is not supported for: "
					+ SysmacUserAlarmsGenerator.class.getSimpleName());
		}
	}

	private int dataTypesWithArray() {
		int dataTypesWithArray = 0;
		for (DataType dataType : this) {
			if (dataType.getBaseType().getArray().isPresent()) {
				dataTypesWithArray++;
			}
		}
		return dataTypesWithArray;
	}

//	private Optional<BaseTypeArray> findArray() {
//		for (DataType dataType : this) {
//			Optional<BaseTypeArray> array = dataType.getBaseType().getArray();
//			if (array.isPresent()) {
//				return array;
//			}
//		}
//		return Optional.empty();
//	}

	@Override
	public String toString() {
		TitleBuilder reply = new TitleBuilder();
		reply.setSeperator(".");
		for (DataType dataType : this) {
			if (!dataType.getBaseType().isStruct()) {
				reply.append(dataType.getName());
			}
		}
		return reply.toString();
	}

//	/**
//	 * @return Gets the minimum value in an array of one of the {@link DataType}s or
//	 *         returns 0 if there are no {@link DataType}s has an array
//	 */
//	public int getArrayMin() {
//		Optional<BaseTypeArray> array = findArray();
//		if (array.isPresent()) {
//			return array.get().getMin();
//		} else {
//			return 0;
//		}
//	}
//
//	/**
//	 * @return Gets the maximum value in an array of one of the {@link DataType}s or
//	 *         returns 0 if there are no {@link DataType}s has an array
//	 */
//	public int getArrayMax() {
//		Optional<BaseTypeArray> array = findArray();
//		if (array.isPresent()) {
//			return array.get().getMax();
//		} else {
//			return 0;
//		}
//	}

}
