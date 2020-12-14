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

	}
	
	
}
