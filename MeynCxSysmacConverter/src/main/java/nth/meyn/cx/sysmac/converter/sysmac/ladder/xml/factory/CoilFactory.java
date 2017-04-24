package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.COIL;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class CoilFactory implements LadderElementFactory<COIL> {

	private static final String BOOL = "BOOL";
	private static final String COIL = "Coil";


	public static LadderElement create(IdFactory idFactory,
			String programName, boolean inverted, boolean diffUp, boolean diffDown, boolean set,
			boolean reset, String varName) {
		LadderElement ladderElement = new LadderElement();
		ladderElement.setInstanceID(idFactory.createNext());
		ladderElement.setLadderElementType(COIL);
		ladderElement.setInverted(Boolean.toString(inverted));
		ladderElement.setDiffUp(Boolean.toString(diffUp));
		ladderElement.setDiffDown(Boolean.toString(diffDown));
		ladderElement.setSet(Boolean.toString(set));
		ladderElement.setReset(Boolean.toString(reset));
		ladderElement.setVariableName(varName);
		ladderElement.setBaseVariableName(varName);
		ladderElement.setProgramName(programName);
		ladderElement.setBaseVariableDataType(BOOL);
		return ladderElement;
	}

	@Override
	public List<LadderElement> createForInput1(
			nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.COIL cxCoil,
			IdFactory idFactory, String programName) {
		LadderElement ladderElement = create(idFactory, programName,
				cxCoil.getNegated() == 1, cxCoil.getDiffUp() == 1, cxCoil.getDiffDown() == 1, false,
				false, cxCoil.getOperands().getOperand().getName());
		return Arrays.asList(ladderElement);
	}

}
