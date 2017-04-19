package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.CONTACT;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class ContactFactory implements LadderElementFactory<CONTACT> {

	private static final String BOOL = "BOOL";
	private static final String CONTACT = "Contact";
	
	@Override
	public List<LadderElement> create(CONTACT cxContact, IdFactory idFactory,
			String programName ) {
		LadderElement ladderElement = create(idFactory, programName,
				cxContact.getNegated() == 1, cxContact.getDiffUp() == 1, cxContact.getDiffDown() == 1, cxContact.getOperands().getOperand().getName());
		return Arrays.asList(ladderElement);
	}

	public static LadderElement create(IdFactory idFactory,
			String programName, boolean inverted, boolean diffUp, boolean diffDown,  String varName) {
		LadderElement ladderElement = new LadderElement();
		ladderElement.setInstanceID(idFactory.createNext());
		ladderElement.setLadderElementType(CONTACT);
		ladderElement.setInverted(Boolean.toString(inverted));
		ladderElement.setDiffUp(Boolean.toString(diffUp));
		ladderElement.setDiffDown(Boolean.toString(diffDown));
		ladderElement.setVariableName(varName);
		ladderElement.setBaseVariableName(varName);
		ladderElement.setProgramName(programName);
		ladderElement.setBaseVariableDataType(BOOL);
		return ladderElement;
	}

}
