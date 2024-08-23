package application;

//import java.util.random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnagramUtils {
	private static final List<String> words = Arrays.asList("hello", "type", "stop", "phone");
    
	public static String getWord(int index) {
		return words.get(index);
	}
	
	public static int getWordCount() {
		return words.size() - 1;
	}
	public static String getRandomWord(int index) {
		
		//Collections.shuffle(words); //this way, i do not need to access a word from a random array index
		return words.get(index);
	}
	
	public static String jumbleWord(String word) {
		List<Character> letters = new ArrayList<Character>();
		for (char c : word.toCharArray()) {
			letters.add(c);
		}
		Collections.shuffle(letters);
		
		String jumbledWord = new String("");
		
		while (!jumbledWord.equalsIgnoreCase(word)) {
			
		
			for (char letter : letters) {
				//System.out.print(letter + " ");
				jumbledWord+=letter;
			}
		}
		
		return jumbledWord.toString();
	}
    
	public static boolean isCorrectWord(String s1, String s2) {
		s1 = s1.toLowerCase().replaceAll("\\s+", ""); //replaces all extra spaces with empty spaces
		s2 = s2.toLowerCase().replaceAll("\\s+", "");
		
		return s1.equals(s2);
		
		
	}
	
}
