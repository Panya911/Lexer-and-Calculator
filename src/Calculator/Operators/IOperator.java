package Calculator.Operators;


import Calculator.ICalcPart;
import Calculator.Operands.IOperand;

public interface IOperator extends ICalcPart{
    IOperand apply(IOperand first, IOperand second);
    int getPriority();
}
