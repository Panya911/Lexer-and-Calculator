package Lexer.TokenReaders;

public class StringReader extends DFATokenReader {

    @Override
    protected void initializeDFA() {
        setTokenType("s");
        setStartState("start");
        setFinishStates("end");

        addTransition("start", "\"", "reading");

        addDefaultTransition("reading", "reading");
        addTransition("reading", "\\", "escape");
        addTransition("reading", "\"", "end");
        addEOLTransition("reading", "null");

        addDefaultTransition("escape", "reading");
    }
}