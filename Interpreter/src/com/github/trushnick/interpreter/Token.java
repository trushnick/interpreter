package com.github.trushnick.interpreter;

/**
 * Created by trushnick on 15.11.15.
 */
public class Token {

    public enum TokenType {
        OP1("[\\*/]"), OP2("[\\+\\-]"),
        LB("\\("), RB("\\)"),
        NUMBER("[0-9]+");

        public final String pattern;

        private TokenType(String pattern) {
            this.pattern = pattern;
        }
    }

    private TokenType name;
    private String value;

    Token(TokenType name, String value) {
        this.name = name;
        this.value = value;
    }

    public TokenType getName() {
        return name;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("<%s,%s>", name, value);
    }
}
