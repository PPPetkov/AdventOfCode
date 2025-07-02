package week3.day17;

import utils.InputParser;
import java.util.List;

public class NoSuchThingAsTooMuch {
    public int[] getJugSizes(List<String> jugs) {
        int[] sizes = new int[jugs.size()];

        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = Integer.parseInt(jugs.get(i));
        }

        return sizes;
    }

    private int countWays(int goal, int[] jugs, int current) {
        if (goal == 0) {
            return 1;
        }
        if (current >= jugs.length || goal < 0) {
            return 0;
        }

        return countWays(goal - jugs[current], jugs, current + 1) +
                countWays(goal, jugs, current + 1);
    }

    public int partOne(String filename) {
        int[] jugs = getJugSizes(InputParser.parseInput(filename));

        return countWays(150, jugs, 0);
    }

    private int countWaysLimited(int goal, int[] jugs, int current, int remainingUses) {
        if (goal == 0 && remainingUses == 0) {
            return 1;
        }
        if (remainingUses == 0 || goal < 0 || current >= jugs.length) {
            return 0;
        }

        return countWaysLimited(goal - jugs[current], jugs, current + 1, remainingUses-1)
                + countWaysLimited(goal, jugs, current + 1, remainingUses);
    }

    public int partTwo(String filename) {
        int[] jugs = getJugSizes(InputParser.parseInput(filename));

        for (int i = 1; i <= jugs.length; i++) {
            int ways = countWaysLimited(150, jugs, 0, i);
            if (ways != 0) {
                return ways;
            }
        }

        return 0;
    }
}
