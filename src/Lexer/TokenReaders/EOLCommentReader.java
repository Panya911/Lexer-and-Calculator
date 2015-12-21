package Lexer.TokenReaders;

public class EOLCommentReader extends DFATokenReader {

	@Override
	protected void initializeDFA() {
		setTokenType("eolc");
        setStartState("start");
        setFinishStates("end");

        addTransition("start", "/", "start_slash");

        addTransition("start_slash", "/", "reading");

        addDefaultTransition("reading", "reading");
        addTransition("reading", "\n", "end");
        addEOLTransition("reading", "end");
	}
}
