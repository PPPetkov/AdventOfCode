package com.pppetkov.adventofcode2023.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Hand {
    public static final int NUMBER_OF_CARDS_IN_HAND = 5;
    private final String cards;

    public Hand(String cards){
        if(cards.length() > NUMBER_OF_CARDS_IN_HAND){
            throw new IllegalArgumentException("Hand must contain " + NUMBER_OF_CARDS_IN_HAND + " cards");
        }
        this.cards = cards;
    }

    public String getCards(){
        return cards; 
    }

    // for part 2 J stands for Joker and can replace any of the other cards;
    public HandType getHandType(){
        Map<Character, Integer> strengthToNumberOfOccurences = new HashMap<>();
        List<Integer> occurences = new ArrayList<>();

        for(int i = 0; i < cards.length(); ++i){
            strengthToNumberOfOccurences.putIfAbsent(cards.charAt(i), 0);
            strengthToNumberOfOccurences.put(cards.charAt(i), strengthToNumberOfOccurences.get(cards.charAt(i)) + 1);
        }

        for (Integer numberOfOccurrences : strengthToNumberOfOccurences.values()) {
            occurences.add(numberOfOccurrences);
        }

        int numberOfDifferentCards = occurences.size();
        //part 2
        Integer numberOfJokers = strengthToNumberOfOccurences.get('J');
        //

        if(numberOfDifferentCards == 1){
            // no changes for part 2
            return HandType.FIVE_OF_A_KIND;
        }


        if(numberOfDifferentCards == 2){
            //part2
            if(numberOfJokers != null){
                //XXXJJ becomes XXXXX in part2
                return HandType.FIVE_OF_A_KIND; 
            }
            //

            if(occurences.contains(4)){
                return HandType.FOUR_OF_A_KIND;
            }

            return HandType.FULL_HOUSE;
        }

        if(numberOfDifferentCards == 3){
            if(occurences.contains(3)){
                //part2
                if(numberOfJokers != null){
                    //XXXYJ becomes XXXYX
                    return HandType.FOUR_OF_A_KIND;
                }
                //

                return HandType.THREE_OF_A_KIND;
            }

            if(numberOfJokers != null){
                //XXJJY becomes XXXXY
                if(numberOfJokers == 2){
                    return HandType.FOUR_OF_A_KIND;
                }

                //XXJYY becomes XXXYY
                return HandType.FULL_HOUSE;
            }
            return HandType.TWO_PAIR;
        }

        if(numberOfDifferentCards == 4){
            //part2
            if(numberOfJokers != null){
                //XXYZJ becomes XXYZX
                return HandType.THREE_OF_A_KIND;
            }
            //

            return HandType.ONE_PAIR;
        }

        if(numberOfJokers != null){
            return HandType.ONE_PAIR;
        }
        
        return HandType.HIGH_CARD;
    }

    public int getCardStrength(int cardNumber){
        switch (cards.charAt(cardNumber)) {
            //for part 1
            //case 'J': return 12;
            //for part 2 J stands for Joker and is the weakest card;
            case 'J': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case 'T': return 10;
            case 'Q': return 12;
            case 'K': return 13;
            case 'A': return 14;
            default:
                return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(obj == null || (getClass() != obj.getClass())){
            return false;
        }

        Hand other = (Hand) obj;
        return cards.equals(other.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
