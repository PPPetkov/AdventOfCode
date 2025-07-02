package week1.day4;

import utils.InputParser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HighEntropyPassphrases {
    private boolean isValid(String phrase) {
        String[] words = phrase.split("\\s+");
        Set<String> encountered = new HashSet<>();

        for (String word : words) {
            if (!encountered.add(word)) {
                return false;
            }
        }

        return true;
    }

    public int partOne(String filename) {
        List<String> passphrases = InputParser.parseInput(filename);
        int count = 0;
        for (String phrase : passphrases) {
            if (isValid(phrase)) {
                count++;
            }
        }

        return count;
    }

    private boolean isValidNoAnagrams(String phrase) {
        String[] words = phrase.split("\\s+");
        Set<String> encountered = new HashSet<>();

        for (String word : words) {
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            if (!encountered.add(String.valueOf(arr))) {
                return false;
            }
        }

        return true;
    }

    public int partTwo(String filename) {
        List<String> passphrases = InputParser.parseInput(filename);
        int count = 0;
        for (String phrase : passphrases) {
            if (isValidNoAnagrams(phrase)) {
                count++;
            }
        }

        return count;
    }
}
