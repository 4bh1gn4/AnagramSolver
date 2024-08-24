package application;

//import java.util.random;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
///// to do: datamuse api

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnagramUtils {
	private static final List<String> threeLetterWords = new ArrayList<>();
	private static final List<String> fourLetterWords = new ArrayList<>();
	private static final List<String> fiveLetterWords = new ArrayList<>();
	
	static {
		loadWords("src/application/three-letters.txt", threeLetterWords);
        loadWords("src/application/four-letters.txt", fourLetterWords);
        loadWords("src/application/five-letters.txt", fiveLetterWords);
	}
	
	private static void loadWords(String filePath, List<String> wordList) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = reader.readLine();
			if (line != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					wordList.add(word.toUpperCase());
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getWord(int index, int level) {
		switch (level) {
		case 1: return threeLetterWords.get(index);
		case 2: return fourLetterWords.get(index);
		case 3: return fiveLetterWords.get(index);
		default: throw new IllegalArgumentException("Invalid level: " + level);
		}
	}
	
	public static int getWordCount(int level) {
		switch (level) {
        case 1: return threeLetterWords.size() - 1;
        case 2: return fourLetterWords.size();
        case 3: return fiveLetterWords.size();
        default: throw new IllegalArgumentException("Invalid level: " + level);
		}
	}
	/*
	public static String getRandomWord(int index) {
		
		//Collections.shuffle(words); //this way, i do not need to access a word from a random array index
		//return words.get(index);
	}
	*/
	public static String jumbleWord(String word) {
		List<Character> letters = new ArrayList<Character>();
		for (char c : word.toCharArray()) {
			letters.add(c);
		}
		
		String jumbledWord;
		do {
			Collections.shuffle(letters);
			StringBuilder sb = new StringBuilder();
			
			for (char letter : letters) {
				//System.out.print(letter + " ");
				sb.append(letter);
			}
			jumbledWord = sb.toString();
		} while (jumbledWord.equalsIgnoreCase(word));
		
		return jumbledWord.toString();
	}
    
	public static boolean isCorrectWord(String s1, String s2) {
		s1 = s1.toLowerCase().replaceAll("\\s+", ""); //replaces all extra spaces with empty spaces
		s2 = s2.toLowerCase().replaceAll("\\s+", "");
		
		return s1.equals(s2);
		
		
	}
	
}
