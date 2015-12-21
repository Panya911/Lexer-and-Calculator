package Lexer.TokenReaders;


import Calculator.ICalcPart;
import Calculator.Operands.IOperand;
import Calculator.Operands.Vectors.Vector;
import Calculator.Operands.Vectors.VectorND;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class VectorTokenReader implements ITokenReader {

    private final ArrayList<ITokenReader> readers;

    public VectorTokenReader(IntReader intReader, IdentifierReader identifierReader,
                             OperatorReader operatorReader) {
        readers = new ArrayList<>();
        readers.add(intReader);
        readers.add(identifierReader);
        readers.add(operatorReader);
    }

    @Override
    public Token tryReadToken(String input) {
        if (input.length() == 0)
            return null;
        if (input.charAt(0) == '(') {
            int index = 0;
            ArrayList<IOperand> parts = new ArrayList<>();
            do {
                index++;
                Token maxToken = null;
                for (ITokenReader reader : readers) {
                    Token token = reader.tryReadToken(input.substring(index));
                    if (maxToken == null && token != null) {
                        maxToken = token;
                        continue;
                    }
                    if (token != null && token.getText().length() > maxToken.getText().length())
                        maxToken = token;
                }
                if (maxToken != null)
                    parts.add((IOperand) maxToken.getValue());
                else return null;
                index += maxToken.getText().length();
            } while (input.charAt(index) == ',');
            if (input.charAt(index) == ')')
                return new Token("vector", input.substring(0, index + 1), new VectorND(parts));
        }
        return null;
    }
}
