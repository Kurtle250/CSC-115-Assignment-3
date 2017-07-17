/**
 * Prepared for UVic CSC 115, Summer 2017, Assignment #3.
 */

/*
 * IMPORTANT:
 *
 * Code in this method use the "for-each" loop. Some students
 * may prefer to old-school "for" loop as taught in CSC 110/111,
 * and some students may even want to play around with 
 * listIterator() method of the LinkedList class and use this
 * as part of code for a loop.ø
 *
 * Official documentation on the LinkedList class can be found
 * at: http://bit.ly/2txwoxS
 */

import java.util.List;
import java.util.LinkedList;


public class PostfixMachine {

    /**
     * PURPOSE:
     *
     *   Given a string corresponding to some arithmetic expression,
     *   return a list of ExpressionToken where each token in the
     *   original string corresponds to one token in the resulting
     *   list.
     *   
     *   This method simply tokenizes the list. It performs
     *   absolutely no conversions from infix to postfix or back. In
     *   fact, syntactically incorrect expressions can also be
     *   tokenized (i.e., some other code needs to determine if
     *   the resulting list of tokens is a valid expression).
     *
     *   There is one severe limitation with this code in that
     *   negative numbers (i.e., -100) cannot be expressed in that
     *   way. To represent -100 we must instead write 0 - 100 (i.e.,
     *   the tokenizer cannot distinguish between unary and binary
     *   uses of the "-" symbol).
     *
     * EXAMPLES:
     *
     *   If expression = "2" then the list returned is:
     *       { (OPERAND, 2) }
     *
     *   If expression = "32 + 413 * 5" then the list returned is:
     *       { (OPERAND, 32), (PLUS, 0), (OPERAND, 413),
     *         (MULTIPLY, 0), (OPERAND, 5) }
     *
     *   If expression = "( ( 3 ( %" then the list returned is:
     *       { (LEFT_PAREN, 0), (LEFT_PAREN, 0), (OPERAND, 3),
     *         (LEFT_PAREN, 0), (MODULO, 0) }
     */
    public static List<ExpressionToken> tokenize(String expression) {
        LinkedList<ExpressionToken> list = new LinkedList<>();

        String symbols[] = {"\\(", "\\)", "\\+", "\\-", "\\*", "\\/"};

        for (String regex : symbols) {
            String replace = " " + regex + " ";
            expression = expression.replaceAll(regex, replace);
        }

        expression = expression.trim();

        String[] tokens = expression.split("\\s+");
        for (String t : tokens) {
            ExpressionToken et = new ExpressionToken(t);
            list.add(et);
        }

        return list;
    }


    /**
     * PURPOSE:
     *   Given a list of ExpressionTokens, evaluate them as
     *   a postfix expression, and return the result. All
     *   operands are integers. If an error occurs during
     *   evaluation (e.g., too few operands; too many operands;
     *   unrecognized operator; a division-by-zero is being 
     *   attempted) then throw a SyntaxErrorException.
     *
     *
     * EXAMPLES:
     *
     *   If list = { (OPERAND, 3), (OPERAND, 5), (PLUS, 0) }
     *   then the value returned is 8.
     *
     *   If list = { (OPERAND, 10), (OPERAND, 2), (DIVIDE, 0) }
     *   then the value returned is 5.
     *
     *   If list = { (OPERAND, 2), (PLUS, 0) } then a
     *   SyntaxErrorException is thrown.
     *
     *   If list = { (OPERAND, 10), (OPERAND, 2), (OPERAND, 0) }
     *   then a SyntaxErrorException is thrown.
     *
     *   If list = { (OPERAND, 10), (OPERAND, 0), (DIVIDE, 0) }
     *   then a SyntaxErrorException is thrown.
     *
     *   If list = { (OPERAND, 10), (OPERAND, 30), (UNKNOWN, 0) }
     *   then a SyntaxErrorException is thrown.
     */
    public static int eval(List<ExpressionToken> postfixExpression) {
        
        StackLinked<Integer> stack = new StackLinked<>();

        int op1 = 0, op2 = 0,result = 0;

        for( ExpressionToken token : postfixExpression){
            
            if( token.kind == 0 ){
                stack.push(token.value);
            }else if (token.kind == 3){
                throw new SyntaxErrorException("unknown value in stack");

            }else {
            try{
                op1 = stack.pop();
                op2 = stack.pop();
                }catch(StackEmptyException e){
                    throw new SyntaxErrorException("not valid postfix expression");
                }

                
                switch(token.kind){

                    case 4 : result = op2 + op1;
                    break;
                    case 5 : result = op2 - op1;
                    break;
                    case 6 : result = op2 * op1;
                    break;
                    case 7 : 
                        if( op1 == 0 ){
                            throw new SyntaxErrorException("Cannot divide by zero");
                    }
                    result = op2 / op1;
                    break;
                    case 8 :
                        if( op1 == 0 ){
                            throw new SyntaxErrorException("Cannot divide by zero");
                    }
                    result = op2 % op1;
                }
                stack.push(result);
                
            }

        }
        if( stack.size() > 1 || stack.isEmpty()) {
            throw new SyntaxErrorException("To many elements in stack");
        }

        return stack.pop();
    }

