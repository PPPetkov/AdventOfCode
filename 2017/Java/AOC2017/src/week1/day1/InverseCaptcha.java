package week1.day1;

import utils.InputParser;

public class InverseCaptcha {
    public int partOne(String filename) {
        String digits = InputParser.parseInputLine(filename);
        int len = digits.length();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            char c = digits.charAt(i);
            if (c == digits.charAt((i + 1) % len)) {
                sum += c - '0';
            }
        }

        return sum;
    }

    public int partTwo(String filename) {
        String digits = InputParser.parseInputLine(filename);
        int len = digits.length();
        int halfLen = len / 2;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            char c = digits.charAt(i);
            if (c == digits.charAt((i + halfLen) % len)) {
                sum += c - '0';
            }
        }

        return sum;
    }
}
