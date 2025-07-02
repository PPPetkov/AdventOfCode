package week2.day8;

import utils.InputParser;

import java.util.List;

public class Matchsticks {
    private int findStringValueLength(String s) {
        int len = 0;
        char[] arr = s.toCharArray();
        for (int i = 1; i < arr.length - 1; i++) {
            char c = arr[i];
            len++;
            if (c == '\\') {
                if (arr[i+1] == '\\' || arr[i+1] == '"') {
                    i++;
                } else if (arr[i+1] == 'x') {
                    i += 3;
                }
            }
        }

        return len;
    }

    public int partOne(String filename) {
        List<String> input = InputParser.parseInput(filename);
        int result = 0;

        for (String s : input) {
            result += s.length() - findStringValueLength(s);
        }

        return result;
    }

    private int findEncodedStringLength(String s) {
        int len = 6;
        char[] arr = s.toCharArray();
        for (int i = 1; i < arr.length - 1; i++) {
            char c = arr[i];
            len++;
            if (c == '\\') {
                if (arr[i+1] == '\\' || arr[i+1] == '"') {
                    len += 3;
                    i++;
                } else if (arr[i+1] == 'x') {
                    len++;
                }
            }
        }

        return len;
    }

    public int partTwo(String filename) {
        List<String> input = InputParser.parseInput(filename);
        int result = 0;

        for (String s : input) {
            result += findEncodedStringLength(s) - s.length();
        }

        return result;
    }
}
