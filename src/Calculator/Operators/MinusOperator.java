package Calculator.Operators;

import Calculator.ICalcPart;
import Calculator.MultiOperand;
import Calculator.Operands.IOperand;

import java.util.ArrayList;

public class MinusOperator implements IOperator {

    @Override
    public IOperand apply(IOperand first, IOperand second) {
        IOperand sub = first.sub(second);
        if (sub != null)
            return sub;
        sub = second.sum(first);
        if (sub != null)
            return sub;
        ArrayList<ICalcPart> calcParts = new ArrayList<ICalcPart>();
        calcParts.add(first);
        calcParts.add(new MinusOperator());
        calcParts.add(second);
        return new MultiOperand(calcParts);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String toString()
    {
        return "-";
    }
}
