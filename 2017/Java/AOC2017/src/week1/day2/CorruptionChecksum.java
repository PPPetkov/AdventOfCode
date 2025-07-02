package week1.day2;

import utils.InputParser;

import java.util.ArrayList;
import java.util.List;

public class CorruptionChecksum {
    private int minMaxDifference(String row) {
        String[] tokens = row.trim().split("\\s+");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (String token : tokens) {
            int current = Integer.parseInt(token);
            min = Math.min(current, min);
            max = Math.max(current, max);
        }

        return max - min;
    }
    public int partOne(String filename) {
        List<String> rows = InputParser.parseInput(filename);
        int sum = 0;
        for (String row : rows) {
            sum += minMaxDifference(row);
        }

        return sum;
    }

    private List<Integer> getNumbers(String row) {
        List<Integer> numbers = new ArrayList<>();
        String[] tokens = row.trim().split("\\s+");

        for (String token : tokens) {
            numbers.add(Integer.parseInt(token));
        }

        return numbers;
    }

    private int getQuotient(String row) {
        List<Integer> numbers = getNumbers(row);

        for (int i = 0; i < numbers.size() - 1; i++) {
            int first = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) {
                int second = numbers.get(j);
                if (first % second == 0) {
                    return first / second;
                }
                if (second % first == 0) {
                    return second / first;
                }
            }
        }

        return 0;
    }

    public int partTwo(String filename) {
        List<String> rows = InputParser.parseInput(filename);
        int sum = 0;
        for (String row : rows) {
            sum += getQuotient(row);
        }

        return sum;
    }
}
