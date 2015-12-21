package Calculator.Operands;

import Calculator.ICalcPart;

public interface IOperand extends ICalcPart {
    IOperand sum(IOperand other);
    IOperand sub(IOperand other);
    IOperand mul(IOperand other);
}
