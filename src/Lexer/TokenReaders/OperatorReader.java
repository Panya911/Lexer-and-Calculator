package Lexer.TokenReaders;

import Calculator.Operators.MinusOperator;
import Calculator.Operators.MulOperator;
import Calculator.Operators.PlusOperator;

import java.util.Arrays;
import java.util.List;

public class OperatorReader implements ITokenReader {
    private static final List<String> OPERATORS = Arrays.asList("=", ">", "<", "!", "~", "?", ":", "==", "<=", ">=", "!=",
            "&&", "||", "++", "--", "+", "-", "*", "/", "&", "|", "^", "%", "<<", ">>", ">>>", "+=", "-=",
            "*=", "/=", "&=", "|=", "^=", "%=", "<<=", ">>=", ">>>=");
    private static final String SYMBOLS = "=><!~?:&|+-*/^%";

    public Token tryReadToken(String input) {
        if (input.length() == 0)
            return null;

        int i = 0;
        while (i < input.length() && SYMBOLS.indexOf(input.charAt(i)) != -1)
            i++;


        String token = input.substring(0, i);
        while (!OPERATORS.contains(token) && i > 0) {
            i--;
            token = input.substring(0, i);
        }

        if (i == 0)
            return null;
        if (token.equals("+"))
            return new Token("operator", token, new PlusOperator());
        if (token.equals("-"))
            return new Token("operator", token, new MinusOperator());
        if (token.equals("*"))
            return new Token("operator", token, new MulOperator());
        return new Token("operator", token);
    }
}
