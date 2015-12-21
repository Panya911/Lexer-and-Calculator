package Calculator;

import Calculator.Operands.IOperand;
import Calculator.Operators.IOperator;
import Lexer.ICodeLexer;
import Lexer.TokenReaders.Token;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator {

    private final ICodeLexer lexer;

    public Calculator(ICodeLexer lexer) {
        this.lexer = lexer;
    }

    public String Calculate(String expression) {
        ArrayList<Token> tokens = lexer.divideOnLexem(expression);
        ArrayList<ICalcPart> parts = toCalcPartsList(tokens);
        parts = toPostfixNotation(parts);
        return evaluateExpression(parts).toString();
    }


    private IOperand evaluateExpression(ArrayList<ICalcPart> postfixNotation) {
        Stack<IOperand> answer = new Stack<>();
        for (ICalcPart part : postfixNotation) {
            if (part instanceof IOperand) {
                answer.push((IOperand) part);
                continue;
            }
            if (part instanceof IOperator) {
                IOperand second = answer.pop();
                IOperand first = answer.pop();
                answer.push(((IOperator) part).apply(first, second));
            }
        }
        return answer.peek();
    }

    private ArrayList<ICalcPart> toPostfixNotation(ArrayList<ICalcPart> infixNotation) {
        ArrayList<ICalcPart> result = new ArrayList<>();
        Stack<ICalcPart> stack = new Stack<>();
        for (ICalcPart part : infixNotation) {
            if (part instanceof IOperand) {
                result.add(part);
                continue;
            }
            if (part instanceof IBracket) {
                //check on correct
                IBracket bracket = (IBracket) part;
                if (bracket.isOpened())
                    stack.push(bracket);
                else {
                    ICalcPart top = stack.peek();
                    while (!(top instanceof IBracket)) {
                        result.add(stack.pop());
                        top = stack.peek();
                    }
                    stack.pop();
                }
                continue;
            }
            if (part instanceof IOperator) {
                IOperator operator = (IOperator) part;
                while (!stack.isEmpty() && stack.peek() instanceof IOperator &&
                        ((IOperator) stack.peek()).getPriority() >= operator.getPriority()) {
                    result.add(stack.pop());
                }
                stack.push(operator);
                continue;
            }
            assert false;
        }
        while (!stack.isEmpty())
            result.add(stack.pop());
        return result;
    }

    private ArrayList<ICalcPart> toCalcPartsList(ArrayList<Token> tokens) {
        ArrayList<ICalcPart> parts = new ArrayList<>();
        for (Token t : tokens) {
            ICalcPart part;
            try {
                part = (ICalcPart) t.getValue();
            } catch (ClassCastException e) {
                return null; //todo
            }
            parts.add(part);
        }
        return parts;
    }
}
