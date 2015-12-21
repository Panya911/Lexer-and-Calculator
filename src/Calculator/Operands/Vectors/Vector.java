package Calculator.Operands.Vectors;

import Calculator.Operands.IOperand;
import Calculator.Operators.MinusOperator;
import Calculator.Operators.PlusOperator;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Vector implements IOperand {
    protected final ArrayList<IOperand> coordinates;

    public Vector(ArrayList<IOperand> coordinates) {
        this.coordinates = coordinates;
    }

    private int getDimensionalCount() {
        return coordinates.size();
    }

    @Override
    public IOperand sum(IOperand otherVector) {
        if (!(otherVector instanceof Vector))
            return null;
        Vector v = (Vector) otherVector;
        if (v.getDimensionalCount() != this.getDimensionalCount())
            return null;
        IOperand[] newCoordinates = new IOperand[getDimensionalCount()];
        PlusOperator plus=new PlusOperator();
        for (int i = 0; i < newCoordinates.length; i++) {
            assert i < 0 || i > coordinates.size() : "i out of bounds " + i;
            newCoordinates[i]= plus.apply(coordinates.get(i),v.coordinates.get(i));
        }
        return buildVector(newCoordinates);
    }

    @Override
    public IOperand sub(IOperand otherVector) {
        if (!(otherVector instanceof Vector))
            return null;
        Vector v = (Vector) otherVector;
        if (v.getDimensionalCount() != this.getDimensionalCount())
            return null;
        IOperand[] newCoordinates = new IOperand[getDimensionalCount()];
        MinusOperator minus=new MinusOperator();
        for (int i = 0; i < newCoordinates.length; i++) {
            assert i < 0 || i > coordinates.size() : "i out of bounds " + i;
            newCoordinates[i] =minus.apply(coordinates.get(i),v.coordinates.get(i));

        }
        return buildVector(newCoordinates);
    }

    @Override
    public IOperand mul(IOperand scalar) {
//        double[] newCoordinates = new double[coordinates.length];
//        for (int i = 0; i < newCoordinates.length; i++) {
//            newCoordinates[i] = coordinates[i] * scalar;
//        }
//        return buildVector(newCoordinates);
        return null;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        for (IOperand operand : coordinates) {
            builder.append(operand.toString());
            builder.append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(')');
        return builder.toString();
    }

    protected abstract Vector buildVector(IOperand[] coordinates);
}
