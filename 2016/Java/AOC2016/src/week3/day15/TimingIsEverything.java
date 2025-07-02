package week3.day15;

import utils.InputParser;

import java.util.List;

public class TimingIsEverything {
    private int[][] initDiscs(List<String> discData) {
        int[][] discs = new int[2][discData.size()];

        for (int i = 0; i < discData.size(); i++) {
            String[] tokens = discData.get(i).split("\\s+");
            discs[0][i] = Integer.parseInt(tokens[3]);
            discs[1][i] = Integer.parseInt(tokens[11].substring(0, tokens[11].length() - 1));
        }

        return discs;
    }

    private boolean shouldPressButton(int[][] discs) {
        for (int i = 0; i < discs[0].length; i++) {
            if ((discs[1][i] + i + 1) % discs[0][i] != 0) {
                return false;
            }
        }

        return true;
    }

    public int partOne(String filename) {
        int[][] discs = initDiscs(InputParser.parseInput(filename));
        int time = 0;

        while (true) {
            if (shouldPressButton(discs)) {
                return time;
            }

            for (int i = 0; i < discs[0].length; i++) {
                discs[1][i] = (discs[1][i] + 1) % discs[0][i];
            }
            time++;
        }
    }

    private int[][] initDiscs(List<String> discData, int bottomDiscPositions, int bottomDiscStart) {
        int[][] discs = new int[2][discData.size() + 1];

        for (int i = 0; i < discData.size(); i++) {
            String[] tokens = discData.get(i).split("\\s+");
            discs[0][i] = Integer.parseInt(tokens[3]);
            discs[1][i] = Integer.parseInt(tokens[11].substring(0, tokens[11].length() - 1));
        }

        discs[0][discData.size()] = bottomDiscPositions;
        discs[1][discData.size()] = bottomDiscStart;

        return discs;
    }

    public int partTwo(String filename, int bottomDiscPositions, int bottomDiscStart) {
        int[][] discs = initDiscs(InputParser.parseInput(filename), bottomDiscPositions, bottomDiscStart);
        int time = 0;

        while (true) {
            if (shouldPressButton(discs)) {
                return time;
            }

            for (int i = 0; i < discs[0].length; i++) {
                discs[1][i] = (discs[1][i] + 1) % discs[0][i];
            }
            time++;
        }
    }
}
