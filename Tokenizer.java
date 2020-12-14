import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tokenizer {

	static String[] doTokenize(String s) {
		String s2 = s.replaceAll("\\s+", "");
		String[] final = s2.split("(?<=[-+*/=()])|(?=[-+*/=()])");
		
		return final;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		System.out.println(Arrays.toString(doTokenize("123+56*num1"))); 
		System.out.println(Arrays.toString(doTokenize("(1+ 23) * 9"))); 
		System.out.println(Arrays.toString(doTokenize("aa1= (14 - 3) *2/a23"))); 

	}
	
	
}
