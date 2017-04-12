package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.COIL;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.CONTACT;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.CONTACT.Operands.Operand;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;

public class CxLadderModelFactory {

	public static List<CxLadderModel> createLadderModels(CxLadderDiagram cxLadderDiagram) {
		List<CxLadderModel> cxLadderModels = new ArrayList<>();
		List<RUNG> rungs = cxLadderDiagram.getRungList().getRUNG();
		for (RUNG rung : rungs) {
			CxLadderModel cxLadderModel = new CxLadderModel(rung);
			cxLadderModels.add(cxLadderModel);
		}
		return cxLadderModels;
	}

	public static Set<CxVariable> createVariables(List<CxLadderModel> cxLadderModels) {
		Set<CxVariable> variables = new HashSet<>();
		for (CxLadderModel cxLadderModel : cxLadderModels) {
			List<CONTACT> contacts = cxLadderModel.get(CONTACT.class);
			variables.addAll(getVariablesFromContacts(contacts));

			List<COIL> coils = cxLadderModel.get(COIL.class);
			variables.addAll(getVariablesFromCoils(coils));

			List<INSTRUCTION> instructions = cxLadderModel.get(INSTRUCTION.class);
			variables.addAll(getVariablesFromInstructions(instructions));

			// TODO FB instance and FBVARIABLES
		}
		return variables;
	}

	private static Set<CxVariable> getVariablesFromInstructions(List<INSTRUCTION> instructions) {
		Set<CxVariable> variables = new HashSet<>();
		for (INSTRUCTION instruction : instructions) {
			List<nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION.Operands.Operand> operands = instruction
					.getOperands().getOperand();
			for (nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION.Operands.Operand operand : operands) {
				String variableInfo = operand.getPOUDefinition();
				if (!variableInfo.isEmpty()) {
					CxVariable variable = createVariable(variableInfo);
					variables.add(variable);
				}
			}
		}
		return variables;
	}

	private static Set<CxVariable> getVariablesFromCoils(List<COIL> coils) {
		Set<CxVariable> variables = new HashSet<>();
		for (COIL coil : coils) {
			nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.COIL.Operands.Operand operand = coil
					.getOperands().getOperand();
			String variableInfo = operand.getPOUDefinition();
			CxVariable variable = createVariable(variableInfo);
			variables.add(variable);
		}
		return variables;
	}

	private static Set<CxVariable> getVariablesFromContacts(List<CONTACT> contacts) {
		Set<CxVariable> variables = new HashSet<>();
		for (CONTACT contact : contacts) {
			Operand operand = contact.getOperands().getOperand();
			String variableInfo = operand.getPOUDefinition();
			CxVariable variable = createVariable(variableInfo);
			variables.add(variable);
		}
		return variables;
	}

	private static CxVariable createVariable(String variableInfo) {
//		System.out.println(variableInfo);
		String[] variableInfos = variableInfo.split(",");
		CxVariable variable = new CxVariable();
		variable.setName(variableInfos[0]);
		int variableTypeId = Integer.parseInt(variableInfos[2]);
		variable.setType(CxVariableType.valueOfId(variableTypeId));
		variable.setComment(variableInfos[6]);
		return variable;
	}

	public static Set<CxVariable> createVariableExamples() {
		Set<CxVariable> variables =new HashSet<>();
		CxVariable variable = createVariable("iTest1,0,0,0,,FALSE,Example:Input variable 1,");
		variables.add(variable);
		variable = createVariable("oTest1,0,0,0,,FALSE,Example:Ouput variabale 1,");
		variables.add(variable);
		return variables;
	}

}
