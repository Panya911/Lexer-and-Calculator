package Lexer.TokenReaders;

import Calculator.ClosedBracket;
import Calculator.OpenedBracket;

public class SeparatorReader implements ITokenReader {
    private static final String SEPARATORS = "(){}[];,.";

    public Token tryReadToken(String input) {
        if (input.length() == 0)
            return null;

        Character symbol = input.charAt(0);
        if (SEPARATORS.indexOf(symbol) != -1) {
            if (symbol == '(' || symbol == '{' || symbol == '[')
                return new Token("separator", symbol.toString(), new OpenedBracket(symbol.charValue()));
            if (symbol == ')' || symbol == '}' || symbol == ']')
                return new Token("separator", symbol.toString(), new ClosedBracket(symbol.charValue()));
            return new Token("separator", symbol.toString(), symbol);
        }
        return null;
    }
}