
package eg.edu.alexu.csd.datastructure.stack.cs;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExpressionEvaluatorByAYIT {

    ExpressionEvaluatorByAY eet = new ExpressionEvaluatorByAY();
            
    @Test
    public void test(){
        
        assertEquals("5 2 10 * + ",eet.infixToPostfix( "5+2*10" ));
        assertEquals(25,eet.evaluate("5 2 10 * + "));
        
        assertEquals("3 21 5 + * ",eet.infixToPostfix( "3*(21+5)" ));
        assertEquals(78,eet.evaluate("3 21 5 + * "));
        
        assertEquals("0 5 - 0 2 - * 4 2 0 3 - * + + ",eet.infixToPostfix("-5*-2+(4+2*-3)"));
        assertEquals(8,eet.evaluate("0 5 - 0 2 - * 4 2 0 3 - * + + "));
        
        assertEquals("13 5 200 4 * * 90 / + 3 * ",eet.infixToPostfix("(((13+5*(200*4)/90))*3)"));
        assertEquals(172,eet.evaluate("13 5 200 4 * * 90 / + 3 * "));
        
        assertEquals("A 0 B - C * 0 D - / + 0 F + - ",eet.infixToPostfix("A+-B*C/-D-+F"));
        
        try{
            eet.infixToPostfix( "(5+2" );
        }
        catch(RuntimeException r){
            assertEquals("Please Check expression parentheses",r.getMessage());
        }
        try{
            eet.infixToPostfix( "((3+5*(2*4)/9))*3)" );
        }
        catch(RuntimeException r){
            assertEquals("Please Check expression parentheses",r.getMessage());
        }
        try{
            eet.infixToPostfix( "3*/2+5/4" );
        }
        catch(RuntimeException r){
            assertEquals("Invalid expression at character number 3",r.getMessage());
        }
        try{
            eet.infixToPostfix( "/3*2+5/4" );
        }
        catch(RuntimeException r){
            assertEquals("Invalid expression at character number 1",r.getMessage());
        }
        try{
            eet.infixToPostfix( "(A+B)C" );
        }
        catch(RuntimeException r){
            assertEquals("Invalid expression at character number 5",r.getMessage());
        }
        try{
            eet.evaluate( "4 0 / " );
        }
        catch(RuntimeException r){
            assertEquals("Can't divide by zero!",r.getMessage());
        }
        try{
            eet.evaluate( "4 0 " );
        }
        catch(RuntimeException r){
            assertEquals("Invalid expression",r.getMessage());
        }
        try{
            eet.evaluate( "4 B + " );
        }
        catch(RuntimeException r){
            assertEquals("Invalid expression",r.getMessage());
        }
        
    }
    
}
