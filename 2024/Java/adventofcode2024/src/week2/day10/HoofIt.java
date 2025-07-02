package week2.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HoofIt {
    public static void main(String[] args) throws IOException {
        System.out.println(partOne("resources/input.txt"));
    }

    private static int partOne(String filename) throws IOException {
        List<List<Character>> topographicMap = parseInput(filename);
        int totalScore = 0;
        int rows = topographicMap.size();
        int cols = topographicMap.getFirst().size();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (topographicMap.get(i).get(j) == '0') {
                    totalScore += findScore(i, j, '/', topographicMap);
                }
            }
        }
        return totalScore;
    }

    private static int findScore(int i, int j, char prev, List<List<Character>> map) {
        if (i < 0 || i >= map.size() || j < 0 || j >= map.getFirst().size()) {
            return 0;
        }
        char c = map.get(i).get(j);
        if (prev != c - 1) {
            return 0;
        }
        if (c == '9') {
            return 1;
        }
        return findScore(i+1, j, c, map) + findScore(i - 1, j, c, map) + findScore(i, j + 1, c, map) + findScore(i, j - 1, c, map);
    }

    private static List<List<Character>> parseInput(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<List<Character>> map = new ArrayList<>();
        while (reader.ready()) {
            List<Character> row = new ArrayList<>();
            String line = reader.readLine();
            for (char c : line.toCharArray()) {
                row.add(c);
            }
            map.add(row);
        }
        return map;
    }
}
