package com.pppetkov.adventofcode2023.day7;

import java.util.Comparator;

public class HandByTypeComparator implements Comparator<Hand> {

    @Override
    public int compare(Hand h1, Hand h2) {
        return h1.getHandType().compareTo(h2.getHandType());
    }
}
