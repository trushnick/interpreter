package com.github.trushnick.interpreter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by trushnick on 15.11.15.
 */
public class InfixToPostfix {

    private Deque<Token> operationStack = new LinkedList<Token>();
    private String input;
    private List<Token> output = new ArrayList<Token>();

    InfixToPostfix(String input) {
        this.input = input;
    }

    public List<Token> convert() throws IllegalExpressionException {
        String regExp = buildRegExp();
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(input);
        Token tempToken;
        while (matcher.find()) {
            if (matcher.group(Token.TokenType.OP1.name()) != null) {
                tempToken = new Token(Token.TokenType.OP1, matcher.group(Token.TokenType.OP1.name()));
                gotOperation(tempToken, 1);
            } else if (matcher.group(Token.TokenType.OP2.name()) != null) {
                tempToken = new Token(Token.TokenType.OP2, matcher.group(Token.TokenType.OP2.name()));
                gotOperation(tempToken, 0);
            } else if (matcher.group(Token.TokenType.LB.name()) != null) {
                tempToken = new Token(Token.TokenType.LB, matcher.group(Token.TokenType.LB.name()));
                operationStack.push(tempToken);
            } else if (matcher.group(Token.TokenType.RB.name()) != null) {
                gotRightBracket();
            } else if (matcher.group(Token.TokenType.NUMBER.name()) != null) {
                tempToken = new Token(Token.TokenType.NUMBER, matcher.group(Token.TokenType.NUMBER.name()));
                output.add(tempToken);
            }
        }
        while ( !operationStack.isEmpty() )
            output.add(operationStack.pop());
        return output;
    }
    public List<Token> getOutput() {
        return output;
    }

    private String buildRegExp() {
        StringBuilder pattern = new StringBuilder();
        for (Token.TokenType tokenType : Token.TokenType.values())
            pattern.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        System.out.println(pattern.substring(1));
        return pattern.substring(1);
    }
    private void gotOperation(Token token, int prior) {
        if (prior == 0) {
            while ( !operationStack.isEmpty() && operationStack.peek().getName() != Token.TokenType.LB)
                output.add(operationStack.pop());
        } else {
            while ( !operationStack.isEmpty() && operationStack.peek().getName() != Token.TokenType.LB
                    && operationStack.peek().getName() != Token.TokenType.OP2)
                output.add(operationStack.pop());
        }
        operationStack.push(token);
    }
    private void gotRightBracket() throws IllegalExpressionException {
        while ( operationStack.peek().getName() != Token.TokenType.LB
                && !operationStack.isEmpty() )
            output.add(operationStack.pop());
        if ( operationStack.isEmpty() )
            throw new IllegalExpressionException();
        operationStack.pop();
    }

}
