package com.pppetkov.adventofcode2023.day7;

import java.util.Comparator;

public class HandByCardStrengthComparator implements Comparator<Hand> {

    @Override
    public int compare(Hand h1, Hand h2) {
        for(int i = 0; i < Hand.NUMBER_OF_CARDS_IN_HAND; ++i){
            int comparison = Integer.compare(h1.getCardStrength(i), h2.getCardStrength(i));

            if(comparison != 0){
                return comparison;
            }
        }

        return 0;
    }
    
}
