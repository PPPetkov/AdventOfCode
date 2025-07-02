package week2.day5;

import utils.InputParser;
import java.util.List;

public class TwistyTrampolines {
    private int[] getTrampolines(List<String> input) {
        int[] trampolines = new int[input.size()];

        for (int i = 0; i < input.size(); i++) {
            trampolines[i] = Integer.parseInt(input.get(i));
        }

        return trampolines;
    }

    public int partOne(String filename) {
        int[] trampolines = getTrampolines(InputParser.parseInput(filename));

        int crr = 0;
        int steps = 0;
        while (crr < trampolines.length) {
            int prev = crr;
            crr += trampolines[crr];
            trampolines[prev]++;

            steps++;
        }

        return steps;
    }

    public int partTwo(String filename) {
        int[] trampolines = getTrampolines(InputParser.parseInput(filename));

        int crr = 0;
        int steps = 0;
        while (crr < trampolines.length) {
            int prev = crr;
            crr += trampolines[crr];
            trampolines[prev] += trampolines[prev] > 2 ? -1 : 1;

            steps++;
        }

        return steps;
    }
}
