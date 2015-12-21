package Calculator.Operands;

public class VariableOperand implements IOperand {

    private final double count;
    private final String str;
    private final int power;

    public VariableOperand(String str, double count, int power) {
        this.count = count;
        this.str = str;
        this.power = power;
    }

    @Override
    public IOperand sum(IOperand other) {
        if (other instanceof VariableOperand) {
            VariableOperand operand = (VariableOperand) other;
            if (power != operand.power)
                return null;
            if (str.equals(operand.str))
                return new VariableOperand(str, count + operand.count, power);
        }
        return null;
    }

    @Override
    public IOperand sub(IOperand other) {
        if (other instanceof VariableOperand) {
            VariableOperand operand = (VariableOperand) other;
            if (power != operand.power)
                return null;
            if (str.equals(operand.str))
                return new VariableOperand(str, count - operand.count, power);
        }
        return null;
    }

    @Override
    public IOperand mul(IOperand other) {
        if (other instanceof RealNumber)
            return new VariableOperand(str, ((RealNumber) other).getValue() * count, power);
        if (other instanceof VariableOperand && ((VariableOperand) other).str.equals(str)) {
            int newPower = power + ((VariableOperand) other).power;
            if (str.equals("i") && newPower % 2 == 0)
                return new RealNumber(-1 * count);
            return new VariableOperand(str, count, newPower);
        }
        return null;
    }

    @Override
    public String toString() {
        if (count == 0)
            return "0";
        String result = "";
        for (int i = 0; i < power; i++)
            result += '*' + str;
        return new RealNumber(count).toString() + result;
    }
}
