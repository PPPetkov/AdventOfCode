package com.pppetkov.adventofcode2023.day5;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.pppetkov.adventofcode2023.utility.Utility;

public class SeedAlmanac {
    private static List<List<List<BigInteger>>> generateMaps(List<String> input) {
        List<List<List<BigInteger>>> maps = new ArrayList<>();
        List<List<BigInteger>> map = new ArrayList<>();

        for (int i = 3; i < input.size(); ++i) {
            String line = input.get(i);
            if (line.isBlank()) {
                ++i;
                maps.add(map);
                map = new ArrayList<>();
            } else {
                List<BigInteger> integers = new ArrayList<>();
                String[] numbers = line.split("\\s+");

                for (String number : numbers) {
                    integers.add(new BigInteger(number));
                }
                map.add(integers);
            }

        }
        maps.add(map);
        return maps;
    }

    public static BigInteger findLowestLocation(String filename) throws IOException {
        List<String> input = Utility.readInput(filename);

        String[] seedsAsStrings = input.get(0).split("\\s+");
        List<BigInteger> seeds = new ArrayList<>();

        for (int i = 1; i < seedsAsStrings.length; ++i) {
            seeds.add(new BigInteger(seedsAsStrings[i]));
        }

        List<List<List<BigInteger>>> maps = generateMaps(input);

        BigInteger location = BigInteger.ZERO;
        while(true){

            BigInteger current = location;
            for (int currentMap = maps.size() - 1; currentMap >= 0; --currentMap) {
                List<List<BigInteger>> map = maps.get(currentMap);
                for (int line = 0; line < map.size(); ++line) {
                    List<BigInteger> numbers = map.get(line);
                    BigInteger source = numbers.get(1);
                    BigInteger destination = numbers.get(0);
                    BigInteger range = numbers.get(2);

                    if (current.compareTo(destination) >= 0 && current.compareTo(destination.add(range)) < 0) {
                        current = source.add(current.subtract(destination));
                        break;
                    }
                }
            }
            
            if(seeds.contains(current)){
                return location;
            }

            location = location.add(BigInteger.ONE);
        }
    }

    public static BigInteger findLowestLocationPart2(String filename) throws IOException {
        List<String> input = Utility.readInput(filename);
        BigInteger thousand = BigInteger.valueOf(1000);
        String[] seedsAsStrings = input.get(0).split("\\s+");
        List<BigInteger> seeds = new ArrayList<>();

        for (int i = 1; i < seedsAsStrings.length; i += 2) {
            seeds.add(new BigInteger(seedsAsStrings[i]));
            seeds.add(seeds.get(seeds.size() - 1).add(new BigInteger(seedsAsStrings[i+1])));
        }

        List<List<List<BigInteger>>> maps = generateMaps(input);

        BigInteger location = BigInteger.ZERO;
        while(true){

            BigInteger current = location;
            for (int currentMap = maps.size() - 1; currentMap >= 0; --currentMap) {
                List<List<BigInteger>> map = maps.get(currentMap);
                for (int line = 0; line < map.size(); ++line) {
                    List<BigInteger> numbers = map.get(line);
                    BigInteger src = numbers.get(1);
                    BigInteger dst = numbers.get(0);
                    BigInteger range = numbers.get(2);

                    if (current.compareTo(src) >= 0 && current.compareTo(dst.add(range)) < 0) {
                        current = src.add(current.subtract(dst));
                        break;
                    }
                }
            }

            for(int i = 0; i < seeds.size(); i += 2){
                BigInteger low = seeds.get(i);
                BigInteger high = seeds.get(i + 1);

                if(current.compareTo(low) >= 0 && current.compareTo(high) < 0){
                    return location;
                }
            }

            if(location.remainder(thousand).equals(BigInteger.ZERO)){
                System.out.println(location);
            }

            location = location.add(BigInteger.ONE);
        }
    }
}
