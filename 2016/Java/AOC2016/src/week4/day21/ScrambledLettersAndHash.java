package week4.day21;

import com.sun.jdi.IntegerType;
import utils.InputParser;

import java.util.Arrays;
import java.util.List;

public class ScrambledLettersAndHash {
    private void swapPositions(int x, int y, StringBuilder string) {
        char temp = string.charAt(x);
        string.setCharAt(x, string.charAt(y));
        string.setCharAt(y, temp);
    }

    private void rotateRight(int steps, StringBuilder string) {
        String origin = string.toString();
        int len = origin.length();

        for (int i = 0; i < len; i++) {
            string.setCharAt((i + steps) % len, origin.charAt(i));
        }
    }

    private void rotateLeft(int steps, StringBuilder string) {
        String origin = string.toString();
        int len = origin.length();

        for (int i = 0; i < len; i++) {
            string.setCharAt(((i - steps) % len + len) % len, origin.charAt(i));
        }
    }

    private void rotateByChar(int index, StringBuilder string) {
        rotateRight(index + 1 + (index > 3 ? 1 : 0), string);
    }

    private void reversePositions(int x, int y, StringBuilder string) {
        String origin = string.toString();
        int len = origin.length();

        for (int i = 0; i < len; i++) {
            if (i >= x && i <= y) {
                string.setCharAt(i, origin.charAt(y - (i - x)));
            } else {
                string.setCharAt(i, origin.charAt(i));
            }
        }
    }

    private void movePosition(int x, int y, StringBuilder string) {
        char c = string.charAt(x);

        if (x > y) {
            for (int i = x; i > y; i--) {
                string.setCharAt(i, string.charAt(i - 1));
            }
        } else {
            for (int i = x; i < y; i++) {
                string.setCharAt(i, string.charAt(i + 1));
            }
        }
        string.setCharAt(y, c);
    }

    private void scramble(String instruction, StringBuilder string) {
        String[] tokens = instruction.split("\\s+");

        switch (tokens[0]) {
            case "swap" -> {
                if (tokens[1].equals("position")) {
                    swapPositions(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[5]), string);
                } else {
                    swapPositions(string.indexOf(tokens[2]), string.indexOf(tokens[5]), string);
                }
            }
            case "rotate" -> {
                if (tokens[1].equals("right")) {
                    rotateRight(Integer.parseInt(tokens[2]), string);
                } else if (tokens[1].equals("left")) {
                    rotateLeft(Integer.parseInt(tokens[2]), string);
                } else {
                    rotateByChar(string.indexOf(tokens[6]), string);
                }
            }
            case "reverse" -> reversePositions(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[4]), string);
            default -> movePosition(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[5]), string);
        }
    }

    public String partOne(String filename, String input) {
        List<String> instructions = InputParser.parseInput(filename);
        StringBuilder scrambled = new StringBuilder(input);

        for (String instruction : instructions) {
            scramble(instruction, scrambled);
        }

        return scrambled.toString();
    }

    private void rotateByCharReversed(int index, StringBuilder string) {
        switch (index) {
            case 0, 1: rotateLeft(1, string); break;
            case 2: rotateRight(2, string); break;
            case 3: rotateLeft(2, string); break;
            case 4: rotateRight(1, string); break;
            case 5: rotateLeft(3, string); break;
            case 7: rotateLeft(4, string); break;
        }
    }

    private void unscramble(String instruction, StringBuilder string) {
        String[] tokens = instruction.split("\\s+");

        switch (tokens[0]) {
            case "swap" -> {
                if (tokens[1].equals("position")) {
                    swapPositions(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[5]), string);
                } else {
                    swapPositions(string.indexOf(tokens[2]), string.indexOf(tokens[5]), string);
                }
            }
            case "rotate" -> {
                if (tokens[1].equals("right")) {
                    rotateLeft(Integer.parseInt(tokens[2]), string);
                } else if (tokens[1].equals("left")) {
                    rotateRight(Integer.parseInt(tokens[2]), string);
                } else {
                    rotateByCharReversed(string.indexOf(tokens[6]), string);
                }
            }
            case "reverse" -> reversePositions(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[4]), string);
            default -> movePosition(Integer.parseInt(tokens[5]), Integer.parseInt(tokens[2]), string);
        }
    }

    public String partTwo(String filename, String input) {
        List<String> instructions = InputParser.parseInput(filename);
        StringBuilder scrambled = new StringBuilder(input);

        for (String instruction : instructions.reversed()) {
            unscramble(instruction, scrambled);
        }

        return scrambled.toString();
    }
}
