
package eg.edu.alexu.csd.datastructure.stack.cs;

import java.util.Scanner;

public class UI_ExpressionEvaluator {
    
    private static Scanner input = new Scanner(System.in);
    private static ExpressionEvaluatorByAY ee = new ExpressionEvaluatorByAY();
    
    public static void main(String[] args) {
        boolean flag = true;
        while(flag){
            System.out.println("Please choose an action : ");
            System.out.println("(1) infix to postfix");
            System.out.println("(2) evaluate infix");
            System.out.println("(3) evaluate postfix");
            System.out.println("(4) Exit");
            char action = input.nextLine().charAt(0);
            switch(action){
                case '1' :  
                        System.out.println("Postfix : "+ UI_INToPO(ReadExpression()));
                        System.out.println("----------------------------------"); 
                        break;
                case '2' : 
                        System.out.println("value is : "+ UI_evaluateIN(ReadExpression())); 
                        System.out.println("----------------------------------"); 
                        break;
                case '3' :
                        System.out.println("value is : "+ UI_evaluatePO(ReadExpression())); 
                        System.out.println("----------------------------------"); 
                        break;
                case '4' :  flag= false; break;
                default : System.out.println("Invalid input"); break;
            }
        } 
    }
    private static String ReadExpression() {
        System.out.println("Enter the expression : ");
        return input.nextLine();
    }
    private static String UI_INToPO(String exp){
        try{
            return ee.infixToPostfix(exp);
        }
        catch(RuntimeException r){
            System.out.println(r.getMessage());
            return UI_INToPO(ReadExpression());
        }
    }
    private static int UI_evaluateIN(String exp){
        String postfix = UI_INToPO(sympolValue(exp));
        try{
            return ee.evaluate(postfix);
        }
        catch(RuntimeException r){
            System.out.println(r.getMessage());
            return UI_evaluateIN(ReadExpression());
        }
    }
    private static int UI_evaluatePO(String exp){
        try{
            return ee.evaluate(sympolValue(exp));
        }
        catch(RuntimeException r){
            System.out.println(r.getMessage());
            System.out.println("check expression and make sure you use space as separator");
            return UI_evaluatePO(ReadExpression());
        }
    }
    private static String sympolValue(String exp){
        StringBuilder ex = new StringBuilder();
        for(int i=0; i<exp.length(); i++){
            if(Character.isDigit(exp.charAt(i)) || exp.charAt(i)==' '
                || ee.isOperator(exp.charAt(i)) || exp.charAt(i)=='(' || exp.charAt(i)==')')
                ex.append(exp.charAt(i));
            else if(Character.isAlphabetic(exp.charAt(i))){
                StringBuilder temp = new StringBuilder();
                while(i<exp.length()&&Character.isAlphabetic(exp.charAt(i))){
                    temp.append(exp.charAt(i++));
                }
                i--;
                System.out.println("value of "+temp.toString()+" is : ");
                String te = input.nextLine();
                ex.append(te);
                for(int j=0; j<te.length(); j++)
                    if(!Character.isDigit(te.charAt(j))){
                        System.out.println("Invalid input");
                        return sympolValue(exp);
                    }
            }
        }
        return ex.toString();
    }
}
