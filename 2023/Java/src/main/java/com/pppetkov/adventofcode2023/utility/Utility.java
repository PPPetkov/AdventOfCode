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

    public static long findLCM(List<Integer> numbers){
        long lcm = 1;
        int divisor = 2;

        
        while(true){
            int ones = 0;
            boolean isDivisible = false;
            for(int i = 0; i < numbers.size(); ++i){
                Integer n = numbers.get(i);
                if(n == 1){
                    ++ones;
                } else{
                    if(n % divisor == 0){
                        isDivisible = true;
                        n /= divisor;
                    }
    
                    numbers.set(i, n);
                }
            }

            if(isDivisible){
                lcm *= divisor;
            }

            ++divisor;

            if(ones == numbers.size()){
                return lcm;
            }
        }
    }
}
