package com.pppetkov.adventofcode2023.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CubeConundrum {

    //part 1
    private static final int MAX_RED_CUBES = 12;
    private static final int MAX_GREEN_CUBES = 13;
    private static final int MAX_BLUE_CUBES = 14;

    private static boolean isGamePossible(String game){
        String[] sets = game.split(" ");
        
        int cubesNum;

        //The first 2 words in sets are always "Game X:", so the loop starts at index 2
        for(int i = 2; i < sets.length - 1; i += 2){
            cubesNum = Integer.parseInt(sets[i]);

            if(sets[i + 1].contains("red")){
                if(cubesNum > MAX_RED_CUBES){
                    return false;
                }
            } else if(sets[i + 1].contains("green")){
                if(cubesNum > MAX_GREEN_CUBES){
                    return false;
                }
            } else{
                if(cubesNum > MAX_BLUE_CUBES){
                    return false;
                }
            }
        }

        return true;
    }

    public static int SolveConundrum(String filename) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        int gameNumber = 1;
        int result = 0;

        while((line = reader.readLine()) != null){
            if(isGamePossible(line)){
                result += gameNumber;
            }
            ++gameNumber;
        }

        reader.close();

        return result;
    }
    //
    
    //part 2
    private static int calculatePowerOfCubes(String game){
        String[] sets = game.split(" ");
        int drawnCubes;

        int maxRed = 0;
        int maxGreen = 0;
        int maxBlue = 0;
        for(int i = 2; i < sets.length; i += 2){
            drawnCubes = Integer.parseInt(sets[i]);

            if(sets[i + 1].contains("red")){
                maxRed = Math.max(drawnCubes, maxRed);
            } else if(sets[i + 1].contains("green")){
                maxGreen = Math.max(drawnCubes, maxGreen);
            } else{
                maxBlue = Math.max(drawnCubes, maxBlue);
            }
        }

        return maxRed * maxGreen * maxBlue;
    }

    public static int SolveConundrum2(String filename) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        int result = 0;

        while((line = reader.readLine()) != null){
            result += calculatePowerOfCubes(line);
        }

        reader.close();

        return result;
    }
}
