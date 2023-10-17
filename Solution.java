import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static boolean isCompounded(String word, Set<String> wordSet) {
        if (wordSet.contains(word)) {
            return true;
        }
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
            if (wordSet.contains(prefix) && isCompounded(suffix, wordSet)) {
                return true;
            }
        }
        return false;
    }

    public static String[] findLongestCompoundedWords(String filename) throws IOException {
        Set<String> wordSet = new HashSet<>();
        String longestCompound = "";
        String secondLongestCompound = "";

        long startTime = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String word = line.trim();
            if (isCompounded(word, wordSet)) {
                if (word.length() >= longestCompound.length()) {
                    secondLongestCompound = longestCompound;
                    longestCompound = word;
                } else if (secondLongestCompound == null || word.length() >= secondLongestCompound.length()) {
                    secondLongestCompound = word;
                }
            }
            wordSet.add(word);
        }

        long endTime = System.currentTimeMillis();
        double processingTime = (endTime - startTime) / 1000.0; // in seconds

        return new String[]{longestCompound, secondLongestCompound, String.valueOf(processingTime)};
    }

    public static void main(String[] args) throws IOException {
        String input1 = "Input_01.txt";
        String input2 = "Input_02.txt";

        String[] result1 = findLongestCompoundedWords(input1);
        String[] result2 = findLongestCompoundedWords(input2);

        System.out.println("For Input_01.txt:");
        System.out.println("Longest compounded word: " + result1[0]);
        System.out.println("Second longest compounded word: " + result1[1]);
        System.out.println("Time taken to process: " + result1[2] + " seconds");

        System.out.println("\nFor Input_02.txt:");
        System.out.println("Longest compounded word: " + result2[0]);
        System.out.println("Second longest compounded word: " + result2[1]);
        System.out.println("Time taken to process: " + result2[2] + " seconds");
    }
}
