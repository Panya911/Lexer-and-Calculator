package Lexer.TokenReaders;

import Calculator.Operands.VariableOperand;

public class IdentifierReader implements ITokenReader{
	public Token tryReadToken(String input) {
		// TODO Задача 1: доделать по аналогии с WhitespaceReader
		// Должен вести себя в полном соответствии со спецификацией
		// IdentifierChars:
		// * JavaLetter
		// * IdentifierChars JavaLetterOrDigit
		//
		// http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
		if(input==null ||input.length()==0)
			return null;
		if ( !Character.isJavaIdentifierStart(input.charAt(0)))
			return null;
		int i=1;
		while(i<input.length() && Character.isJavaIdentifierPart(input.charAt(i)))
			i++;
		String str=input.substring(0, i);
		//todo before return you should check keywords bool and null identifier
		return new Token("id",str,new VariableOperand(str,1,1));
		
	}
}
