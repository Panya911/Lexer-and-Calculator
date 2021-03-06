package Lexer.TokenReaders;

import java.util.Arrays;
import java.util.List;

public class KeywordReader implements ITokenReader {
    static final List<String> KEYWORDS = Arrays.asList("abstract", "continue", "for", "new", "switch",
            "assert", "default", "if", "package", "synchronized", "boolean", "do", "goto", "private", "this", "break",
            "double", "implements", "protected", "throw", "byte", "else", "import", "public", "throws", "case", "enum",
            "instanceof", "return", "transient", "catch", "extends", "int", "short", "try", "char", "final",
            "interface", "static", "void", "class", "finally", "long", "strictfp", "volatile", "const", "float",
            "native", "super", "while", "true", "false", "null");

    public Token tryReadToken(String input) {
        if (input.length() == 0 || !Character.isJavaIdentifierStart(input.charAt(0))) {
            return null;
        }

        int i =  1;
        while (i < input.length() && Character.isJavaIdentifierPart(input.charAt(i))) {
            i++;
        }

        String token = input.substring(0, i);
        if (KEYWORDS.contains(token))
            return new Token("keyword", token);
        return null;
    }
}
