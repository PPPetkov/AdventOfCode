package week4.day25;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CodeChronicle {
    private static final int TUMBLER_HEIGHT = 7;
    public static long partOne(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        List<List<Integer>> keys = new ArrayList<>();
        List<List<Integer>> locks = new ArrayList<>();

        while (reader.ready()) {
            List<String> schematic = parseSchematic(reader);
            List<Integer> heights = getPinHeights(schematic);

            if (isKey(schematic)) {
                keys.add(heights);
                heights.replaceAll(h -> TUMBLER_HEIGHT - h - 2);
            } else {
                locks.add(heights);
            }
        }

        return countPairs(keys, locks);
    }

    private static boolean keyFits(List<Integer> key, List<Integer> lock) {
        for (int i = 0; i < key.size(); i++) {
            if (key.get(i) < lock.get(i)) {
                return false;
            }
        }
        return true;
    }

    private static long countPairs(List<List<Integer>> keys, List<List<Integer>> locks) {
        long count = 0;
        for (List<Integer> key : keys) {
            for (List<Integer> lock : locks) {
                if (keyFits(key, lock)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isKey(List<String> schematic) {
        return schematic.getFirst().charAt(0) == '.';
    }

    private static List<Integer> getPinHeights(List<String> schematic) {
        List<Integer> heights = new ArrayList<>();
        for (int j = 0; j < schematic.getFirst().length(); j++) {
            int height = 0;
            for (String s : schematic) {
                if (s.charAt(j) == '#') {
                    height++;
                }
            }
            heights.add(height - 1);
        }

        return heights;
    }

    private static List<String> parseSchematic(BufferedReader reader) throws IOException {
        List<String> schematic = new ArrayList<>();
        String line = reader.readLine();

        while (line != null && !line.isEmpty()) {
            schematic.add(line);
            line = reader.readLine();
        }

        return schematic;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partOne("resources/input.txt"));
    }
}
