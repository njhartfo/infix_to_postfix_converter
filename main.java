import java.util.Stack; 
import java.util.Scanner;
  
public class InfixToPostfix 
{ 
    // sets priority value of operator, higher value higher priority
    static int setPriority(char c) 
    { 
        if(c == '+' || c == '-') { return 1; }
        if(c == '*' || c == '/') { return 2; }
        if(c == '^'){ return 3; }
        else{ return -1; }
    } 
       
    static String InToPost(String equation) 
    { 
        // empty string to hold postfix 
        String postfix = new String(""); 
          
        // empty stack for operators
        Stack<Character> operators = new Stack<>(); 
          
        for (int i = 0; i < equation.length(); ++i) 
        { 
            char c = equation.charAt(i); 
              
            // add operand to result 
            if (Character.isLetterOrDigit(c)) { postfix += c; } 
               
            // push '(' to the stack
            else if (c == '(') { operators.push(c); } 
              
            // pop ')' and output from the stack  
            // until you hit a '('
            else if (c == ')') 
            { 
                while (!operators.isEmpty() && operators.peek() != '(') { postfix += operators.pop(); }
                operators.pop(); 
            } 
            else // an operator is encountered 
            { 
                while (!operators.isEmpty() && setPriority(c) <= setPriority(operators.peek())) { postfix += operators.pop(); } 
                operators.push(c); 
            } 
       
        } 
       
        // pop all the operators from the stack 
        while (!operators.isEmpty())
        { 
            if(operators.peek() == '(') { return "Invalid Expression"; }
            postfix += operators.pop(); 
        } 
        return postfix; 
    } 
    
    // input loop for additional equations
    // modified version of query from ConvertDemonstration.java from
    public static boolean question(Scanner input, String prompt)
    {
        String answer;
        
        System.out.print(prompt + " [Y or N]: ");
        answer = input.nextLine( ).toUpperCase( );
        
        while (!answer.startsWith("Y") && !answer.startsWith("N"))
        {
            System.out.print("Invalid response. Please type Y or N: ");
            answer = input.nextLine( ).toUpperCase( );
        }

      return answer.startsWith("Y");
    }
    
    // Driver method  
    public static void main(String[] args)  
    { 
        Scanner input = new Scanner(System.in);
        String equation;
        String answer;
        
        System.out.println("Enter an equation made form unsigned numbers and operators.");
        
        do
        {
            System.out.print("Enter your expression: ");
            equation = input.nextLine();
            answer = InToPost(equation);
            System.out.println("The postfix expression is: " + answer);
        }while(question(input, "Enter another equation?"));
    }
}
