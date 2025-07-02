package week2.day11;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class RadioisotopeGenerators {
    private State initState() {
        // thulium = 0
        // plutonium = 1
        // strontium = 2
        // promethium = 3
        // ruthenium = 4
        return new State(1, new int[]{1, 2, 2, 3, 3}, new int[]{1, 1, 1, 3, 3});
    }

    public int partOne() {
        State start = initState();
        Queue<State> queue = new ArrayDeque<>();
        Set<State> visited = new HashSet<>();
        queue.add(start);
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println(size + " " + steps);
            while (size-- > 0) {
                State current = queue.remove();

                if (current.isFinalState()) {
                    return steps;
                }
                if (visited.contains(current) || !current.isValidState()) {
                    continue;
                }
                visited.add(current);
                queue.addAll(current.getNextStates());
            }
            steps++;
        }

        return 0;
    }

    private State initStateTwo() {
        // thulium = 0
        // plutonium = 1
        // strontium = 2
        // promethium = 3
        // ruthenium = 4
        // elerium = 5
        // dilithium = 6
        return new State(1, new int[]{1, 2, 2, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3, 1, 1});
    }

    public int partTwo() {
        State start = initStateTwo();
        Queue<State> queue = new ArrayDeque<>();
        Set<State> visited = new HashSet<>();
        queue.add(start);
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println(size + " " + steps);
            while (size-- > 0) {
                State current = queue.remove();

                if (current.isFinalState()) {
                    return steps;
                }
                if (visited.contains(current)) {
                    continue;
                }
                visited.add(current);
                queue.addAll(current.getNextStates());
            }
            steps++;
        }

        return 0;
    }
}
