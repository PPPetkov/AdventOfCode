package week1.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuardGallivant {
    private static final char[] GUARD_ORIENTATIONS = {'<', '^', '>', 'v'};

    public static int partOne(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<StringBuilder> lab = readLab(reader);
        return countVisited(getPath(lab, findGuard(lab)));
    }

    private static boolean[][] getPath(List<StringBuilder> lab, int[] guardPosition) {
        int rows = lab.size();
        int cols = lab.getFirst().length();
        boolean[][] visited = new boolean[rows][cols];
        int guardX = guardPosition[0];
        int guardY = guardPosition[1];
        visited[guardX][guardY] = true;
        char orientation = lab.get(guardX).charAt(guardY);

        while (isInBounds(guardX, guardY, rows, cols)) {
            int nextX = guardX;
            int nextY = guardY;

            switch (orientation) {
                case '<': nextY--; break;
                case '^': nextX--; break;
                case '>': nextY++; break;
                case 'v': nextX++; break;
            }

            if (isInBounds(nextX, nextY, rows, cols) && lab.get(nextX).charAt(nextY) == '#') {
                orientation = rotate(orientation);
                continue;
            }

            guardX = nextX;
            guardY = nextY;
            if (isInBounds(guardX, guardY, rows, cols) && !visited[guardX][guardY]) {
                visited[guardX][guardY] = true;
            }
        }

        return visited;
    }

    private static List<StringBuilder> readLab(BufferedReader reader) throws IOException {
        List<StringBuilder> lab = new ArrayList<>();

        while (reader.ready()) {
            lab.add(new StringBuilder(reader.readLine()));
        }

        return lab;
    }

    private static int[] findGuard(List<StringBuilder> lab) {
        int rows = lab.size();
        for (int i = 0; i < rows; i++) {
            StringBuilder line = lab.get(i);

            for (char orientation : GUARD_ORIENTATIONS) {
                int index = line.indexOf(String.valueOf(orientation));
                if (index != -1) {
                    return new int[]{i, index};
                }
            }
        }

        return new int[]{0,0};
    }

    private static boolean isInBounds(int x, int y, int rows, int cols) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }

    private static char rotate(char orientation) {
        return switch (orientation) {
            case '^' -> '>';
            case '>' -> 'v';
            case 'v' -> '<';
            case '<' -> '^';
            default -> orientation;
        };
    }

    private static int countVisited(boolean[][] visited) {
        int result = 0;
        for (boolean[] booleans : visited) {
            for (boolean bool : booleans) {
                if (bool) {
                    result++;
                }
            }
        }

        return result;
    }

    public static int partTwo(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<StringBuilder> lab = readLab(reader);
        int[] guardPosition = findGuard(lab);
        char orientation = lab.get(guardPosition[0]).charAt(guardPosition[1]);
        int guardFrontX = guardPosition[0];
        int guardFrontY = guardPosition[1];

        switch (orientation) {
            case '>': guardFrontY++; break;
            case 'v': guardFrontX++; break;
            case '<': guardFrontY--; break;
            case '^': guardFrontX--; break;
        }

        boolean[][] originalPath = getPath(lab, guardPosition);
        int result = 0;
        int rows = originalPath.length;
        int cols = originalPath[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == guardPosition[0] && j == guardPosition[1]) {
                    continue;
                }
                if (i == guardFrontX && j == guardFrontY) {
                    continue;
                }
                if (originalPath[i][j]) {
                    lab.get(i).setCharAt(j, '#');
                    if (loopsForever(lab, guardPosition, orientation)) {
                        result++;
                    }
                    //System.out.println(result);
                    lab.get(i).setCharAt(j, '.');
                }
            }
        }

        return result;
    }

    private static boolean loopsForever(List<StringBuilder> lab, int[] start, char startOrientation) {
        int rows = lab.size();
        int cols = lab.getFirst().length();
        int guardX = start[0];
        int guardY = start[1];
        char orientation = lab.get(guardX).charAt(guardY);
        Map<Character, List<List<Integer>>> wallHits = new HashMap<>();

        while (isInBounds(guardX, guardY, rows, cols)) {
            int nextX = guardX;
            int nextY = guardY;

            switch (orientation) {
                case '<': nextY--; break;
                case '^': nextX--; break;
                case '>': nextY++; break;
                case 'v': nextX++; break;
            }

            if (isInBounds(nextX, nextY, rows, cols) && lab.get(nextX).charAt(nextY) == '#') {
                wallHits.putIfAbsent(orientation, new ArrayList<>());
                List<List<Integer>> walls = wallHits.get(orientation);
                for (List<Integer> wall : walls) {
                    if (wall.getFirst() == nextX && wall.getLast() == nextY) {
                        return true;
                    }
                }

                orientation = rotate(orientation);
                walls.add(List.of(nextX, nextY));
                continue;
            }

            if (nextX == start[0] && nextY == start[1] && orientation == startOrientation) {
                return true;
            }

            guardX = nextX;
            guardY = nextY;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partTwo("resources/input.txt"));
    }
}
