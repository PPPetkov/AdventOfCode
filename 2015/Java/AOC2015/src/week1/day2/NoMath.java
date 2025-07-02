package week1.day2;

import utils.InputParser;

import java.util.List;

public class NoMath {
    public int partOne(String filename) {
        List<String> input = InputParser.parseInput(filename);

        return input.stream()
                .map(Present::of)
                .mapToInt(Present::calculateRequiredPaper)
                .sum();
    }

    public int partTwo(String filename) {
        List<String> input = InputParser.parseInput(filename);

        return input.stream()
                .map(Present::of)
                .mapToInt(Present::calculateRequiredRibbon)
                .sum();
    }
}
