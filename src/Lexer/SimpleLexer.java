package Lexer;

import Lexer.TokenReaders.Token;
import Lexer.TokenReaders.ITokenReader;

import java.util.ArrayList;

public class SimpleLexer implements ICodeLexer {

    private ITokenReader[] readers;

    public SimpleLexer(ITokenReader[] readers) {
        this.readers = readers;
    }

    @Override
    public ArrayList<Token> divideOnLexem(String input) {
        ArrayList<Token> tokens = new ArrayList<>();
        while (hasNextTokens(input)) {
            Token token = readNextToken(input);
            tokens.add(token);
            int length = token.getText().length();
            input = input.substring(length);
        }
        return tokens;
    }


    public Token readNextToken(String input) {
        Token maxToken = null;
        for (ITokenReader reader : readers) {
            Token token = reader.tryReadToken(input);
            if (maxToken == null && token != null) {
                maxToken = token;
                continue;
            }
            if (token != null && token.getText().length() > maxToken.getText().length())
                maxToken = token;
        }
        if (maxToken != null)
            return maxToken;
        return null;
    }

    public boolean hasNextTokens(String input) {
        for (ITokenReader reader : readers) {
            Token token = reader.tryReadToken(input);
            if (token != null)
                return true;
        }
        return false;
    }
}
