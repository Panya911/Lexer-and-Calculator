package Lexer.TokenReaders;

public class WordReader implements ITokenReader {
    private final String word;
    private final boolean ignoreCase;

    // Задача 1: Добавить параметр ignoreCase — чувствительность к регистру
    public WordReader(String word) {
        this.word = word;
        this.ignoreCase = false;
    }

    public WordReader(String word, boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        this.word = this.ignoreCase ? word.toLowerCase() : word;
    }

    public Token tryReadToken(String input) {
        if (word.regionMatches(this.ignoreCase, 0, input, 0, word.length()))
            return new Token("kw", input.substring(0, word.length()));
        return null;
    }
}