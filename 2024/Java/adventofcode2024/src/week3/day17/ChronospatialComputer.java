package week3.day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class ChronospatialComputer {
    public static String partOne(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        long A = Long.parseLong(parseInput(reader));
        long B = Long.parseLong(parseInput(reader));
        long C = Long.parseLong(parseInput(reader));
        reader.skip(1);
        List<Long> instructions = parseInstructions(parseInput(reader));

        return runProgram(instructions, A, B, C);
    }

    private static String runProgram(List<Long> instructions, long A, long B, long C) {
        int instructionPointer = 0;
        StringBuilder output = new StringBuilder();

        while (instructionPointer < instructions.size() - 1) {
            long instruction = instructions.get(instructionPointer++);
            long operand = instructions.get(instructionPointer++);
            long combo = getComboOperandValue(operand, A, B, C);

            switch (instruction) {
                case 0L:
                    A = (long) (A / Math.pow(2, combo));
                    break;
                case 1L:
                    B ^= operand;
                    break;
                case 2L:
                    B = combo % 8;
                    break;
                case 3L:
                    if (A != 0) instructionPointer = (int) operand;
                    break;
                case 4L:
                    B ^= C;
                    break;
                case 5L:
                    if (!output.isEmpty()) output.append(',');
                    output.append(combo % 8);
                    break;
                case 6L:
                    B = (long) (A / Math.pow(2, combo));
                    break;
                case 7L:
                    C = (long) (A / Math.pow(2, combo));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + instruction);
            }
        }
        return output.toString();
    }

    private static long getComboOperandValue(long operand, long A, long B, long C) {
        return switch (operand) {
            case 4L -> A;
            case 5L -> B;
            case 6L -> C;
            default -> operand;
        };
    }

    private static List<Long> parseInstructions(String s) {
        String[] tokens = s.split(",");

        List<Long> instructions = new ArrayList<>();
        for (String token : tokens) {
            instructions.add(Long.parseLong(token));
        }

        return instructions;
    }

    private static String parseInput(BufferedReader reader) throws IOException {
        String[] tokens = reader.readLine().split("[:\\s]");
        return tokens[tokens.length - 1];
    }

    private static long partTwo(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        long A = Long.parseLong(parseInput(reader));
        long B = Long.parseLong(parseInput(reader));
        long C = Long.parseLong(parseInput(reader));
        reader.skip(1);
        String instructionsString = parseInput(reader);
        List<Long> instructions = parseInstructions(instructionsString);
        Queue<Long> candidates = new ArrayDeque<>();
        candidates.add(0L);
        candidates.add(3L);
        int len = instructionsString.length();

        for (int i = len - 3; i > -1; i -= 2) {
            String goal = instructionsString.substring(i);
            System.out.println(goal);
            int queueSize = candidates.size();
            while (queueSize > 0) {
                long candidate = candidates.poll();
                for (int j = 0; j < 8; j++) {
                    A = (candidate << 3) | j;
                    if (goal.equals(runProgram(instructions, A, B, C))) {
                        candidates.add(A);
                    }
                }
                queueSize--;
            }
        }
        return candidates.stream().min(Comparator.naturalOrder()).get();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partTwo("resources/input.txt"));
    }
}
