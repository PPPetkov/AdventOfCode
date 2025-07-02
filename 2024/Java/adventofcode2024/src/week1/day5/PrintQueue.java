package week1.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrintQueue {
    private static Map<Integer, Set<Integer>> getOrdering(BufferedReader reader) throws IOException {
        Map<Integer, Set<Integer>> ordering = new HashMap<>();

        while (reader.ready()) {
            String line = reader.readLine();
            if (line.isEmpty()){
                break;
            }

            int index = line.indexOf('|');
            int number1 = Integer.parseInt(line.substring(0, index));
            int number2 = Integer.parseInt(line.substring(index + 1));

            ordering.putIfAbsent(number2, new HashSet<>());
            ordering.get(number2).add(number1);
        }

        return ordering;
    }

    private static List<Integer> getUpdate(String line) {
        String[] numbers = line.split(",");
        List<Integer> result = new ArrayList<>();

        for (String number : numbers) {
            result.add(Integer.parseInt(number));
        }

        return result;
    }

    private static List<List<Integer>> getUpdates(BufferedReader reader) throws IOException {
        List<List<Integer>> updates = new ArrayList<>();

        while (reader.ready()) {
            updates.add(getUpdate(reader.readLine()));
        }

        return updates;
    }

    private static boolean isOrdered(List<Integer> update, Map<Integer, Set<Integer>> ordering) {
        for (int i = 1; i < update.size(); i++) {
            int current = update.get(i);
            int prev = update.get(i - 1);

            if (!ordering.getOrDefault(current, Collections.emptySet()).contains(prev)) {
                return false;
            }
        }

        return true;
    }

    public static long partOne(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        Map<Integer, Set<Integer>> ordering = getOrdering(reader);
        List<List<Integer>> updates = getUpdates(reader);
        int sum = 0;

        for (List<Integer> update : updates) {
            if (isOrdered(update, ordering)) {
                sum += update.get(update.size()/2);
            }
        }

        return sum;
    }

    public static int partTwo(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        Map<Integer, Set<Integer>> ordering = getOrdering(reader);
        List<List<Integer>> updates = getUpdates(reader);
        int sum = 0;

        for (List<Integer> update : updates) {
            if (!isOrdered(update, ordering)) {
                reorderUpdate(update, ordering);
                sum += update.get(update.size()/2);
            }
        }

        return sum;
    }

    private static void reorderUpdate(List<Integer> update, Map<Integer, Set<Integer>> ordering) {
        boolean ordered = true;
        for (int i = 1; i < update.size(); i++) {
            int current = update.get(i);
            int prev = update.get(i - 1);

            if (!ordering.getOrDefault(current, Collections.emptySet()).contains(prev)) {
                update.set(i, prev);
                update.set(i - 1, current);
                ordered = false;
                break;
            }
        }

        if(!ordered) {
            reorderUpdate(update, ordering);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partTwo("resources/input.txt"));
    }
}
