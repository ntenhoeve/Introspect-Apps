package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.COIL;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.CONTACT;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION.Operands;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION.Operands.Operand;

public class CxVariableFactory {

	public static Set<CxVariable> createVariables(List<CxLadderModel> cxLadderModels) {
		Set<CxVariable> variables = new HashSet<>();
		for (CxLadderModel cxLadderModel : cxLadderModels) {
			List<CONTACT> contacts = cxLadderModel.get(CONTACT.class);
			variables.addAll(CxVariableFactory.getVariablesFromContacts(contacts));
	
			List<COIL> coils = cxLadderModel.get(COIL.class);
			variables.addAll(CxVariableFactory.getVariablesFromCoils(coils));
	
			List<INSTRUCTION> instructions = cxLadderModel.get(INSTRUCTION.class);
			variables.addAll(CxVariableFactory.getVariablesFromInstructions(instructions));
	
			// TODO FB instance and FBVARIABLES
		}
		return variables;
	}

	static Set<CxVariable> getVariablesFromInstructions(List<INSTRUCTION> instructions) {
		Set<CxVariable> variables = new HashSet<>();
		for (INSTRUCTION instruction : instructions) {
			List<nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION.Operands.Operand> operands = instruction
					.getOperands().getOperand();
			for (nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION.Operands.Operand operand : operands) {
				String variableInfo = operand.getPOUDefinition();
				if (!variableInfo.isEmpty()) {
					CxVariable variable = CxVariableFactory.createVariable(variableInfo);
					variables.add(variable);
				}
			}
		}
		return variables;
	}

	static Set<CxVariable> getVariablesFromCoils(List<COIL> coils) {
		Set<CxVariable> variables = new HashSet<>();
		for (COIL coil : coils) {
			nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.COIL.Operands.Operand operand = coil
					.getOperands().getOperand();
			String variableInfo = operand.getPOUDefinition();
			CxVariable variable = CxVariableFactory.createVariable(variableInfo);
			variables.add(variable);
		}
		return variables;
	}

	static Set<CxVariable> getVariablesFromContacts(List<CONTACT> contacts) {
		Set<CxVariable> variables = new HashSet<>();
		for (CONTACT contact : contacts) {
			 nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.CONTACT.Operands.Operand operand = contact.getOperands().getOperand();
			String variableInfo = operand.getPOUDefinition();
			if (variableInfo.length()>0) {
				CxVariable variable = CxVariableFactory.createVariable(variableInfo);
				variables.add(variable);
			}
		}
		return variables;
	}

	static CxVariable createVariable(String variableInfo) {
		System.out.println(variableInfo);
		String[] variableInfos = variableInfo.split(",");
		CxVariable variable = new CxVariable();
		variable.setName(variableInfos[0]);
		int variableTypeId = Integer.parseInt(variableInfos[1]);
		variable.setType(CxVariableType.valueOfId(variableTypeId));
		int arraySize = Integer.parseInt(variableInfos[2].replace("[", "").replace("]", ""));
		variable.setArraySize(arraySize);
		variable.setType(CxVariableType.valueOfId(variableTypeId));
		int dataTypeId = Integer.parseInt(variableInfos[2]);
		variable.setDataType(CxDataType.valueOfId(dataTypeId));
		if (variableInfos.length > 6) {
		variable.setComment(variableInfos[6]);
		} else if (variableInfos.length > 7) {
			variable.setRetained(variableInfos[7] == "1");
		} else if (variableInfos.length > 8) {
			variable.setFunctionBlockNameOrStructName(variableInfos[8]);
		}
		return variable;
	}

	public static Set<CxVariable> createVariableExamples() {
		Set<CxVariable> cxVariables=new HashSet<>();
		cxVariables.add( createVariable("iTest1","Input variable 1"));
		cxVariables.add( createVariable("oTest1","Ouput variabale 1"));
		return cxVariables;
	}

	private static CxVariable createVariable(String name, String comment) {
		CxVariable cxVariable=new CxVariable();
		cxVariable.setDataType(CxDataType.BOOL);
		cxVariable.setName(name);
		cxVariable.setComment(comment);
		return cxVariable;
	}
//	public static Set<CxVariable> createVariableExamples() {
//		Set<CxVariable> variables = new HashSet<>();
//		CxVariable variable = createVariable("iTest1,0,0,0,,FALSE,Example:Input variable 1,");
//		variables.add(variable);
//		variable = createVariable("oTest1,0,0,0,,FALSE,Example:Ouput variabale 1,");
//		variables.add(variable);
//		return variables;
//	}

}
