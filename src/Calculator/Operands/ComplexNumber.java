package Calculator.Operands;

//
//public class ComplexNumber implements IOperand {
//
//    private final double realPart;
//    private final double imaginaryPart;
//
//    public double getRealPart()
//    {
//        return realPart;
//    }
//
//    public double getImaginaryPart()
//    {
//        return imaginaryPart;
//    }
//
//    public ComplexNumber(double realPart, double imaginaryPart)
//    {
//        this.realPart=realPart;
//        this.imaginaryPart=imaginaryPart;
//    }
//
//    @Override
//    public IOperand sum(IOperand other) {
//        if(other instanceof ComplexNumber)
//        {
//            ComplexNumber c=(ComplexNumber)other;
//            return new ComplexNumber(realPart+c.realPart,imaginaryPart+c.imaginaryPart);
//        }
//        return null;
//    }
//
//    @Override
//    public IOperand sub(IOperand other) {
//        if(other instanceof ComplexNumber)
//        {
//            ComplexNumber c=(ComplexNumber)other;
//            return new ComplexNumber(realPart-c.realPart,imaginaryPart-c.imaginaryPart);
//        }
//        return null;
//    }
//
//    @Override
//    public IOperand mul(IOperand other) {
//        return null;
//    }
//}
