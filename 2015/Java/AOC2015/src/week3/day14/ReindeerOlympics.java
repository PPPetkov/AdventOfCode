package week3.day14;

import utils.InputParser;

import java.util.ArrayList;
import java.util.List;

public class ReindeerOlympics {
    private static final int TOTAL_SECONDS = 2503;

    private List<ReindeerInfo> getReindeerStats(List<String> data) {
        List<ReindeerInfo> result = new ArrayList<>();

        for (String reindeerInfo : data) {
            String[] tokens = reindeerInfo.split("\\s+");

            result.add(new ReindeerInfo(
                    tokens[0],
                    Integer.parseInt(tokens[3]),
                    Integer.parseInt(tokens[6]),
                    Integer.parseInt(tokens[13])));
        }

        return result;
    }

    public int partOne(String filename) {
        List<ReindeerInfo> info = getReindeerStats(InputParser.parseInput(filename));
        int winnerDistance = Integer.MIN_VALUE;

        for (ReindeerInfo reindeer : info) {
            int timesRan = TOTAL_SECONDS / (reindeer.restTime() + reindeer.runtime());
            int timeLeft = TOTAL_SECONDS % (reindeer.restTime() + reindeer.runtime());

            int currentDistance = (timesRan * reindeer.runtime() + Math.min(timeLeft, reindeer.runtime())) * reindeer.speed();

            winnerDistance = Math.max(winnerDistance, currentDistance);
        }

        return winnerDistance;
    }

    private void initTimes(List<ReindeerInfo> infos, int[] times) {
        for (int i = 0; i < infos.size(); i++) {
            times[i] = infos.get(i).runtime();
        }
    }

    private int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int x : arr) {
            if (x > max) {
                max = x;
            }
        }

        return max;
    }

    private void addPoints(int[] points, int[] distances) {
        int maxDistance = findMax(distances);
        for (int i = 0; i < points.length; i++) {
            if (distances[i] == maxDistance) {
                points[i]++;
            }
        }
    }

    public int partTwo(String filename) {
        List<ReindeerInfo> reindeerInfos = getReindeerStats(InputParser.parseInput(filename));
        int size = reindeerInfos.size();
        boolean[] isResting = new boolean[size];
        int[] distances = new int[size];
        int[] times = new int[size];
        int[] points = new int[size];

        initTimes(reindeerInfos, times);
        for (int i = 1; i < TOTAL_SECONDS; i++) {
            for (int j = 0; j < size; j++) {
                if (times[j] == 0) {
                    times[j] = isResting[j] ? reindeerInfos.get(j).runtime() : reindeerInfos.get(j).restTime();
                    isResting[j] = !isResting[j];
                }

                if (!isResting[j]) {
                    distances[j] += reindeerInfos.get(j).speed();
                }
                times[j]--;
            }

            addPoints(points, distances);
        }

        return findMax(points);
    }
}
