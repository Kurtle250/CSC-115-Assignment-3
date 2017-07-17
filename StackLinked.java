public class StackLinked <T>  implements Stack<T>{

    private StackNode<T> top;
    
    private int count;

   


    public StackLinked(){
        top = null;
        count = 0;
    }

    
    public int size() {

        return count;
    }

    public boolean isEmpty() {
        
        return count == 0;
    }


    public void push(T data) {

       StackNode<T> tmp = new StackNode<>(data);
        tmp.next = top;
        top = tmp;
        count++;
    }


    public  T pop() throws StackEmptyException{
        
        if(isEmpty()){
            throw new StackEmptyException();
        }
        T value = top.data; 
        top = top.next;
        count--;

    return value;
    }


    public T peek() throws StackEmptyException{
        
       if(isEmpty()){
            throw new StackEmptyException();
        }
        T value = top.getValue();
        
    return value;
        
    }


    public void makeEmpty() {
        
        while(!isEmpty()){
            pop();
        }
    }


    /** 
     * PURPOSE:
     *   
     * Creates and returns a string representation of the contents
     * of the Stack. This is meant to support the work of debugging
     * a solution to A#3.
     */ 
    public String toString() {
        return "";
    }
}

