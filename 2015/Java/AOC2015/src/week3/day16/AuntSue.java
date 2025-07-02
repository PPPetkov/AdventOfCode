package week3.day16;

import utils.InputParser;

import java.util.List;

public class AuntSue {
    private static final int[] GIFTER_SUE = new int[]{3,7,2,3,0,0,5,3,2,1};
    private static final int INFO_SIZE = 10;

    private static final int CHILDREN_INDEX = 0;
    private static final int CATS_INDEX = 1;
    private static final int SAMOYEDS_INDEX = 2;
    private static final int POMERANIANS_INDEX = 3;
    private static final int AKITAS_INDEX = 4;
    private static final int VIZSLAS_INDEX = 5;
    private static final int GOLDFISH_INDEX = 6;
    private static final int TREES_INDEX = 7;
    private static final int CARS_INDEX = 8;
    private static final int PERFUMES_INDEX = 9;

    private static final String CHILDREN = "children";
    private static final String CATS = "cats";
    private static final String SAMOYEDS = "samoyeds";
    private static final String POMERANIANS = "pomeranians";
    private static final String AKITAS = "akitas";
    private static final String VIZSLAS = "vizslas";
    private static final String GOLDFISH = "goldfish";
    private static final String TREES = "trees";
    private static final String CARS = "cars";
    private static final String PERFUMES = "perfumes";

    private int[][] getSueInfo(List<String> input) {
        int[][] info = new int[input.size()][INFO_SIZE];

        for (int i = 0; i < input.size(); i++) {
            String replaced = input.get(i).replaceAll("[,:]", "");
            String[] tokens = replaced.split("\\s+");
            for (int j = 0; j < INFO_SIZE; j++) {
                info[i][j] = -1;
            }

            for (int j = 2; j < tokens.length - 1; j += 2) {
                int ownedCount = Integer.parseInt(tokens[j+1]);
                switch (tokens[j]) {
                    case CHILDREN -> info[i][CHILDREN_INDEX] = ownedCount;
                    case CATS -> info[i][CATS_INDEX] = ownedCount;
                    case SAMOYEDS -> info[i][SAMOYEDS_INDEX] = ownedCount;
                    case POMERANIANS -> info[i][POMERANIANS_INDEX] = ownedCount;
                    case AKITAS -> info[i][AKITAS_INDEX] = ownedCount;
                    case VIZSLAS ->  info[i][VIZSLAS_INDEX] = ownedCount;
                    case GOLDFISH -> info[i][GOLDFISH_INDEX] = ownedCount;
                    case TREES -> info[i][TREES_INDEX] = ownedCount;
                    case CARS -> info[i][CARS_INDEX] = ownedCount;
                    case PERFUMES -> info[i][PERFUMES_INDEX] = ownedCount;
                }
            }
        }

        return info;
    }

    private int countMatches(int[] candidate) {
        int matches = 0;
        for (int i = 0; i < candidate.length; i++) {
            if (candidate[i] == GIFTER_SUE[i]) {
                matches++;
            }
        }

        return matches;
    }

    public int partOne(String filename) {
        int[][] aunts = getSueInfo(InputParser.parseInput(filename));
        int gifter = -1;
        int gifterMatches = -1;

        for (int i = 0; i < aunts.length; i++) {
            int currentMatches = countMatches(aunts[i]);
            if (currentMatches > gifterMatches) {
                gifter = i;
                gifterMatches = currentMatches;
            }
        }

        return gifter + 1;
    }

    private int countMatchesTwo(int[] candidate) {
        int matches = 0;

        for (int i = 0; i < candidate.length; i++) {
            if (i == CATS_INDEX || i == TREES_INDEX) {
                if (candidate[i] > GIFTER_SUE[i]) {
                    matches++;
                }
            } else if (i == POMERANIANS_INDEX || i == GOLDFISH_INDEX) {
                if (candidate[i] != -1 && candidate[i] < GIFTER_SUE[i]) {
                    matches++;
                }
            } else if (candidate[i] == GIFTER_SUE[i]) {
                matches++;
            }
        }

        return matches;
    }

    public int partTwo(String filename) {
        int[][] aunts = getSueInfo(InputParser.parseInput(filename));
        int gifter = -1;
        int gifterMatches = -1;

        for (int i = 0; i < aunts.length; i++) {
            int currentMatches = countMatchesTwo(aunts[i]);

            if (currentMatches > gifterMatches) {
                gifter = i;
                gifterMatches = currentMatches;
            }
        }

        return gifter + 1;
    }
}
