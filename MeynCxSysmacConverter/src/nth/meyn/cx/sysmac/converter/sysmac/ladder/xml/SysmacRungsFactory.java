package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml;

import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxLadderModel;
import nth.meyn.cx.sysmac.converter.sysmac.clipboard.SysmacLadderDataFactory;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement.ConnectionPoint;

public class SysmacRungsFactory {

	public static Rungs create(List<CxLadderModel> cxLadderModels) {
		Rungs sysmacRungs=new Rungs();
		SysmacLadderElementFactory ladderElementFactory = new SysmacLadderElementFactory();
		for (CxLadderModel cxLadderModel : cxLadderModels) {
			RungXML sysmacRung=createLadderRung(cxLadderModel, ladderElementFactory);
			sysmacRungs.getRungXML().add(sysmacRung);
		}
		return sysmacRungs;
	}

	public static Rungs createExample() {
		Rungs sysmacRungs=new Rungs();
		SysmacLadderElementFactory ladderElementFactory = new SysmacLadderElementFactory();
			RungXML sysmacRung=createLadderRungExample( ladderElementFactory);
			sysmacRungs.getRungXML().add(sysmacRung);
		return sysmacRungs;
	}
	
	private static RungXML createLadderRung(CxLadderModel cxLadderModel, SysmacLadderElementFactory ladderElementFactory) {
		 RungXML sysmacRung = new RungXML();
	
		 sysmacRung.setComment(cxLadderModel.getComment());
		 sysmacRung.setHeight((float) 56);//TODO test if this can be removed
		 sysmacRung.setWidth((short) 661);//TODO test if this can be removed
		 sysmacRung.setLabel("");
		 
		 List<LadderElement> ladderElements = sysmacRung.getLadderElement();
		 
		 //TODO
		 
		 int startId = 2;//10067;
		String contactID = ladderElementFactory.getHex(startId);//
		 LadderElement contact=ladderElementFactory.createContact(contactID, "SimpleThings", "iTest1", false, false,false );
		 ladderElements.add(contact);
		 
		 String coilID = ladderElementFactory.getHex(startId+3);
		 LadderElement coil=ladderElementFactory.createCoil(coilID, "SimpleThings", "oTest1", false, false,false ,false,false);
		 ladderElements.add(coil);
		 
		 String leftPowerRailID = ladderElementFactory.getHex(startId+3+3);
		 LadderElement leftPowerRail = ladderElementFactory.createLeftPowerRail(leftPowerRailID);
		 ladderElements.add(leftPowerRail);
		 
		 String rightPowerRailID = ladderElementFactory.getHex(startId+3+3+2);
		 LadderElement rightPowerRail = ladderElementFactory.createRightPowerRail(rightPowerRailID);
		 ladderElements.add(rightPowerRail);
		 
		 String edgeID=ladderElementFactory.getHex(startId+3+3+2+2);
		 ladderElementFactory.createConnection(sysmacRung, edgeID, leftPowerRail, contact);
		 edgeID=ladderElementFactory.getHex(startId+3+3+2+2+1);
		 ladderElementFactory.createConnection(sysmacRung, edgeID,contact, coil);
		 edgeID=ladderElementFactory.getHex(startId+3+3+2+2+1+1);
		 ladderElementFactory.createConnection(sysmacRung, edgeID,coil, rightPowerRail);
		 
		return sysmacRung;
	}

	/**
	 * XML .length=2305, 2319 (comment.length=7, 21):  Sysmac does not recognize clipboard. See {@link SysmacLadderDataFactory#fixXmlLenghtIssue}
	 */
	private static RungXML createLadderRungExample( SysmacLadderElementFactory ladderElementFactory) {
		 RungXML sysmacRung = new RungXML(); 
		 sysmacRung.setComment("1234567");  
		 sysmacRung.setHeight((float) 56);//TODO test if this can be removed
		 sysmacRung.setWidth((short) 661);//TODO test if this can be removed
		 sysmacRung.setLabel("");
		 
		 List<LadderElement> ladderElements = sysmacRung.getLadderElement();
		 
		 int startId = 2;//10067;
		String contactID = ladderElementFactory.getHex(startId);//
		 LadderElement contact=ladderElementFactory.createContact(contactID, "SimpleThings", "iTest1", false, false,false );
		 ladderElements.add(contact);
		 
		 String coilID = ladderElementFactory.getHex(startId+3);
		 LadderElement coil=ladderElementFactory.createCoil(coilID, "SimpleThings", "oTest1", false, false,false ,false,false);
		 ladderElements.add(coil);
		 
		 String leftPowerRailID = ladderElementFactory.getHex(startId+3+3);
		 LadderElement leftPowerRail = ladderElementFactory.createLeftPowerRail(leftPowerRailID);
		 ladderElements.add(leftPowerRail);
		 
		 String rightPowerRailID = ladderElementFactory.getHex(startId+3+3+2);
		 LadderElement rightPowerRail = ladderElementFactory.createRightPowerRail(rightPowerRailID);
		 ladderElements.add(rightPowerRail);
		 
		 String edgeID=ladderElementFactory.getHex(startId+3+3+2+2);
		 ladderElementFactory.createConnection(sysmacRung, edgeID, leftPowerRail, contact);
		 edgeID=ladderElementFactory.getHex(startId+3+3+2+2+1);
		 ladderElementFactory.createConnection(sysmacRung, edgeID,contact, coil);
		 edgeID=ladderElementFactory.getHex(startId+3+3+2+2+1+1);
		 ladderElementFactory.createConnection(sysmacRung, edgeID,coil, rightPowerRail);
		 
		return sysmacRung;
	}

	
}
