/**
 * Stack.java
 *
 * A specification of the Stack ADT -- UVic CSC 115, Summer 2017
 *
 */
public interface Stack<E> {

    /*
     * Purpose:
     *   return the number of items currently pushed onto the stack.
     *
     * Returns:
     *   the number of items in the stack
     *
     * Examples:
     *
     * If s is {x, y, z, w}
     *    s.size() returns 4.
     * If s is {}:
     *    s.size() returns 0.
     */
	int size();


    /*
     * Purpose:
     *   returns the boolean state of the stack (empty or not empty)
     *
     * Returns:
     *   false if stack is empty, true otherwise
     *
     * Examples:
     *
     * If s is {x, y, z, w}:
     *    s.isEmpty() returns false.
     * If s is {}:
     *    s.isEmpty() returns true.
     */
	boolean isEmpty();


    /*
     * Purpose:
     *   places the values passed as a parameter onto the top of
     *   the stack.
     *
     * Returns:
     *   nothing.
     *
     * Examples:
     *
     * If s is {x, y, z, w}:
     *   then after s.push(m), s is {a, x, y, z, w} (i.e., top of stack
     *   is the left-most value in the sequence, bottom of stack is
     *   the right-most value in the sequence}.
     * If s is {}:
     *   then after s.push(k), s is {k}.
     */
	void push (E element);


    /*
     * Purpose:
     *   removes the value at the top of the stack (if it exists),
     *   and returns it to the caller. If the stack was empty before
     *   the call, then an exception is thrown by the method.
     *
     * Returns:
     *   The value of type E at the top of the stack (if stack is
     *   non-empty)
     *
     * Examples:
     *
     * If s is {x, y, z, w}:
     *    then after s.pop(), s is {y, z, w} and x is returned to the
     *    caller.
     * If s is {}:
     *    then a StackEmptyException() is thrown.
     */
	E pop() throws StackEmptyException;



    /*
     * Purpose:
     *   returns the value at the top of the stack (if it exists)
     *   but does not modify the contents of the stack. If the stack
     *   was empty before the call, then an exception is thrown
     *   by the method.
     *
     * Returns:
     *   The value of type E at the top of the stack (if stack is
     *   non-empty)
     *
     * Examples:
     *
     * If s is {x, y, z, w}:
     *    then after s.peek(), s is {x, y, z, w} and x is returned to the
     *    caller.
     * If s is {}:
     *    then a StackEmptyException() is thrown.
     */
	E peek() throws StackEmptyException;


    /*
     * Purpose:
     *   clear the stack of all its content.
     *
     * Returns:
     *   nothing.
     *
     * Examples:
     *
     * If s is {x, y, z, w}:
     *   then after s.makeEmpty(), s is {}.
     * If s is {}:
     *   then after s.makeEmpty(), s is {}.
     */
	void makeEmpty();

	/*
	 * Purpose:
	 *	return a string representation of the stack
	 *
	 * Returns:
	 *  A string representation of the stack.
	 *
	 * Examples:
	 *
	 * If is is {x, y, z, w}, s.toString() returns "x y z w"
	 *
	 * If s is {}, s.toString() returns ""
	 */
	 String toString();
}
