package com.pppetkov.adventofcode2023.day7;

import java.util.Comparator;

public class HandComplexComparator implements Comparator<Hand> {

    private Comparator<Hand> byType;
    private Comparator<Hand> byStrength;

    public HandComplexComparator(Comparator<Hand> byType, Comparator<Hand> byStrength){
        this.byStrength = byStrength;
        this.byType = byType;
    }

    @Override
    public int compare(Hand h1, Hand h2) {
        int comparisonByType = byType.compare(h1, h2);

        if(comparisonByType == 0){
            return byStrength.compare(h1, h2);
        }

        return comparisonByType;
    }
    
}
