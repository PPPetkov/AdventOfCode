package week2.day9;

import utils.InputParser;

import java.util.Queue;

public class StreamProcessing {
    public int partOne(String filename) {
        String input = InputParser.parseInputLine(filename);

        int openGroups = 0;
        int totalScore = 0;
        boolean canceledNext = false;
        boolean inGarbage = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (inGarbage) {
                if (canceledNext) {
                    canceledNext = false;
                    continue;
                }
                if (c == '!') {
                    canceledNext = true;
                } else if (c == '>') {
                    inGarbage = false;
                }
            } else {
                if (c == '{') {
                    openGroups++;
                } else if (c == '}' && openGroups > 0) {
                    totalScore += openGroups--;
                } else if (c == '<') {
                    inGarbage = true;
                }
            }
        }

        return totalScore;
    }

    public int partTwo(String filename) {
        String input = InputParser.parseInputLine(filename);

        int garbageSize = 0;
        boolean canceledNext = false;
        boolean inGarbage = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (inGarbage) {
                if (canceledNext) {
                    canceledNext = false;
                    continue;
                }
                if (c == '!') {
                    canceledNext = true;
                } else if (c == '>') {
                    inGarbage = false;
                } else {
                    garbageSize++;
                }
            } else {
                if (c == '<') {
                    inGarbage = true;
                }
            }
        }

        return garbageSize;
    }
}
