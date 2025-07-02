package util;

import java.io.BufferedReader;
import java.util.List;

public class InputParser {
    public static List<String> parseListOfStrings(BufferedReader reader) {
        return reader.lines()
                .toList();
    }
}
