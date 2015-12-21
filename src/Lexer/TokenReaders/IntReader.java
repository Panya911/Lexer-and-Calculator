package Lexer.TokenReaders;

import Calculator.Operands.RealNumber;

public class IntReader implements ITokenReader {

    public Token tryReadToken(String input) {
        int system;
        // http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.1

        if (input.length() == 0) {
            return null;
        }
        char sym;

        system = getSystem(input);

        if (system == 0)
            return null;

        if ((system == 2 || system == 16) && input.length() == 2)
            return new Token("i", "0", new RealNumber(0));


        int i = (system == 16 || system == 2) ? 2 : 0;
        for (; i < input.length(); i++) {
            sym = input.charAt(i);
            if (isCorrectDigit(sym, system) || isCorrectUnderscore(input, i, system))
                continue;
            else
                break;
        }

        String token = input.substring(0, i);
        boolean isLong = input.length() > i && Character.toLowerCase(input.charAt(i)) == 'l';
        if (isLong) {
            token = token + input.charAt(i);
        }
        return new Token("i", token, new RealNumber(toValue(token, system, isLong)));
    }

    private boolean isCorrectDigit(char sym, int system) {
        sym = Character.toLowerCase(sym);
        return (sym >= '0' && sym < ('0' + system)) // || sym == '_'
                || (system > 10 && sym >= 'a' && sym <= ('a' + system - 10));
    }

    private boolean isCorrectUnderscore(String input, int i, int system) {
        if (input.charAt(i) != '_') {
            return false;
        }
        while (input.length() > ++i) {
            char sym = input.charAt(i);
            if (sym == '_')
                continue;
            else if (isCorrectDigit(sym, system))
                return true;
            else
                return false;
        }
        return false;
    }

    private int getSystem(String input) {
        char sym = input.charAt(0);
        if (sym == '0') {
            if (input.length() == 1)
                return 10;
            sym = Character.toLowerCase(input.charAt(1));
            if (sym == 'x')
                return 16;
            else if (sym == 'b')
                return 2;
            else if (sym >= '1' && sym <= '7')
                return 8;
            else
                return 10;

        } else if (sym >= '1' && sym <= '9')
            return 10;
        return 0;
    }

    private long toValue(String input, int system, boolean isLong) {
        int offset = system == 16 || system == 2 ? 2 : 0;
        if (isLong)
            return Long.parseLong(input.substring(offset, input.length() - 1).replaceAll("_", ""), system);
        else {
            try {
                return Integer.parseInt(input.substring(offset).replaceAll("_", ""), system);
            } catch (NumberFormatException e) {
                return Long.parseLong(input.substring(offset, input.length() - 1).replaceAll("_", ""), system);
            }
        }
    }
}
