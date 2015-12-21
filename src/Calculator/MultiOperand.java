package Calculator;

import Calculator.Operands.IOperand;
import Calculator.Operators.MinusOperator;
import Calculator.Operators.MulOperator;
import Calculator.Operators.PlusOperator;

import java.util.ArrayList;

public class MultiOperand implements IOperand {

    private final ArrayList<ICalcPart> internalParts;

    public MultiOperand(ArrayList<ICalcPart> parts) {
        internalParts = parts;
    }

    @Override
    public IOperand sum(IOperand other) {
        if (other instanceof MultiOperand) {
            MultiOperand mo = (MultiOperand) other;
            return sumWithMultioperand(mo);
        }

        return sumWithSimpleOperand(other);
    }

    private IOperand sumWithMultioperand(MultiOperand mo) {
        ArrayList<ICalcPart> newInternalParts = new ArrayList<>();
        for (int i = 0; i < mo.internalParts.size(); i += 2) {
            {
                boolean success = false;
                IOperand second = (IOperand) mo.internalParts.get(i);
                for (int j = 0; j < internalParts.size(); j += 2) {
                    IOperand first = (IOperand) internalParts.get(j);
                    IOperand result = first.sum(second);
                    if (result != null) {
                        newInternalParts.set(j, result);
                        success = true;
                    }
                }
                if (!success) {
                    newInternalParts.add(new PlusOperator());
                    newInternalParts.add(second);
                }
            }
        }
        return new MultiOperand(newInternalParts);
    }

    private IOperand sumWithSimpleOperand(IOperand other) {
        ArrayList<ICalcPart> newInternalParts = (ArrayList<ICalcPart>) internalParts.clone();
        for (int i = 0; i < newInternalParts.size(); i++) {
            if (newInternalParts.get(i) instanceof IOperand) {
                IOperand result = ((IOperand) newInternalParts.get(i)).sum(other);
                if (result != null) {
                    newInternalParts.set(i, result);
                    return new MultiOperand(newInternalParts);
                }
            }
        }
        newInternalParts.add(new PlusOperator());
        newInternalParts.add(other);
        return new MultiOperand(newInternalParts);
    }

    @Override
    public IOperand sub(IOperand other) {
        if (other instanceof MultiOperand) {
            MultiOperand mo = (MultiOperand) other;
            return subWithMultiOperand(mo);
        }
        return subWithSimpleOperand(other);
    }

    private IOperand subWithMultiOperand(MultiOperand mo) {
        ArrayList<ICalcPart> newInternalParts = new ArrayList<>();
        for (int i = 0; i < mo.internalParts.size(); i += 2) {
            {
                boolean success = false;
                IOperand second = (IOperand) mo.internalParts.get(i);
                for (int j = 0; j < internalParts.size(); j += 2) {
                    IOperand first = (IOperand) internalParts.get(j);
                    IOperand result = first.sub(second);
                    if (result != null) {
                        newInternalParts.set(j, result);
                        success = true;
                    }
                }
                if (!success) {
                    newInternalParts.add(new MinusOperator());
                    newInternalParts.add(second);
                }
            }
        }
        return new MultiOperand(newInternalParts);
    }

    private IOperand subWithSimpleOperand(IOperand other) {
        ArrayList<ICalcPart> newInternalParts = (ArrayList<ICalcPart>) internalParts.clone();
        for (int i = 0; i < newInternalParts.size(); i++) {
            if (newInternalParts.get(i) instanceof IOperand) {
                IOperand result = ((IOperand) newInternalParts.get(i)).sub(other);
                if (result != null) {
                    newInternalParts.set(i, result);
                    return new MultiOperand(newInternalParts);
                }
            }
        }
        newInternalParts.add(new MinusOperator());
        newInternalParts.add(other);
        return new MultiOperand(newInternalParts);
    }

    @Override
    public IOperand mul(IOperand other) {
        if (other instanceof MultiOperand) {
            MultiOperand mo = (MultiOperand) other;
            return mulWithMultiOperand(mo);
        }
        return mulWithSimpleOperand(other);
    }

    private IOperand mulWithMultiOperand(MultiOperand mo) {
        ArrayList<ICalcPart> newInternalParts = new ArrayList<>();
        for (int i = 0; i < mo.internalParts.size(); i += 2) {
            {
                boolean success = false;
                IOperand second = (IOperand) mo.internalParts.get(i);
                for (int j = 0; j < internalParts.size(); j += 2) {
                    IOperand first = (IOperand) internalParts.get(j);
                    IOperand result = first.mul(second);
                    if (result != null) {
                        newInternalParts.set(j, result);
                        success = true;
                    }
                }
                if (!success) {
                    newInternalParts.add(new MulOperator());
                    newInternalParts.add(second);
                }
            }
        }
        return new MultiOperand(newInternalParts);
    }

    private IOperand mulWithSimpleOperand(IOperand other) {
        ArrayList<ICalcPart> newInternalParts = (ArrayList<ICalcPart>) internalParts.clone();
        for (int i = 0; i < newInternalParts.size(); i++) {
            if (newInternalParts.get(i) instanceof IOperand) {
                IOperand result = ((IOperand) newInternalParts.get(i)).mul(other);
                if (result != null) {
                    newInternalParts.set(i, result);
                    return new MultiOperand(newInternalParts);
                }
            }
        }
        newInternalParts.add(new MulOperator());
        newInternalParts.add(other);
        return new MultiOperand(newInternalParts);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (ICalcPart part : internalParts)
            builder.append(part.toString());
        return builder.toString();
    }
}
