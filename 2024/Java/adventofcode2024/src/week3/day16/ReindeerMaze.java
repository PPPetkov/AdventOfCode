package week3.day16;

import util.DirectedLocation;
import util.Location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class ReindeerMaze {
    private static final int FORWARD_PRICE = 1;
    private static final int TURN_PRICE = 1000;

    public static long partOne(String filename) throws IOException {
        List<String> maze = parseMaze(filename);

        return findLowestScore(findDistances(maze), DirectedLocation.of(findSymbol(maze, 'E')));
    }

    private static Map<DirectedLocation, Integer> findDistances(List<String> maze) {
        Map<DirectedLocation, Integer> distances = new HashMap<>();
        Queue<DirectedLocation> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Set<DirectedLocation> processed = new HashSet<>();

        DirectedLocation startLocation = DirectedLocation.of(findSymbol(maze, 'S'));
        distances.put(startLocation, 0);
        queue.add(startLocation);

        while (!queue.isEmpty()) {
            DirectedLocation location = queue.poll();

            if (!processed.contains(location)) {
                processed.add(location);
                int distance = distances.get(location);

                DirectedLocation neighbour = location.moveForward();
                if (!processed.contains(neighbour) && maze.get(neighbour.x()).charAt(neighbour.y()) != '#') {
                    processNeighbour(neighbour, queue, FORWARD_PRICE, distances, distance);
                }

                neighbour = location.turn(false);
                if (!processed.contains(neighbour) && maze.get(neighbour.x()).charAt(neighbour.y()) != '#') {
                    processNeighbour(neighbour, queue, TURN_PRICE, distances, distance);
                }

                neighbour = location.turn(true);
                if (!processed.contains(neighbour) && maze.get(neighbour.x()).charAt(neighbour.y()) != '#') {
                    processNeighbour(neighbour, queue, TURN_PRICE, distances, distance);
                }
            }
        }

        return distances;
    }

    private static void processNeighbour(DirectedLocation neighbour, Queue<DirectedLocation> queue, int weight,
                                         Map<DirectedLocation, Integer> distances, int distance) {
        distance += weight;
        if (distance < distances.getOrDefault(neighbour, Integer.MAX_VALUE)) {
            distances.put(neighbour, distance);
        }
        queue.add(neighbour);
    }

    private static int[] findSymbol(List<String> maze, char c) {
        for (int i = 0; i < maze.size(); i++) {
            int j = maze.get(i).indexOf(c);
            if (j != -1) {
                return new int[]{i, j};
            }
        }

        return new int[]{0, 0};
    }

    private static List<String> parseMaze(String filename) throws IOException {
        return (new BufferedReader(new FileReader(filename))).lines()
                .toList();
    }

    private static int findLowestScore(Map<DirectedLocation, Integer> distances, DirectedLocation exit) {
        int price = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            exit = exit.turn(false);
            price = Math.min(price, distances.get(exit));
        }
        return price;
    }

    public static int partTwo(String filename) throws IOException {
        List<String> maze = parseMaze(filename);
        Map<DirectedLocation, Integer> distances = new HashMap<>();
        Map<DirectedLocation, List<DirectedLocation>> prev = findShortestPaths(maze, distances);
        DirectedLocation exit = DirectedLocation.of(findSymbol(maze, 'E'));
        int lowest = findLowestScore(distances, exit);

        Set<Location> pathNodes = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            System.out.println(lowest);
            if (distances.get(exit) == lowest) {
                findNodesInShortestPaths(prev, exit, pathNodes);
            }
            exit = exit.turn(true);
        }

        return  pathNodes.size();
    }

    private static Map<DirectedLocation, List<DirectedLocation>> findShortestPaths(List<String> maze, Map<DirectedLocation, Integer> distances) {
        Map<DirectedLocation, List<DirectedLocation>> prev = new HashMap<>();
        Queue<DirectedLocation> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Set<DirectedLocation> processed = new HashSet<>();

        DirectedLocation start = DirectedLocation.of(findSymbol(maze, 'S'));
        distances.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            DirectedLocation current = queue.poll();

            if (!processed.contains(current)) {
                processed.add(current);
                int distance = distances.get(current);

                DirectedLocation neighbour = current.moveForward();
                if (!processed.contains(neighbour) && maze.get(neighbour.x()).charAt(neighbour.y()) != '#') {
                    processNeighbourAlter(current, neighbour, queue, FORWARD_PRICE, distances, distance, prev);
                }

                neighbour = current.turn(false);
                if (!processed.contains(neighbour) && maze.get(neighbour.x()).charAt(neighbour.y()) != '#') {
                    processNeighbourAlter(current, neighbour, queue, TURN_PRICE, distances, distance, prev);
                }

                neighbour = current.turn(true);
                if (!processed.contains(neighbour) && maze.get(neighbour.x()).charAt(neighbour.y()) != '#') {
                    processNeighbourAlter(current, neighbour, queue, TURN_PRICE, distances, distance, prev);
                }
            }
        }
        return prev;
    }

    private static void processNeighbourAlter(DirectedLocation prev, DirectedLocation current,  Queue<DirectedLocation> queue, int weight, Map<DirectedLocation, Integer> distances, int distance, Map<DirectedLocation, List<DirectedLocation>> predecessors) {
        distance += weight;

        if (distance < distances.getOrDefault(current, Integer.MAX_VALUE)) {
            distances.put(current, distance);
            predecessors.put(current, new ArrayList<>());
            predecessors.get(current).add(prev);
        } else if (distance == distances.get(current)) {
            predecessors.putIfAbsent(current, new ArrayList<>());
            predecessors.get(current).add(prev);
        }

        queue.add(current);
    }

    private static void findNodesInShortestPaths(Map<DirectedLocation, List<DirectedLocation>> prev, DirectedLocation current, Set<Location> nodes) {
        nodes.add(Location.of(current));

        List<DirectedLocation> predecessors = prev.getOrDefault(current, Collections.emptyList());

        for (DirectedLocation predecessor : predecessors) {
            findNodesInShortestPaths(prev, predecessor, nodes);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partTwo("resources/input.txt"));
    }
}
