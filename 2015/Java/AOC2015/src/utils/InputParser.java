package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class InputParser {
    public static String parseInputLine(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.readLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File could not be found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error when reading input", e);
        }
    }

    public static List<String> parseInput(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            return reader.lines().toList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File could not be found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error when reading input", e);
        }
    }
}
