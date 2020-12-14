import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.*;
import java.math.BigDecimal;


public class mainEvaluator {
	
    public static int preceden(String op) {
        if (op.equals("*") || op.equals("/") || op.equals("%")){
            return 3;
        } else if (op.equals("+") || op.equals("-")){
            return 2;
        } else
            return 1;
    }
    
    public static String calc(double x, double y, String op){
        String exp = "";
        
        if (op.equals("+")){
            exp = Double.toString(add(x,y));
        } else if (op.equals("*")){
            exp = Double.toString(mult(x,y));
        } else if (op.equals("%")) {
        	exp = Double.toString(mod(x,y));
        } else if (op.equals("-")) {
        	exp = Double.toString(sub(x,y));
        } else if (op.equals("/")) {
        	exp = Double.toString(div(x,y));
        }
        
        return exp;
    }
	
    public static double multi(double x, double y){
        return x * y;
    }
    
    public static double add(double x, double y){
        return x + y;
    }
    
    public static double subt(double x, double y) {
    	return x - y;
    }
    public static double divi(double x, double y) {
    	return x / y;
    }
    
    public static double mod(double x, double y) {
    	System.out.println("X: " + x + "Y: " + y + x%y);
    	return x % y;
    }
    
	public static String evalExpr(String s) {
		String[] s2 = Tokenizer.doTokenize(s);

		double x = 0, y = 0;
		int current = 0, next = 0;
		String op = "";
		String currentOp = "";
		String newV = "";
		boolean d = false;
			
		Stack<String> nums = new Stack<String>();
		Stack<String> ops = new Stack<String>();
		Stack<String> vars = new Stack<String>();
		
		Pattern pat = Pattern.compile("^[A-Za-z]");
		Matcher match = pat.matcher(s2[0]);
		boolean bool = match.matches();
	
		if (!mainParser.checkExprHelp(s) && !bool){
        	return null;
        }
		
		for(int i = 0; i < s2.length; i++) {	
			if (s2[i].contains(".") || s2[i].contains("/"))
				d = true;
			if(bool && s2.length > 1) {
				vars.push(s2[i]);
			} else if (bool && s2.length == 1){
				vars.push(s2[i]);
			}
			
			if (s2[i].equals("") || s2[i].equals("="))
				continue;
			if (mainParser.checkExprHelp(s2[i])){
				nums.push(s2[i]);
			} else if (s2[i].equals("(")){
				ops.push(s2[i]);
			} else if (s2[i].equals(")")) {
				while (!ops.isEmpty() && !ops.peek().equals("(") && nums.size()>1){
					op = ops.pop();
					y = Double.parseDouble(nums.pop());
					x = Double.parseDouble(nums.pop());
					newV = calc(x, y, op);
					nums.push(newV);	
						
				}

				if (!ops.isEmpty() && !ops.peek().equals("("))
					ops.pop();
			} else if (!mainParser.checkExprHelp(s2[i])){
				currentOp = s2[i];
				currentPrec = preceden(currentOp);

				if (!ops.isEmpty()) {
					nextPrec = preceden(ops.peek());
				} else 
					nextPrec = 0;
				if (!ops.isEmpty() && ops.peek().equals("("))
					ops.pop();
				while (!ops.isEmpty() && nextPrec >= currentPrec) {
					op = ops.pop();
					y = Double.parseDouble(nums.pop());
					x = Double.parseDouble(nums.pop());
					newV = calc(x, y, op);
					nums.push(newV);

				}
				ops.push(s2[i]);
			} else if(bool) {
				vars.push(s2[i]);
			}
				
		}
		
		while (!ops.isEmpty() && nums.size() > 1) {
			op = ops.pop();
			y = Double.parseDouble(nums.pop());
			x = Double.parseDouble(nums.pop());
			newV = calc(x, y, op);
			nums.push(newV);	
		}
		
		if (!vars.isEmpty() && nums.isEmpty()) {
	    	return vars.pop();
		} else if (d && !nums.isEmpty()) {
			return nums.pop();
		} else if (!numbers.isEmpty()) {
			BigDecimal num = new BigDecimal(nums.pop());
		    return num.stripTrailingZeros().toPlainString();
	    } else 
	    	return null;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
}
