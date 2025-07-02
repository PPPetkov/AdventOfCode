package week4.day24;

import utils.InputParser;
import java.util.List;

public class ItHangsInTheBalance {
    private long[] getWeights(List<String> input) {
        long[] result = new long[input.size()];

        for (int i = 0; i < input.size(); i++) {
            result[i] = Long.parseLong(input.get(i));
        }

        return result;
    }

    private long getMinimumQuantumEntanglement(long goal, long size, long entanglement, long[] weights, int current) {
        if (goal == 0 && size == 0) {
            return entanglement;
        }

        if (goal < 0 || size == 0 || current == weights.length) {
            return Long.MAX_VALUE;
        }

        return Math.min(getMinimumQuantumEntanglement(goal - weights[current], size - 1, entanglement * weights[current], weights, current + 1),
                getMinimumQuantumEntanglement(goal, size, entanglement, weights, current + 1));
    }

    private long sumArr(long[] arr) {
        long sum = 0;

        for (long x : arr) {
            sum += x;
        }

        return sum;
    }

    public long partOne(String filename) {
        long[] weights = getWeights(InputParser.parseInput(filename));
        long goal = sumArr(weights) / 3;
        long size = 1;

        while (size < weights.length) {
            long entanglement = getMinimumQuantumEntanglement(goal, size, 1, weights, 0);
            if (entanglement != Long.MAX_VALUE) {
                return entanglement;
            }
            size++;
        }

        return 0;
    }

    public long partTwo(String filename) {
        long[] weights = getWeights(InputParser.parseInput(filename));
        long goal = sumArr(weights) / 4;
        long size = 1;

        while (size < weights.length) {
            long entanglement = getMinimumQuantumEntanglement(goal, size, 1, weights, 0);
            if (entanglement != Long.MAX_VALUE) {
                return entanglement;
            }
            size++;
        }

        return 0;
    }
}
