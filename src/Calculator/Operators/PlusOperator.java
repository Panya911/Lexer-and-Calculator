package Calculator.Operators;

import Calculator.ICalcPart;
import Calculator.MultiOperand;
import Calculator.Operands.IOperand;

import java.util.ArrayList;

public class PlusOperator implements IOperator {
    @Override
    public IOperand apply(IOperand first, IOperand second) {
        IOperand sum = first.sum(second);
        if (sum != null)
            return sum;
        sum= second.sum(first);
        if (sum!=null)
            return sum;
        ArrayList<ICalcPart> calcParts= new ArrayList<ICalcPart>();
        calcParts.add(first);
        calcParts.add(new PlusOperator());
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
        return "+";
    }
}
