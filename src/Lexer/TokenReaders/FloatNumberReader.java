package Lexer.TokenReaders;

public class FloatNumberReader extends DFATokenReader {

	@Override
	protected void initializeDFA() {
		final String digits = "1234567890";
        setTokenType("f");
        setStartState("start");
        setFinishStates("end", "noread_end");
        setInvalidStates("e_begin_part", "e_digit_part", "noread_end", "first_dot");

        addTransition("start", digits, "integer_part");
        addTransition("start", ".", "first_dot");

        addTransition("first_dot", digits, "float_part");
        addTransition("first_dot", "e", "e_begin_part");
        addEOLTransition("first_dot", "end");
        addDefaultTransition("first_dot", "noread_end");

        addTransition("integer_part", digits, "integer_part");
        addTransition("integer_part", ".", "float_part");
        addTransition("integer_part", "e", "e_begin_part");
        addTransition("integer_part", "fd", "end");

        addTransition("float_part", digits, "float_part");
        addTransition("float_part", "e", "e_begin_part");
        addTransition("float_part", "fd", "end");
        addEOLTransition("float_part", "end");
        addDefaultTransition("float_part", "noread_end");

        addTransition("e_begin_part", digits, "e_part");
        addTransition("e_begin_part", "+-", "e_digit_part");
        addDefaultTransition("e_begin_part", "end");
        addEOLTransition("e_begin_part", "end");

        addTransition("e_part", digits, "e_part");
        addTransition("e_part", "fd", "end");
        addEOLTransition("e_part", "end");
        addDefaultTransition("e_part", "end");

        addTransition("e_digit_part", digits, "e_part");
	}
}
