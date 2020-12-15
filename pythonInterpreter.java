import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pythonInterpreter {
	
	static HashMap<String, String> mainMap = new HashMap<>();
	static String[] linesOfCode = new String[1000];
	
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
				mainMap.put(tokens[0], mainEvaluator.evalExpr(str));
			} else if (tokens.length == 1 && (mainMap.containsKey(str))){
				System.out.println(mainMap.get(tokens[0]));
			} else if (tokens[0].equals("print")){
				System.out.println(mainEvaluator.evalExpr(str.substring(5,str.length())));
			} 
		} else if (mainParser.checkExprHelp(str)) {
			System.out.println(mainEvaluator.evalExpr(str));
		} 
	}
	
	public static String[] lines() {
		List<String> stringList = new ArrayList<String>();
		String newLine = new String();
	    
	    try {
	    	File file = new File("pythonCode.py");
	        Scanner scan = new Scanner(file);        
	        
	        while (scan.hasNextLine()) {
	        	newLine = scan.nextLine();
	        	if(!newLine.isEmpty()) {
		        	if(newLine.charAt(0) != '#') {
		        		stringList.add(newLine);
		        	}
	        	}
	        }
	        String[] strings = new String[ stringList.size() ];
	        stringList.toArray( strings );
	        
	        scan.close();
	        
		    return strings;

	      } catch (FileNotFoundException e) {
	    	  System.out.println(e);
	    	  System.out.println("File Not Found");
	      }	   
	    return null;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
				
		linesOfCode = lines();

		for(int i = 0; i < linesOfCode.length; i++) {
			if (linesOfCode[i].equals("quit")) {
				System.exit(0);
			}
				
			interpret(linesOfCode[i]);
		}
		
		
		

	}
	

}
