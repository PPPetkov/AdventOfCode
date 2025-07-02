package week1.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HistorianHysteria {
    public static long partTwo(String filename) {
        long similarityScore = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            List<Long> firstList = new ArrayList<>();
            Map<Long, Long> occurencesInSecondList = new HashMap<>();

            while (reader.ready()) {
                String line = reader.readLine();
                String[] numbers = line.split("\\s+");

                firstList.add(Long.valueOf(numbers[0]));

                Long secondNumber = Long.valueOf(numbers[1]);
                occurencesInSecondList.put(secondNumber, occurencesInSecondList.getOrDefault(secondNumber, 0L) + 1);
            }

            for (long number : firstList) {
                similarityScore += number * occurencesInSecondList.getOrDefault(number, 0L);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return similarityScore;
    }

    public static long partOne(String filename) {
        long sumOfDistances = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            PriorityQueue<Long> firstList = new PriorityQueue<>();
            PriorityQueue<Long> secondList = new PriorityQueue<>();

            while (reader.ready()) {
                String line = reader.readLine();
                String[] numbers = line.split("\\s+");

                firstList.add(Long.valueOf(numbers[0]));
                secondList.add(Long.valueOf(numbers[1]));
            }

            while (!firstList.isEmpty() && !secondList.isEmpty()) {
                sumOfDistances += Math.abs(firstList.poll() - secondList.poll());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sumOfDistances;
    }

    public static void main(String[] args) {
        System.out.println(partTwo("resources/input.txt"));
    }
}
