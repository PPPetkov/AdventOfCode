package com.pppetkov.adventofcode2023.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static List<String> readInput(String filename) throws IOException{
        List<String> input = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while((line = reader.readLine()) != null){
            input.add(line);
        }

        reader.close();
        return input;
    }
}
