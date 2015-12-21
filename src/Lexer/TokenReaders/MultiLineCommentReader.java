package Lexer.TokenReaders;

public class MultiLineCommentReader extends DFATokenReader {
	@Override
	protected void initializeDFA() {
		setTokenType("mlc");
		setStartState("start");
		setFinishStates("end");

		addTransition("start", "/", "start_slash");

		addTransition("start_slash", "*", "reading");

		addDefaultTransition("reading", "reading");
		addTransition("reading", "*", "asterisk");
		addEOLTransition("reading", "null");

		addDefaultTransition("asterisk", "reading");
		addTransition("asterisk", "/", "end");
		addEOLTransition("asterisk", "null");
	}
}
