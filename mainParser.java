import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.String;
import java.util.Scanner;

public class mainParser {
	
	static boolean checkID(String[] s){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length; i++){
			sb.append(s[i]);
		}
		String s2 = sb.toString();

		
		if(s2 == null)
			return false;

		Pattern pat = Pattern.compile(".*[\\d]");
		Matcher match = pat.matcher(s2);
		boolean bool = match.matches();
		return bool;
		
	}
	
	static boolean checkFactor(String[] s){
		String[] subArray = {};
		
		if (s.length < 1)
			return false;

		boolean openP = false;
		boolean closeP = false;
		int openI = -1;
		int closeI = -1;
		
		for (int i = 0; i < s.length; i++){
			if (s[i].equals("(")) {
				openP = true;
				openI = i;
			} 
			if (s[i].equals(")")){
				closeP = true;
				closeI = i;
			}
		}
		
		if (openP && closeP) {
			subArray = Arrays.copyOfRange(s, openI, closeI - 1);
			return (checkExpr(subArray));
		}
		else if (openP && !closeP) {
			subArray = Arrays.copyOfRange(s, openI + 1, s.length);
			return checkExpr(subArray);
		} else if (closeP && !openP) {
			subArray = Arrays.copyOfRange(s, 0, closeI);
			return checkExpr(subArray);
		} else {
			return checkID(s);
		}
		
	}

	static boolean checkTerm(String[] s){
		String[] l = {};
		String[] r = {};
		
		if (s.length < 1)
			return false;
		
		int start = 0;
		int end = s.length;
		boolean multi = false;
		int multiI = -1;

		
		for (int i = 0; i < s.length; i++){
			if (s[i].equals("*") || s[i].equals("/") || s[i].equals("%")) {
				multi = true;
				multiI = i;
			}
		}
		
		if (multi) {
			l = Arrays.copyOfRange(s, start, multiI);
			r = Arrays.copyOfRange(s,  multiI+1, end);
			return ((checkTerm(l)) && checkFactor(r));
		}
		else
			return checkFactor(s);
	
	}
	
	static boolean checkExprHelp(String s){
		String[] st = {};
		if (Balanced.checkBalanced(s)) {
			st = Tokenizer.doTokenize(s);
			return checkExpr(st);

		} else {
			return Balanced.checkBalanced(s);
		}
	}

	static boolean checkExpr(String[] s){
		String[] l = {};
		String[] r = {};
		
		if (s.length < 1) 
			return false;
		int start = 0;
		int end = s.length;
		boolean plus = false;
		int plusI = -1;
		
		for (int i = 0; i < s.length; i++){
			if (s[i].equals("+") || s[i].equals("-")) {
				plus = true;
				plusI = i;
			}
		}

		if (plus) {
			l = Arrays.copyOfRange(s, start, plusI);
			r = Arrays.copyOfRange(s,  plusI+1, end);
			return ((checkExpr(l)) && checkTerm(r));
		}
		else
			return checkTerm(s);
	}
	
	public static void main(String[] args) {

		System.out.println(checkExprHelp("123"));
		System.out.println(checkExprHelp("1 + 3"));
		System.out.println(checkExprHelp("(1 + 3) * 45"));
		System.out.println(checkExprHelp("(1 + (2 + 1)) * 45"));
		System.out.println(checkExprHelp("(1 + (2 + 1)) * (78+3*15) +45"));
		System.out.println(checkExprHelp("(1 +"));
		System.out.println(checkExprHelp("1 + * 2"));

	
	}

}
