package com.pppetkov.adventofcode2023.day6;

import java.io.IOException;
import java.util.List;

import com.pppetkov.adventofcode2023.utility.Utility;

public class BoatRace {
    private static long calculateWaysToBeatRecord(long time, long record){
        long d = time*time - 4 * record;
        long low = (long)Math.floor((time - Math.sqrt(d))/2 + 1);
        long high = (long)Math.ceil((time + Math.sqrt(d))/2);

        return Math.abs(high - low);
    }

    public static long getSolutionPart1(String filename) throws IOException{
        List<String> input = Utility.readInput(filename);

        String[] times = input.get(0).split("\\s+");
        String[] records = input.get(1).split("\\s+");

        long result = 1;

        for(int i = 1; i < times.length; ++i){
            long time = Integer.parseInt(times[i]);
            long record = Integer.parseInt(records[i]);
            long x = calculateWaysToBeatRecord(time, record);
            result *= x;
        }

        return result;
    }

    public static long getSolutionPart2(String filename) throws IOException{
        List<String> input = Utility.readInput(filename);

        String[] times = input.get(0).split("\\s+");
        String[] records = input.get(1).split("\\s+");

        String time = "";
        String record = "";

        for(int i = 1; i < times.length; ++i){
            time += times[i];
            record += records[i];
        }
        
        return calculateWaysToBeatRecord(Long.parseLong(time), Long.parseLong(record));
    }
}
