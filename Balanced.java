import java.util.Stack;

public class Balanced {
	
	
	static boolean checkBalanced(String s) { 
		Stack<Character> newStack = new Stack<Character>();
			for (int i = 0; i < s.length(); i++) {		
				
				if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '<' || s.charAt(i) == '{') {
					newStack.push(s.charAt(i));
				}	

				if (s.charAt(i) == ')') {
					if (newStack.isEmpty() || newStack.peek() != '(') 
						return false;
					else newStack.pop();
				} else if (s.charAt(i) == ']') {
					if (newStack.isEmpty() || newStack.peek() != '[') 
						return false;
					else newStack.pop();
				} else if (s.charAt(i) == '>') {
					if (newStack.isEmpty() || newStack.peek() != '<') 
						return false;
					else newStack.pop();
				} else if (s.charAt(i) == '}') {
					if (newStack.isEmpty() || newStack.peek() != '{') 
						return false;
					else newStack.pop();
				} 
				
			}
		
		if (!newStack.isEmpty()) 
			return false;
		else
			return true;
	}
			

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}