package Calculator.Operators;


import Calculator.ICalcPart;
import Calculator.MultiOperand;
import Calculator.Operands.IOperand;

import java.util.ArrayList;

public class MulOperator implements IOperator {

    @Override
    public IOperand apply(IOperand first, IOperand second) {
        IOperand mul = first.mul(second);
        if (mul != null)
            return mul;
        mul= second.mul(first);
        if (mul!=null)
            return mul;
        ArrayList<ICalcPart> calcParts= new ArrayList<>();
        calcParts.add(first);
        calcParts.add(new MulOperator());
        calcParts.add(second);
        return new MultiOperand(calcParts);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String toString()
    {
        return "*";
    }
}
