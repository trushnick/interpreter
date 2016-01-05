package com.github.trushnick.interpreter;

import java.util.List;

/**
 * Created by trushnick on 15.11.15.
 */
public class Interpreter {

    private List<Token> input;
    private int result = 0;

    public Interpreter(List<Token> input) {
        this.input = input;
    }

    public int calculate() throws IllegalExpressionException {
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).getName() == Token.TokenType.OP1
                    || input.get(i).getName() == Token.TokenType.OP2) {
                makeOperation(i);
                i = i - 2;
            }
        }
        result = Integer.parseInt(input.get(0).getValue());
        return result;
    }

    private void makeOperation(int index) throws IllegalExpressionException {
        if (index - 2 < 0)
            throw new IllegalExpressionException();
        Token operation = input.get(index);
        int secondOperand = Integer.parseInt(input.get(index - 1).getValue());
        int firstOperand = Integer.parseInt(input.get(index - 2).getValue());
        int resultOfOperation = 0;
        switch (operation.getValue().charAt(0)) {
            case '*': {
                resultOfOperation = firstOperand * secondOperand;
                break;
            }
            case '/': {
                resultOfOperation = firstOperand / secondOperand;
                break;
            }
            case '+': {
                resultOfOperation = firstOperand + secondOperand;
                break;
            }
            case '-': {
                resultOfOperation = firstOperand - secondOperand;
            }
        }
        input.remove(index);
        input.remove(index - 1);
        input.set(index - 2, new Token(Token.TokenType.NUMBER, String.valueOf(resultOfOperation)));
    }

}
