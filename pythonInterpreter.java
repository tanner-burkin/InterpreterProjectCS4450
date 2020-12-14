import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pythonInterpreter {
	
	static HashMap<String, String> mainMap = new HashMap<>();
	
	public static boolean checkVar(String str){
		Pattern pat = Pattern.compile("^[A-Za-z][\\_]*[\\w]*");
		Matcher match = pat.matcher(str);
		boolean bool = match.matches();
		return bool;
	}
	
	
	public static void interpret(String str){
		String[] tokens = Tokenizer.doTokenize(str);

		if (checkVar(tokens[0])){
			if (tokens.length > 2 && tokens[1].equals("=")){
				str = "";
				for (int i = 0; i < tokens.length; i++){
					if (checkVar(tokens[i]) && i >= 2){
						tokens[i] = mainMap.get(tokens[i]).toString();
					} 
					str = str + tokens[i];
				}
				mainMap.put(tokens[0], Evaluator.evalExpr(str));
			} else if (tokens.length == 1 && (mainMap.containsKey(str))){
				System.out.println(mainMap.get(tokens[0]));
			} else if (tokens[0].equals("print")){
				System.out.println(Evaluator.evalExpr(str.substring(5,str.length())));
			} 
		} else if (SimpleParser.isExprHelper(str)) {
			System.out.println(Evaluator.evalExpr(str));
		} 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.print(">>> ");
			String newString = scanner.nextLine();
			
			if (newString.equals("quit")) {
				System.exit(0);
			} else 
				interpret(newString);

		}

	}

}
