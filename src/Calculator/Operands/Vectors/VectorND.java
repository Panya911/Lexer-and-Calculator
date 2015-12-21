package Calculator.Operands.Vectors;


import Calculator.Operands.IOperand;

import java.util.ArrayList;

public class VectorND extends Vector {

    public VectorND(ArrayList<IOperand> coordinates) {
        super(coordinates);
    }

    @Override
    protected Vector buildVector(IOperand[] coordinates) {

        ArrayList<IOperand> operands = new ArrayList<>();
        for (IOperand coordinate : coordinates)
            operands.add(coordinate);
        return new VectorND(operands);
    }
}
