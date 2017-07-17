/**
 * Prepared for UVic CSC 115, Summer 2017, Assignment #3.
 *
 * This class encapsulates the information needed to represent
 * tokens in a simple arithmetic expression. Some other structure
 * is needed to store a sequence of ExpressionTokens.
 */
 
 
public class ExpressionToken {
    int kind;  
    int value;  // Only used if ExpressionToken instance is an OPERAND

    /**
     * List of kinds of ExpressionTokens. Everything below OPERAND
     * represents either a delimiter (i.e., parentheses) or an
     * operator.
     */ 
    public final static int OPERAND = 0;
    public final static int LEFT_PAREN = 1;
    public final static int RIGHT_PAREN = 2;
    public final static int UNKNOWN = 3;
    public final static int ADD = 4;
    public final static int SUBTRACT = 5;
    public final static int MULTIPLY = 6;
    public final static int DIVIDE = 7;
    public final static int MODULO = 8;


    /**
     * PURPOSE:
     *
     * Constructor accepts a string as an argument, and returns 
     * an appropriately initialized instance of ExpressionToken.
     */ 
    public ExpressionToken(String token) {
        value = 0;

        try {
            if (token.charAt(0) == '+') {
                kind = ADD;
            } else if (token.charAt(0) == '-') {
                kind = SUBTRACT;
            } else if (token.charAt(0) == '*') {
                kind = MULTIPLY;
            } else if (token.charAt(0) == '/') {
                kind = DIVIDE;
            } else if (token.charAt(0) == '%') {
                kind = MODULO;
            } else if (token.charAt(0) == '(') {
                kind = LEFT_PAREN;
            } else if (token.charAt(0) == ')') {
                kind = RIGHT_PAREN;
            } else {
                kind = OPERAND;
                value = Integer.parseInt(token);
            }
        }
        catch (NumberFormatException nfe) {
            /* If we reach this point, then something has gone
             * horribly wrong and the token type is UNKNOWN.
             */
            kind = UNKNOWN;
        } 
    }


    /**
     * PURPOSE:
     *
     * Constructor accepts both the *kind* of ExpressionToken
     * to be instantiated and a *value*. Note that it doesn't matter
     * what is passed to *value* if *kind* is something other
     * than OPERAND; however, just for the sake of consistency,
     * when creating something other than an OPERAND, *value* is
     * set to zero.
     */ 
    public ExpressionToken(int kind, int value) {
        this.kind = kind;

        if (this.kind == OPERAND) {
            this.value = value;
        } else {
            this.value = 0;
        }
    }


    /**
     * PURPOSE:
     *
     * Creates and returns a string representation of the state
     * of the ExpressionToken. This is meant to support the work
     * of debugging a solution to A#3.
     */ 
    public String toString() {
        switch (kind) {
            case OPERAND:
                return "operand: " + value;
            case ADD:
                return "op: +";
            case SUBTRACT:
                return "op: -";
            case MULTIPLY:
                return "op: *";
            case DIVIDE:
                return "op: /";
            case MODULO:
                return "op: %";
            case LEFT_PAREN:
                return "op: (";
            case RIGHT_PAREN:
                return "op: )";
            default:
                return "<????>";
        }
    }


    /**
     * PURPOSE:
     *
     * Compare two instance of ExpressionToken (the one upon which
     * equals() is invoked, and the one passed in as a parameter).
     * For safety the code double-checks to ensure the parameter *o*
     * is indeed an ExpressionToken instance.
     */ 
    public boolean equals(Object o) {
        if (!(o instanceof ExpressionToken)) {
            return false;
        }

        ExpressionToken et = (ExpressionToken)o;
        if (et.kind != this.kind) {
            return false;
        }

        if (et.kind == OPERAND && et.value != this.value) {
            return false;
        }

        return true;
    }


    /**
     * PURPOSE:
     *
     * Returns an integer value that represents the precedence of
     * the operator stored in the ExpressionToken.
     *
     * Of course, sometime the ExpressioToken is *not* an operator,
     * in which case the precedence returned is zero. Otherwise
     * a value is returned such that the relative order of two
     * operators (i.e., "*" has higher precedence than "+") is
     * reflected in the values returned.
     *
     * For more information on Java operator prececedence, have a
     * look at: http://bit.ly/1x445G5
     */ 
    public int precedence() {
        if (kind == OPERAND) {
            return 0;
        }

        switch(kind) {
            case OPERAND:
            case LEFT_PAREN:
            case RIGHT_PAREN:
                return 0;
            case ADD:
            case SUBTRACT:
                return 11;
            case MULTIPLY:
            case DIVIDE:
            case MODULO:
                return 12;
            default:
                return 0;
        }
    }
}
