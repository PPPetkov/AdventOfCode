package week2.day10;

import utils.InputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnotHash {
    private int[] generateList(int size) {
        int[] list = new int[size];

        for (int i = 0; i < size; i++) {
            list[i] = i;
        }

        return list;
    }

    private void reverseSubList(int[] list, int start, int end) {
        int size = list.length;
        while (start < end) {
            int temp = list[start % size];
            list[start % size] = list[end % size];
            list[end % size] = temp;
            start++;
            end--;
        }
    }

    public int partOne(String filename) {
        List<Integer> lengths = Arrays.stream(InputParser.parseInputLine(filename).split(",")).map(Integer::parseInt).toList();

        int listSize = 256;
        int[] list = generateList(listSize);
        int skipSize = 0;
        int currentPosition = 0;

        for (int length : lengths) {
            reverseSubList(list, currentPosition, currentPosition + length - 1);
            currentPosition += length + skipSize;
            skipSize++;
        }

        return list[0]*list[1];
    }

    private List<Integer> getAsciiCodes(String input) {
        List<Integer> codes = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            codes.add((int) input.charAt(i));
        }
        codes.add(17);
        codes.add(31);
        codes.add(73);
        codes.add(47);
        codes.add(23);
        return codes;
    }

    public String getKnotHash(String input) {
        List<Integer> lengths = getAsciiCodes(input);
        int size = 256;
        int[] list = generateList(size);
        int skipSize = 0;
        int currentPosition = 0;

        for (int i = 0; i < 64; i++) {
            for (int length : lengths) {
                reverseSubList(list, currentPosition, currentPosition + length - 1);
                currentPosition += length + skipSize;
                skipSize++;
            }
        }

        int[] dense = new int[16];
        for (int i = 0; i < 16; i++) {
            int current = list[i*16];
            for (int j = 1; j < 16; j++) {
                current ^= list[i*16 + j];
            }
            dense[i] = current;
        }

        StringBuilder result = new StringBuilder();
        for (int x : dense) {
            result.append(String.format("%02x", x));
        }

        return result.toString();
    }

    public String partTwo(String filename) {
        return getKnotHash(InputParser.parseInputLine(filename));
    }
}
