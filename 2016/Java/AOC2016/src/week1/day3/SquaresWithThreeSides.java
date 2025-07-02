package week1.day3;

import utils.InputParser;
import java.util.List;

public class SquaresWithThreeSides {
    private boolean isPossibleTriangle(String triangle) {
        String[] sides = triangle.trim().split("\\s+");

        return isPossibleTriangle(Integer.parseInt(sides[0]), Integer.parseInt(sides[1]), Integer.parseInt(sides[2]));
    }

    private boolean isPossibleTriangle(int a, int b, int c) {
        return a + b > c && b + c > a && a + c > b;
    }

    public int partOne(String filename) {
        List<String> triangles = InputParser.parseInput(filename);
        int count = 0;
        for (String triangle : triangles) {
            if (isPossibleTriangle(triangle)) {
                count++;
            }
        }

        return count;
    }

    public int partTwo(String filename) {
        List<String> triangles = InputParser.parseInput(filename);
        int count = 0;

        for (int i = 0; i < triangles.size() - 2; i += 3) {
            String[] row1 = triangles.get(i).trim().split("\\s+");
            String[] row2 = triangles.get(i+1).trim().split("\\s+");
            String[] row3 = triangles.get(i+2).trim().split("\\s+");

            for (int j = 0; j < row1.length; j++) {
                if (isPossibleTriangle(Integer.parseInt(row1[j]), Integer.parseInt(row2[j]), Integer.parseInt(row3[j]))) {
                    count++;
                }
            }
        }

        return count;
    }
}
