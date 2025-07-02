package week3.day21;

import util.InputParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class KeypadConundrum {
    private static final char[][] NUMERIC_KEYPAD = {
            {'7', '8', '9'},
            {'4', '5', '6'},
            {'1', '2', '3'},
            {' ', '0', 'A'}
    };

    private static final char[][] DIRECTIONAL_KEYPAD = {
            {' ', '^', 'A'},
            {'<', 'v', '>'}
    };

    public static long partOne(String filename) throws IOException {
        List<String> codes;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            codes = InputParser.parseListOfStrings(reader);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        return 0;
    }

    

    public static void main(String[] args) throws IOException {
        System.out.println(partOne("resources/input.txt"));
    }
}
