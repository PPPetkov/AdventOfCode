package com.pppetkov.adventofcode2023.day7;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.pppetkov.adventofcode2023.utility.Utility;

public class CamelCards {
    private static Map<Hand, Integer> generateTreeMap(List<String> input){
        Map<Hand, Integer> result = new TreeMap<>(new HandComplexComparator(new HandByTypeComparator(), new HandByCardStrengthComparator()));

        for (String string : input) {
            String[] handAndBid = string.split("\\s+");
            result.put(new Hand(handAndBid[0]), Integer.valueOf(handAndBid[1]));
        }

        return result;
    }

    public static int calculateTotalWinnings(String filename) throws IOException{
        List<String> input = Utility.readInput(filename);
        Integer total = 0;
        Map<Hand, Integer> handToBidAmount = generateTreeMap(input);
        Integer rank = 1;

        for (Integer bid : handToBidAmount.values()) {
            System.out.println(bid);
            total += bid*rank;
            ++rank; 
        }

        return total;
    }
}
