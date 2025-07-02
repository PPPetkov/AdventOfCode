package week1.day6;

import utils.InputParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignalsAndNoise {
    private List<Map<Character, Integer>> getHistograms(List<String> input) {
        List<Map<Character, Integer>> histograms = new ArrayList<>();
        for (int i = 0; i < input.getFirst().length(); i++) {
            histograms.add(new HashMap<>());
        }

        for (String s : input) {
            for (int i = 0; i < s.length(); i++) {
                Map<Character, Integer> histogram = histograms.get(i);
                char c = s.charAt(i);

                histogram.putIfAbsent(c, 0);
                histogram.put(c, histogram.get(c) + 1);
            }
        }

        return histograms;
    }

    private char findMostCommonCharacter(Map<Character, Integer> histogram) {
        char c = ' ';
        int encounters = 0;
        for (Map.Entry<Character, Integer> entry : histogram.entrySet()) {
            if (entry.getValue() > encounters) {
                c = entry.getKey();
                encounters = entry.getValue();
            }
        }

        return c;
    }

    private char findLeastCommonCharacter(Map<Character, Integer> histogram) {
        char c = ' ';
        int encounters = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : histogram.entrySet()) {
            if (entry.getValue() < encounters) {
                c = entry.getKey();
                encounters = entry.getValue();
            }
        }

        return c;
    }

    public String partOne(String filename) {
        List<String> input = InputParser.parseInput(filename);
        List<Map<Character, Integer>> histograms = getHistograms(input);
        StringBuilder message = new StringBuilder();

        for (Map<Character, Integer> histogram : histograms) {
            message.append(findMostCommonCharacter(histogram));
        }

        return message.toString();
    }

    public String partTwo(String filename) {
        List<String> input = InputParser.parseInput(filename);
        List<Map<Character, Integer>> histograms = getHistograms(input);
        StringBuilder message = new StringBuilder();

        for (Map<Character, Integer> histogram : histograms) {
            message.append(findLeastCommonCharacter(histogram));
        }

        return message.toString();
    }
}
