package com.pppetkov.adventofcode2023.day4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pppetkov.adventofcode2023.utility.Utility;

public class Scratchcards {
    private static List<Integer> getNumbers(String[] card, int index){
        List<Integer> numbers = new ArrayList<>();
        int i = index;
        while(i < card.length && !card[i].equals("|")){
            numbers.add(Integer.parseInt(card[i++]));
        }

        return numbers;
    }

    public static int calculatePoints(String filename) throws IOException{
        List<String> input = Utility.readInput(filename);
        int totalPoints = 0;

        for (String string : input) {
            String[] card = string.split("\\s+");
            List<Integer> winningNumbers = getNumbers(card, 2);
            List<Integer> playerNumbers = getNumbers(card, 3 + winningNumbers.size());
            
            int count = 0;
            for (Integer winningNumber : winningNumbers) {
                if(playerNumbers.contains(winningNumber)){
                    ++count;
                }
            }

            if(count != 0){
                totalPoints += Math.pow(2, count - 1);
            }
        }

        return totalPoints;
    }

    public static int calculateWonScratchcards(String filename) throws IOException{
        List<String> input = Utility.readInput(filename);
        List<Integer> matchesPerCard = new ArrayList<>();
        Map<Integer, Integer> cardsOwned = new HashMap<>();

        for(String string : input){
            String[] card = string.split("\\s+");
            List<Integer> winningNumbers = getNumbers(card, 2);
            List<Integer> playerNumbers = getNumbers(card, 3 + winningNumbers.size());

            int count = 0;
            for(Integer winningNumber : winningNumbers){
                if(playerNumbers.contains(winningNumber)){
                    ++count;
                }
            }

            matchesPerCard.add(count);
        }

        for(int i = 0; i < input.size(); ++i){
            cardsOwned.put(i, 1);
        }

        for(int i = 0; i < input.size() - 1; ++i){
            Integer matches = matchesPerCard.get(i);
            for(int j = 1; j <= matches && i + j < input.size(); ++j){
                cardsOwned.put(i + j, cardsOwned.get(i + j) + cardsOwned.get(i));
            }
        }
        int sum = 0;
        for (Integer cards : cardsOwned.values()) {
            sum += cards;
        }

        return sum;
    }
}
