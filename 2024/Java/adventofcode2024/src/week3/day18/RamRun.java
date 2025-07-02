package week3.day18;

import util.Location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class RamRun {
    private static final int MAZE_DIMENSIONS = 71;

    public static int partOne(String filename) throws IOException {
        List<Location> fallCoordinates = parseCoordinates(filename);
        char[][] maze = new char[MAZE_DIMENSIONS][MAZE_DIMENSIONS];
        simulateBytefall(maze, fallCoordinates.size(), fallCoordinates);
        printMaze(maze);
        return findMinDistance(maze, new Location(0, 0), new Location(MAZE_DIMENSIONS - 1, MAZE_DIMENSIONS - 1));
    }

    private static void printMaze(char[][] maze) {
        for (char[] row : maze) {
            System.out.println(row);
        }
    }

    private static int findMinDistance(char[][] maze, Location start, Location end) {
        Queue<Location> queue = new ArrayDeque<>();
        Set<Location> visited = new HashSet<>();
        queue.add(start);
        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Location current = queue.poll();
                int x = current.x();
                int y = current.y();
                if (!visited.contains(current) && isInBounds(x, y, MAZE_DIMENSIONS, MAZE_DIMENSIONS) && maze[current.x()][current.y()] != '#') {
                    visited.add(current);
                    if (current.equals(end)) {
                        return distance;
                    }
                    queue.add(new Location(x + 1, y));
                    queue.add(new Location(x - 1, y));
                    queue.add(new Location(x, y + 1));
                    queue.add(new Location(x, y - 1));
                }
                size--;
            }
            distance++;
        }

        return -1;
    }

    private static boolean isInBounds(int x, int y, int width, int height) {
        return x >= 0 && x < height && y >= 0 && y < width;
    }

    private static Location partTwo(String filename) throws IOException {
        List<Location> fallCoordinates = parseCoordinates(filename);
        char[][] maze = new char[MAZE_DIMENSIONS][MAZE_DIMENSIONS];
        Location start = new Location(0, 0);
        Location end = new Location(MAZE_DIMENSIONS - 1, MAZE_DIMENSIONS - 1);
        for (int i = 0; i < fallCoordinates.size(); i++) {
            Location current = fallCoordinates.get(i);
            maze[current.x()][current.y()] = '#';
            if (findMinDistance(maze, start, end) == -1) {
                return current;
            }
        }

        return start;
    }

    private static List<Location> parseCoordinates(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<Location> coordinates = new ArrayList<>();

        while (reader.ready()) {
            String[] tokens = reader.readLine().split(",");
            coordinates.add(new Location(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
        }

        return coordinates;
    }

    private static void simulateBytefall(char[][] maze, int byteCount, List<Location> coordinates) {
        for (int i = 0; i < byteCount; i++) {
            maze[coordinates.get(i).x()][coordinates.get(i).y()] = '#';
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partTwo("resources/input.txt"));
    }
}
