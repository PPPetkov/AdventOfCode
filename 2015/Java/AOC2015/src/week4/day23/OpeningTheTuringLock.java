package week4.day23;

import utils.InputParser;

import java.util.List;

public class OpeningTheTuringLock {
    private int runProgram(int a, int b, List<String> instructions) {
        for (int i = 0; i < instructions.size(); i++) {
            String[] instruction = instructions.get(i).split("\\s+");

            if (instruction[0].equals("hlf")) {
                if (instruction[1].contains("a")) {
                    a /= 2;
                } else {
                    b /= 2;
                }
            } else if (instruction[0].equals("tpl")) {
                if (instruction[1].contains("a")) {
                    a *= 3;
                } else {
                    b *= 3;
                }
            } else if (instruction[0].equals("inc")) {
                if (instruction[1].contains("a")) {
                    a++;
                } else {
                    b++;
                }
            } else if (instruction[0].equals("jmp")) {
                i += Integer.parseInt(instruction[1]) - 1;
            } else if (instruction[0].equals("jie")) {
                int r = instruction[1].contains("a") ? a : b;
                if (r % 2 == 0) {
                    i += Integer.parseInt(instruction[2]) - 1;
                }
            } else if (instruction[0].equals("jio")) {
                int r = instruction[1].contains("a") ? a : b;
                if (r == 1) {
                    i += Integer.parseInt(instruction[2]) - 1;
                }
            }
        }

        return b;
    }

    public int partOne(String filename) {
        return runProgram(0, 0, InputParser.parseInput(filename));
    }

    public int partTwo(String filename) {
        return runProgram(1, 0, InputParser.parseInput(filename));
    }
}
