package application;

public class AnagramUtils {
	public static boolean isAnagram(String s1, String s2) {
		s1 = s1.toLowerCase().replaceAll("\\s+", ""); //replaces all extra spaces with empty spaces
		s2 = s2.toLowerCase().replaceAll("\\s+", "");
		
		return s1.equals(s2);
		/*
		char[] s1Array = s1.toCharArray();
		char[] s2Array = s2.toCharArray();
		
		java.util.Arrays.sort(s1Array);
		java.util.Arrays.sort(s2Array);
		
		return java.util.Arrays.equals(s1Array, s2Array);
		*/
	}
}
