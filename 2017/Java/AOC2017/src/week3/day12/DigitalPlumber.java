package week3.day12;

import utils.InputParser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DigitalPlumber {
    private Set<Integer> getConnectedComponent(int start, List<List<Integer>> adjacencyLists) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int current = queue.remove();

                if (!visited.add(current)) {
                    continue;
                }

                queue.addAll(adjacencyLists.get(current));
            }
        }

        return visited;
    }

    private List<List<Integer>> generateAdjacencyLists(List<String> input) {
        List<List<Integer>> adjacencyLists = new ArrayList<>();

        for (String line : input) {
            String[] tokens = line.split(" <-> ")[1].split(", ");
            List<Integer> list = new ArrayList<>();

            for (String token : tokens) {
                list.add(Integer.parseInt(token));
            }

            adjacencyLists.add(list);
        }

        return adjacencyLists;
    }

    public int partOne(String filename) {
        return getConnectedComponent(0, generateAdjacencyLists(InputParser.parseInput(filename))).size();
    }

    private boolean foundComponent(int x, List<Set<Integer>> components) {
        for (Set<Integer> component : components) {
            if (component.contains(x)) {
                return true;
            }
        }

        return false;
    }

    public int partTwo(String filename) {
        List<List<Integer>> adjacencyLists = generateAdjacencyLists(InputParser.parseInput(filename));
        List<Set<Integer>> components = new ArrayList<>();

        for (int i = 0; i < adjacencyLists.size(); i++) {
            if (!foundComponent(i, components)) {
                components.add(getConnectedComponent(i, adjacencyLists));
            }
        }

        return components.size();
    }
}
