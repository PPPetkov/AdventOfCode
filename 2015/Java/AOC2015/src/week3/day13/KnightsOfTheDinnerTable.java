package week3.day13;

import utils.InputParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KnightsOfTheDinnerTable {
    private Map<String, Map<String, Integer>> generateHappinessTable(List<String> input) {
        Map<String, Map<String,Integer>> table = new HashMap<>();

        for (String data : input) {
            String[] tokens = data.split("\\s+");
            int happiness = Integer.parseInt(tokens[3]);

            if (tokens[2].equals("lose")) {
                happiness = -happiness;
            }

            table.putIfAbsent(tokens[0], new HashMap<>());
            table.get(tokens[0]).put(tokens[10].substring(0, tokens[10].length()-1), happiness);
        }

        return table;
    }

    private int findOptimalHappiness(String start, String current, Map<String, Map<String, Integer>> table, Set<String> visited, int happiness) {
        if (visited.size() == table.size() - 1) {
            return happiness + table.get(current).get(start) + table.get(start).get(current);
        }
        visited.add(current);

        int optimal = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : table.get(current).entrySet()) {
            if (!visited.contains(entry.getKey())) {
                String next = entry.getKey();
                optimal = Math.max(optimal, findOptimalHappiness(start, next, table, new HashSet<>(visited),
                        happiness + entry.getValue() + table.get(next).get(current)));
            }
        }

        return optimal;
    }

    public int partOne(String filename) {
        List<String> input = InputParser.parseInput(filename);
        Map<String, Map<String, Integer>> table = generateHappinessTable(input);

        String start = "Alice";

        return findOptimalHappiness(start, start, table, new HashSet<>(), 0);
    }

    public int partTwo(String filename) {
        List<String> input = InputParser.parseInput(filename);
        Map<String, Map<String, Integer>> table = generateHappinessTable(input);

        Map<String, Integer> myHappiness = new HashMap<>();
        String me = "me";
        for (Map.Entry<String, Map<String, Integer>> entry : table.entrySet()) {
            myHappiness.put(entry.getKey(), 0);
            entry.getValue().put("me", 0);
        }
        table.put(me, myHappiness);

        return findOptimalHappiness(me, me, table, new HashSet<>(), 0);
    }
}
