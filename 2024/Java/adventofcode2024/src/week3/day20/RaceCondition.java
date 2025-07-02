package week3.day20;

import exception.SymbolNotFoundException;
import util.InputParser;
import util.Location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RaceCondition {
    private static int[][] DIRECTIONS = {
            {0, 1},
            {0,-1},
            {1,0},
            {-1,0}
    };

    private static int[][] DIRECTIONS2 = {
            {0, 2},
            {0, -2},
            {2, 0},
            {-2, 0},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };

    public static long partOne(String filename) {
        List<String> maze;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            maze = InputParser.parseListOfStrings(reader);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        Location start = findSymbol(maze, 'S');
        Location end = findSymbol(maze, 'E');

        Map<Location, Integer> distances = findDistances(maze, start, end);
        int result = 0;
        int pathLength = distances.get(end);
        int distanceDifference = 99;
        for (Location current : distances.keySet()) {
            int x = current.x();
            int y = current.y();
            int currentDistance = distances.get(current);
            for (int[] dir : DIRECTIONS2) {
                if (pathLength - currentDistance - calculateDistance(end, new Location(x + dir[0], y + dir[1]), distances, 2) > distanceDifference) {
                    result++;
                }
            }
        }

        return result;
    }

    private static int calculateDistance(Location end, Location current, Map<Location, Integer> distances, int distanceToAdd) {
        if (distances.get(current) == null) {
            return Integer.MAX_VALUE;
        }
        return distances.get(end) - distances.get(current) + distanceToAdd;
    }

    private static Map<Location, Integer> findDistances(List<String> maze, Location start, Location end) {
        Map<Location, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        int distance = 0;
        Set<Location> visited = new HashSet<>();
        Location next = start;
        while (!next.equals(end)) {
            visited.add(next);
            distances.put(next, distance++);

            for (int[] dir : DIRECTIONS) {
                Location loc = new Location(next.x() + dir[0], next.y() + dir[1]);
                if (!visited.contains(loc) && isInBounds(loc.x(), loc.y(), maze.getFirst().length(), maze.size()) &&
                maze.get(loc.x()).charAt(loc.y()) != '#') {
                    next = loc;
                    break;
                }
            }
        }

        distances.put(next, distance);
        return distances;
    }

    private static boolean isInBounds(int x, int y, int width, int height) {
        return x >= 0 && x < height
                && y >= 0 && y < width;
    }

    private static Location findSymbol(List<String> maze, char symbol) {
        for (int x = 0; x < maze.size(); x++) {
            int y = maze.get(x).indexOf(symbol);

            if (y != -1) {
                return new Location(x, y);
            }
        }

        throw new SymbolNotFoundException(symbol + " was not found in the maze");
    }

    public static int partTwo(String filename) {
        List<String> maze;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            maze = InputParser.parseListOfStrings(reader);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        Location start = findSymbol(maze, 'S');
        Location end = findSymbol(maze, 'E');

        Map<Location, Integer> distances = findDistances(maze, start, end);
        int result = 0;
        int pathLength = distances.get(end);
        int minPathDifference = 99;
        int maxCellDistance = 20;
        for (Location current : distances.keySet()) {
            int currentDistance = distances.get(current);
            for (Location other : distances.keySet()) {
                int manhattan = current.getManhattanDistance(other);
                if (manhattan > 0 && manhattan <= maxCellDistance) {
                    if (pathLength - currentDistance - calculateDistance(end, other, distances, manhattan) > minPathDifference) {
                        result++;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(partTwo("resources/input.txt"));
    }
}
