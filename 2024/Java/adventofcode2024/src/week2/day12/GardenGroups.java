package week2.day12;

import util.Location;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GardenGroups {
    private static final int[][] DIRECTIONS = new int[][]{{0, -1}, {0, +1}, {-1, 0}, {+1, 0}};

    public static long partOne(String filename) throws FileNotFoundException {
        List<String> garden = new BufferedReader(new FileReader(filename)).lines().toList();
        Map<Location, List<Location>> regions = findAllRegions(garden);

        long price = 0;
        for (List<Location> region : regions.values()) {
            price += region.size() * findRegionPerimeter(region, garden);
        }

        return price;
    }

    private static long findRegionPerimeter(List<Location> region, List<String> garden) {
        long perimeter = 0;

        for (Location location : region) {
            char c = garden.get(location.x()).charAt(location.y());
            for (int[] direction : DIRECTIONS) {
                int neighbourX = location.x() + direction[0];
                int neighbourY = location.y() + direction[1];

                if (neighbourX < 0 || neighbourX >= garden.size() || neighbourY < 0 || neighbourY >= garden.getFirst().length() || garden.get(neighbourX).charAt(neighbourY) != c) {
                    perimeter++;
                }
            }
        }

        return perimeter;
    }

    public static long partTwo(String filename) throws FileNotFoundException {
        List<String> garden = new BufferedReader(new FileReader(filename)).lines().toList();
        Map<Location, List<Location>> regions = findAllRegions(garden);

        long price = 0;
        for (List<Location> region : regions.values()) {
            long sides = countRegionSides(region, garden);
            System.out.println(region.size() + " " + sides);
            price += region.size() * sides;
        }

        return price;
    }

    private static long countRegionSides(List<Location> region, List<String> garden) {
        int sides = 0;
        char plant = garden.get(region.getFirst().x()).charAt(region.getFirst().y());
        System.out.println(plant + " " + region.getFirst().x() + " " + region.getFirst().y());
        for (Location location : region) {
            boolean[][] neighbours = new boolean[3][3];
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    neighbours[i+1][j+1] = isInRegion(garden, plant, location.x() + i, location.y() + j);
                }
            }

            sides += countCellSides(neighbours);
        }

        return sides;
    }

    private static int countCellSides(boolean[][] neighbours) {
        int cornerCount = 0;

        if (!neighbours[0][0] && neighbours[0][1] == neighbours[1][0]) cornerCount++;
        if (neighbours[0][0] && !neighbours[0][1] && !neighbours[1][0]) cornerCount++;

        if (!neighbours[0][2] && neighbours[0][1] == neighbours[1][2]) cornerCount++;
        if (neighbours[0][2] && !neighbours[0][1] && !neighbours[1][2]) cornerCount++;

        if (!neighbours[2][0] && neighbours[2][1] == neighbours[1][0]) cornerCount++;
        if (neighbours[2][0] && !neighbours[2][1] && !neighbours[1][0]) cornerCount++;

        if (!neighbours[2][2] && neighbours[2][1] == neighbours[1][2]) cornerCount++;
        if (neighbours[2][2] && !neighbours[2][1] && !neighbours[1][2]) cornerCount++;

        return cornerCount;
    }

    private static boolean isInRegion(List<String> garden, char plant, int x, int y) {
        return x >= 0 && x < garden.size()
                && y >= 0 && y < garden.getFirst().length()
                && plant == garden.get(x).charAt(y);
    }

    private static Map<Location, List<Location>> findAllRegions(List<String> garden) {
        boolean[][] visited = new boolean[garden.size()][garden.getFirst().length()];
        Map<Location, List<Location>> regions = new HashMap<>();

        for (int i = 0; i < garden.size(); i++) {
            for (int j = 0; j < garden.getFirst().length(); j++) {
                if (!visited[i][j]) {
                    List<Location> region = new ArrayList<>();
                    findRegion(i, j, region, visited, garden.get(i).charAt(j), garden);
                    regions.put(new Location(i, j), region);
                }
            }
        }

        return regions;
    }

    private static void findRegion(int x, int y, List<Location> region, boolean[][] visited, char c, List<String> garden) {
        if (x < 0 || x >= garden.size() || y < 0 || y >= garden.getFirst().length() || visited[x][y] || garden.get(x).charAt(y) != c) {
            return;
        }

        visited[x][y] = true;
        region.add(new Location(x, y));
        for (int[] direction : DIRECTIONS) {
            findRegion(x + direction[0], y + direction[1], region, visited, c, garden);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(partOne("resources/input.txt"));
        System.out.println(partTwo("resources/input.txt"));
    }
}
