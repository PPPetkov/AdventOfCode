package com.pppetkov.adventofcode2023.day9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pppetkov.adventofcode2023.utility.Utility;

public class MirageMaintenance {
    private static List<List<Integer>> generateTableOfDifferences(List<Integer> numbers){
        List<List<Integer>> differencesTable = new ArrayList<>();
        differencesTable.add(numbers);
        boolean allAreEqual = false;
        int prevIndex = 0;

        while(!allAreEqual){
            List<Integer> differences = new ArrayList<>();
            List<Integer> prevRow = differencesTable.get(prevIndex++);
            allAreEqual = true;

            for(int i = 0; i < prevRow.size() - 1; ++i){
                Integer difference = prevRow.get(i + 1) - prevRow.get(i);

                if(i != 0 && difference != differences.get(i - 1)){
                    allAreEqual = false;
                }

                differences.add(difference);
            }

            differencesTable.add(differences);
        }

        return differencesTable;
    }

    private static int extrapolateValueForwards(List<Integer> numbers){
        List<List<Integer>> table = generateTableOfDifferences(numbers);
        int extrapolatedValue = 0;

        for(int i = table.size() - 1; i >= 0; --i){
            extrapolatedValue += table.get(i).getLast();
        }

        return extrapolatedValue;
    }

    private static int extrapolateValueBackwards(List<Integer> numbers){
        List<List<Integer>> table = generateTableOfDifferences(numbers);
        int extrapolatedValue = 0;

        for(int i =  table.size() - 1; i >= 0; --i){
            extrapolatedValue = table.get(i).getFirst() - extrapolatedValue;
        }

        return extrapolatedValue;
    }

    public static int sumExtrapolatedValues(String filename) throws IOException{
        List<String> input = Utility.readInput(filename);
        int sum = 0;

        for(String line : input){
            String[] numbersAsStrings = line.split("\\s+");
            List<Integer> numbers = new ArrayList<>();

            for(String number : numbersAsStrings){
                numbers.add(Integer.parseInt(number));
            }

            //part1
            //sum += extrapolateValueForwards(numbers);
            
            //part2
            sum += extrapolateValueBackwards(numbers);
        }

        return sum;
    }
}
