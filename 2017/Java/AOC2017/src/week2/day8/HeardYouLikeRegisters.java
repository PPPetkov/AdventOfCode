package week2.day8;

import utils.InputParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeardYouLikeRegisters {
    private int getLargestRegister(Map<String, Integer> registers) {
        int max = Integer.MIN_VALUE;
        for (int val : registers.values()) {
            max = Math.max(val, max);
        }
        return max;
    }

    public int partOne(String filename) {
        List<String> instructions = InputParser.parseInput(filename);
        Map<String, Integer> registers = new HashMap<>();

        for (String instruction : instructions) {
            String[] tokens = instruction.split("\\s+");
            String register = tokens[0];
            String operation = tokens[1];
            int operationValue = Integer.parseInt(tokens[2]);
            String comparedRegister = tokens[4];
            Comparison comparison = Comparison.fromString(tokens[5]);
            int comparedValue = Integer.parseInt(tokens[6]);

            registers.putIfAbsent(register, 0);
            registers.putIfAbsent(comparedRegister, 0);

            int comparedRegisterValue = registers.get(comparedRegister);
            boolean comparisonResult = switch (comparison) {
                case EQUAL -> comparedRegisterValue == comparedValue;
                case NOT_EQUAL -> comparedRegisterValue != comparedValue;
                case LESS_THAN -> comparedRegisterValue < comparedValue;
                case LESS_THAN_EQ -> comparedRegisterValue <= comparedValue;
                case GREATER_THAN -> comparedRegisterValue > comparedValue;
                case GREATER_THAN_EQ -> comparedRegisterValue >= comparedValue;
            };
            if (comparisonResult) {
                switch (operation) {
                    case "inc": registers.put(register, registers.get(register) + operationValue); break;
                    case "dec": registers.put(register, registers.get(register) - operationValue); break;
                }
            }
        }

        return getLargestRegister(registers);
    }

    public int partTwo(String filename) {
        List<String> instructions = InputParser.parseInput(filename);
        Map<String, Integer> registers = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (String instruction : instructions) {
            String[] tokens = instruction.split("\\s+");
            String register = tokens[0];
            String operation = tokens[1];
            int operationValue = Integer.parseInt(tokens[2]);
            String comparedRegister = tokens[4];
            Comparison comparison = Comparison.fromString(tokens[5]);
            int comparedValue = Integer.parseInt(tokens[6]);

            registers.putIfAbsent(register, 0);
            registers.putIfAbsent(comparedRegister, 0);

            int comparedRegisterValue = registers.get(comparedRegister);
            boolean comparisonResult = switch (comparison) {
                case EQUAL -> comparedRegisterValue == comparedValue;
                case NOT_EQUAL -> comparedRegisterValue != comparedValue;
                case LESS_THAN -> comparedRegisterValue < comparedValue;
                case LESS_THAN_EQ -> comparedRegisterValue <= comparedValue;
                case GREATER_THAN -> comparedRegisterValue > comparedValue;
                case GREATER_THAN_EQ -> comparedRegisterValue >= comparedValue;
            };
            if (comparisonResult) {
                switch (operation) {
                    case "inc": registers.put(register, registers.get(register) + operationValue); break;
                    case "dec": registers.put(register, registers.get(register) - operationValue); break;
                }
            }
            max = Math.max(registers.get(register), max);
        }

        return max;
    }
}
