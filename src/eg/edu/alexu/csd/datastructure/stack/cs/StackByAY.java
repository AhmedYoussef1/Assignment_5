
package eg.edu.alexu.csd.datastructure.stack.cs;

public class StackByAY implements IStack {
    
    private class Node {
        Object element;
        Node next;
    }
    
    private Node top = null;
    /**
    * Removes the element at the top of stack and returns that element.
    *
    * @return top of stack element, or through exception if empty
    */
    @Override
    public Object pop() {
        if(this.isEmpty())
            throw new RuntimeException("Stack is empty");
        Object temp = top.element;
        top = top.next;
        return temp;
    }
    /**
    * Get the element at the top of stack without removing it from stack.
    *
    * @return top of stack element, or through exception if empty
    */
    @Override
    public Object peek() {
        if(this.isEmpty())
            throw new RuntimeException("Stack is empty");
        return top.element;
    }
    /**
    * Pushes an item onto the top of this stack.
    *
    * @param element
    * to insert
    */
    @Override
    public void push(Object element) {
        Node newnode = new Node();
        newnode.element = element;
        newnode.next = top;
        top = newnode;
    }
    /**
    * Tests if this stack is empty
    *
    * @return true if stack empty
    */
    @Override
    public boolean isEmpty() {
        return top==null;
    }
    /**
    * Returns the number of elements in the stack.
    *
    * @return number of elements in the stack
    */
    @Override
    public int size() {
        Node cur = top;
        int size = 0;
        while(cur!= null){
            size++;
            cur = cur.next;
        }
        return size;
    }
    /**
    * Print elements of this stack from top to down
    */
    public void print() {
        Node cur = top;
        while(cur!= null){
            System.out.println(cur.element);
            cur = cur.next;
        }
    }
    
}
