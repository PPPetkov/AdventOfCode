package week4.day21;

import utils.InputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FractalArt {
    private char[][] getGrid(String line) {
        return Arrays.stream(line.split("/"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    private String convertGridToLine(char[][] grid) {
        return String.join("/", Arrays.stream(grid)
                .map(String::new)
                .toArray(String[]::new));
    }

    private char[][] rotate(char[][] grid) {
        int n = grid.length;
        char[][] rotated = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                rotated[j][n - i - 1] = grid[i][j];
        return rotated;
    }

    private char[][] flip(char[][] grid) {
        List<char[]> list = new ArrayList<>(Arrays.asList(grid));
        Collections.reverse(list);
        return list.toArray(new char[0][]);
    }

    public List<String> getVariants(String line) {
        List<String> variants = new ArrayList<>();
        char[][] grid = getGrid(line);
        for (int i = 0; i < 4; i++) {
            grid = rotate(grid);
            variants.add(convertGridToLine(grid));
            variants.add(convertGridToLine(flip(grid)));
        }
        return variants;
    }

    private Map<String, String> getRules(List<String> data) {
        Map<String, String> rules = new HashMap<>();
        for (String line : data) {
            String[] parts = line.split(" => ");
            getVariants(parts[0]).forEach(v -> rules.put(v, parts[1]));
        }
        return rules;
    }

    public int partOne(String filename) {
        Map<String, String> rules = getRules(InputParser.parseInput(filename));
        char[][] grid = { {'.', '#', '.'}, {'.', '.', '#'}, {'#', '#', '#'} };

        for (int n = 0; n < 5; n++) {  // Iterate 5 times
            int size = grid.length % 2 == 0 ? 2 : 3;
            List<List<String>> grids = splitGrid(grid, size, rules);
            grid = reconstructGrid(grids);
        }
        return countOnPixels(grid);
    }

    public int partTwo(String filename) {
        Map<String, String> rules = getRules(InputParser.parseInput(filename));
        char[][] grid = { {'.', '#', '.'}, {'.', '.', '#'}, {'#', '#', '#'} };

        for (int n = 0; n < 18; n++) {  // Iterate 5 times
            int size = grid.length % 2 == 0 ? 2 : 3;
            List<List<String>> grids = splitGrid(grid, size, rules);
            grid = reconstructGrid(grids);
        }
        return countOnPixels(grid);
    }

    private List<List<String>> splitGrid(char[][] grid, int size, Map<String, String> rules) {
        List<List<String>> grids = new ArrayList<>();
        for (int i = 0; i < grid.length; i += size) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < grid.length; j += size) {
                char[][] square = new char[size][size];
                for (int x = 0; x < size; x++)
                    System.arraycopy(grid[i + x], j, square[x], 0, size);

                String transformed = rules.getOrDefault(convertGridToLine(square), convertGridToLine(square));
                row.add(transformed);
            }
            grids.add(row);
        }
        return grids;
    }

    private char[][] reconstructGrid(List<List<String>> grids) {
        int newSize = grids.size() * grids.getFirst().getFirst().split("/").length;
        char[][] newGrid = new char[newSize][newSize];

        for (int i = 0; i < grids.size(); i++) {
            for (int j = 0; j < grids.get(i).size(); j++) {
                char[][] subGrid = getGrid(grids.get(i).get(j));
                int subSize = subGrid.length;
                for (int x = 0; x < subSize; x++)
                    System.arraycopy(subGrid[x], 0, newGrid[i * subSize + x], j * subSize, subSize);
            }
        }
        return newGrid;
    }

    private int countOnPixels(char[][] grid) {
        int count = 0;
        for (char[] row : grid)
            for (char cell : row)
                if (cell == '#') count++;
        return count;
    }
}
