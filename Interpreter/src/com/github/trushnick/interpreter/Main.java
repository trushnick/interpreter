package com.github.trushnick.interpreter;

import java.util.List;

/**
 * Created by trushnick on 15.11.15.
 */
public class Main {

    public static void main(String[] args) throws IllegalExpressionException {
        System.out.println("Infix form: 53*(21+13)");
        InfixToPostfix itp = new InfixToPostfix("5+3*(5-(3+1)))");
        List<Token> tokenList = itp.convert();
        System.out.println("Postfix form: ");
        for (Token token : tokenList)
            System.out.println(token);
        Interpreter interpreter = new Interpreter(tokenList);
        int result = interpreter.calculate();
        System.out.println("Result: " + result);
    }

}
