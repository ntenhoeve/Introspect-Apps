package nth.meyn.cx.sysmac.converter.cx.symbolOLD;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.plaf.ListUI;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.TokenReader;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.Tokenizer;

public class CxSymbolParser {

	private final static String BEGIN_LIST="BEGIN_LIST_$#[";
	private final static String COMMA=",";
	private final static String NEW_LINE_SPACE="\r\n ";
			private final static String SEMI_COLON=";";
			private final static String END_LIST="END_LIST_$#[";
			private final List<String> delimiters=Arrays.asList(BEGIN_LIST, NEW_LINE_SPACE, COMMA, SEMI_COLON, END_LIST);
			private final List<String> tokens;
					
	public CxSymbolParser(String text) {
		tokens = Tokenizer.createTokens(text, delimiters);
	}
	


	public List<CxSymbol> createCxSymbols() {
		List<CxSymbol> cxSymbols=new ArrayList<>();
		TokenReader tokenReader = new TokenReader(tokens);
		tokenReader.readNextToken(BEGIN_LIST);
		while (tokenReader.readNextToken(NEW_LINE_SPACE).isPresent()) {
			CxSymbol cxSymbol=createCxSymbol(tokenReader);
			cxSymbols.add(cxSymbol);
		}
		return cxSymbols;
	}

	private CxSymbol createCxSymbol(TokenReader tokenReader) {
		CxSymbol cxSymbol=new CxSymbol();
		cxSymbol.setName(tokenReader.readNextToken().get().trim());
		tokenReader.readNextToken(COMMA);
		cxSymbol.setAddress(tokenReader.readNextToken().get());
		tokenReader.readNextToken(COMMA);
		cxSymbol.setType(tokenReader.readNextToken().get());
		tokenReader.readNextToken(COMMA);
		tokenReader.readNextToken(COMMA);
		cxSymbol.setAutomaticAddressing(tokenReader.peekNextToken().get().equals("A"));
		tokenReader.readNextToken(COMMA);
		tokenReader.readNextToken(COMMA);
		cxSymbol.setComment(tokenReader.readNextToken().get());
		return cxSymbol;
	}
}
