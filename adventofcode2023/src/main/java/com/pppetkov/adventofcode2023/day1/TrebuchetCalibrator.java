package com.pppetkov.adventofcode2023.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrebuchetCalibrator{
    //Part 1
    public static int findNumber(String str){
        int n = 0;

        for(int i = 0; i < str.length(); ++i){
            char c = str.charAt(i);
            if(Character.isDigit(c)){
                n += Character.getNumericValue(c);
                break;
            }
        }

        for(int i = str.length() - 1; i >= 0; --i){
            char c = str.charAt(i);
            if(Character.isDigit(c)){
                return n*10 + Character.getNumericValue(c);
            }
        }

        return n;
    }
    //

    //Part 2
    public static String replaceWordsWithDigits(String str){
        str = str.replace("one", "o1e");
        str = str.replace("two", "t2o");
        str = str.replace("three", "t3e");
        str = str.replace("four", "4");
        str = str.replace("five", "5e");
        str = str.replace("six", "6");
        str = str.replace("seven", "7");
        str = str.replace("eight", "e8t");
        str = str.replace("nine", "n9n");

        return str;
    }
    //
    
    public static int calibrate(String filename) throws IOException{
        int result = 0;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while((line = reader.readLine()) != null){
            result += findNumber(replaceWordsWithDigits(line));
        }

        reader.close();
        
        return result;
    }

}