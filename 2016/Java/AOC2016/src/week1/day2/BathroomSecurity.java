package week1.day2;

import utils.InputParser;

import java.util.List;

public class BathroomSecurity {
    private static final char[][] KEYPAD_ONE = {
            {'X', 'X', 'X', 'X', 'X'},
            {'X', '1', '2', '3', 'X'},
            {'X', '4', '5', '6', 'X'},
            {'X', '7', '8', '9', 'X'},
            {'X', 'X', 'X', 'X', 'X'}
    };

    private static final char[][] KEYPAD_TWO = {
            {'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', '1', 'X', 'X', 'X'},
            {'X', 'X', '2', '3', '4', 'X', 'X'},
            {'X', '5', '6', '7', '8', '9', 'X'},
            {'X', 'X', 'A', 'B', 'C', 'X', 'X'},
            {'X', 'X', 'X', 'D', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X'}
    };

    private String getCode(List<String> input, char[][] keypad, int x, int y) {
        StringBuilder code = new StringBuilder();

        for (String s : input) {
            for (char c : s.toCharArray()) {
                int nextX = x;
                int nextY = y;

                switch (c) {
                    case 'U': nextX--; break;
                    case 'D': nextX++; break;
                    case 'L': nextY--; break;
                    case 'R': nextY++; break;
                }
                if (keypad[nextX][nextY] != 'X') {
                    x = nextX;
                    y = nextY;
                }
            }

            code.append(keypad[x][y]);
        }

        return code.toString();
    }

    public String partOne(String filename) {
        return getCode(InputParser.parseInput(filename), KEYPAD_ONE, 2, 2);
    }

    public String partTwo(String filename) {
        return getCode(InputParser.parseInput(filename), KEYPAD_TWO, 3, 1);
    }
}
