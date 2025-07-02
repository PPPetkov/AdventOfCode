package week4.day23;

import utils.InputParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoprocessorConflagration {
    private boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    private Map<String, Integer> initRegisters() {
        Map<String, Integer> registers = new HashMap<>();
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);
        registers.put("e", 0);
        registers.put("f", 0);
        registers.put("g", 0);
        registers.put("h", 0);

        return registers;
    }

    public int partOne(String filename) {
        List<String> instructions = InputParser.parseInput(filename);
        Map<String, Integer> registers = initRegisters();
        int mulInvocations = 0;
        for (int i = 0; i < instructions.size(); i++) {
            String[] tokens = instructions.get(i).split("\\s+");
            int x = isNumber(tokens[1]) ? Integer.parseInt(tokens[1]) : registers.get(tokens[1]);
            int y = isNumber(tokens[2]) ? Integer.parseInt(tokens[2]) : registers.get(tokens[2]);

            switch (tokens[0]) {
                case "set" -> registers.put(tokens[1], y);
                case "sub" -> registers.put(tokens[1], x - y);
                case "mul" -> {
                    registers.put(tokens[1], x * y);
                    mulInvocations++;
                }
                default -> {
                    if (x != 0) {
                        i += y - 1;
                    }
                }
            }
        }

        return mulInvocations;
    }

    public int partTwo(String filename) {
        int h = 0;

        for (int b = 107900; b <= 124900; b += 17) {
            boolean isPrime = true;

            for (int d = 2; d * d <= b; d++) {
                if (b % d == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (!isPrime) {
                h++;
            }
        }

        return h;
    }
}
