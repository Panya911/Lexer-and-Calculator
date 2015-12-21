package Default;

import Calculator.Calculator;
import Lexer.ICodeLexer;
import Lexer.SimpleLexer;
import Lexer.TokenReaders.*;

public class Program {
    public static void main(String[] args) {
        ICodeLexer lexer = initializeLexer();
        Calculator calc = new Calculator(lexer);
        System.out.println(calc.Calculate("15+(2,a,6)+2*i*i+2-(1,b,15)+c+15*c+(1,2)"));
    }

    private static ICodeLexer initializeLexer() {
        ITokenReader[] readers = new ITokenReader[]
                {
                        new SeparatorReader(),
                        new OperatorReader(),
                        new IntReader(),
                        new VectorTokenReader(new IntReader(),new IdentifierReader(),new OperatorReader()),
                        new IdentifierReader()
                };
        return new SimpleLexer(readers);
    }
}
