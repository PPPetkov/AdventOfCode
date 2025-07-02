package week2.day7;

import utils.InputParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssemblyRequired {
    private boolean isNumber(char[] str) {
        for (char c : str) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private int execute(String command, Map<String, String> wires, Map<String, Integer> values, String wire) {
        if (values.get(wire) != null) {
            return values.get(wire);
        }
        if (command == null) {
            return Integer.parseInt(wire);
        }
        String[] tokens = command.split("\\s+");

        if (tokens.length == 1) {
            if (isNumber(tokens[0].toCharArray())) {
                values.put(wire, Integer.parseInt(tokens[0]));
                return values.get(wire);
            } else {
                int s = execute(wires.get(tokens[0]), wires, values, tokens[0]);
                values.put(wire, s);
                return s;
            }
        }

        int result = 0;

        if (tokens[0].equals("NOT")) {
            result = (int) ~execute(wires.get(tokens[1]), wires, values, tokens[1]);
        } else if (tokens[1].equals("AND")) {
            int left = execute(wires.get(tokens[0]), wires, values, tokens[0]);
            int right = execute(wires.get(tokens[2]), wires, values, tokens[2]);
            result = (int) (left & right);
        } else if (tokens[1].equals("OR")) {
            int left = execute(wires.get(tokens[0]), wires, values, tokens[0]);
            int right = execute(wires.get(tokens[2]), wires, values, tokens[2]);
            result = (int) (left | right);
        } else if (tokens[1].equals("RSHIFT")) {
            int left = execute(wires.get(tokens[0]), wires, values, tokens[0]);
            int right = execute(wires.get(tokens[2]), wires, values, tokens[2]);
            result = (int) (left >> right);
        } else if (tokens[1].equals("LSHIFT")) {
            int left = execute(wires.get(tokens[0]), wires, values, tokens[0]);
            int right = execute(wires.get(tokens[2]), wires, values, tokens[2]);
            result = (int) (left << right);
        }

        values.put(wire, result);
        return result;
    }

    public int partOne(String filename) {
        List<String> instructions = InputParser.parseInput(filename);
        Map<String, String> wires = mapWiresToCommands(instructions);

        return execute(wires.get("a"), wires, new HashMap<>(), "a");
    }

    private Map<String, String> mapWiresToCommands(List<String> instructions) {
        Map<String, String> wires = new HashMap<>();

        for (String instruction : instructions) {
            String[] tokens = instruction.split(" -> ");
            wires.put(tokens[1], tokens[0]);
        }

        return wires;
    }

    public int partTwo(String filename) {
        List<String> instructions = InputParser.parseInput(filename);
        Map<String, String> wires = mapWiresToCommands(instructions);

        int signal = execute(wires.get("a"), wires, new HashMap<>(), "a");
        wires.put("b", String.valueOf(signal));

        return execute(wires.get("a"), wires, new HashMap<>(), "a");
    }
}
