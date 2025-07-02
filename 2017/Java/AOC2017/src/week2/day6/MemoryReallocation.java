package week2.day6;

import utils.InputParser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MemoryReallocation {
    private int[] getBanks(String input) {
        String[] numbers = input.split("\\s+");
        int[] banks = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            banks[i] = Integer.parseInt(numbers[i]);
        }
        return banks;
    }

    private int getMaxIndex(int[] arr) {
        int max = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                index = i;
                max = arr[i];
            }
        }

        return index;
    }

    public int partOne(String filename) {
        int[] banks = getBanks(InputParser.parseInputLine(filename));
        Set<String> configurations = new HashSet<>();
        configurations.add(Arrays.toString(banks));

        while (true) {
            int index = getMaxIndex(banks);
            int memory = banks[index];
            banks[index++] = 0;
            while (memory-- > 0) {
                banks[index++ % banks.length]++;
            }

            if (!configurations.add(Arrays.toString(banks))) {
                return configurations.size();
            }
        }
    }

    public int partTwo(String filename) {
        int[] banks = getBanks(InputParser.parseInputLine(filename));
        Map<String, Integer> configurations = new HashMap<>();
        configurations.put(Arrays.toString(banks), 0);
        int cycles = 0;
        while (true) {
            int index = getMaxIndex(banks);
            int memory = banks[index];
            banks[index++] = 0;
            while (memory-- > 0) {
                banks[index++ % banks.length]++;
            }

            String key = Arrays.toString(banks);
            if (configurations.containsKey(key)) {
                return configurations.size() - configurations.get(key) - 1;
            }
            configurations.put(key, cycles);
            cycles++;
        }
    }
}
