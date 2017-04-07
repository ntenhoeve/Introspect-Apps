package nth.meyn.cx.sysmac.converter.cx.ladderOLD;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.management.Descriptor;
import javax.swing.plaf.ListUI;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.TokenReader;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.Tokenizer;
import nth.meyn.cx.sysmac.converter.cx.symbolOLD.CxSymbol;

public class CxLadderParserOld {

	private final static String LADDER_RUNG="CLadderRung";
	private final static String LADDER_ELEMENT="CLadderElement"; 
	private final static String SINGLE_QOUTE="'";
			private final static String DIVIDER="";
			private final List<String> tokens;
			private List<String> DELIMITERS;
					
	public CxLadderParserOld(String text) {
		DELIMITERS=new ArrayList<>();
		DELIMITERS.add(LADDER_RUNG);
		DELIMITERS.add(LADDER_ELEMENT);
		DELIMITERS.add(SINGLE_QOUTE);
		DELIMITERS.add(DIVIDER);
		DELIMITERS.add(CxDiff.UP.getPresentation());
		DELIMITERS.add(CxDiff.DOWN.getPresentation());
		DELIMITERS.addAll(CxCommand.getNames());
		tokens = Tokenizer.createTokens(text, DELIMITERS);
	}
	


	public List<CxLadderRung> createCxLadder() {
		List<CxLadderRung> rungs=new ArrayList<>();
		TokenReader tokenReader = new TokenReader(tokens);
		tokenReader.readNextToken(LADDER_RUNG);
//		tokenReader.readNextToken(LADDER_ELEMENT);
		tokenReader.readNextToken(SINGLE_QOUTE);
		tokenReader.readPreviousToken();
	
		List<String> commands = CxCommand.getNames();
		List<String >commandsAndQoute=new ArrayList<>();
		commandsAndQoute.addAll(commands);
		commandsAndQoute.add(SINGLE_QOUTE);
		
		CxLadderRung rung = null;
		
		while (tokenReader.hasTokenAfterCursor(commandsAndQoute)) {
			String commandOrQoute = tokenReader.readFirstNextToken(commandsAndQoute).get();
			if (commandOrQoute.equals(SINGLE_QOUTE)) {
				rung = new CxLadderRung();
				rungs.add(rung);
				String description=tokenReader.readNextToken().get().trim();
				rung.setDescription(description);
				tokenReader.readNextToken(SINGLE_QOUTE);
			} else {
			
			CxCommand command=CxCommand.valueOfName(commandOrQoute);
			String diffString = tokenReader.peekPreviousToken().get();
			CxDiff diff=CxDiff.valueOfPresentation(diffString);
			List<String> parameters=new ArrayList<>(); 
			boolean endOfCommand=false;
			while (! tokenReader.peekCurrentToken().equals(DIVIDER) && !endOfCommand) {
				String token = tokenReader.readNextToken().get();
				if (token.equals(commandOrQoute)) {
					endOfCommand=true;
				} else {
					String parameter=getParameter(token);
					if (parameter!=null) {
					parameters.add(parameter);
					}
				}
			}
			CxStatement statement=new CxStatement(diff,command, parameters);
			rung.getStatements().add(statement);
			}
		}
		
		
		return rungs;
	}



	private String getParameter(String token) {
		if (token.length()>3) {
			return token.substring(1, token.length()-3);
		}
		return null;
	}
}
