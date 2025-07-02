package week2.day12;

import utils.InputParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSAbacus {
    public int partOne(String filename) {
        String input = InputParser.parseInputLine(filename);
        Pattern numberPattern = Pattern.compile("-?\\d+");
        Matcher matcher = numberPattern.matcher(input);
        int sum = 0;

        while(matcher.find()) {
            sum += Integer.parseInt(matcher.group());
        }

        return sum;
    }

    public int partTwo(String filename) {
        return 0;
    }
}
