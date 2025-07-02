package week1.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BridgeRepair {
    private static long concatenate(long x, long y) {
        return Long.parseLong(String.valueOf(x) + String.valueOf(y));
    }

    private static boolean performOperations(int n, List<Long> numbers, long result, long goal) {
        if (n >= numbers.size()) {
            return result == goal;
        }

        return performOperations(n+1, numbers, result + numbers.get(n), goal) ||
                performOperations(n+1, numbers, result * numbers.get(n), goal) ||
                performOperations(n+1, numbers, concatenate(result, numbers.get(n)), goal);
    }

    public static long partOne(String filename) throws IOException {
        long sum = 0;
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        while (reader.ready()) {
            String line = reader.readLine();
            String[] splitLine = line.split(":");
            long goal = Long.parseLong(splitLine[0]);
            List<Long> numbers = parseNumbers(splitLine[1].trim().split("\\s+"));

            if (performOperations(1, numbers, numbers.getFirst(), goal)) {
                sum += goal;
            }
        }

        return sum;
    }

    private static List<Long> parseNumbers(String[] line) {
        List<Long> result = new ArrayList<>();
        for (String numberString : line) {
            result.add(Long.parseLong(numberString));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partOne("resources/input.txt"));
        System.out.println(concatenate(1000, 167));
    }
}
