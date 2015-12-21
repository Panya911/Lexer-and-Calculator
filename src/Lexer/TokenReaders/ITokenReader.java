package Lexer.TokenReaders;

public interface ITokenReader {
	Token tryReadToken(String input);
}
