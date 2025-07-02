package week4.day25;

import java.util.HashMap;
import java.util.Map;

public class TheHaltingProblem {
    private int checksum(Map<Integer, Boolean> tape) {
        int sum = 0;
        for (boolean b : tape.values()) {
            if (b) {
                sum++;
            }
        }
        return sum;
    }

    public int partOne(int steps) {
        char state = 'A';
        Map<Integer, Boolean> tape = new HashMap<>();
        int current = 0;
        while (steps-- > 0) {
            boolean val = tape.getOrDefault(current, false);
            switch (state) {
                case 'A' -> {
                    tape.put(current, !val);
                    current += val ? -1 : 1;
                    state = 'B';
                }
                case 'B' -> {
                    tape.put(current, val);
                    current += val ? -1 : 1;
                    state = val ? 'B' : 'C';
                }
                case 'C' -> {
                    tape.put(current, !val);
                    current += val ? -1 : 1;
                    state = val ? 'A' : 'D';
                }
                case 'D' -> {
                    tape.put(current--, true);
                    state = val ? 'F' : 'E';
                }
                case 'E' -> {
                    tape.put(current--, !val);
                    state = val ? 'D' : 'A';
                }
                case 'F' -> {
                    tape.put(current, true);
                    current += val ? -1 : 1;
                    state = val ? 'E' : 'A';
                }
            }
        }

        return checksum(tape);
    }
}
