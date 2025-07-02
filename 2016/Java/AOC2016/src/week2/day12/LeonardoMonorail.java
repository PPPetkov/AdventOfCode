package week2.day12;

import utils.InputParser;
import java.util.List;

public class LeonardoMonorail {
    private int findValue(String token, int[] registers) {
        return switch (token) {
            case "a", "b", "c", "d" -> registers[token.charAt(0) - 'a'];
            default -> Integer.parseInt(token);
        };
    }

    private void runProgram(List<String> instructions, int[] registers) {
        for (int i = 0; i < instructions.size(); i++) {
            String[] tokens = instructions.get(i).split("\\s+");
            switch (tokens[0]) {
                case "cpy" -> registers[tokens[2].charAt(0) - 'a'] = findValue(tokens[1], registers);
                case "inc" -> registers[tokens[1].charAt(0) - 'a']++;
                case "dec" -> registers[tokens[1].charAt(0) - 'a']--;
                case "jnz" -> {
                    int x = findValue(tokens[1], registers);
                    if (x != 0) {
                        i += Integer.parseInt(tokens[2]) - 1;
                    }
                }
            }
        }

    }

    public int partOne(String filename) {
        int[] registers = new int[]{0,0,0,0};
        runProgram(InputParser.parseInput(filename), registers);
        return registers[0];
    }

    public int partTwo(String filename) {
        int[] registers = new int[]{0,0,1,0};
        runProgram(InputParser.parseInput(filename), registers);
        return registers[0];
    }
}
