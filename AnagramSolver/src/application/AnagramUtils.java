package application;

//import java.util.random;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnagramUtils {
	private static final List<String> words = Arrays.asList("hello", "type", "stop", "phone");
    
	public static String getRandomWord() {
		Collections.shuffle(words); //this way, i do not need to access a word from a random array index
		return words.get(0);
	}
	
	public static String jumbleWord(String word) {
		List<char[]> letters = Arrays.asList(word.toCharArray());
		Collections.shuffle(letters);
		
		StringBuilder jumbledWord = new StringBuilder();
		
		for (char[] letter : letters) {
			jumbledWord.append(letter);
		}
		
		return jumbledWord.toString();
	}
    
	public static boolean isCorrectWord(String s1, String s2) {
		s1 = s1.toLowerCase().replaceAll("\\s+", ""); //replaces all extra spaces with empty spaces
		s2 = s2.toLowerCase().replaceAll("\\s+", "");
		
		return s1.equals(s2);
		
		
	}
	
}
