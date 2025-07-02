package week1.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RedNosedReports {
    private static final int MIN_DIFFERENCE = 1;
    private static final int MAX_DIFFERENCE = 3;

    private static boolean isSafeReport(String report) {
        String[] levelStrings = report.split("\\s+");

        for (int skip = -1; skip < levelStrings.length; skip++) {
            boolean isSafe = true;
            int prevLevel, currentLevel;
            int startIndex = skip == 0 ? 1 : 0;
            prevLevel = Integer.parseInt(levelStrings[startIndex]);
            if (skip == 1) {
                currentLevel = Integer.parseInt(levelStrings[startIndex + 2]);
                startIndex += 2;
            } else {
                currentLevel = Integer.parseInt(levelStrings[startIndex + 1]);
                startIndex++;
            }

            boolean isDecreasing = prevLevel > currentLevel;
            for (int i = startIndex; i < levelStrings.length; i++) {
                if (i == skip) {
                    continue;
                }

                currentLevel = Integer.parseInt(levelStrings[i]);
                int difference = Math.abs(currentLevel - prevLevel);
                if ((isDecreasing && currentLevel > prevLevel) ||
                    (!isDecreasing && currentLevel < prevLevel) ||
                    difference < MIN_DIFFERENCE || difference > MAX_DIFFERENCE) {
                    isSafe = false;
                    break;
                }

                prevLevel = currentLevel;
            }

            if (isSafe) {
                return true;
            }
        }

        return false;
    }

    public static long partOne(String filename) throws IOException {
        long safeReportCount = 0;

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        while (reader.ready()) {
            if (isSafeReport(reader.readLine())) {
                safeReportCount++;
            }
        }

        return safeReportCount;
    }

    public static void main(String[] args){
        try {
            System.out.println(partOne("resources/input.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
