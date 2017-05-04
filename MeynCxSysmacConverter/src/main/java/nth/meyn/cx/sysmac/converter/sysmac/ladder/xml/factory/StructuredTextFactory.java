package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.UUID;

import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class StructuredTextFactory {
	private static final String INLINE_STRUCTURED_TEXT_SERVICES_INSERT_INLINE_ST = "InlineStructuredTextServices.InsertInlineST";

	/**
	 * <LadderElement instanceID="0x00002948" ladderElementType="InlineStructuredTextServices.InsertInlineST" textEntityID="92e967a1-85c1-4c02-afda-c78de0942289" text="AA:=10+2;">
	 * @param idFactory
	 * @param script
	 * @return
	 */
	public static LadderElement create(IdFactory idFactory, String script) {
		LadderElement ladderElement = new LadderElement();
		ladderElement.setInstanceID(idFactory.createNextElementId());
		ladderElement.setLadderElementType(INLINE_STRUCTURED_TEXT_SERVICES_INSERT_INLINE_ST);
		ladderElement.setTextEntityID(UUID.randomUUID().toString());
		ladderElement.setText(script);
		return ladderElement;
	}
}
