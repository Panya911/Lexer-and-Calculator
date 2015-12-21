package Calculator.Operands;

public class RealNumber implements IOperand {

    private final double number;

    public RealNumber(double number) {
        this.number = number;
    }

    public double getValue()
    {
        return number;
    }

    @Override
    public IOperand sum(IOperand other) {
        if (!(other instanceof RealNumber))
            return null;
        RealNumber n = (RealNumber) other;
        return new RealNumber(number + n.number);
    }

    @Override
    public IOperand sub(IOperand other) {
        if (!(other instanceof RealNumber))
            return null;
        RealNumber n = (RealNumber) other;
        return new RealNumber(number - n.number);
    }

    @Override
    public IOperand mul(IOperand other) {
        if (!(other instanceof RealNumber))
            return null;
        RealNumber n = (RealNumber) other;
        return new RealNumber(number * n.number);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        if(number-(int)number==0)
            return String.valueOf((int)number);
        return String.valueOf(number);
    }
}
