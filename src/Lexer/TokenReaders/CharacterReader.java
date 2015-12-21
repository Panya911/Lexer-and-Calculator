package Lexer.TokenReaders;


public class CharacterReader extends DFATokenReader {

    @Override
    protected void initializeDFA() {
        final String oct_digits = "12345670";
        final String hex_digits = "1234567890abcdefABCDEF";
        setTokenType("c");
        setStartState("start");
        setFinishStates("end");

        addTransition("start", "'", "first_char");

        addTransition("first_char", "\\", "escape");
        addDefaultTransition("first_char", "expect_quote");

        addDefaultTransition("escape", "expect_quote");
        addTransition("escape", "u", "escape_u_1");
        addTransition("escape", oct_digits, "escape_number");

        addTransition("escape_number", oct_digits, "escape_8_3");
        addTransition("escape_number", "\'", "end");

        addTransition("escape_u_1", hex_digits, "escape_u_2");
        addTransition("escape_u_2", hex_digits, "escape_u_3");
        addTransition("escape_u_3", hex_digits, "escape_u_4");
        addTransition("escape_u_4", hex_digits, "expect_quote");

        addTransition("escape_8_3", oct_digits, "expect_quote");

        addTransition("expect_quote", "'", "end");
    }

}