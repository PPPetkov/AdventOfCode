package week1.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver {
    public static long partOne(String filename) throws IOException {
        long sum = 0;
        boolean enabled = true;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        Pattern pattern = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((0|[1-9][0-9]{0,2}),(0|[1-9][0-9]{0,2})\\)");
        while (reader.ready()) {
            String line = reader.readLine();
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                System.out.println(matcher.group());
                if(matcher.group().equals("don't()")) {
                    enabled = false;
                } else if (matcher.group().equals("do()")) {
                    enabled = true;
                } else if (matcher.group().contains("mul")){
                    if (enabled) {
                        sum += Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
                    }
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partOne("resources/input.txt"));
    }
}
