package Lexer;

import Lexer.TokenReaders.*;

import java.util.ArrayList;

public interface ICodeLexer {
	ArrayList<Token> divideOnLexem(String input);
}
