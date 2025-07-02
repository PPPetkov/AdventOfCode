package week2.day9;

import utils.InputParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InASingleNight {
    private Map<String, Map<String, Integer>> generateMap(List<String> input) {
        Map<String, Map<String, Integer>> map = new HashMap<>();

        for (String s : input) {
            String[] tokens = s.split("\\s++");

            map.putIfAbsent(tokens[0], new HashMap<>());
            map.putIfAbsent(tokens[2], new HashMap<>());
            int distance = Integer.parseInt(tokens[4]);

            map.get(tokens[0]).put(tokens[2], distance);
            map.get(tokens[2]).put(tokens[0], distance);
        }

        return map;
    }

    private int findShortest(int dist, String start, Map<String, Map<String, Integer>> map, Set<String> visited) {
        if (map.size() == visited.size() + 1) {
            return dist;
        }

        visited.add(start);
        Map<String, Integer> distances = map.get(start);
        int distance = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            if (!visited.contains(entry.getKey())) {
                distance = Math.min(findShortest(dist + entry.getValue(), entry.getKey(), map, new HashSet<>(visited)), distance);
            }
        }

        return distance;
    }

    public int partOne(String filename) {
        List<String> input = InputParser.parseInput(filename);
        Map<String, Map<String, Integer>> map = generateMap(input);
        int shortestPath = Integer.MAX_VALUE;

        for (String city : map.keySet()) {
            shortestPath = Math.min(shortestPath, findShortest(0, city, map, new HashSet<>()));
        }

        return shortestPath;
    }

    private int findLongest(int dist, String start, Map<String, Map<String, Integer>> map, Set<String> visited) {
        if (map.size() == visited.size() + 1) {
            return dist;
        }

        visited.add(start);
        Map<String, Integer> distances = map.get(start);
        int distance = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            if (!visited.contains(entry.getKey())) {
                distance = Math.max(findLongest(dist + entry.getValue(), entry.getKey(), map, new HashSet<>(visited)), distance);
            }
        }

        return distance;
    }

    public int partTwo(String filename) {
        List<String> input = InputParser.parseInput(filename);
        Map<String, Map<String, Integer>> map = generateMap(input);
        int longestPath = Integer.MIN_VALUE;

        for (String city : map.keySet()) {
            longestPath = Math.max(longestPath, findLongest(0, city, map, new HashSet<>()));
        }

        return longestPath;
    }
}
