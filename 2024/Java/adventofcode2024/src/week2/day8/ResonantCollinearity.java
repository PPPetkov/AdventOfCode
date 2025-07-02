package week2.day8;

import util.Location;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResonantCollinearity {
    public static int partOne(String filename) throws IOException {
        Map<Character, List<Location>> antennaLocations = new HashMap<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        int rows = lines.size();
        int cols = lines.getFirst().length();

        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);
                if (c != '.') {
                    antennaLocations.computeIfAbsent(c, _ -> new ArrayList<>())
                            .add(new Location(row, col));
                }
            }
        }

        // Calculate results
        Set<Location> result = new HashSet<>();

        antennaLocations.values().forEach(locations -> {
            int size = locations.size();
            for (int i = 0; i < size - 1; i++) {
                Location first = locations.get(i);
                for (int j = i + 1; j < size; j++) {
                    Location second = locations.get(j);
                    addGeneratedPoints(first, second, rows, cols, result);
                }
            }
        });

        return result.size();
    }

    private static void addGeneratedPoints(Location first, Location second, int rows, int cols, Set<Location> result) {
        double d = distance(first, second);
        int vX = second.x() - first.x();
        int vY = second.y() - first.y();
        int t = (int) (d / Math.sqrt(vX * vX + vY * vY));

        List<Location> generatedPoints = List.of(
                new Location(first.x() - t * vX, first.y() - t * vY),
                new Location(first.x() + t * vX, first.y() + t * vY),
                new Location(second.x() - t * vX, second.y() - t * vY),
                new Location(second.x() + t * vX, second.y() + t * vY)
        );

        generatedPoints.stream()
                .filter(l -> isValid(l, rows, cols) && !l.equals(first) && !l.equals(second))
                .forEach(result::add);
    }

    private static boolean isValid(Location loc, int rows, int cols) {
        return loc.x() >= 0 && loc.x() < rows && loc.y() >= 0 && loc.y() < cols;
    }

    private static double distance(Location first, Location second) {
        int dx = second.x() - first.x();
        int dy = second.y() - first.y();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static int partTwo(String filename) throws IOException {
        Map<Character, List<Location>> antennaLocations = new HashMap<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        int rows = lines.size();
        int cols = lines.getFirst().length();

        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);
                if (c != '.') {
                    antennaLocations.computeIfAbsent(c, _ -> new ArrayList<>())
                            .add(new Location(row, col));
                }
            }
        }

        Set<Location> result = new HashSet<>();
        for (List<Location> locations : antennaLocations.values()) {
            int size = locations.size();
            for (int i = 0; i < size; i++) {
                Location fst = locations.get(i);
                for (int j = i + 1; j < size; j++) {
                    Location snd = locations.get(j);
                    int A = snd.y() - fst.y();
                    int B = fst.x() - snd.x();
                    int C = fst.y() * -B - fst.x() * A;

                    for (int x = 0; x < rows; x++) {
                        for (int y = 0; y < cols; y++) {
                            if (isOnLine(x, y, A, B, C)) {
                                result.add(new Location(x, y));
                            }
                        }
                    }
                }
            }
        }
        return result.size();
    }

    private static boolean isOnLine(int x, int y, int a, int b, int c) {
        return x*a + y*b + c == 0;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(partTwo("resources/input.txt"));
    }
}
