package week2.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlutonianPebbles {
    private static final int BLINKS_PART_ONE = 25;
    private static final int BLINKS_PART_TWO = 75;

    public static long countStones(String input, int blinkCount) throws IOException {
        Map<Long, Long> counts = new HashMap<>();
        for (long stone : parseInput(input)) {
            counts.put(stone, counts.getOrDefault(stone, 0L) + 1);
        }

        for (int i = 0; i < blinkCount; i++) {
            Map<Long, Long> next = new HashMap<>();
            for (Map.Entry<Long, Long> entry : counts.entrySet()) {
                long key = entry.getKey();
                long val = entry.getValue();
                int digits = (int) (Math.log10(key) + 1);

                if (key == 0) {
                    next.put(1L, val);
                } else if (digits % 2 == 0) {
                    long div = (long) Math.pow(10, (double) digits / 2);
                    long left = key / div;
                    long right = key % div;

                    next.put(left, next.getOrDefault(left, 0L) + val);
                    next.put(right, next.getOrDefault(right, 0L) + val);
                } else {
                    next.put(key * 2024, val);
                }
                counts = next;
            }
        }
        
        return counts.values().stream().mapToLong(Long::longValue).sum();
    }
    private static List<Long> parseInput(String filename) throws IOException {
        String line = new BufferedReader(new FileReader(filename)).readLine();
        String[] numbers = line.split("\\s++");
        List<Long> list = new ArrayList<>();
        for (String number : numbers) {
           list.add(Long.parseLong(number));
        }

        return list;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(countStones("resources/input.txt", BLINKS_PART_ONE));
        System.out.println(countStones("resources/input.txt", BLINKS_PART_TWO));
    }
}
