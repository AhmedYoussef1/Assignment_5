
package eg.edu.alexu.csd.datastructure.stack.cs;

import org.junit.Test;
import static org.junit.Assert.*;

public class StackByAYIT {
    
    StackByAY stack = new StackByAY();
    
    @Test
    public void test(){
        
        // test exceptions when creating new stack
        try{
            stack.peek();
        }
        catch(RuntimeException r){
            assertEquals("Stack is empty",r.getMessage());
        }
        try{
            stack.pop();
        }
        catch(RuntimeException r){
            assertEquals("Stack is empty",r.getMessage());
        }
        
        // test peek(), pop(), and size()
        stack.push("ahmed");
        stack.push(5.3);
        stack.push("youssef");
        
        assertEquals(3,stack.size());
        assertEquals("youssef",stack.peek());
        
        stack.pop();
        
        assertEquals(5.3,stack.pop());
        assertEquals("ahmed",stack.peek());
        
        stack.pop();
        
        assertEquals(0,stack.size());
        
        // test exceptions after pop all elements
        try{
            stack.peek();
        }
        catch(RuntimeException r){
            assertEquals("Stack is empty",r.getMessage());
        }
        try{
            stack.pop();
        }
        catch(RuntimeException r){
            assertEquals("Stack is empty",r.getMessage());
        }       
        
    }
    
    
}
