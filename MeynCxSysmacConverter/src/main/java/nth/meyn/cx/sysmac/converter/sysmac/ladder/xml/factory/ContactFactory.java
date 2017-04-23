package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.CONTACT;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class ContactFactory implements LadderElementFactory<CONTACT> {

	private static final String BOOL = "BOOL";
	private static final String CONTACT = "Contact";
	
	
	
//*	P_First_Cycle_Task	BOOL	A200.15	First Task Execution Flag	0	
//	P_Cycle_Time_Error	BOOL	A401.08	Cycle Time Error Flag	0	
//	P_Low_Battery	BOOL	A402.04	Low Battery Flag	0	
//	P_IO_Verify_Error	BOOL	A402.09	I/O Verification Error Flag	0	
//	P_CIO	WORD	A450	CIO Area Parameter	0	
//	P_WR	WORD	A451	WR Area Parameter	0	
//	P_HR	WORD	A452	HR Area Parameter	0	
//	P_DM	WORD	A460	DM Area Parameter	0	
//	P_EM0	WORD	A461	EM0 Area Parameter	0	
//	P_EM1	WORD	A462	EM1 Area Parameter	0	
//	P_EM2	WORD	A463	EM2 Area Parameter	0	
//	P_EM3	WORD	A464	EM3 Area Parameter	0	
//	P_EM4	WORD	A465	EM4 Area Parameter	0	
//	P_EM5	WORD	A466	EM5 Area Parameter	0	
//	P_EM6	WORD	A467	EM6 Area Parameter	0	
//	P_EM7	WORD	A468	EM7 Area Parameter	0	
//	P_EM8	WORD	A469	EM8 Area Parameter	0	
//	P_EM9	WORD	A470	EM9 Area Parameter	0	
//	P_EMA	WORD	A471	EMA Area Parameter	0	
//	P_EMB	WORD	A472	EMB Area Parameter	0	
//	P_EMC	WORD	A473	EMC Area Parameter	0	
//	P_Output_Off_Bit	BOOL	A500.15	Output OFF Bit	0	
//	P_GE	BOOL	CF000	Greater Than or Equals (GE) Flag	0	
//	P_NE	BOOL	CF001	Not Equals (NE) Flag	0	
//	P_LE	BOOL	CF002	Less Than or Equals (LE)  Flag	0	
//	P_ER	BOOL	CF003	Instruction Execution Error (ER) Flag	0	
//	P_CY	BOOL	CF004	Carry (CY) Flag	0	
//	P_GT	BOOL	CF005	Greater Than (GT) Flag	0	
//	P_EQ	BOOL	CF006	Equals (EQ) Flag	0	
//	P_LT	BOOL	CF007	Less Than (LT) Flag	0	
//	P_N	BOOL	CF008	Negative (N) Flag	0	
//	P_OF	BOOL	CF009	Overflow (OF) Flag	0	
//	P_UF	BOOL	CF010	Underflow (UF) Flag	0	
//	P_AER	BOOL	CF011	Access Error Flag	0	
//*	P_0_1s	BOOL	CF100	0.1 second clock pulse bit	0	
//	P_0_2s	BOOL	CF101	0.2 second clock pulse bit	0	
//*	P_1s	BOOL	CF102	1.0 second clock pulse bit	0	
//*	P_0_02s	BOOL	CF103	0.02 second clock pulse bit	0	
//*	P_1min	BOOL	CF104	1 minute clock pulse bit	0	
//*	P_0_01s	BOOL	CF105	0.01 second clock pulse bit	0	
//*	P_1ms	BOOL	CF106	1 milisecond clock pulse bit	0	
//*	P_0_1ms	BOOL	CF107	0.1 milisecond clock pulse bit	0	
//*	P_On	BOOL	CF113	Always ON Flag	0	
//*	P_Off	BOOL	CF114	Always OFF Flag	0	

	
	@Override
	public List<LadderElement> create(CONTACT cxContact, IdFactory idFactory,
			String programName ) {
		String variableName = cxContact.getOperands().getOperand().getName();
		switch (variableName) {
			case "P_1min" : return createPulseFunction(idFactory, programName, "Get1minClk");
			case "P_1s" : return createPulseFunction(idFactory, programName, "Get1sClk");
			case "P_0_1s" : return createPulseFunction(idFactory, programName, "Get100msClk");
			case "P_0_2s" : return createPulseFunction(idFactory, programName, "Get100msClk");//Yuk: there is no Get20msClk 
			case "P_0_01s" : return createPulseFunction(idFactory, programName, "Get10msClk");
			case "P_0_02s" : return createPulseFunction(idFactory, programName, "Get20msClk");
			case "P_1ms" : return createPulseFunction(idFactory, programName, "Get1msClk");
			case "P_0_1ms" : return createPulseFunction(idFactory, programName, "Get100usClk");
			
			case "P_First_Cycle": 
				return createContact(idFactory, programName,
						cxContact.getNegated() == 1, cxContact.getDiffUp() == 1, cxContact.getDiffDown() == 1, "P_First_Run");
			default: return createContact(idFactory, programName,
					cxContact.getNegated() == 1, cxContact.getDiffUp() == 1, cxContact.getDiffDown() == 1, cxContact.getOperands().getOperand().getName());
				
		}
	}

	private List<LadderElement> createPulseFunction(IdFactory idFactory,	String programName ,String pulseFunctionName) {
		boolean isPolynomial=false;
		boolean isUserDefinedType=false;
		String powerPinInName="EN";
		String powerPinOutName="";
		List<LadderElement> ladderElements = InstructionFactory.create(idFactory, InstructionFactory.FUNCTION, pulseFunctionName, isPolynomial, isUserDefinedType, powerPinInName, powerPinOutName);
		return ladderElements;
	}

	public static List<LadderElement> createContact(IdFactory idFactory,
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
		return Arrays.asList(ladderElement);

	}

}
