package Lexer.TokenReaders;

public class WhitespaceReader implements ITokenReader {
	/**
	 * ������ � ������ ������ ������������ ���������� ���������� ��������.
	 * 
	 * @return ���������� ����������� ������� ������.
	 */
	public Token tryReadToken(String input) {
		if(input==null || input.length()==0)
			return null;
		int i = 0;
		int len = input.length();
		while (i < len && Character.isWhitespace(input.charAt(i)))
			i++;
		return new Token("ws", input.substring(0, i));
	}
}
