
package eg.edu.alexu.csd.datastructure.stack.cs;

public class ExpressionEvaluatorByAY implements IExpressionEvaluator {
    /**
    * Takes a symbolic/numeric infix expression as input and converts it to
    * postfix notation. There is no assumption on spaces between terms or the
    * length of the term (e.g., two digits symbolic or numeric term)
    *
    * @param expression
    * infix expression
    * @return postfix expression
    */
    @Override
    public String infixToPostfix(String expression) {
        this.isVaildExpression(expression);
        StackByAY itp = new StackByAY();
        StringBuilder postfix = new StringBuilder();
        for(int i = 0; i<expression.length(); i++) {
            if(expression.charAt(i)=='('){
                String temp[] = this.parentheses(expression, i);
                postfix.append(this.infixToPostfix(temp[0]));
                i = Integer.parseInt(temp[1]);
            }
            else if( !this.isOperator(expression.charAt(i)) ){
                StringBuilder temp = new StringBuilder();
                while(i<expression.length() && !this.isOperator(expression.charAt(i)))
                    temp.append(expression.charAt(i++));
                postfix.append(temp); postfix.append(" ");
                i--;
            }
            else if( (expression.charAt(i)=='+'|| expression.charAt(i)=='-') && (i==0
                    || this.isOperator(expression.charAt(i-1)) || expression.charAt(i-1)=='(')){
                postfix.append("0 ");
                if(expression.charAt(i)=='+') itp.push('p');
                if(expression.charAt(i)=='-') itp.push('n');
                
            }
            else{
                if(itp.isEmpty()){
                    itp.push(expression.charAt(i));
                }
                else{
                    if(isHigher(expression.charAt(i),(Character)itp.peek())){
                        itp.push(expression.charAt(i));
                    }
                    else{
                        while(!itp.isEmpty() && !isHigher(expression.charAt(i),(Character)itp.peek())){
                            if((Character)itp.peek()=='n'){
                                postfix.append('-'); itp.pop();
                            }
                            else if((Character)itp.peek()=='p'){
                                postfix.append('+'); itp.pop(); 
                            }
                            else
                                postfix.append(itp.pop());
                            postfix.append(" ");
                        }
                        itp.push(expression.charAt(i));
                    }
                }
            }
        }
        while(!itp.isEmpty()){
            if((Character)itp.peek()=='n'){
                postfix.append('-'); itp.pop();
            }
            else if((Character)itp.peek()=='p'){
                postfix.append('+'); itp.pop(); 
            }
            else
                postfix.append(itp.pop());
            postfix.append(" ");
        }
        return postfix.toString();
    }
    /**
    * Evaluate a postfix numeric expression, with a single space separator
    *
    * @param expression
    * postfix expression
    * @return the expression evaluated value
    */
    @Override
    public int evaluate(String expression) {
        if(expression.length()==0) throw new RuntimeException("Expression not found");
        StackByAY eva = new StackByAY();
        for(int i=0; i<expression.length(); i++){
            if(!isOperator(expression.charAt(i)) && expression.charAt(i)!=' '){
                StringBuilder num = new StringBuilder("");
                while(i<expression.length() && expression.charAt(i) != ' ')
                    num.append(expression.charAt(i++));
                try{eva.push((double)Integer.parseInt(num.toString()));}
                catch(Exception r){
                    throw new RuntimeException("Invalid expression");
                }
            }
            else if(isOperator(expression.charAt(i))){
                double temp1, temp2;
                try{
                    temp1 = (double)eva.pop(); temp2 = (double)eva.pop(); 
                }
                catch(RuntimeException r){
                    throw new RuntimeException("Invalid expression");
                }
                switch(expression.charAt(i)){
                    case '+' : eva.push(temp2 + temp1);  break; 
                    case '-' : eva.push(temp2 - temp1);  break; 
                    case '*' : eva.push(temp2 * temp1);  break; 
                    case '/' : if(temp1==0)
                                    throw new RuntimeException("Can't divide by zero!");
                                eva.push(temp2 / temp1);  break;
                }
            }     
        }
        if(eva.size()!=1)
            throw new RuntimeException("Invalid expression");
        return (int)Math.round((double)eva.peek());
    }
    /**
     * check if character is an operator character
     * @param c
     * @return true if operator character found
     */
    public boolean isOperator(char c) {
        return (c == '+' || c == '-' || c=='/' || c=='*');
    }
    /**
     * check priority of two operator characters
     * @param f
     * @param s
     * @return true if f is higher priority than s
     */
    private boolean isHigher(char f, char s){
        return ( ( (f=='n' || f=='p') && (s!='n'||s!='p') ) || ((f=='*' || f=='/') && (s=='+' || s=='-')) );
    }
    /**
     * @param exp
     * @param start
     * @return the expression between two parentheses
     */
    private String[] parentheses(String exp, int start) {
        StringBuilder temp = new StringBuilder();
        StackByAY p = new StackByAY();
        p.push(exp.charAt(start++));
        while(!p.isEmpty()){
            if(exp.charAt(start)=='(')        p.push(exp.charAt(start));
            else if(exp.charAt(start)==')')   p.pop();
            if(!p.isEmpty())                  temp.append(exp.charAt(start));
            start++;
        }
        return new String[]{temp.toString(),Integer.toString(start-1)};
    }
    /**
     * check for validation of expression to be converted to postfix
     * @param exp 
     */
    public void isVaildExpression(String exp){
        StackByAY p = new StackByAY();
        if(this.isOperator(exp.charAt(exp.length()-1)))
            throw new RuntimeException("Invalid expression at last character");
        for(int i=0; i<exp.length(); i++){
            if(!(this.isOperator(exp.charAt(i)) || Character.isDigit(exp.charAt(i)) || 
               Character.isAlphabetic(exp.charAt(i)) || exp.charAt(i)=='(' || exp.charAt(i)==')'))
                throw new RuntimeException(
                        String.format("Invalid expression at character number %d, you can use +,-,*,/,(,) only",i+1));
            if(exp.charAt(i)=='(')
                p.push('(');
            else if(exp.charAt(i)==')'){
                if(p.isEmpty())
                    throw new RuntimeException("Please Check expression parentheses");
                else
                    p.pop();
            }
            if( (exp.charAt(i)=='*' || exp.charAt(i)=='/') && 
                (i==0 || isOperator(exp.charAt(i-1)) || exp.charAt(i-1)=='(' || exp.charAt(i+1)==')' || i==exp.length()-1))
                throw new RuntimeException(
                         String.format("Invalid expression at character number %d",i+1));
            if(  (i!=0 && exp.charAt(i)=='(') && (!this.isOperator(exp.charAt(i-1)) && exp.charAt(i-1)!='(') )
                throw new RuntimeException(
                         String.format("Invalid expression at character number %d",i+1));
            if(  (i!=exp.length()-1 && exp.charAt(i)==')') && (!this.isOperator(exp.charAt(i+1)) && exp.charAt(i+1)!=')') )
                throw new RuntimeException(
                         String.format("Invalid expression at character number %d",i+1));
        }
        if(!p.isEmpty())
            throw new RuntimeException("Please Check expression parentheses");
    }
    
}
