package Lexer.TokenReaders;

import java.util.*;

public abstract class DFATokenReader implements ITokenReader {
	private String startState;
	private List<String> finishStates;
	private List<String> invalidStates;
	private final Map<String, Map<String, String>> transitions;
	private String tokenType;

	protected abstract void initializeDFA();
	
	public DFATokenReader() {
		transitions = new HashMap<>();
		initializeDFA();
	}

	protected void setTokenType(String s) {
		tokenType = s;
	}

	protected void setStartState(String s) {
		startState = s;
	}

	protected void setInvalidStates(String... states) {
		invalidStates = new ArrayList<String>();
		for (String el : states)
			invalidStates.add(el);
	}

	protected void setFinishStates(String... states) {
		finishStates = new ArrayList<String>();
		for (String el : states)
			finishStates.add(el);
		finishStates.add("null");
	}

	protected void addTransition(String fromState, String symbols, String toState) {
		if (!transitions.containsKey(fromState)) {
			transitions.put(fromState, new HashMap<>());
			transitions.get(fromState).put(null, "null");
		}
		transitions.get(fromState).put(symbols, toState);
	}

	protected void addDefaultTransition(String fromState, String toState) {
		addTransition(fromState, null, toState);
	}

	protected void addEOLTransition(String fromState, String toState) {
		addTransition(fromState, "", toState);
	}

	public Token tryReadToken(String input){
		String state = startState;
		int i = 0;
		int lastRight = i;
		while (!finishStates.contains(state)) {
			if (i < input.length()) {
				char symbol = input.charAt(i);
				state = getNextState(state, symbol);
				i += 1;
				if (invalidStates == null || !(invalidStates.contains(state)))
					lastRight = i;
			} else {
				state = getEOLState(state);
				break;
			}
		}
		if (lastRight == 0 || state.equals("null") || !finishStates.contains(state))
			return null;
		return new Token(this.tokenType, input.substring(0, lastRight));
	}

	
	private String getNextState(String state, char symbol){
		for (String symbols : transitions.get(state).keySet())
			if (symbols != null && symbols.indexOf(symbol) != -1)
				return transitions.get(state).get(symbols);
			return transitions.get(state).get(null);
	}

	private String getEOLState(String state){
		return transitions.get(state).containsKey("") ? transitions.get(state).get("") : state;
	}
}