    /**
     * PURPOSE:
     *   Given a list of ExpressionTokens, and assume the list
     *   represents an infix expression, return a list that
     *   represents the postfix equivalent. If an error
     *   occurs during evaluation (e.g., missing parenthesis;
     *   unknown operator) then throw a SyntaxErrorException.
     *
     *
     * EXAMPLES:
     *
     *   If list = { (OPERAND, 3), (PLUS, 0), (OPERAND, 5) }
     *   then the list returned is { (OPERAND, 3), (OPERAND, 5),
     *   (PLUS, 0) }
     *
     *   If list = { (OPERAND, 10), (PLUS, 0), (OPERAND, 20), 
     *   (MULTIPLY, 0), (OPERAND, 30) }
     *   then the list returned is { (OPERAND, 10), (OPERAND, 20),
     *   (OPERAND, 30), (MULTIPLY, 0), (PLUS, 0) }
     *
     *   If list = { (LEFT_PAREN, 0), (OPERAND, 10), (PLUS, 0),
     *   (OPERAND, 20), (RIGHT_PAREN, 0), (MULTIPLY, 0), (OPERAND 30) }
     *   then the list returned is { (OPERAND, 10), (OPERAND, 20),
     *   (PLUS, 0), (OPERAND, 30), (MULTIPLY, 0) }
     *
     *   If list = { (OPERAND, 2), (UNKNOWN, 0), (OPERAND, 5) } then
     *   a SyntaxErrorException is thrown.
     *
     *   If list = { (OPERAND, 2), (RIGHT_PAREN, 0) } then a
     *   SyntaxErrorException is thrown.
     */
    public static List<ExpressionToken> infix2prefix(List<ExpressionToken> infixExpression){   
        
        StackLinked<ExpressionToken> stack = new StackLinked<>();
        List<ExpressionToken> list = new LinkedList<>();

        for(ExpressionToken token : infixExpression){
            if(token.kind == ExpressionToken.OPERAND){
                list.add(token);
            } else if (token.kind == ExpressionToken.LEFT_PAREN){
                stack.push(token);
            } else if(token.kind > 3 && token.kind < 9 ){
                while(!stack.isEmpty() && stack.peek().value != ExpressionToken.LEFT_PAREN &&
                 token.precedence()<= stack.peek().precedence()){
                        list.add(stack.pop());
                }
                stack.push(token);     
            } else if(token.kind == ExpressionToken.RIGHT_PAREN){
                while(!stack.isEmpty() && stack.peek().kind != ExpressionToken.LEFT_PAREN){
                    list.add(stack.pop());                   
                }
                if(!stack.isEmpty()){
                    stack.pop();
                }else{
                    throw new SyntaxErrorException("stack is empty");
                }
            }  

        }
        while(!stack.isEmpty()){
                list.add(stack.pop());
        }
        return list;
    }
}


