package week1.day5;

import utils.InputParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NiceStrings {
    private static final Set<Character> vowels = Set.of('a', 'i', 'e', 'o', 'u');
    private static final Set<String> forbiddenStrings = Set.of("ab", "cd", "pq", "xy");

    private boolean checkString(char[] str) {
        int vowelCount = 0;
        boolean hasConsecutiveLetter = false;
        for (int i = 0; i < str.length - 1; i++) {
            if (vowels.contains(str[i])) {
                vowelCount++;
            }
            if (forbiddenStrings.contains(String.valueOf(str[i]) + str[i + 1])) {
                return false;
            }
            if (str[i] == str[i + 1]) {
                hasConsecutiveLetter = true;
            }
        }

        if (vowels.contains(str[str.length - 1])) {
            vowelCount++;
        }

        return vowelCount > 2 && hasConsecutiveLetter;
    }

    public int partOne(String filename) {
        List<String> input = InputParser.parseInput(filename);
        int count = 0;

        for (String s : input) {
            if (checkString(s.toCharArray())) {
                count++;
            }
        }

        return count;
    }

    private boolean pairAppearsTwice(char[] str) {
        Map<String, Integer> foundPairs = new HashMap<>();

        for (int i = 0; i < str.length - 1; i++) {
            String currentPair = String.valueOf(str[i]) + str[i + 1];

            Integer index = foundPairs.get(currentPair);

            if (index != null) {
                if (index != i - 1) {
                    return true;
                }
            } else {
                foundPairs.put(currentPair, i);
            }
        }

        return false;
    }

    private boolean inbetweenLetters(char[] str) {
        for (int i = 0; i < str.length - 2; i++) {
            if (str[i] == str[i + 2]) {
                return true;
            }
        }

        return false;
    }

    public int partTwo(String filename) {
        List<String> input = InputParser.parseInput(filename);
        int count = 0;

        for (String s : input) {
            char[] str = s.toCharArray();
            if (inbetweenLetters(str) && pairAppearsTwice(str)) {
                count++;
            }
        }

        return count;
    }
}
