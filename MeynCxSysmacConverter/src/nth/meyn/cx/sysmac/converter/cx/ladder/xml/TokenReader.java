package nth.meyn.cx.sysmac.converter.cx.ladder.xml;

import java.util.List;
import java.util.Optional;


/**
 * @deprecated use xml from clipboard instead
 * @author nilsth
 *
 */

public class TokenReader {

	private static final int MAX_VALUE = Integer.MAX_VALUE;
	private static final int NOT_FOUND = -1;
	private final List<String> tokens;
	private int currentTokenIndex;

	public TokenReader(List<String> tokens) {
		this.tokens = tokens;
		currentTokenIndex=0;
	}
	
public boolean hasNextToken() {
		return currentTokenIndex<tokens.size();
	}
	
	public Optional<String> peekNextToken() {
		if (currentTokenIndex+1<tokens.size()) {
			String nextToken=tokens.get(currentTokenIndex+1);
			return Optional.of(nextToken);
		} else {
			return Optional.empty();
		}
	}
	
	public Optional<String> readFirstNextToken(List<String> tokensToFind) {
		int index=MAX_VALUE;
		for (String tokenToFind : tokensToFind) {
			int foundIndex=findNextToken(tokenToFind);
			if (foundIndex!=NOT_FOUND && index>foundIndex) {
				index=foundIndex;
			}
		}
		if (index==MAX_VALUE) {
			return Optional.empty();
		} else {
			currentTokenIndex=index;
			return Optional.of(tokens.get(currentTokenIndex));			
		}
	}
	
	private int findNextToken(String tokenToFind) {
		for (int index=currentTokenIndex+1;index<tokens.size();index++) {
			if (tokens.get(index).equals(tokenToFind)) {
				return index;
			}
		}
		return NOT_FOUND;
	}
	
	
	public Optional<String> readNextToken(String tokenToFind) {
		for (int index=currentTokenIndex+1;index<tokens.size();index++) {
			if (tokens.get(index).equals(tokenToFind)) {
				currentTokenIndex=index;
				String tokenFound = tokens.get(currentTokenIndex);
				return Optional.of( tokenFound);
			}
		}
		return Optional.empty();
	}

	public Optional<String> readNextToken() {
		if (currentTokenIndex+1<tokens.size()) {
				String tokenFound = tokens.get(++currentTokenIndex);
				return Optional.of( tokenFound);
		} else {
			return  Optional.empty();
		}
	}

	public boolean hasTokenAfterCursor(List<String> tokensToFind) {
		for (int index=currentTokenIndex+1;index<tokens.size();index++) {
			for (String tokenToFind : tokensToFind) {
				if (tokens.get(index).equals(tokenToFind)) {
					return true;
				}		
			}
		}
		return false;	}

	public Optional<String> peekPreviousToken() {
		if (currentTokenIndex==0) {
			return Optional.empty();
		} else {
			String previousToken=tokens.get(currentTokenIndex-1);
			return Optional.of(previousToken);
		}
	}

	public String peekCurrentToken() {
		return tokens.get(currentTokenIndex);
	}

	public void readPreviousToken() {
		if (currentTokenIndex>0) {
			currentTokenIndex--;
		}
	}
	
	
	
	

}
