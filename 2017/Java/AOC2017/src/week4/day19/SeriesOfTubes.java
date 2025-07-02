package week4.day19;

import utils.Direction;
import utils.InputParser;

import java.util.List;

public class SeriesOfTubes {
    private boolean inBounds(int x, int y, List<String> maze) {
        return x >= 0 && y >= 0 && x < maze.size() && y < maze.get(x).length();
    }

    public String partOne(String filename) {
        List<String> maze = InputParser.parseInput(filename);
        int x = 0;
        int y = maze.getFirst().indexOf('|');
        Direction dir = Direction.SOUTH;
        StringBuilder path = new StringBuilder();
        while (maze.get(x).charAt(y) != ' ') {
            char c = maze.get(x).charAt(y);
            if (c == '+') {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    dir = (inBounds(x, y + 1, maze) && (maze.get(x).charAt(y + 1) == '-' || Character.isAlphabetic(maze.get(x).charAt(y + 1)))) ? Direction.EAST : Direction.WEST;
                } else {
                    dir = (inBounds(x + 1, y, maze) && (maze.get(x + 1).charAt(y) == '|' || Character.isAlphabetic(maze.get(x+1).charAt(y)))) ? Direction.SOUTH : Direction.NORTH;
                }
            } else if (Character.isAlphabetic(c)) {
                path.append(c);
            }

            switch (dir) {
                case NORTH -> x--;
                case SOUTH -> x++;
                case EAST -> y++;
                case WEST -> y--;
            }
        }

        return path.toString();
    }

    public int partTwo(String filename) {
        List<String> maze = InputParser.parseInput(filename);
        int x = 0;
        int y = maze.getFirst().indexOf('|');
        Direction dir = Direction.SOUTH;
        int steps = 0;
        while (maze.get(x).charAt(y) != ' ') {
            char c = maze.get(x).charAt(y);
            if (c == '+') {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    dir = (inBounds(x, y + 1, maze) && (maze.get(x).charAt(y + 1) == '-' || Character.isAlphabetic(maze.get(x).charAt(y + 1)))) ? Direction.EAST : Direction.WEST;
                } else {
                    dir = (inBounds(x + 1, y, maze) && (maze.get(x + 1).charAt(y) == '|' || Character.isAlphabetic(maze.get(x+1).charAt(y)))) ? Direction.SOUTH : Direction.NORTH;
                }
            }

            switch (dir) {
                case NORTH -> x--;
                case SOUTH -> x++;
                case EAST -> y++;
                case WEST -> y--;
            }
            steps++;
        }

        return steps;
    }
}
