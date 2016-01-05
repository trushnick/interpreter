package com.github.trushnick.interpreter;

/**
 * Created by trushnick on 15.11.15.
 */
public class IllegalExpressionException extends Exception {

    public IllegalExpressionException() {
        super();
    }
    public IllegalExpressionException(String msg) {
        super(msg);
    }

}
