package week3.day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LinenLayout {
    public static long partOne(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        Set<String> inventory = Set.of(reader.readLine().split(", "));
        reader.skip(1);
        List<String> towels = parseTowels(reader);
        int maxSubstringLength = inventory.stream()
                .map(String::length)
                .max(Comparator.naturalOrder()).get();

        return towels.stream()
                .filter(towel -> possibleToCreateTowel(towel, inventory, maxSubstringLength))
                .count();

    }

    private static boolean possibleToCreateTowel(String towel, Set<String> inventory, int maxSubstringLength) {
        if (towel.isEmpty()) {
            return true;
        }

        int maxLen = Math.min(maxSubstringLength, towel.length());
        for (int i = 1; i <= maxLen; i++) {
            String substring = towel.substring(0, i);
            if (inventory.contains(substring) && possibleToCreateTowel(towel.substring(i), inventory, maxSubstringLength)) {
                return true;
            }
        }

        return false;
    }

    private static List<String> parseTowels(BufferedReader reader) throws IOException {
        return reader.lines().toList();
    }

    public static long partTwo(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        Set<String> inventory = Set.of(reader.readLine().split(", "));
        reader.skip(1);
        List<String> towels = parseTowels(reader);
        int maxSubstringLength = inventory.stream()
                .map(String::length)
                .max(Comparator.naturalOrder()).get();

        Map<String, Long> cache = new HashMap<>();
        cache.put("", 1L);
        long count = 0;

        for (String towel : towels) {
            count += countPermutations(towel, inventory, cache, maxSubstringLength);
        }

        return count;
    }

    private static long countPermutations(String towel, Set<String> inventory, Map<String, Long> cache, int maxSubstringLength) {
        if (cache.get(towel) != null) {
            return cache.get(towel);
        }
        int maxLen = Math.min(maxSubstringLength, towel.length());
        long count = 0;

        for (int i = 1; i <= maxLen; i++) {
            if (inventory.contains(towel.substring(0, i))) {
                count += countPermutations(towel.substring(i), inventory, cache, maxSubstringLength);
            }
        }

        cache.put(towel, count);
        return count;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(System.currentTimeMillis());
        System.out.println(partTwo("resources/input.txt"));
        System.out.println(System.currentTimeMillis());
    }
}
