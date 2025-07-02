package week1.day1;

import utils.InputParser;

public class NotQuiteLisp {
    public int partOne(String filename) {
        String input = InputParser.parseInputLine(filename);
        int floor = 0;

        for (char c : input.toCharArray()) {
            if (c == '(') {
                floor++;
            } else {
                floor--;
            }
        }

        return floor;
    }

    public int partTwo(String filename) {
        String input = InputParser.parseInputLine(filename);
        int floor = 0;
        int position = 0;

        for (char c : input.toCharArray()) {
            if (c == '(') {
                floor++;
            } else {
                floor--;
            }
            position++;

            if (floor == -1) {
                return position;
            }
        }

        return 0;
    }
}
