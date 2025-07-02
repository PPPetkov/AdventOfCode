package week3.day18;

import utils.InputParser;

public class LikeARogue {
    private int countSafeTiles(StringBuilder row) {
        int count = 0;
        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) == '.') {
                count++;
            }
        }
        return count;
    }

    private boolean isTrap(StringBuilder row, int i) {
        boolean leftTrap = i - 1 >= 0 && row.charAt(i - 1) == '^';
        boolean centerTrap = row.charAt(i) == '^';
        boolean rightTrap = i + 1 < row.length() && row.charAt(i + 1) == '^';

        return (leftTrap && centerTrap && !rightTrap) ||
                (!leftTrap && centerTrap && rightTrap) ||
                (leftTrap && !centerTrap && !rightTrap) ||
                (!leftTrap && !centerTrap && rightTrap);
    }

    private StringBuilder getNextRow(StringBuilder current) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < current.length(); i++) {
            result.append(isTrap(current, i) ? '^' : '.');
        }

        return result;
    }

    public int partOne(String filename, int rows) {
        StringBuilder current = new StringBuilder(InputParser.parseInputLine(filename));
        rows--;
        int safeTiles = countSafeTiles(current);

        while (rows > 0) {
            current = getNextRow(current);
            rows--;
            safeTiles += countSafeTiles(current);
        }

        return safeTiles;
    }

    public int partTwo(String filename, int rows) {
        return partOne(filename, rows);
    }
}
