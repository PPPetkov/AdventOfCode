package week2.day9;

import utils.InputParser;

public class ExplosivesInCyberspace {
    public int partOne(String filename) {
        String input = InputParser.parseInputLine(filename);

        int size = 0;
        int length = input.length();
        for (int i = 0; i < length; i++) {
            if (input.charAt(i) == '(') {
                int endIndex = input.indexOf(')', i, length);
                if (endIndex == -1) {
                    size++;
                } else {
                    String marker = input.substring(i + 1, endIndex);
                    String[] tokens = marker.split("x");
                    int charCount = Integer.parseInt(tokens[0]);
                    int times = Integer.parseInt(tokens[1]);
                    size += charCount * times;
                    i = endIndex + charCount;
                }
            } else {
                size++;
            }
        }

        return size;
    }

    private long findDecompressedSize(String s) {
        long decompressedSize = 0;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                int endIndex = s.indexOf(')', i, len);
                if (endIndex == -1) {
                    decompressedSize++;
                } else {
                    String marker = s.substring(i + 1, endIndex);
                    String[] tokens = marker.split("x");
                    int charCount = Integer.parseInt(tokens[0]);
                    int times = Integer.parseInt(tokens[1]);
                    decompressedSize += findDecompressedSize(s.substring(endIndex + 1, endIndex + 1 + charCount)) * times;
                    i = endIndex + charCount;
                }
            } else {
                decompressedSize++;
            }
        }

        return decompressedSize;
    }

    public long partTwo(String filename) {
        return findDecompressedSize(InputParser.parseInputLine(filename));
    }
}
